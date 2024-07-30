package info.u_team.useful_backpacks.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.u_team_core.util.MenuUtil;
import info.u_team.useful_backpacks.menu.FilterConfiguratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class FilterConfiguratorBlock extends UBlock {
	
	private static final Component CONTAINER_NAME = Component.translatable("container.usefulbackpacks.filter_configurator");
	
	public FilterConfiguratorBlock() {
		super(Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).sound(SoundType.WOOD).ignitedByLava());
	}
	
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else {
			if (player instanceof ServerPlayer serverPlayer) {
				MenuUtil.openMenu(serverPlayer, state.getMenuProvider(level, pos), data -> {
				}, true);
			}
			return InteractionResult.CONSUME;
		}
	}
	
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, player) -> {
			return new FilterConfiguratorMenu(id, inventory, ContainerLevelAccess.create(level, pos));
		}, CONTAINER_NAME);
	}
	
}
