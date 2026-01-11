package z3roco01.noobnouns.mixin;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.util.PlayerListUtil;

@Mixin(Scoreboard.class)
public abstract class ScoreboardMixin {
    @Inject(method = "addScoreHolderToTeam", at = @At("TAIL"))
    private void addScoreHolderToTeam(String scoreHolderName, Team team, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayerEntity player = Noobnouns.server.getPlayerManager().getPlayer(scoreHolderName);

        // cant do anything with a null player
        if(player == null) return;

        PlayerListUtil.updateName(player);
    }
}
