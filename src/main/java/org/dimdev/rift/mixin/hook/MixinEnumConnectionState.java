package org.dimdev.rift.mixin.hook;

import org.dimdev.rift.listener.PacketAdder;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.IPacket;
import net.minecraft.network.ProtocolType;
import net.minecraft.util.Direction;

//@Mixin(ProtocolType.class)
public abstract class MixinEnumConnectionState {
   /* @Shadow protected abstract ProtocolType registerPacket(Direction direction, Class<? extends IPacket<?>> packetClass);

    @Mixin(targets = "net/minecraft/network/ProtocolType$1")
    public abstract static class Handshaking extends MixinEnumConnectionState {
        @Inject(method = "<init>", at = @At("RETURN"))
        private void registerModPackets(CallbackInfo ci) {
            for (PacketAdder packetAdder : RiftLoader.instance.getListeners(PacketAdder.class)) {
                packetAdder.registerHandshakingPackets(this::registerPacket);
            }
        }
    }

    @Mixin(targets = "net/minecraft/network/ProtocolType$2")
    public abstract static class Play extends MixinEnumConnectionState {
        @Inject(method = "<init>", at = @At("RETURN"))
        private void registerModPackets(CallbackInfo ci) {
            for (PacketAdder packetAdder : RiftLoader.instance.getListeners(PacketAdder.class)) {
                packetAdder.registerPlayPackets(this::registerPacket);
            }
        }
    }

    @Mixin(targets = "net/minecraft/network/ProtocolType$3")
    public abstract static class Status extends MixinEnumConnectionState {
        @Inject(method = "<init>", at = @At("RETURN"))
        private void registerModPackets(CallbackInfo ci) {
            for (PacketAdder packetAdder : RiftLoader.instance.getListeners(PacketAdder.class)) {
                packetAdder.registerStatusPackets(this::registerPacket);
            }
        }
    }

    @Mixin(targets = "net/minecraft/network/ProtocolType$4")
    public abstract static class Login extends MixinEnumConnectionState {
        @Inject(method = "<init>", at = @At("RETURN"))
        private void registerModPackets(CallbackInfo ci) {
            for (PacketAdder packetAdder : RiftLoader.instance.getListeners(PacketAdder.class)) {
                packetAdder.registerLoginPackets(this::registerPacket);
            }
        }
    }

*/}
