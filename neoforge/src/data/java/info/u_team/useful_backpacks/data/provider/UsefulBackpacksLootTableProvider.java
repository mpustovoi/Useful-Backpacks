package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;

import info.u_team.u_team_core.data.CommonLootTableProvider;
import info.u_team.u_team_core.data.GenerationData;

public class UsefulBackpacksLootTableProvider extends CommonLootTableProvider {
	
	public UsefulBackpacksLootTableProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register(LootTableRegister register) {
		registerBlock(FILTER_CONFIGURATOR, addBasicBlockLootTable(FILTER_CONFIGURATOR.get()), register);
	}
	
}
