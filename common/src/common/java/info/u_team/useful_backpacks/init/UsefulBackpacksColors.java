package info.u_team.useful_backpacks.init;

import java.util.stream.Stream;

import info.u_team.u_team_core.api.registry.client.ColorProviderRegister;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.Util;
import net.minecraft.world.item.component.DyedItemColor;

public class UsefulBackpacksColors {
	
	private static final ColorProviderRegister COLORS = Util.make(ColorProviderRegister.create(), colors -> {
		colors.register((itemColors, blockColors, colorRegister) -> colorRegister.register((stack, index) -> {
			return DyedItemColor.getOrDefault(stack, BackpackItem.DEFAULT_COLOR);
		}, Stream.of(UsefulBackpacksItems.SMALL_BACKPACK.get(), UsefulBackpacksItems.MEDIUM_BACKPACK.get(), UsefulBackpacksItems.LARGE_BACKPACK.get())));
	});
	
	static void register() {
		COLORS.register();
	}
	
}
