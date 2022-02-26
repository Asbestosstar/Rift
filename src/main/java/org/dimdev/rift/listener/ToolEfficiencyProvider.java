package org.dimdev.rift.listener;

import net.minecraft.block.Block;
import net.minecraft.item.ToolItem;

import java.util.Set;

public interface ToolEfficiencyProvider {
    void addEffectiveBlocks(ToolItem tool, Set<Block> target);
}
