package de.greenbre.permissions.listener;

import de.greenbre.permissions.Permissions;
import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.RangUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.entity.Player;

public class SignChange_Listener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("permissions.use")) {
            if(e.getLine(0) != null && e.getLine(0) != "") {
                if(e.getLine(0).equalsIgnoreCase("[SHOWRANG]")) {
                    String playerName = e.getLine(1);
                    if(!RangUtils.existsUser(playerName)) {
                        p.sendMessage(Messages_Manager.SIGN_CREATE_USER_ERROR);
                        e.setCancelled(true);
                        return;
                    }
                    if(playerName != "" && playerName != null) {
                        e.setLine(0, null);
                        e.setLine(1, playerName);
                        e.setLine(2, RangUtils.getGroup(p).equals("DEFAULT") ? Messages_Manager.PLAYER_NORANG_MESSAGE : RangUtils.getGroup(Bukkit.getPlayer(playerName)));
                        e.setLine(3, null);
                        p.sendMessage();
                    } else {
                        p.sendMessage(Messages_Manager.SIGN_CREATE_USER_ERROR);
                    }
                }
            }
        } else {
            p.sendMessage(Messages_Manager.NOPERMISSION);
            e.setCancelled(true);
        }
    }

}
