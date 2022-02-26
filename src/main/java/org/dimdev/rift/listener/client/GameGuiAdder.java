package org.dimdev.rift.listener.client;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.IInventory;



public interface GameGuiAdder {
    //void displayGui(ClientPlayerEntity player, String id, NamedScreenHandlerFactory interactionObject);
    void displayContainerGui(ClientPlayerEntity player, String id, IInventory inventory);
}
