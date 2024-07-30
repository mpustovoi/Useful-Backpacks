package info.u_team.useful_backpacks.init;

import java.util.List;

import info.u_team.u_team_core.api.registry.CreativeModeTabRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class UsefulBackpacksCreativeTabs {
	
	public static final CreativeModeTabRegister CREATIVE_MODE_TABS = CreativeModeTabRegister.create(UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", builder -> {
		builder.icon(() -> new ItemStack(UsefulBackpacksItems.SMALL_BACKPACK.get()));
		builder.title(Component.translatable("creativetabs.usefulbackpacks.tab"));
		builder.displayItems((parameters, output) -> {
			UsefulBackpacksBlocks.BLOCKS.itemIterable().forEach(item -> {
				output.accept(item);
			});
			UsefulBackpacksItems.ITEMS.entryIterable().forEach(item -> {
				output.accept(item);
				if (item instanceof BackpackItem) {
					for (final DyeColor color : DyeColor.values()) {
						output.accept(DyedItemColor.applyDyes(new ItemStack(item), List.of(DyeItem.byColor(color))));
					}
				}
			});
		});
	});
	
	static void registerMod() {
		CREATIVE_MODE_TABS.register();
	}
	
}
