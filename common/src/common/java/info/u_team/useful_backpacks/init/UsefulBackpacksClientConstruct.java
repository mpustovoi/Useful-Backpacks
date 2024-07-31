package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.useful_backpacks.UsefulBackpacksReference;

@Construct(modid = UsefulBackpacksReference.MODID, client = true)
public class UsefulBackpacksClientConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		UsefulBackpacksColors.register();
		UsefulBackpacksScreens.register();
	}
	
}
