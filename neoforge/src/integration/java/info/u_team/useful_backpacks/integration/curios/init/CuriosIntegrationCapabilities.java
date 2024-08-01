package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.useful_backpacks.init.UsefulBackpacksItems;
import info.u_team.useful_backpacks.integration.curios.capability.CuriosBackpackCapability;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;

public class CuriosIntegrationCapabilities {
	
	private static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(CuriosCapability.ITEM, (stack, context) -> new CuriosBackpackCapability(stack), //
				UsefulBackpacksItems.SMALL_BACKPACK.get(), //
				UsefulBackpacksItems.MEDIUM_BACKPACK.get(), //
				UsefulBackpacksItems.LARGE_BACKPACK.get(), //
				UsefulBackpacksItems.ENDERCHEST_BACKPACK.get());
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(CuriosIntegrationCapabilities::onRegisterCapabilities);
	}
	
}
