package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.util.MenuUtil;
import info.u_team.u_team_core.util.TooltipCreator;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.component.ItemFilterComponent;
import info.u_team.useful_backpacks.init.UsefulBackpacksDataComponentTypes;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemFilterItem extends FilterItem {
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
			if (player.isShiftKeyDown()) {
				stack.set(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get(), ItemFilterComponent.EMPTY);
			} else {
				final int selectedSlot = hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1;
				
				final ItemFilterComponent component = stack.get(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get());
				final boolean isStrict;
				if (component == null) {
					isStrict = false;
				} else {
					isStrict = component.isStrict();
				}
				MenuUtil.openMenu(serverPlayer, new SimpleMenuProvider((id, playerInventory, unused) -> {
					return new ItemFilterMenu(id, playerInventory, stack, selectedSlot, isStrict);
				}, stack.getHoverName()), buffer -> {
					buffer.writeVarInt(selectedSlot);
					buffer.writeBoolean(isStrict);
				}, false);
			}
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	protected boolean matches(ItemStack filterStack, ItemStack matchStack) {
		final ItemFilterComponent component = filterStack.get(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get());
		final boolean strict = component.isStrict();
		final ItemStack stack = component.getStack();
		
		if (strict) {
			return ItemStack.isSameItemSameComponents(stack, matchStack);
		} else {
			return ItemStack.isSameItem(stack, matchStack);
		}
	}
	
	@Override
	public boolean isUsable(ItemStack filterStack) {
		if (filterStack.getItem() instanceof ItemFilterItem) {
			final ItemFilterComponent component = filterStack.get(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get());
			if (component != null) {
				return component.isPresent() && !component.getStack().isEmpty();
			}
		}
		return false;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (!isUsable(stack)) {
			tooltip.add(TooltipCreator.create(this, "not_configured", 0).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "not_configured", 1, TooltipCreator.create(UsefulBackpacksReference.MODID, "click", "right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		} else {
			final ItemFilterComponent component = stack.get(UsefulBackpacksDataComponentTypes.ITEM_FILTER_COMPONENT.get());
			tooltip.add(TooltipCreator.create(this, "configured", 0).withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
			tooltip.add(TooltipCreator.create(this, "configured", 1, Component.translatable(component.getStack().getDescriptionId()).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 2, Component.literal(Boolean.toString(component.isStrict())).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
			tooltip.add(TooltipCreator.create(this, "configured", 3, TooltipCreator.create(UsefulBackpacksReference.MODID, "click", "shift_right_click", 0).withStyle(ChatFormatting.ITALIC, ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
		}
	}
	
	@Override
	public boolean canBeDropped(ItemStack stack, Player player) {
		return !(player.containerMenu instanceof ItemFilterMenu);
	}
}
