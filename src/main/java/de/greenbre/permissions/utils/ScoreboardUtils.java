package de.greenbre.permissions.utils;

import de.greenbre.permissions.manager.Messages_Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardUtils {

    public static void sendScoreboard(Player player) {
        Scoreboard board = TablistUtils.sb;
        Objective obj = board.getObjective("aaa") != null ? board.getObjective("aaa") : board.registerNewObjective("aaa", "bbb");


        obj.setDisplayName("§6Scoreboard");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team rang = board.getTeam("rang") != null ? board.getTeam("rang") : board.registerNewTeam("rang");

        obj.getScore("").setScore(6);
        obj.getScore("§eBewerberaufgabe").setScore(5);
        obj.getScore("§6PlayLegends.net").setScore(4);
        obj.getScore(" ").setScore(3);
        obj.getScore("§7Rang:").setScore(2);
        obj.getScore("§a").setScore(1);
        obj.getScore("  ").setScore(0);

        if(rang.hasEntry("§a")) rang.removeEntry("§a");
        rang.addEntry("§a");
        if(RangUtils.getGroup(player).equals("DEFAULT")) {
            rang.setPrefix(Messages_Manager.PLAYER_NORANG_MESSAGE);
        } else {
            rang.setPrefix(MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(RangUtils.getGroup(player))[2]) + RangUtils.getGroup(player));
        }

        player.setScoreboard(board);

    }

    public static void updateScoreboard(Player player) {
        if(player.getScoreboard() != null) {
            Team rang = null;
            if(player.getScoreboard().getTeam("rang") == null) {
                rang = TablistUtils.sb.registerNewTeam("rang");
                rang.addEntry("§a");
            } else {
                rang = player.getScoreboard().getTeam("rang");
            }
            rang.setPrefix(MessageUtils.translateColorCodes(RangUtils.groupsAndPrefixes.get(RangUtils.getGroup(player))[2]) + RangUtils.getGroup(player));
        }
    }

}
