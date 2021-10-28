package info.u_team.useful_backpacks.item;

import java.util.Arrays;
import java.util.List;

import info.u_team.u_team_core.api.dye.IDyeableItem;
import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.config.ServerConfig;
import info.u_team.useful_backpacks.container.BackpackContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksItemGroups;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import net.minecraft.world.item.Item.Properties;

public class BackpackItem extends UItem implements AutoPickupBackpack, IDyeableItem {
	
	private final Backpack backpack;
	
	public BackpackItem(Backpack backpack) {
		super(UsefulBackpacksItemGroups.GROUP, new Properties().stacksTo(1).rarity(backpack.getRarity()));
		this.backpack = backpack;
		addColoredItem(this);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!world.isClientSide && player instanceof ServerPlayer) {
			open((ServerPlayer) player, stack, hand == InteractionHand.MAIN_HAND ? player.inventory.selected : -1);
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public void open(ServerPlayer player, ItemStack backpackStack, int selectedSlot) {
		NetworkHooks.openGui(player, new SimpleMenuProvider((id, playerInventory, unused) -> {
			return new BackpackContainer(id, playerInventory, getInventory(player, backpackStack), backpack, selectedSlot);
		}, backpackStack.getHoverName()), buffer -> {
			buffer.writeEnum(backpack);
			buffer.writeVarInt(selectedSlot);
		});
	}
	
	@Override
	public Container getInventory(ServerPlayer player, ItemStack backpackStack) {
		return new BackpackInventory(backpackStack, backpack.getInventorySize());
	}
	
	@Override
	public void saveInventory(Container inventory, ItemStack backpackStack) {
		if (inventory instanceof BackpackInventory) {
			((BackpackInventory) inventory).writeItemStack();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
		addTooltip(stack, world, tooltip, flag);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.isSame(oldStack, newStack);
	}
	
	// Getter
	
	public Backpack getBackpack() {
		return backpack;
	}
	
	// Item group
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		if (!allowdedIn(group)) {
			return;
		}
		items.add(new ItemStack(this));
		for (final DyeColor color : DyeColor.values()) {
			items.add(IDyeableItem.colorStack(new ItemStack(this, 1), Arrays.asList(color)));
		}
	}
	
	// Default backpack color if not present
	
	@Override
	public int getDefaultColor() {
		return 0x816040;
	}
	
	// Fix bug #22 (too large packet size with certain mod items) and kind of reverted (config option) with #24
	
	@Override
	public CompoundTag getShareTag(ItemStack stack) {
		if (ServerConfig.getInstance().shareAllNBTData.get()) {
			return super.getShareTag(stack);
		}
		if (!stack.hasTag()) {
			return null;
		}
		final CompoundTag compound = stack.getTag().copy();
		compound.remove("Items");
		if (compound.isEmpty()) {
			return null;
		}
		return compound;
	}
	
	// Fix bug #30 (dupe bug when lagging server)
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, Player player) {
		return !(player.containerMenu instanceof BackpackContainer);
	}
}
