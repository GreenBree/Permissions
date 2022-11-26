package de.greenbre.permissions.commands;

import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.MessageUtils;
import de.greenbre.permissions.utils.RangUtils;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rang_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Messages_Manager.WRONG_SENDER);
            return true;
        }
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("rang")) {
            if(args.length == 0) {
                String groupName = RangUtils.getGroup(p);
                String[] groupInfo = RangUtils.groupsAndPrefixes.get(groupName);
                if(groupName.equals("DEFAULT")) {
                    p.sendMessage(Messages_Manager.CMD_RANG_NORANG);
                    return true;
                }
                p.sendMessage(Messages_Manager.CMD_RANG_MESSAGE.replace("%GROUP%", MessageUtils.translateColorCodes(groupInfo[2])+groupName));
                if(Boolean.parseBoolean(RangUtils.playerOfflinePermissions.get(p)[1])) {
                    String date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date(Long.parseLong(RangUtils.playerOfflinePermissions.get(p)[2])));
                    p.sendMessage(Messages_Manager.CMD_RANG_TIMEMSG.replace("%DATE%", date));
                }
            } else {
                p.sendMessage(Messages_Manager.SYNTAXERROR+"");
                return true;
            }
        }
        return false;
    }
}
