package org.dimdev.rift.mixin.hook;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.dimdev.rift.listener.DataPackFinderAdder;
import org.dimdev.rift.listener.ServerTickable;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.profiler.Profiler;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
 /*   @Shadow @Final private ResourcePackList<ResourcePackInfo> resourcePacks;
    @Shadow @Final public Profiler profiler;
    @Shadow public abstract void convertMapIfNeeded(String p_convertMapIfNeeded_1_);
    @Shadow public ServerWorld[] worlds;
    @Shadow public long[][] timeOfLastDimensionTick;
    @Shadow public abstract SaveFormat getActiveAnvilConverter();
    @Shadow public abstract void setResourcePackFromWorld(String p_setResourcePackFromWorld_1_, SaveHandler p_setResourcePackFromWorld_2_);
    @Shadow public abstract String getFolderName();
    @Shadow protected abstract void setUserMessage(ITextComponent p_200245_1_);
    @Shadow public abstract GameType getGameType();
    @Shadow public abstract boolean canStructuresSpawn();
    @Shadow public abstract boolean isHardcore();
    @Shadow public abstract void func_195560_a(File p_195560_1_, WorldInfo p_195560_2_);
    @Shadow public abstract void initialWorldChunkLoad();
    @Shadow public abstract void setDifficultyForAllWorlds(Difficulty p_setDifficultyForAllWorlds_1_);
    @Shadow public abstract CompoundNBT getCustomBossEvents();
    @Shadow public abstract PlayerList getPlayerList();
    @Shadow public abstract boolean isDemo();
    @Shadow public abstract boolean isSinglePlayer();
    @Shadow public abstract Difficulty getDifficulty();
    @Shadow private boolean enableBonusChest;

    private Map<DimensionType, Integer> dimensionTypeToWorldIndex = new HashMap<>();
    private Map<Integer, Integer> dimensionIdToWorldIndex = new HashMap<>();

    @Inject(method = "func_195560_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/resources/ResourcePackList;addPackFinder(Lnet/minecraft/resources/IPackFinder;)V", shift = At.Shift.AFTER))
    private void afterAddVanillaPackFinder(File serverDirectory, WorldInfo worldInfo, CallbackInfo ci) {
        for (DataPackFinderAdder resourcePackFinderAdder : RiftLoader.instance.getListeners(DataPackFinderAdder.class)) {
            for (IPackFinder packFinder : resourcePackFinderAdder.getDataPackFinders()) {
                resourcePacks.addPackFinder(packFinder);
            }
        }
    }

    @Inject(method = "tick", at = @At(value = "RETURN"))
    private void onTick(CallbackInfo ci) {
        profiler.startSection("mods");
        for (ServerTickable tickable : RiftLoader.instance.getListeners(ServerTickable.class)) {
            profiler.startSection(() -> tickable.getClass().getCanonicalName().replace('.', '/'));
            tickable.serverTick((MinecraftServer) (Object) this);
            profiler.endSection();
        }
        profiler.endSection();
    }

    @Overwrite
    public void loadAllWorlds(String saveName, String worldName, long seed, WorldType type, JsonElement generatorOptions) {
        convertMapIfNeeded(saveName);

    //    setUserMessage(new TextComponentTranslation("menu.loadingLevel"));

        SaveHandler saveHandler = getActiveAnvilConverter().getSaveLoader(saveName, (MinecraftServer) (Object) this);
        setResourcePackFromWorld(getFolderName(), saveHandler);
        WorldInfo worldInfo = saveHandler.loadWorldInfo();
        WorldSettings worldSettings = getWorldSettings(worldInfo, seed, type, generatorOptions);

        if (worldInfo == null) {
            worldInfo = new WorldInfo(worldSettings, worldName);
        } else {
            worldInfo.setWorldName(worldName);
        }

        func_195560_a(saveHandler.getWorldDirectory(), worldInfo);

        // Create overworld
        ServerWorld overworld = isDemo() ? new ServerWorldDemo((MinecraftServer) (Object) this, saveHandler, worldInfo, 0, profiler)
                                         : new ServerWorld((MinecraftServer) (Object) this, saveHandler, worldInfo, 0, profiler);
        overworld.init();

        overworld.initialize(worldSettings);
        overworld.addEventListener(new ServerWorldEventHandler((MinecraftServer) (Object) this, overworld));
        if (!isSinglePlayer()) {
            overworld.getWorldInfo().setGameType(getGameType());
        }

        List<ServerWorld> worldList = new ArrayList<>();
        worldList.add(overworld);
        dimensionIdToWorldIndex.put(0, 0);
        dimensionTypeToWorldIndex.put(DimensionType.OVERWORLD, 0);

        // Create other worlds
        List<DimensionType> dimensionTypes;
        try {
            @SuppressWarnings("JavaReflectionMemberAccess")
            Field dimensionTypesField = DimensionType.class.getDeclaredField("dimensionTypes");
            dimensionTypesField.setAccessible(true);
            //noinspection unchecked
            dimensionTypes = new ArrayList<>(((Map<Integer, DimensionType>) dimensionTypesField.get(null)).values());
            dimensionTypes.remove(DimensionType.OVERWORLD);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        for (DimensionType dimensionType : dimensionTypes) {
            dimensionIdToWorldIndex.put(dimensionType.getId(), worldList.size());
            dimensionTypeToWorldIndex.put(dimensionType, worldList.size());
            ServerWorld world = new ServerWorldMulti((MinecraftServer) (Object) this, saveHandler, dimensionType.getId(), overworld, profiler);
            world.init();
            world.addEventListener(new ServerWorldEventHandler((MinecraftServer) (Object) this, world));
            if (!isSinglePlayer()) {
                world.getWorldInfo().setGameType(getGameType());
            }

            worldList.add(world);
        }

        // Initialize other things
        worlds = worldList.toArray(new ServerWorld[0]);
        timeOfLastDimensionTick = new long[worlds.length][100];

        getPlayerList().setPlayerManager(worlds);
        if (worldInfo.getCustomBossEvents() != null) {
            getCustomBossEvents().read(worldInfo.getCustomBossEvents());
        }

        if (overworld.getWorldInfo().getDifficulty() == null) {
            setDifficultyForAllWorlds(getInitialDifficulty());
        }

        // initialWorldChunkLoad();
    }

    protected WorldSettings getWorldSettings(@Nullable WorldInfo worldInfo, long seed, WorldType worldType, JsonElement generatorOptions) {
        if (worldInfo == null) {
            if (isDemo()) {
                return ServerWorldDemo.DEMO_WORLD_SETTINGS;
            } else {
                WorldSettings worldSettings = new WorldSettings(seed, getGameType(), canStructuresSpawn(), isHardcore(), worldType);
                worldSettings.func_205390_a(generatorOptions);
                if (enableBonusChest) {
                    worldSettings.enableBonusChest();
                }
                return worldSettings;
            }
        } else {
            return new WorldSettings(worldInfo);
        }
    }

    protected EnumDifficulty getInitialDifficulty() {
        return getDifficulty();
    }

    @Overwrite
    public ServerWorld getWorld(DimensionType dimensionType) {
        return worlds[dimensionTypeToWorldIndex.get(dimensionType)];
    }

    @Overwrite
    public ServerWorld getWorld(int dimensionId) {
        return worlds[dimensionIdToWorldIndex.get(dimensionId)];
    }

*/
}
