package info.u_team.useful_backpacks.type;

import java.util.function.IntFunction;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Rarity;

// TODO move from enum
public enum BackpackType implements StringRepresentable {
	
	SMALL(0, "small", Rarity.COMMON, 5, 3, 44, 24, 8, 82, 176, 164),
	MEDIUM(1, "medium", Rarity.UNCOMMON, 9, 6, 8, 24, 8, 136, 176, 218),
	LARGE(2, "large", Rarity.RARE, 13, 9, 8, 24, 44, 190, 248, 272);
	
	private static final IntFunction<BackpackType> BY_ID = ByIdMap.continuous(BackpackType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	
	public static final Codec<BackpackType> CODEC = StringRepresentable.fromValues(BackpackType::values);
	public static final StreamCodec<ByteBuf, BackpackType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, BackpackType::getId);
	
	private final int id;
	private final String name;
	private final Rarity rarity;
	private final int inventoryWidth, inventoryHeight;
	private final int slotBackpackX, slotBackpackY;
	private final int slotPlayerX, slotPlayerY;
	private final int textureSizeX, textureSizeY;
	
	private BackpackType(int id, String name, Rarity rarity, int inventoryWidth, int inventoryHeight, int slotBackpackX, int slotBackpackY, int slotPlayerX, int slotPlayerY, int textureSizeX, int textureSizeY) {
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.inventoryWidth = inventoryWidth;
		this.inventoryHeight = inventoryHeight;
		this.slotBackpackX = slotBackpackX;
		this.slotBackpackY = slotBackpackY;
		this.slotPlayerX = slotPlayerX;
		this.slotPlayerY = slotPlayerY;
		this.textureSizeX = textureSizeX;
		this.textureSizeY = textureSizeY;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getSerializedName() {
		return name;
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	
	public int getInventoryWidth() {
		return inventoryWidth;
	}
	
	public int getInventoryHeight() {
		return inventoryHeight;
	}
	
	public int getInventorySize() {
		return inventoryWidth * inventoryHeight;
	}
	
	public int getSlotBackpackX() {
		return slotBackpackX;
	}
	
	public int getSlotBackpackY() {
		return slotBackpackY;
	}
	
	public int getSlotPlayerX() {
		return slotPlayerX;
	}
	
	public int getSlotPlayerY() {
		return slotPlayerY;
	}
	
	public int getTextureSizeX() {
		return textureSizeX;
	}
	
	public int getTextureSizeY() {
		return textureSizeY;
	}
}
