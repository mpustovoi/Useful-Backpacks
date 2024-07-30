package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.component.ItemFilterComponent;
import info.u_team.useful_backpacks.component.TagFilterComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.ItemContainerContents;

public class UsefulBackpacksDataComponentTypes {
	
	public static final CommonRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = CommonRegister.create(Registries.DATA_COMPONENT_TYPE, UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<DataComponentType<ItemContainerContents>> BACKPACK_COMPONENT = DATA_COMPONENT_TYPES.register("backpack", () -> {
		return DataComponentType.<ItemContainerContents> builder().persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC).cacheEncoding().build();
	});
	
	public static final RegistryEntry<DataComponentType<ItemContainerContents>> FILTER_COMPONENT = DATA_COMPONENT_TYPES.register("filter", () -> {
		return DataComponentType.<ItemContainerContents> builder().persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC).cacheEncoding().build();
	});
	
	public static final RegistryEntry<DataComponentType<ItemFilterComponent>> ITEM_FILTER_COMPONENT = DATA_COMPONENT_TYPES.register("item_filter", () -> {
		return DataComponentType.<ItemFilterComponent> builder().persistent(ItemFilterComponent.CODEC).networkSynchronized(ItemFilterComponent.STREAM_CODEC).cacheEncoding().build();
	});
	
	public static final RegistryEntry<DataComponentType<TagFilterComponent>> TAG_FILTER_COMPONENT = DATA_COMPONENT_TYPES.register("tag_filter", () -> {
		return DataComponentType.<TagFilterComponent> builder().persistent(TagFilterComponent.CODEC).networkSynchronized(TagFilterComponent.STREAM_CODEC).cacheEncoding().build();
	});
	
	static void register() {
		DATA_COMPONENT_TYPES.register();
	}
	
}
