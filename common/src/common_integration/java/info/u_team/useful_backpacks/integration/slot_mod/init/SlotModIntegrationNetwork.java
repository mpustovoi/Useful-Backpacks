package info.u_team.useful_backpacks.integration.slot_mod.init;

import info.u_team.u_team_core.api.network.NetworkHandler;
import info.u_team.u_team_core.api.network.NetworkHandlerEnvironment;
import info.u_team.u_team_core.api.network.NetworkMessage;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.integration.slot_mod.message.OpenBackpackMessage;
import net.minecraft.resources.ResourceLocation;

public class SlotModIntegrationNetwork {
	
	public static final NetworkHandler NETWORK = NetworkHandler.create(ResourceLocation.fromNamespaceAndPath(UsefulBackpacksReference.MODID, "slot_mod"), 0);
	
	public static final NetworkMessage<OpenBackpackMessage> OPEN_BACKPACK_MESSAGE = NETWORK.register("open_backpack", NetworkHandlerEnvironment.SERVER, OpenBackpackMessage.STREAM_CODEC, OpenBackpackMessage::handle);
	
	public static void register() {
		NETWORK.register();
	}
	
}
