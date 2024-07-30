package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.menu.FilterConfiguratorMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FilterConfiguratorScreen extends UContainerMenuScreen<FilterConfiguratorMenu> {
	
	private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(UsefulBackpacksReference.MODID, "textures/gui/filter_configurator.png");
	
	public FilterConfiguratorScreen(FilterConfiguratorMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, BACKGROUND);
	}
}
