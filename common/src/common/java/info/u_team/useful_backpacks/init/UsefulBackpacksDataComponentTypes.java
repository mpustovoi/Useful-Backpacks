package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.component.BackpackComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public class UsefulBackpacksDataComponentTypes {
	
	public static final CommonRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = CommonRegister.create(Registries.DATA_COMPONENT_TYPE, UsefulBackpacksReference.MODID);
	
	public static final RegistryEntry<DataComponentType<BackpackComponent>> BACKPACK_COMPONENT = DATA_COMPONENT_TYPES.register("backpack", () -> {
		return DataComponentType.<BackpackComponent> builder().persistent(BackpackComponent.CODEC).networkSynchronized(BackpackComponent.STREAM_CODEC).cacheEncoding().build();
	});
	
	static void register() {
		DATA_COMPONENT_TYPES.register();
	}
	
}
