package net.votion.votioncraftmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.votion.votioncraftmod.VotionCraftMod;
import net.votion.votioncraftmod.item.ModItems;

public class OrbThrowerItem  extends Item {
    public OrbThrowerItem(Properties pProperties) {
        super(pProperties);

    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = new ItemStack(ModItems.OrbThrower.get());
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 0.5F, 0.8F );
        if (!pLevel.isClientSide) {
            if (!pPlayer.getAbilities().instabuild && pPlayer.getInventory().countItem(ModItems.PowerOrb.get())>0) {


            Snowball snowball = new Snowball(pLevel, pPlayer){
                private ServerLevel level = (ServerLevel) pLevel;

                @Override
                protected void onHit(HitResult pResult){
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);
                }
            };
            snowball.setItem(new ItemStack(ModItems.PowerOrb.get()));

            snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2F, 0.0F);
            pLevel.addFreshEntity(snowball);
            pPlayer.getInventory().removeItem(pPlayer.getInventory().findSlotMatchingItem(new ItemStack(ModItems.PowerOrb.get())),1);


            } else if (pPlayer.getAbilities().instabuild){
                Snowball snowball = new Snowball(pLevel, pPlayer){
                    private ServerLevel level = (ServerLevel) pLevel;

                    @Override
                    protected void onHit(HitResult pResult){
                        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);
                    }
                };
                snowball.setItem(new ItemStack(ModItems.PowerOrb.get()));
                snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2F, 0.0F);
                pLevel.addFreshEntity(snowball);
            }
        }




        return InteractionResultHolder.success(itemstack);
    }


}


