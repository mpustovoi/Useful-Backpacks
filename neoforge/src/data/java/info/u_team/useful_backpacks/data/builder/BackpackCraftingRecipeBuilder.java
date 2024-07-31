package info.u_team.useful_backpacks.data.builder;

import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
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
		super.save(new RecipeOutput() {
			
			@Override
			public Builder advancement() {
				return output.advancement();
			}
			
			@Override
			public void accept(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement, ICondition... conditions) {
				// TODO maybe rework
				output.accept(id, new Recipe<>() {
					
					@Override
					public boolean matches(RecipeInput input, Level level) {
						return false;
					}
					
					@Override
					public ItemStack assemble(RecipeInput input, Provider registries) {
						return null;
					}
					
					@Override
					public boolean canCraftInDimensions(int width, int height) {
						return false;
					}
					
					@Override
					public ItemStack getResultItem(HolderLookup.Provider registries) {
						return null;
					}
					
					@Override
					public RecipeSerializer<?> getSerializer() {
						return UsefulBackpacksRecipeSerializers.BACKPACK.get();
					}
					
					@Override
					public RecipeType<?> getType() {
						return null;
					}
					
				}, advancement, conditions);
			}
		}, location);
	}
}
