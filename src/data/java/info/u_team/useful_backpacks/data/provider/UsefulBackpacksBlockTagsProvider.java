package info.u_team.useful_backpacks.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.u_team_core.item.tier.VanillaTierTags;
import info.u_team.useful_backpacks.init.UsefulBackpacksBlocks;

public class UsefulBackpacksBlockTagsProvider extends CommonBlockTagsProvider {
	
	public UsefulBackpacksBlockTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		tag(VanillaTierTags.MINEABLE_WITH_AXE).add(UsefulBackpacksBlocks.FILTER_CONFIGURATOR.get());
	}
	
}