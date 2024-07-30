package info.u_team.useful_backpacks.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_backpacks.type.BackpackType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public class BackpackComponent {
	
	public static final Codec<BackpackComponent> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(BackpackType.CODEC.fieldOf("item").forGetter(BackpackComponent::getType), //
				ItemContainerContents.CODEC.fieldOf("contents").forGetter(BackpackComponent::getContents)) //
				.apply(instance, BackpackComponent::new);
	});
	
	public static final StreamCodec<RegistryFriendlyByteBuf, BackpackComponent> STREAM_CODEC = StreamCodec.composite( //
			BackpackType.STREAM_CODEC, BackpackComponent::getType, //
			ItemContainerContents.STREAM_CODEC, BackpackComponent::getContents, //
			BackpackComponent::new);
	
	private final BackpackType type;
	private final ItemContainerContents contents;
	
	private BackpackComponent(BackpackType type, ItemContainerContents contents) {
		this.type = type;
		this.contents = contents;
	}
	
	public BackpackComponent(BackpackType type, NonNullList<ItemStack> items) {
		this.type = type;
		this.contents = ItemContainerContents.fromItems(items);
	}
	
	public BackpackType getType() {
		return type;
	}
	
	public ItemContainerContents getContents() {
		return contents;
	}
	
}
