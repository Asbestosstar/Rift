package org.dimdev.rift.mixin.core.client;

import org.dimdev.rift.injectedmethods.RiftFluid;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

@Mixin(LavaFluid.class)
public class MixinLavaFluid implements RiftFluid {
    @Override
    public TextureAtlasSprite getStillTexture() {
        return Minecraft.getInstance().getModelManager().getBlockModelShapes().getModel(Blocks.LAVA.getDefaultState()).getParticleTexture();
    }

    @Override
    public TextureAtlasSprite getFlowingTexture() {
        return Minecraft.getInstance().getTextureMap().getSprite(ModelBakery.LOCATION_LAVA_FLOW);
    }

    @Override
    public int getColorMultiplier(IWorldReader world, BlockPos pos) {
        return 0xFFFFFF;
    }
}
