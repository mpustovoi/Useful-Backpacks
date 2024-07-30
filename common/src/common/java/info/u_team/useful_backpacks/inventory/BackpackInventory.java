package info.u_team.useful_backpacks.inventory;

import info.u_team.useful_backpacks.component.BackpackComponent;
import info.u_team.useful_backpacks.init.UsefulBackpacksDataComponentTypes;
import info.u_team.useful_backpacks.type.BackpackType;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class BackpackInventory extends SimpleContainer {
	
	private final ItemStack stack;
	private BackpackType type;
	
	public BackpackInventory(ItemStack stack, int count) {
		super(count);
		this.stack = stack;
		readItemStack();
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void readItemStack() {
		final BackpackComponent component = stack.get(UsefulBackpacksDataComponentTypes.BACKPACK_COMPONENT.get());
		if (component != null) {
			component.getContents().copyInto(items);
			type = component.getType();
		}
	}
	
	public void writeItemStack() {
		if (type != null) {
			final BackpackComponent component = new BackpackComponent(type, items);
			stack.set(UsefulBackpacksDataComponentTypes.BACKPACK_COMPONENT.get(), component);
		}
	}
	
}