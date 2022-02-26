package org.dimdev.rift.mixin.hook;

import org.dimdev.rift.listener.ChunkGeneratorReplacer;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.WorldType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.server.ServerWorld;

@Mixin(ServerWorld.class)
public class MixinWorldServer {

    @SuppressWarnings("ConstantConditions")
    @Redirect(method = "createChunkProvider", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;createChunkGenerator()Lnet/minecraft/world/gen/IChunkGenerator;"))
    protected <T extends GenerationSettings> ChunkGenerator onCreateChunkGenerator(Dimension dimension) {
    	ServerWorld world = (ServerWorld) (Object) this;
       WorldType type = world.getWorldType();
        ChunkGenerator generator = null;
        for(ChunkGeneratorReplacer adder : RiftLoader.instance.getListeners(ChunkGeneratorReplacer.class)) {
            ChunkGenerator value = adder.createChunkGenerator(world, type, dimension.getType().getId());
            if(value != null) generator = value;
        }
        return generator != null ? generator : dimension.createChunkGenerator();
    }
}
