package org.dimdev.rift.mixin.hook.client;

import org.dimdev.rift.listener.client.OverlayRenderer;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.IngameGui;

@Mixin(IngameGui.class)
public class MixinGuiIngame {
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void onRenderGameOverlay(CallbackInfo ci) {
        for (OverlayRenderer overlayRenderer : RiftLoader.instance.getListeners(OverlayRenderer.class)) {
            overlayRenderer.renderOverlay();
        }
    }
}
