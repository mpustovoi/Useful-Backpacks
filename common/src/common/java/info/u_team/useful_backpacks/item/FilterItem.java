package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.api.Filter;
import net.minecraft.world.item.ItemStack;

public abstract class FilterItem extends UItem implements Filter {
	
	public FilterItem() {
		super(new Properties().stacksTo(1));
	}
	
	@Override
	public boolean matchItem(ItemStack filterStack, ItemStack matchStack) {
		if (isUsable(filterStack)) {
			return matches(filterStack, matchStack);
		} else {
			return false;
		}
	}
	
	protected abstract boolean matches(ItemStack filterStack, ItemStack matchStack);
	
}
