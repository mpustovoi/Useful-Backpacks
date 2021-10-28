package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.api.IBackpack;
import info.u_team.useful_backpacks.container.slot.BackpackSlot;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;

public class BackpackContainer extends UContainer {
	
	private final Container backpackInventory;
	private final Backpack backpack;
	private final int selectedSlot;
	
	// Client
	public static BackpackContainer createClientContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		final Backpack backpack = buffer.readEnum(Backpack.class);
		final int selectedSlot = buffer.readVarInt();
		return new BackpackContainer(id, playerInventory, new SimpleContainer(backpack.getInventorySize()), backpack, selectedSlot);
	}
	
	// Server
	public BackpackContainer(int id, Inventory playerInventory, Container backpackInventory, Backpack backpack, int selectedSlot) {
		super(UsefulBackpacksContainerTypes.BACKPACK.get(), id);
		this.backpackInventory = backpackInventory;
		this.backpack = backpack;
		this.selectedSlot = selectedSlot;
		appendBackpackInventory(backpack.getSlotBackpackX(), backpack.getSlotBackpackY());
		appendPlayerInventory(playerInventory, backpack.getSlotPlayerX(), backpack.getSlotPlayerY());
	}
	
	public void appendBackpackInventory(int x, int y) {
		for (int height = 0; height < backpack.getInventoryHeight(); height++) {
			for (int width = 0; width < backpack.getInventoryWidth(); width++) {
				addSlot(new BackpackSlot(backpackInventory, width + height * backpack.getInventoryWidth(), width * 18 + x, height * 18 + y));
			}
		}
	}
	
	@Override
	public void broadcastChanges() {
		super.broadcastChanges();
		if (backpackInventory instanceof BackpackInventory) {
			((BackpackInventory) backpackInventory).writeItemStack();
		}
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			final ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (index < backpack.getInventorySize()) {
				if (!this.moveItemStackTo(itemstack1, backpack.getInventorySize(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, backpack.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public ItemStack clicked(int slotId, int dragType, ClickType clickType, Player player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < slots.size()) {
			tmpSlot = slots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.container == player.inventory && tmpSlot.getSlotIndex() == selectedSlot) {
				return tmpSlot.getItem();
			}
		}
		if (clickType == ClickType.SWAP) {
			final ItemStack stack = player.inventory.getItem(dragType);
			final ItemStack currentItem = Inventory.isHotbarSlot(selectedSlot) ? player.inventory.items.get(selectedSlot) : selectedSlot == -1 ? player.inventory.offhand.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return ItemStack.EMPTY;
			}
		}
		return super.clicked(slotId, dragType, clickType, player);
	}
	
	@Override
	public boolean stillValid(Player player) {
		if (backpackInventory instanceof BackpackInventory) {
			final ItemStack stack = ((BackpackInventory) backpackInventory).getStack();
			return !stack.isEmpty() && stack.getItem() instanceof IBackpack;
		}
		return true;
	}
	
	public Backpack getBackpack() {
		return backpack;
	}
}
