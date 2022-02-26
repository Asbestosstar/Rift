package org.dimdev.rift.mixin.hook;

import org.dimdev.rift.injectedmethods.RiftCPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CCustomPayloadPacket;
import net.minecraft.util.ResourceLocation;

@Mixin(CCustomPayloadPacket.class)
public class MixinCPacketCustomPayload implements RiftCPacketCustomPayload {
    @Shadow private ResourceLocation channel;
    @Shadow private PacketBuffer data;

    @Override
    public ResourceLocation getChannelName() {
        return channel;
    }

    @Override
    public PacketBuffer getData() {
        return data;
    }
}
