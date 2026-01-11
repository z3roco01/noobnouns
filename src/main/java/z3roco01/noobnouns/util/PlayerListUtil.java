package z3roco01.noobnouns.util;

import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import z3roco01.noobnouns.Noobnouns;

public class PlayerListUtil {
    /**
     * Updates a players name on the tab list, needed every time any change to it is made (including team)
     */
    public static void updateName(ServerPlayerEntity player) {
        // create a packet to update their displayed named in the list to everyone
        PlayerListS2CPacket packet = new PlayerListS2CPacket(PlayerListS2CPacket.Action.UPDATE_DISPLAY_NAME, player);
        Noobnouns.server.getPlayerManager().sendToAll(packet);

    }
}
