package de.greenbre.permissions.listener;

import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.MessageUtils;
import de.greenbre.permissions.utils.PermissionUtils;
import de.greenbre.permissions.utils.RangUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit_Listener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PermissionUtils.removePlayer(p);
        RangUtils.resetPlayer(p);
        e.setQuitMessage(MessageUtils.translatePlayername(Messages_Manager.PLAYER_QUIT_MESSAGE, p));
    }

}
