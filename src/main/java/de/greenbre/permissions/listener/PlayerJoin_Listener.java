package de.greenbre.permissions.listener;

import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin_Listener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PermissionUtils.putPlayer(p);
        RangUtils.getPlayerPermissions(p);
        ScoreboardUtils.sendScoreboard(p);
        TablistUtils.setPrefix(p);
        e.setJoinMessage(MessageUtils.translatePlayername(Messages_Manager.PLAYER_JOIN_MESSAGE, p));
    }
}
