package org.dimdev.rift.mixin.hook.client;

import javax.annotation.Nullable;

import org.dimdev.rift.mixin.hook.MixinMinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.google.gson.JsonElement;

import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;

@Mixin(IntegratedServer.class)
public abstract class MixinIntegratedServer extends MixinMinecraftServer {
    @Shadow @Final private Minecraft mc;
    @Shadow @Final private WorldSettings worldSettings;

    protected WorldSettings getWorldSettings(@Nullable WorldInfo worldInfo, long seed, WorldType worldType, JsonElement generatorOptions) {
        return worldSettings;
    }

    protected Difficulty getInitialDifficulty() {
        return mc.gameSettings.difficulty;
    }

//    @Overwrite
//    public void loadAllWorlds(String saveName, String worldName, long seed, WorldType type, JsonElement generatorOptions) {
//        super.loadAllWorlds(saveName, worldName, seed, type, generatorOptions);
//    }
}
