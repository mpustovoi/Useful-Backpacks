package info.u_team.useful_backpacks.integration.slot_mod.message;

import java.util.Optional;

import info.u_team.u_team_core.api.network.NetworkContext;
import info.u_team.useful_backpacks.api.Backpack;
import info.u_team.useful_backpacks.integration.slot_mod.util.BackpackSlotModUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class OpenBackpackMessage {
	
	public static final OpenBackpackMessage INSTANCE = new OpenBackpackMessage();
	public static final StreamCodec<ByteBuf, OpenBackpackMessage> STREAM_CODEC = StreamCodec.unit(INSTANCE);
	
	private OpenBackpackMessage() {
	}
	
	public static void handle(OpenBackpackMessage message, NetworkContext context) {
		if (!(context.getPlayer() instanceof final ServerPlayer player)) {
			return;
		}
		context.executeOnMainThread(() -> {
			if (!player.isAlive() || player.hasContainerOpen()) {
				return;
			}
			final Optional<ItemStack> curioBackpack = BackpackSlotModUtil.findBackpack(player);
			if (curioBackpack.isPresent()) {
				final ItemStack stack = curioBackpack.get();
				if (stack.getItem() instanceof final Backpack backpack) {
					backpack.open(player, stack, -2);
				}
			}
		});
	}
	
}
