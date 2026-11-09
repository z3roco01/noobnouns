package z3roco01.noobnouns.util;

import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import z3roco01.noobnouns.Noobnouns;

public class PlayerListUtil {
    /**
     * Updates a players name on the tab list, needed every time any change to it is made (including team)
     */
    public static void updateName(ServerPlayer player) {
        // create a packet to update their displayed named in the list to everyone
        ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME, player);
        Noobnouns.server.getPlayerList().broadcastAll(packet);

    }
}
