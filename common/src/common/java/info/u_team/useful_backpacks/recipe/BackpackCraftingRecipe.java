package info.u_team.useful_backpacks.recipe;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.u_team_core.recipeserializer.UShapedRecipeSerializer;
import info.u_team.u_team_core.util.ColorUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public class BackpackCraftingRecipe extends ShapedRecipe {
	
	public BackpackCraftingRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
		super(group, category, pattern, result, showNotification);
	}
	
	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
		final ItemStack backpackItem = super.assemble(input, registries);
		final List<DyeItem> dyeList = Lists.newArrayList();
		
		boolean backpackPresent = false;
		
		for (int index = 0; index < input.size(); ++index) {
			final ItemStack slotStack = input.getItem(index);
			if (!slotStack.isEmpty()) {
				final Item item = slotStack.getItem();
				if (item instanceof BackpackItem) {
					if (backpackPresent) { // Does not allow multiple backpacks if somebody changed the recipe to be so
						return ItemStack.EMPTY;
					}
					backpackPresent = true;
					backpackItem.applyComponentsAndValidate(slotStack.getComponentsPatch()); // Copy tag from existing one including color and inventory if exist
				} else {
					if (slotStack.is(ItemTags.WOOL)) {
						final DyeColor color = ColorUtil.getColorFromWool(item);
						if (color != null) {
							dyeList.add(DyeItem.byColor(color));
						}
					}
				}
			}
		}
		if (!dyeList.isEmpty() && !dyeList.parallelStream().allMatch(item -> item.getDyeColor() == DyeColor.WHITE)) { // Don't change color if all color is white (neutral element).
			return DyedItemColor.applyDyes(backpackItem, dyeList);
		}
		return backpackItem;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return UsefulBackpacksRecipeSerializers.BACKPACK.get();
	}
	
	public static class Serializer extends UShapedRecipeSerializer<BackpackCraftingRecipe> {
		
		@Override
		protected BackpackCraftingRecipe createRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
			return new BackpackCraftingRecipe(group, category, pattern, result, showNotification);
		}
	}
}
