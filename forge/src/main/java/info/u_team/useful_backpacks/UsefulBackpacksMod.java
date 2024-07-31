package info.u_team.useful_backpacks;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import net.minecraftforge.fml.common.Mod;

@Mod(UsefulBackpacksMod.MODID)
public class UsefulBackpacksMod {
	
	public static final String MODID = UsefulBackpacksReference.MODID;
	
	public UsefulBackpacksMod() {
		AnnotationManager.callAnnotations(MODID);
	}
}
