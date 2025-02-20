package info.u_team.useful_backpacks.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksBlockStateProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksBlockTagsProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksItemModelProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksItemTagsProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksLanguagesProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksLootTableProvider;
import info.u_team.useful_backpacks.data.provider.UsefulBackpacksRecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class UsefulBackpacksDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(UsefulBackpacksMod.MODID, event);
		data.addProvider(event.includeServer(), UsefulBackpacksBlockTagsProvider::new, UsefulBackpacksItemTagsProvider::new);
		data.addProvider(event.includeServer(), UsefulBackpacksLootTableProvider::new);
		data.addProvider(event.includeServer(), UsefulBackpacksRecipeProvider::new);
		
		data.addProvider(event.includeClient(), UsefulBackpacksBlockStateProvider::new);
		data.addProvider(event.includeClient(), UsefulBackpacksItemModelProvider::new);
		data.addProvider(event.includeClient(), UsefulBackpacksLanguagesProvider::new);
	}
}
