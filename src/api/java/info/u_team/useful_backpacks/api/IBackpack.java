package info.u_team.useful_backpacks.api;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IBackpack {
	
	void open(ServerPlayerEntity player, ItemStack stack, int selectedSlot);
	
	IInventory getInventory(ServerPlayerEntity player, ItemStack stack);
}
