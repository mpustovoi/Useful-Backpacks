package info.u_team.useful_backpacks.config;

import org.apache.commons.lang3.tuple.Pair;

import info.u_team.u_team_core.util.ConfigValueHolder;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

public class ForgeCommonConfig {
	
	public static final ModConfigSpec CONFIG;
	private static final ForgeCommonConfig INSTANCE;
	
	static {
		final Pair<ForgeCommonConfig, ModConfigSpec> pair = new Builder().configure(ForgeCommonConfig::new);
		CONFIG = pair.getRight();
		INSTANCE = pair.getLeft();
	}
	
	private final ConfigValueHolder<Boolean> allowStackingBackpacks;
	
	private ForgeCommonConfig(Builder builder) {
		builder.comment("Common configuration settings").push("common");
		final BooleanValue allowStackingBackpacksValue = builder.comment("This option controls if backpacks in backpacks are allowed.", "If set to true you can put backpacks in existing backpacks and stack them together.", "If set to false you cannot put backpacks in backpacks").define("allowStackingBackpacks", true);
		allowStackingBackpacks = new ConfigValueHolder<>(allowStackingBackpacksValue, allowStackingBackpacksValue::set);
		builder.pop();
	}
	
	public static class Impl extends CommonConfig {
		
		@Override
		public ConfigValueHolder<Boolean> allowStackingBackpacks() {
			return INSTANCE.allowStackingBackpacks;
		}
	}
	
}
