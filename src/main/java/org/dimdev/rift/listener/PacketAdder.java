package org.dimdev.rift.listener;

import net.minecraft.network.IPacket;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.ProtocolType;

public interface PacketAdder {
    interface PacketRegistrationReceiver {
    	ProtocolType registerPacket(PacketDirection direction, Class<? extends IPacket<?>> packetClass);
    }

    void registerHandshakingPackets(PacketRegistrationReceiver receiver);
    void registerPlayPackets(PacketRegistrationReceiver receiver);
    void registerStatusPackets(PacketRegistrationReceiver receiver);
    void registerLoginPackets(PacketRegistrationReceiver receiver);
}
