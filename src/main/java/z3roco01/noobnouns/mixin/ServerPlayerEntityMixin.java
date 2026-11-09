package z3roco01.noobnouns.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.NounStore;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin extends Player {
    public ServerPlayerEntityMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "getTabListDisplayName", at = @At("RETURN"), cancellable = true)
    private void getPlayerListName(CallbackInfoReturnable<Component> cir) {
        if(Noobnouns.CONFIG.playerListFormat.isBlank()) {
            cir.setReturnValue(null);
            return;
        }

        String formattedName = NounStore.applyFormat(Noobnouns.CONFIG.playerListFormat, this);
        // add team colour
        Component decoratedName = PlayerTeam.formatNameForTeam(getTeam(), Component.literal(formattedName));

        cir.setReturnValue(decoratedName);
    }
}
