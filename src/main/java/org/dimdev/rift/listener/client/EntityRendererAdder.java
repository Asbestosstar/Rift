package org.dimdev.rift.listener.client;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;

import java.util.Map;

public interface EntityRendererAdder {
    void addEntityRenderers(Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> entityRenderMap, EntityRendererManager renderManager);
}
