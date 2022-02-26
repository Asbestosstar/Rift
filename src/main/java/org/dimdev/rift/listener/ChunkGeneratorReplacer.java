package org.dimdev.rift.listener;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IChunkGeneratorFactory;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * used to substitute custom {@link IChunkGenerator}s for vanilla ones<br/>
 * <b>NOTE:</b><br/>
 *  custom {@link WorldType}s are registered by just instantiating them<br/>
 *  custom {@link ChunkGeneratorType}s are registered via {@link ChunkGeneratorType#registerChunkGeneratorType(String, IChunkGeneratorFactory, ChunkGeneratorType.Settings, boolean)}<br/>
 *  custom {@link ChunkGeneratorType.Settings} are registered via {@link ChunkGeneratorType.Settings#registerChunkGeneratorSettings(String, Supplier)}
 */
public interface ChunkGeneratorReplacer {

    /**
     * create an {@link IChunkGenerator} for your world type
     * @param worldType the world type that is currently set
     * @return a custom chunk generator or {@code null} to fall back to vanilla chunk generator selection
     */
    @Nullable
    <T extends GenerationSettings> ChunkGenerator<T> createChunkGenerator(World world, WorldType worldType, int dimensionID);
}
