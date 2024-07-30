package info.u_team.useful_backpacks.component;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public class ItemFilterComponent {
	
	public static final Codec<ItemFilterComponent> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				Codec.BOOL.orElse(true).fieldOf("strict").forGetter(component -> component.strict), //
				ItemStack.CODEC.optionalFieldOf("stack").forGetter(component -> component.stack)) //
				.apply(instance, ItemFilterComponent::new);
	});
	
	public static final StreamCodec<RegistryFriendlyByteBuf, ItemFilterComponent> STREAM_CODEC = StreamCodec.composite( //
			ByteBufCodecs.BOOL, component -> component.strict, //
			ByteBufCodecs.optional(ItemStack.STREAM_CODEC), component -> component.stack, //
			ItemFilterComponent::new);
	
	public static final ItemFilterComponent EMPTY = new ItemFilterComponent(false, Optional.empty());
	
	private final boolean strict;
	private final Optional<ItemStack> stack;
	
	private ItemFilterComponent(boolean strict, Optional<ItemStack> stack) {
		this.strict = strict;
		this.stack = stack;
	}
	
	public static ItemFilterComponent of(boolean strict, ItemStack stack) {
		return of(strict, Optional.of(stack));
	}
	
	public static ItemFilterComponent of(boolean strict, Optional<ItemStack> stack) {
		return new ItemFilterComponent(strict, stack);
	}
	
	public boolean isPresent() {
		return stack.isPresent();
	}
	
	public boolean isStrict() {
		return strict;
	}
	
	public ItemStack getStack() {
		return stack.get();
	}
	
}
