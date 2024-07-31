package info.u_team.useful_backpacks.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

public class ItemPickupEventHandler {
	
	private static void onEntityItemPickup(ItemEntityPickupEvent.Pre event) {
		final Player player = event.getPlayer();
		
		if (!(player instanceof ServerPlayer serverPlayer)) {
			return;
		}
		
		final ItemStack stackToPickup = event.getItemEntity().getItem();
		final ItemStack resultStack = ItemPickupCommonEventHandler.insertInBackpacks(serverPlayer, stackToPickup);
		stackToPickup.setCount(resultStack.getCount());
		
		if (resultStack.isEmpty()) {
			// event.setResult(); // TODO set something here??
		}
	}
	
	public static void registerNeoForge(IEventBus bus) {
		bus.addListener(EventPriority.HIGHEST, ItemPickupEventHandler::onEntityItemPickup);
	}
	
}
