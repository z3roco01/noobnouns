package z3roco01.noobnouns.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.util.PlayerListUtil;

@Mixin(Scoreboard.class)
public abstract class ScoreboardMixin {
    @Inject(method = "addPlayerToTeam", at = @At("TAIL"))
    private void addScoreHolderToTeam(String player, PlayerTeam team, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayer serverPlayer = Noobnouns.server.getPlayerList().getPlayer(player);

        // cant do anything with a null player
        if(serverPlayer == null) return;

        PlayerListUtil.updateName(serverPlayer);
    }
}
