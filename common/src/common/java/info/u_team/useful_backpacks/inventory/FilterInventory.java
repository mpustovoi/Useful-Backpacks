package info.u_team.useful_backpacks.inventory;

import info.u_team.useful_backpacks.init.UsefulBackpacksDataComponentTypes;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public class FilterInventory extends SimpleContainer {
	
	private final ItemStack stack;
	
	public FilterInventory(ItemStack stack) {
		super(9);
		this.stack = stack;
		readItemStack();
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void readItemStack() {
		final ItemContainerContents component = stack.get(UsefulBackpacksDataComponentTypes.FILTER_COMPONENT.get());
		if (component != null) {
			component.copyInto(getItems());
		}
	}
	
	public void writeItemStack() {
		final ItemContainerContents component = ItemContainerContents.fromItems(getItems());
		stack.set(UsefulBackpacksDataComponentTypes.FILTER_COMPONENT.get(), component);
	}
}