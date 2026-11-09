package z3roco01.noobnouns.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.NounStore;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends Avatar implements ContainerUser {
    @Shadow
    protected abstract MutableComponent decorateDisplayNameComponent(MutableComponent nameComponent);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void getName(CallbackInfoReturnable<Component> cir) {
        // idk if this is needed, but wtv
        if(level().isClientSide()) return;

        String formattedName = NounStore.applyFormat(Noobnouns.CONFIG.formattingString, (Player)(Object)this);

        cir.setReturnValue(Component.literal(formattedName));
    }

    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void getDisplayName(CallbackInfoReturnable<Component> cir) {
        String formattedName = NounStore.applyFormat(Noobnouns.CONFIG.nametagFormat, (Player)(Object)this);

        cir.setReturnValue(decorateDisplayNameComponent(PlayerTeam.formatNameForTeam(getTeam(), Component.literal(formattedName))));
        cir.cancel();
    }
}
