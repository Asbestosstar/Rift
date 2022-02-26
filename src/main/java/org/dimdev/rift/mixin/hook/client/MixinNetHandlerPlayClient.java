package org.dimdev.rift.mixin.hook.client;

import org.dimdev.rift.listener.CustomPayloadHandler;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.util.NBTQueryManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.NetworkTagManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

@Mixin(ClientPlayNetHandler.class)
public class MixinNetHandlerPlayClient {
    @Shadow private ClientWorld world;

    @Shadow @Final private GameProfile profile;
    @Shadow private Minecraft client;
    @Shadow @Final private RecipeManager recipeManager;
    @Shadow @Final private ClientAdvancementManager advancementManager;
    @Shadow @Final private NetworkManager netManager;
    @Shadow private CommandDispatcher<ISuggestionProvider> commandDispatcher;
    @Shadow @Final private ClientSuggestionProvider clientSuggestionProvider;
    @Shadow private NetworkTagManager networkTagManager;
    @Shadow private NBTQueryManager nbtQueryManager;

    @SuppressWarnings("deprecation")
    @Inject(method = "handleCustomPayload", at = @At("HEAD"), cancellable = true)
    private void handleModCustomPayload(SCustomPayloadPlayPacket packet, CallbackInfo ci) {
        ResourceLocation channelName = packet.getChannelName();
        PacketBuffer data = packet.getBufferData();

        for (CustomPayloadHandler customPayloadHandler : RiftLoader.instance.getListeners(CustomPayloadHandler.class)) {
            if (customPayloadHandler.clientHandlesChannel(channelName)) {
                customPayloadHandler.clientHandleCustomPayload(channelName, data);
            }
        }
/*
        Class<? extends Message> messageClass = Message.REGISTRY.get(channelName);
        if (messageClass != null) {
            try {
                Message message = RiftLoader.instance.newInstance(messageClass);
                message.read(data);
                message.process(new ClientMessageContext(
                        client,
                        profile,
                        netManager,
                        recipeManager,
                        advancementManager,
                        commandDispatcher,
                        clientSuggestionProvider,
                        networkTagManager,
                        nbtQueryManager
                ));
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Error creating " + messageClass, e);
            }
            ci.cancel();
        }*/
    }

    @Inject(method = "handleUpdateTileEntity", at = @At("RETURN"))
    private void handleUpdateModTileEntity(SUpdateTileEntityPacket packet, CallbackInfo ci) {
        TileEntity tileEntity = world.getTileEntity(packet.getPos());
       // if (tileEntity == null || packet.getNbtCompound() == null || !packet.getNbtCompound().("id", 8)) {
        //    return;
       // }

        ResourceLocation tileEntityId = TileEntityType.getId(tileEntity.getType());
        ResourceLocation packetId = new ResourceLocation(packet.getNbtCompound().getString("id"));
        if (packetId != null && !packetId.getNamespace().equals("minecraft") && packetId.equals(tileEntityId)) {
//            tileEntity.readFromNBT(packet.getNbtCompound());
        }
    }
}
