package de.greenbre.permissions.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class TablistUtils {

    public static Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();

    public static void registerScoreboard() {
        for(String groups : RangUtils.groupsAndPrefixes.keySet()) {
            String[] values = RangUtils.groupsAndPrefixes.get(groups);
            String prefix = values[0];
            String weighting = values[3];
            String teamName = weighting+groups;
            sb.registerNewTeam(teamName);
            sb.getTeam(teamName).setPrefix(MessageUtils.translateColorCodes(prefix));
        }
    }

    public static void setPrefix(Player player) {
        String group = RangUtils.getGroup(player);
        String[] values = RangUtils.groupsAndPrefixes.get(group);
        String teamName = values[3]+group;
        sb.getTeam(teamName).addPlayer(player);
        sb.getTeam(teamName).setDisplayName(player.getName());
        sb.getTeam(teamName).setColor(ChatColor.GRAY);
        for(Player a : Bukkit.getOnlinePlayers()) a.setScoreboard(sb);
    }

}
