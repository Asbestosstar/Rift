package org.dimdev.rift.mixin.hook.client;

import org.dimdev.rift.listener.client.GameGuiAdder;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.IInventory;

@Mixin(ClientPlayerEntity.class)
public class MixinEntityPlayerSP {
 /*   @Inject(method = "displayGui", at = @At("HEAD"), cancellable = true)
    public void onDisplayGui(IInteractionObject interactionObject, CallbackInfo ci) {
        String id = interactionObject.getGuiID();
        if (!id.startsWith("minecraft:")) {
            for (GameGuiAdder gameGuiAdder : RiftLoader.instance.getListeners(GameGuiAdder.class)) {
                gameGuiAdder.displayGui((ClientPlayerEntity) (Object) this, id, interactionObject);
            }
            ci.cancel();
        }
    }

    @Inject(method = "displayGUIChest", at = @At("HEAD"), cancellable = true)
    public void onDisplayContainerGui(IInventory inventory, CallbackInfo ci) {
        String id = inventory instanceof IInteractionObject ? ((IInteractionObject) inventory).getGuiID() : "minecraft:container";
        if (!id.startsWith("minecraft:")) {
            for (GameGuiAdder gameGuiAdder : RiftLoader.instance.getListeners(GameGuiAdder.class)) {
                gameGuiAdder.displayContainerGui((ClientPlayerEntity) (Object) this, id, inventory);
            }
            ci.cancel();
        }
    }*/
}
