package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.config.NeoForgeCommonConfig;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;

@Construct(modid = UsefulBackpacksReference.MODID)
public class UsefulBackpacksNeoForgeCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, NeoForgeCommonConfig.CONFIG);
	}
}