package z3roco01.noobnouns.mixin;

import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;

@Mixin(Scoreboard.class)
public abstract class ScoreboardMixin {
    @Inject(method = "addScoreHolderToTeam", at = @At("TAIL"))
    private void addScoreHolderToTeam(String scoreHolderName, Team team, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayerEntity player = Noobnouns.server.getPlayerManager().getPlayer(scoreHolderName);

        // cant do anything with a null player
        if(player == null) return;

        // create a packet to update their displayed named in the list to everyone
        PlayerListS2CPacket packet = new PlayerListS2CPacket(PlayerListS2CPacket.Action.UPDATE_DISPLAY_NAME, player);
        Noobnouns.server.getPlayerManager().sendToAll(packet);
    }
}
