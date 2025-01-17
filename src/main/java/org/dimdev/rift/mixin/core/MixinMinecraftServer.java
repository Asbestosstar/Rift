package org.dimdev.rift.mixin.core;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Shadow
    public World[] worlds;

    @Overwrite
    public String getServerModName() {
        return "rift";
    }

    @Inject(
            method = "getCommandSource",
            at = @At(value = "NEW"),
            cancellable = true
    )
    private void beforeNew(CallbackInfoReturnable<CommandSource> ci) {
        if(worlds == null) { // World has not been loaded yet, this might happen when the datapack is loaded for the first time
            ci.setReturnValue(new CommandSource((MinecraftServer) (Object) this, Vec3d.ZERO, Vec2f.ZERO, null, 4, "Server",
                    new StringTextComponent("Server"), (MinecraftServer) (Object) this, null));
            ci.cancel();
        }
    }
}
