package org.dimdev.rift.mixin.hook;

import java.io.File;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.mojang.datafixers.DataFixer;

import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.server.ChunkManager;
import net.minecraft.world.storage.SaveHandler;

//@Mixin(AnvilSaveHandler.class)
public class MixinAnvilSaveHandler {//extends SaveHandler {
  
	/*public MixinAnvilSaveHandler(File p_i49566_1_, String p_i49566_2_, @Nullable MinecraftServer p_i49566_3_, DataFixer p_i49566_4_) {
        super(p_i49566_1_, p_i49566_2_, p_i49566_3_, p_i49566_4_);
    }

    @Overwrite
    @Override
    public ViewFrustum getChunkLoader(Dimension dimension) {
        File worldDirectory = getWorldDirectory();
        int dimensionId = dimension.getType().getId();
        if (dimensionId == 0) {
            return new ChunkManager(worldDirectory, dataFixer);
        } else {
            File dimensionFolder = new File(worldDirectory, "DIM" + dimensionId);
            dimensionFolder.mkdirs();
            return new ChunkManager(dimensionFolder, dataFixer);
        }
    }


*/
}
