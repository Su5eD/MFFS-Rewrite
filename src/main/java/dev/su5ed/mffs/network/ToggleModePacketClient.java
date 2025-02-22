package dev.su5ed.mffs.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ToggleModePacketClient(BlockPos pos, boolean active) {
    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeBoolean(this.active);
    }

    public static ToggleModePacketClient decode(FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        boolean enabled = buf.readBoolean();
        return new ToggleModePacketClient(pos, enabled);
    }

    public void processClientPacket(Supplier<NetworkEvent.Context> ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleToggleActivationPacket(this));
    }
}
