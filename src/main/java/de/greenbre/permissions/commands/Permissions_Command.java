package de.greenbre.permissions.commands;

import de.greenbre.permissions.manager.Config_Manager;
import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.*;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Permissions_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("permissions")) {
            if(sender.hasPermission("permissions.use")) {
                if(args.length == 0) {
                    MessageUtils.sendHelpMessage(sender);
                    return true;
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("reloadmessages")) {
                        Messages_Manager.readMessagesFile(Config_Manager.LANGUAGE);
                        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_RELOADMESSAGES.replace("%LANGUAGE%", Config_Manager.LANGUAGE));
                        return true;
                    } else if(args[0].equalsIgnoreCase("help")) {
                        MessageUtils.sendHelpMessage(sender);
                    } else {
                        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_HELP_HELP);
                        return true;
                    }
                } else if(args.length == 3) {
                    if(args[0].equalsIgnoreCase("group")) {
                        if(args[1].equalsIgnoreCase("delete")) {
                            String groupname = args[2];
                            RangUtils.deleteGroup(groupname);
                            sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_DELETE.replace("%GROUP%",groupname)));
                        } else {
                            MessageUtils.sendHelpMessage(sender);
                            return true;
                        }
                    } else if(args[0].equalsIgnoreCase("user")) {
                        if(args[1].equalsIgnoreCase("removegroup")) {
                            String playerName = args[2];
                            RangUtils.setGroup(playerName, "DEFAULT");
                            if (Bukkit.getPlayer(playerName) != null) {
                                Player target = Bukkit.getPlayer(playerName);
                                RangUtils.updatePlayer(target);
                                target.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG.replace("%GROUP%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get("DEFAULT")[2])+"DEFAULT"));
                            }
                            sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_REMOVEGROUP.replace("%TARGET%", playerName));
                        } else {
                            MessageUtils.sendHelpMessage(sender);
                            return true;
                        }
                    } else {
                        MessageUtils.sendHelpMessage(sender);
                        return true;
                    }
                } else if(args.length >= 3) {
                    if(args[0].equalsIgnoreCase("group")) {
                        if (args[1].equalsIgnoreCase("setprefix")) {
                            String groupname = args[2];
                            String prefix = "";
                            for(int i = 3; i < args.length; i++) prefix = prefix + args[i] + " ";
                            prefix = prefix.substring(0, prefix.length()-1);
                            RangUtils.setPrefix(groupname, prefix);
                            sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_SETPREFIX.replace("%GROUP%",groupname).replace("%GROUPPREFIX%",prefix)));
                        }
                    }
                    if(args.length == 4) {
                        if (args[0].equalsIgnoreCase("group")) {
                            if (args[1].equalsIgnoreCase("addpermission")) {
                                String groupname = args[2];
                                String permission = args[3];
                                RangUtils.addPermission(groupname, permission);
                                sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_ADDPERMISSION.replace("%GROUP%",groupname).replace("%PERMISSION%",permission)));
                            } else if (args[1].equalsIgnoreCase("removepermission")) {
                                String groupname = args[2];
                                String permission = args[3];
                                RangUtils.removePermission(groupname, permission);
                                sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_REMOVEPERMISSION.replace("%GROUP%",groupname).replace("%PERMISSION%",permission)));
                            } else if(args[1].equalsIgnoreCase("setcolor")) {
                                String groupname = args[2];
                                String colorCode = args[3];
                                RangUtils.setColorCode(groupname, colorCode);
                                sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_SETCOLOR.replace("%GROUP%", groupname).replace("%COLOR%", colorCode)));
                            } else if(args[1].equalsIgnoreCase("setweight")) {
                                String groupname = args[2];
                                if(!args[3].matches("[+-]?\\d*(\\.\\d+)?")) {
                                    sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_SETWEIGHTING_NONUMERIC.replace("%GROUP%",groupname).replace("%INPUT%",args[3])));
                                    return true;
                                }
                                String weight = args[3];
                                RangUtils.setWeighting(groupname, weight);
                                sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_SETWEIGHTING_MESSAGE.replace("%GROUP%",groupname).replace("%WEIGHT%",weight)));
                            } else {
                                MessageUtils.sendHelpMessage(sender);
                                return true;
                            }
                        } else if (args[0].equalsIgnoreCase("user")) {
                            if (args[1].equalsIgnoreCase("setgroup")) {
                                String playerName = args[2];
                                String group = args[3];
                                if(RangUtils.existsUser(playerName)) {
                                    if (RangUtils.existsGroup(group)) {
                                        RangUtils.setGroup(playerName, group);
                                        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP.replace("%GROUP%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+group).replace("%TARGET%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+playerName));
                                        if (Bukkit.getPlayer(playerName) != null) {
                                            Player target = Bukkit.getPlayer(playerName);
                                            RangUtils.updatePlayer(target);
                                            target.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG.replace("%GROUP%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+group));
                                        }
                                    } else {
                                        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_GROUPNOTEXISTS.replace("%GROUP%", group));
                                    }
                                } else {
                                    sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_USERNOTEXISTS.replace("%TARGET%", playerName));
                                }
                            } else {
                                MessageUtils.sendHelpMessage(sender);
                                return true;
                            }
                        } else {
                            MessageUtils.sendHelpMessage(sender);
                            return true;
                        }
                    }
                    if(args.length >= 5) {
                        if(args[0].equalsIgnoreCase("group")) {
                            if(args[1].equalsIgnoreCase("create")) {
                                String groupname = args[2];
                                String colorcode = args[3];
                                String weighting = args[4];
                                String prefix = "";
                                for(int i = 5; i < args.length; i++) prefix = prefix + args[i] + " ";
                                RangUtils.createGroup(groupname, prefix, colorcode, Integer.parseInt(weighting));
                                sender.sendMessage(MessageUtils.translatePrefix(Messages_Manager.CMD_PERMISSIONS_GROUP_CREATE.replace("%GROUP%",groupname)));
                            }
                        } else if(args[0].equalsIgnoreCase("user")) {
                            if (args[1].equalsIgnoreCase("setgrouptemp")) {
                                String playerName = args[2];
                                String group = args[3];
                                boolean temp = true;
                                long timeNext = System.currentTimeMillis() / 1000;
                                String timeCode = "";
                                for(int i = 4; i < args.length; i++) timeCode = timeCode + args[i] + " ";
                                for(String tC : timeCode.split(" ")) {
                                    if(tC.length() >= 2) {
                                        String[] tCArgs = tC.split("");
                                        String timeValueStr = "";
                                        for(int i = 0; i < tCArgs.length-1; i++) {
                                            timeValueStr = timeValueStr + tCArgs[i];
                                        }
                                        int timeValue = Integer.parseInt(timeValueStr);
                                        if(timeValue != 0) {
                                            if(tCArgs[tCArgs.length-1].equals("s") || tCArgs[tCArgs.length-1].equals("m") || tCArgs[tCArgs.length-1].equals("h") || tCArgs[tCArgs.length-1].equals("d")) {
                                                switch(tCArgs[tCArgs.length-1]) {
                                                    case "s":
                                                        timeNext = timeNext+(timeValue);
                                                        break;
                                                    case "m":
                                                        timeNext = timeNext+(timeValue*60);
                                                        break;
                                                    case "h":
                                                        timeNext = timeNext+((timeValue*60)*60);
                                                        break;
                                                    case "d":
                                                        timeNext = timeNext+((((timeValue*24)*60)*60));
                                                        break;
                                                }
                                            } else {
                                                sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_SYNTAX);
                                                return true;
                                            }
                                        }
                                    } else {
                                        sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUP_SYNTAX);
                                        return true;
                                    }
                                }
                                long expiryTime = timeNext*1000;
                                RangUtils.setGroupTemp(playerName, group, expiryTime);
                                String dateStr = "";
                                Date date = new Date(expiryTime);
                                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                                dateStr = dateFormat.format(date);
                                sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUPTEMP.replace("%GROUP%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+group).replace("%TARGET%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+playerName).replace("%DATE%", dateStr));
                                if (Bukkit.getPlayer(playerName) != null) {
                                    Player target = Bukkit.getPlayer(playerName);
                                    RangUtils.updatePlayer(target);
                                    target.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUPTEMP_TARGETMSG.replace("%GROUP%", MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(group)[2])+group).replace("%DATE%", dateStr));
                                }
                            }
                        } else {
                            sender.sendMessage(Messages_Manager.CMD_PERMISSIONS_USER_SETGROUPTEMP_SYNTAX);
                            return true;
                        }
                    }
                }
            } else {
                sender.sendMessage(Messages_Manager.NOPERMISSION);
                return true;
            }
        }
        return false;
    }

}
