package info.u_team.useful_backpacks.data.builder;

import info.u_team.useful_backpacks.recipe.BackpackCraftingRecipe;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ICondition;

public class BackpackCraftingRecipeBuilder extends ShapedRecipeBuilder {
	
	public BackpackCraftingRecipeBuilder(RecipeCategory category, ItemLike item, int count) {
		super(category, item, count);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(RecipeCategory category, ItemLike item) {
		return backpackRecipe(category, item, 1);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(RecipeCategory category, ItemLike item, int count) {
		return new BackpackCraftingRecipeBuilder(category, item, count);
	}
	
	@Override
	public void save(RecipeOutput output, ResourceLocation location) {
		super.save(new RecipeOutput() { // TODO rework
			
			@Override
			public Builder advancement() {
				return output.advancement();
			}
			
			@Override
			public void accept(ResourceLocation id, Recipe<?> rawRecipe, AdvancementHolder advancement, ICondition... conditions) {
				final ShapedRecipe recipe = (ShapedRecipe) rawRecipe;
				output.accept(id, new BackpackCraftingRecipe(recipe.getGroup(), recipe.category(), recipe.pattern, recipe.getResultItem(null), recipe.showNotification()), advancement, conditions);
			}
		}, location);
	}
}
