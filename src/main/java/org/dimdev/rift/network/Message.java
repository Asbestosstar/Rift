package org.dimdev.rift.network;

import java.util.function.Predicate;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.network.INetHandler;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.play.client.CCustomPayloadPacket;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

public abstract class Message {
	//   public static final MutableRegistry<MutableRegistry<?>, Class<? extends Message>> REGISTRY = new SimpleRegistry<>();

//    public static final MutableRegistry<ResourceLocation, Class<? extends Message>> REGISTRY = new MutableRegistry<>();

    public abstract void write(PacketBuffer buffer);

    public abstract void read(PacketBuffer buffer);

    public void process(ClientMessageContext context) {
        throw new UnsupportedOperationException("Packet " + getClass() + " can't be processed on client.");
    }

    public void process(ServerMessageContext context) {
        throw new UnsupportedOperationException("Packet " + getClass() + " can't be processed on server.");
    }

//    public final IPacket<? extends INetHandler> toPacket(PacketDirection direction) {
//        ResourceLocation id = Message.REGISTRY.getKey(getClass());
   //     if (id == null) {
 //           throw new IllegalArgumentException("Message was not registered: " + this);
 //       }

  /*      PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        write(buffer);
        switch (direction) {
            case CLIENTBOUND:
                return new SCustomPayloadPlayPacket(id, buffer);
            case SERVERBOUND:
                return new CCustomPayloadPacket(id, buffer);
            default:
                throw new AssertionError("unreachable");
        }
    }

    public final void send(PlayerEntity player) {
        if (player instanceof PlayerModelPart) {
            ((PlayerModelPart) player).connection.getNetworkManager().sendPacket(toPacket(PacketDirection.CLIENTBOUND));
        } else if (player instanceof ClientPlayerEntity) {
            ((ClientPlayerEntity) player).connection.getNetworkManager().sendPacket(toPacket(PacketDirection.SERVERBOUND));
        } else {
            throw new IllegalArgumentException("Only supported for PlayerModelPart and ClientPlayerEntity, but got " + player.getClass());
        }
    }

    public final void sendToAll(MinecraftServer server) {
        for (PlayerModelPart player : server.getPlayerList().getPlayers()) {
            send(player);
        }
    }

    public final void sendToAll(MinecraftServer server, Predicate<PlayerModelPart> filter) {
        for (PlayerModelPart player : server.getPlayerList().getPlayers()) {
            if (filter.test(player)) {
                send(player);
            }
        }
    }

    public final void sendToAll(WorldServer world, Predicate<PlayerModelPart> filter) {
        for (PlayerModelPart player : world.getPlayers(PlayerModelPart.class, filter)) {
            send(player);
        }
    }

    public final void sendToAll(WorldServer world) {
        for (PlayerModelPart player : world.getPlayers(PlayerModelPart.class, player -> true)) {
            send(player);
        }
    }

    public void sendToServer() {
        Minecraft.getInstance().player.connection.sendPacket(toPacket(PacketDirection.SERVERBOUND));
    }
*/
}

