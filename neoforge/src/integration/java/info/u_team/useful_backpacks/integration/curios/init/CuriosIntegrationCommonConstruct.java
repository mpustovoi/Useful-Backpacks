package info.u_team.useful_backpacks.integration.curios.init;

import info.u_team.u_team_core.api.integration.Integration;
import info.u_team.u_team_core.api.integration.ModIntegration;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationAddBackpackIntegration;
import info.u_team.useful_backpacks.integration.slot_mod.init.SlotModIntegrationNetwork;

@Integration(modid = UsefulBackpacksMod.MODID, integration = "curios")
public class CuriosIntegrationCommonConstruct implements ModIntegration {
	
	@Override
	public void construct() {
		BusRegister.registerForge(CuriosIntegrationCapabilities::registerForge);
		
		SlotModIntegrationNetwork.register();
		SlotModIntegrationAddBackpackIntegration.register();
	}
	
}
