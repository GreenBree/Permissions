package de.greenbre.permissions.utils;

import de.greenbre.permissions.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.HashMap;
import java.util.List;

public class PermissionUtils {

    public static HashMap<Player, PermissionAttachment> userPerms = new HashMap<>();

    public static void putPlayer(Player player) {
        PermissionAttachment attachment = player.addAttachment(Permissions.getInstance());
        userPerms.put(player, attachment);
    }

    public static void removePlayer(Player player) {
        if(userPerms.containsKey(player)) userPerms.remove(player);
    }

    public static void setPermission(Player player, String permission) {
        if(!userPerms.containsKey(player)) putPlayer(player);
        PermissionAttachment attachment = userPerms.get(player);
        attachment.setPermission(permission, true);
    }

    public static void removePermission(Player player, String permission) {
        if(userPerms.containsKey(player)) {
            PermissionAttachment attachment = userPerms.get(player);
            attachment.unsetPermission(permission);
        }
    }

    public static void resetPermissions(Player player) {
        if(userPerms.containsKey(player)) {
            PermissionAttachment attachment = userPerms.get(player);
            for(String permission : attachment.getPermissions().keySet()) {
                attachment.setPermission(permission, false);
                attachment.unsetPermission(permission);
            }
        }
    }

    public static void setGroupPermissions(Player player, List<String> permissions) {
        resetPermissions(player);
        for(String perm : permissions) {
            setPermission(player, perm);
            System.out.println(perm);
        }
    }

    public static void removeGroupPermissions(Player player, List<String> permissions) {
        for(String perm : permissions) {
            removePermission(player, perm);
        }
    }

}
