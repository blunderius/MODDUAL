package net.votion.votioncraftmod.block.entity;

import net.votion.votioncraftmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.votion.votioncraftmod.screen.EnhancementStationMenu;
import org.jetbrains.annotations.NotNull;

public class EnhancementStationEntity extends BlockEntity implements MenuProvider{
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    private static final int HEAD_SLOT = 0;
    private static final int LEFT_ARM_SLOT = 1;
    private static final int TORSO_SLOT = 2;
    private static final int RIGHT_ARM_SLOT = 3;
    private static final int LEFT_LEG_SLOT = 4;
    private static final int RIGHT_LEG_SLOT = 5;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int head = -1;
    private int leftArm = -1;
    private int torso = -1;
    private int rightArm = -1;
    private int leftLeg = -1;
    private int rightLeg = -1;

    public EnhancementStationEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENHANCEMENT_STATION_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> EnhancementStationEntity.this.head;
                    case 1 -> EnhancementStationEntity.this.leftArm;
                    case 2 -> EnhancementStationEntity.this.torso;
                    case 3 -> EnhancementStationEntity.this.rightArm;
                    case 4 -> EnhancementStationEntity.this.leftLeg;
                    case 5 -> EnhancementStationEntity.this.rightLeg;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> EnhancementStationEntity.this.head = pIndex;
                    case 1 -> EnhancementStationEntity.this.leftArm = pValue;
                    case 2 -> EnhancementStationEntity.this.torso = pValue;
                    case 3 -> EnhancementStationEntity.this.rightArm = pValue;
                    case 4 -> EnhancementStationEntity.this.leftLeg = pValue;
                    case 5 -> EnhancementStationEntity.this.rightLeg = pValue;
                }

            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public Component getDisplayName() {
        return Component.translatable("block.votioncraftmod.enhancement_station");
    }

    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {

        return new EnhancementStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }



    /*@Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("enhancement_station.progress");
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.PowerOrb.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.BasicGear.get();
        ItemStack result = new ItemStack(ModItems.PowerOrb.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
*/}
