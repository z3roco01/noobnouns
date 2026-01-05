package z3roco01.noobnouns.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.ContainerUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.NounStore;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends PlayerLikeEntity implements ContainerUser {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void getName(CallbackInfoReturnable<Text> cir) {
        // idk if this is needed, but wtv
        if(getEntityWorld().isClient()) return;

        String name = Noobnouns.config.formattingString;

        PlayerEntity player = (PlayerEntity)(Object)this;

        // add in the actual name
        name = name.replace("%n", NounStore.getName(player));
        // add in their pronouns
        name = name.replace("%p", NounStore.getPronouns(player));

        cir.setReturnValue(Text.of(name));
    }

    @Inject(method = "getStringifiedName", at = @At("RETURN"), cancellable = true)
    private void getStringifiedName(CallbackInfoReturnable<String> cir) {
        if(getEntityWorld().isClient()) return;

        cir.setReturnValue(NounStore.getName((PlayerEntity)(Object)this));
    }

    @Inject(method = "getNameForScoreboard", at = @At("RETURN"), cancellable = true)
    private void getNameForScoreboard(CallbackInfoReturnable<String> cir) {
        if(getEntityWorld().isClient()) return;

        cir.setReturnValue(NounStore.getName((PlayerEntity)(Object)this));
    }
}
