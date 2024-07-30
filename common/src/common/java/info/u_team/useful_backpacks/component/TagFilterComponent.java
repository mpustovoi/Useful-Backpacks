package info.u_team.useful_backpacks.component;

import java.util.Optional;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagFilterComponent {
	
	public static final Codec<TagFilterComponent> CODEC = TagKey.codec(Registries.ITEM).optionalFieldOf("tag") //
			.xmap(TagFilterComponent::new, tagMatchTest -> tagMatchTest.tag).codec();
	
	public static final StreamCodec<ByteBuf, TagFilterComponent> STREAM_CODEC = ResourceLocation.STREAM_CODEC //
			.map(location -> TagKey.create(Registries.ITEM, location), TagKey::location) //
			.apply(ByteBufCodecs::optional) //
			.map(TagFilterComponent::new, component -> component.tag);
	
	public static final TagFilterComponent EMPTY = new TagFilterComponent(Optional.empty());
	
	private final Optional<TagKey<Item>> tag;
	
	private TagFilterComponent(Optional<TagKey<Item>> tag) {
		this.tag = tag;
	}
	
	public static TagFilterComponent of(TagKey<Item> tag) {
		return new TagFilterComponent(Optional.of(tag));
	}
	
	public static TagFilterComponent of(ResourceLocation tag) {
		return of(TagKey.create(Registries.ITEM, tag));
	}
	
	public boolean isPresent() {
		return tag.isPresent();
	}
	
	public TagKey<Item> getTag() {
		return tag.get();
	}
	
}
