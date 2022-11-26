package de.greenbre.permissions.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RangUtils {

    public static HashMap<Player, String[]> playerOfflinePermissions = new HashMap<>();
    public static HashMap<String, String[]> groupsAndPrefixes = new HashMap<>();

    public static void getAllGroups() {
        groupsAndPrefixes.clear();
        ResultSet rs = MySQLConnector.getResult("SELECT * FROM `PermissionsGroups`");
        try {
            while(rs.next()) {
                groupsAndPrefixes.put(rs.getString("Groupname"), new String[] {rs.getString("Prefix"),rs.getString("Permissions"), rs.getString("ColorCode"), rs.getString("Weighting")});
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaultGroups() {
        ResultSet rs = null;
        try {
            if(!(rs = MySQLConnector.getResult("SELECT * FROM `PermissionsGroups` WHERE Groupname='DEFAULT';")).next()) {
                MySQLConnector.update("INSERT INTO `PermissionsGroups` (`Groupname`, `Prefix`, `ColorCode`, `Weighting`, `Permissions`) VALUES ('DEFAULT', '&e', '&e', '1000', '');");
            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getGroup(Player player) {
        if (playerOfflinePermissions.containsKey(player)) {
            String[] permArgs = playerOfflinePermissions.get(player);
            return permArgs[0];
        } else {
            return "DEFAULT";
        }
    }

    public static List<String> getGroupPermissions(String group) {
        List<String> list = new ArrayList<>();
        for(String s : groupsAndPrefixes.get(group)[1].split(",")) {
            list.add(s);
        }
        return list;
    }

    public static void addPermission(String group, String permission) {
        List<String> permissions = getGroupPermissions(group);
        permissions.add(permission);
        String perm = "";
        for(String s : permissions) {
            perm+= s+",";
        }
        groupsAndPrefixes.put(group, new String[] {groupsAndPrefixes.get(group)[0], perm, groupsAndPrefixes.get(group)[2], groupsAndPrefixes.get(group)[3]});
        MySQLConnector.update("UPDATE `PermissionsGroups` SET `Permissions`='"+perm+"' WHERE Groupname='"+group+"';");
        for(Player a : Bukkit.getOnlinePlayers()) {
            getPlayerPermissions(a);
        }
    }

    public static void removePermission(String group, String permission) {
        List<String> permissions = getGroupPermissions(group);
        if(permissions.contains(permission)) permissions.remove(permission);
        String perm = "";
        for(String s : permissions) {
            perm+= s+",";
        }
        groupsAndPrefixes.put(group, new String[] {groupsAndPrefixes.get(group)[0], perm, groupsAndPrefixes.get(group)[2], groupsAndPrefixes.get(group)[3]});
        MySQLConnector.update("UPDATE `PermissionsGroups` SET `Permissions`='"+perm+"' WHERE Groupname='"+group+"';");
        for(Player a : Bukkit.getOnlinePlayers()) {
            getPlayerPermissions(a);
        }
    }

    public static boolean existsUser(Player player) {
        try {
            return MySQLConnector.getResult("SELECT `Username` FROM `Permissions` WHERE UUID='"+player.getUniqueId().toString()+"';").next();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existsUser(String playerName) {
        try {
            return MySQLConnector.getResult("SELECT `Username` FROM `Permissions` WHERE Username='"+playerName+"';").next();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existsGroup(String group) {
        if(groupsAndPrefixes.containsKey(group)) return true;
        return false;
    }

    public static void getPlayerPermissions(Player player) {
        if(!existsUser(player)) {
            createUser(player);
        }
        ResultSet rs = MySQLConnector.getResult("SELECT * FROM `Permissions` WHERE UUID='"+player.getUniqueId().toString()+"';");
        String username = "";
        String group = "";
        String prefix = "";
        boolean temp = false;
        long time = 0;
        try {
            while(rs.next()) {
                username = rs.getString("Username");
                group = rs.getString("Groupname");
                temp = rs.getBoolean("PermissionTemp");
                time = Long.parseLong(rs.getString("PermissionsTime"));
            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if(!username.equalsIgnoreCase(player.getName())) {
            updateUsername(player);
        }
        if(temp && System.currentTimeMillis() >= time) {
            group = "DEFAULT";
            setGroup(player.getName(), "DEFAULT");
        }
        playerOfflinePermissions.put(player, new String[] {group, String.valueOf(temp), String.valueOf(time)});
        prefix = groupsAndPrefixes.get(playerOfflinePermissions.get(player)[0])[0];
        PermissionUtils.setGroupPermissions(player, getGroupPermissions(getGroup(player)));
        player.setDisplayName(MessageUtils.translateColorCodes(prefix+player.getName()));
    }

    public static void setGroup(String playername, String group) {
        MySQLConnector.update("UPDATE `Permissions` SET `Groupname`='"+group+"', `PermissionTemp`='0', `PermissionsTime`='0' WHERE Username='"+playername+"'");
    }

    public static void setGroupTemp(String playername, String group, long time) {
        MySQLConnector.update("UPDATE `Permissions` SET `Groupname`='"+group+"', `PermissionTemp`='1', `PermissionsTime`='"+time+"' WHERE Username='"+playername+"'");
    }

    public static void resetPlayer(Player player) {
        if(playerOfflinePermissions.containsKey(player)) playerOfflinePermissions.remove(player);
    }

    public static void updatePlayer(Player player) {
        RangUtils.resetPlayer(player);
        RangUtils.getPlayerPermissions(player);
        ScoreboardUtils.updateScoreboard(player);
        TablistUtils.setPrefix(player);
    }

    public static void createUser(Player player) {
        String username = player.getName();
        String uuid = player.getUniqueId().toString();
        String group = "DEFAULT";
        boolean temp = false;
        long time = 0;
        MySQLConnector.update("INSERT INTO `Permissions` (`Username`, `UUID`, `Groupname`, `PermissionTemp`, `PermissionsTime`) VALUES ('"+username+"', '"+uuid+"', '"+group+"', '"+((temp) ? "1" : "0")+"', '"+time+"');");
    }

    public static void createGroup(String groupname, String prefix, String colorcode, int weighting) {
        MySQLConnector.update("INSERT INTO `PermissionsGroups` (`Groupname`, `Prefix`, `ColorCode`, `Weighting`, `Permissions`) VALUES ('"+groupname+"', '"+prefix+"', '"+colorcode+"', '"+weighting+"', '');");
        groupsAndPrefixes.put(groupname, new String[] {prefix,"", colorcode, String.valueOf(weighting)});
    }

    public static void deleteGroup(String groupname) {
        MySQLConnector.update("DELETE FROM `PermissionsGroups` WHERE Groupname='"+groupname+"';");
        groupsAndPrefixes.remove(groupname);
    }

    public static void updateUsername(Player player) {
        MySQLConnector.update("UPDATE `Permissions` SET `Username`='"+player.getName()+"' WHERE UUID='"+player.getUniqueId().toString()+"';");
    }

    public static void setPrefix(String groupname, String prefix) {
        MySQLConnector.update("UPDATE `PermissionsGroups` SET `Prefix`='"+prefix+"' WHERE Groupname='"+groupname+"';");
        groupsAndPrefixes.put(groupname, new String[] {prefix,groupsAndPrefixes.get(groupname)[1], groupsAndPrefixes.get(groupname)[2], groupsAndPrefixes.get(groupname)[3]});
    }

    public static void setColorCode(String groupname, String colorCode) {
        MySQLConnector.update("UPDATE `PermissionsGroups` SET `ColorCode`='"+colorCode+"' WHERE Groupname='"+groupname+"';");
        groupsAndPrefixes.put(groupname, new String[] {groupsAndPrefixes.get(groupname)[0],groupsAndPrefixes.get(groupname)[1], colorCode, groupsAndPrefixes.get(groupname)[3]});
    }

    public static void setWeighting(String groupname, String weigth) {
        MySQLConnector.update("UPDATE `PermissionsGroups` SET `Weighting`='"+weigth+"' WHERE Groupname='"+groupname+"';");
        groupsAndPrefixes.put(groupname, new String[] {groupsAndPrefixes.get(groupname)[0],groupsAndPrefixes.get(groupname)[1], groupsAndPrefixes.get(groupname)[2], weigth});
    }

}
