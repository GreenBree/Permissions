package de.greenbre.permissions.utils;

import de.greenbre.permissions.manager.Messages_Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String translatePrefix(String message) {
        return translateColorCodes(message.replace("%PREFIX%", Messages_Manager.PREFIX)).replace("%SYNTAX%", Messages_Manager.SYNTAXERROR);
    }

    public static String translatePlayername(String message, Player player) {
        return translatePrefix(message.replace("%PLAYER%", player.getDisplayName()));
    }

    public static void sendHelpMessage(CommandSender sender, int i) {
        sender.sendMessage("Zahl: "+i);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SPACE1);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_CREATE);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_DELETE);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_ADDPERMISSION);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_REMOVEPERMISSIONS);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SETPREFIX);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SETCOLOR);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SETWEIGHT);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SETGROUP);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SETGROUPTEMP);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_REMOVEGROUP);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_HELP);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_RANG);
        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_SPACE2);
    }

}
