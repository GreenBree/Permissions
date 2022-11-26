package de.greenbre.permissions.manager;

import de.greenbre.permissions.utils.MessageUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages_Manager {

    public static String PREFIX = "&7[&cPermissions&7]";
    public static String SYNTAXERROR = "%PREFIX% &cSyntax:";
    public static String NOPERMISSION = "%PREFIX% &cDu hast keine Berechtigung um diesen Befehl auszufuehren!";

    public static String PLAYER_JOIN_MESSAGE = "%PREFIX% &7Der Spieler &e%PLAYER% &7hat das Spiel betreten!";
    public static String PLAYER_QUIT_MESSAGE = "%PREFIX% &7Der Spieler &e%PLAYER% &7hat das Spiel verlassen!";
    public static String PLAYER_NORANG_MESSAGE = "&ckein Rang";
    public static String SIGN_CREATE = "%PREFIX% &7Das Rangschild mit dem Spieler &e%PLAYER% &7wurde erstellt!";
    public static String SIGN_CREATE_USER_ERROR = "%PREFIX% &7Der Spieler &e%PLAYER% &7war noch nicht auf dem Server!";
    public static String WRONG_SENDER = "%PREFIX% &7Du musst ein Spieler sein, um diesen Befehl zu nutzen!";
    public static String CMD_PERMISSIONS_HELP_SPACE1 = "%PREFIX% &7-----------------------------------------";
    public static String CMD_PERMISSIONS_HELP_CREATE = "%PREFIX% &e/permissions group create <Group> <Color> <Wight> [Prefix]";
    public static String CMD_PERMISSIONS_HELP_DELETE = "%PREFIX% &e/permissions group delete <Group>";
    public static String CMD_PERMISSIONS_HELP_ADDPERMISSION = "%PREFIX% &e/permissions group addpermission <Group> <Permissions>!";
    public static String CMD_PERMISSIONS_HELP_REMOVEPERMISSIONS = "%PREFIX% &e/permissions group removepermission <Group> <Permissions>";
    public static String CMD_PERMISSIONS_HELP_SETPREFIX = "%PREFIX% &e/permissions group setprefix <Group> <Prefix>";
    public static String CMD_PERMISSIONS_HELP_SETCOLOR = "%PREFIX% &e/permissions group setcolor <Group> <Color>";
    public static String CMD_PERMISSIONS_HELP_SETWEIGHT = "%PREFIX% &e/permissions group setweight <Group> <Weight>";
    public static String CMD_PERMISSIONS_HELP_SETGROUP = "%PREFIX% &e/permissions user setgroup <Player> <Group>";
    public static String CMD_PERMISSIONS_HELP_SETGROUPTEMP = "%PREFIX% &e/permissions user setgrouptemp <Player> <Group> [Time (4d 3h 2m 1s)]";
    public static String CMD_PERMISSIONS_HELP_REMOVEGROUP = "%PREFIX% &e/permissions user removegroup <Player>";
    public static String CMD_PERMISSIONS_HELP_HELP = "%PREFIX% &e/permissions help";
    public static String CMD_PERMISSIONS_HELP_RANG = "%PREFIX% &e/rang";
    public static String CMD_PERMISSIONS_HELP_SPACE2 = "%PREFIX% &7-----------------------------------------";
    public static String CMD_PERMISSIONS_RELOADMESSAGES = "%PREFIX% &7Du hast die Sprache '&e%LANGUAGE%&7' aktualisiert!";
    public static String CMD_PERMISSIONS_GROUP_DELETE = "%PREFIX% &7Du hast die Gruppe &e%GROUP%&7 gelöscht!";
    public static String CMD_PERMISSIONS_GROUP_CREATE = "%PREFIX% &7Du hast die Gruppe &e%GROUP%&7 erstellt!";
    public static String CMD_PERMISSIONS_GROUP_ADDPERMISSION = "%PREFIX% &7Du hast der Gruppe &e%GROUP%&7 die Berechtigung '&e%PERMISSION%&7' hinzugefuegt!";
    public static String CMD_PERMISSIONS_GROUP_REMOVEPERMISSION = "%PREFIX% &7Du hast der Gruppe &e%GROUP%&7 die Berechtigung '&e%PERMISSION%&7' entfernt!";
    public static String CMD_PERMISSIONS_GROUP_SETPREFIX = "%PREFIX% &7Du hast der Gruppe &e%GROUP%&7 den Prefix '&e%GROUPPREFIX%&7' gesetzt!";
    public static String CMD_PERMISSIONS_GROUP_SETCOLOR = "%PREFIX% &7Du hast der Gruppe &e%GROUP%&7 den Farbcode '&e%COLOR%&7' zugewiesen!";
    public static String CMD_PERMISSIONS_GROUP_SETWEIGHTING_MESSAGE = "%PREFIX% &7Du hast der Gruppe &e%GROUP%&7 die Gewichtung '&e%WEIGHT%&7' zugewiesen!";
    public static String CMD_PERMISSIONS_GROUP_SETWEIGHTING_NONUMERIC = "%PREFIX% &7Die Eingabe '&e%INPUT%&7' ist keine Zahl!";
    public static String CMD_PERMISSIONS_USER_SETGROUP = "%PREFIX% &7Du hast dem Spieler '&e%TARGET%&7' die Gruppe &e%GROUP%&7 zugewiesen!";
    public static String CMD_PERMISSIONS_USER_REMOVEGROUP = "%PREFIX% &7Du hast dem Spieler '&e%TARGET%&7' die Standardgruppe zugewiesen!";
    public static String CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG = "%PREFIX% &7Du hast die Gruppe &e%GROUP%&7 zugewiesen bekommen!";
    public static String CMD_PERMISSIONS_USER_SETGROUP_GROUPNOTEXISTS = "%PREFIX% &7Die Gruppe '&e%GROUP%&7' existiert nicht!";
    public static String CMD_PERMISSIONS_USER_SETGROUP_USERNOTEXISTS = "%PREFIX% &7Der Spieler '&e%TARGET%&7' existiert nicht!";
    public static String CMD_PERMISSIONS_USER_SETGROUP_SYNTAX = "%PREFIX% %SYNTAX% &7/permissions user setgroup <Spieler> <Gruppe>";
    public static String CMD_PERMISSIONS_USER_SETGROUPTEMP = "%PREFIX% &7Du hast dem Spieler '&e%TARGET%&7' temporaer die Gruppe &e%GROUP%&7 bis zum &e%DATE%&7 zugewiesen!";
    public static String CMD_PERMISSIONS_USER_SETGROUPTEMP_TARGETMSG = "%PREFIX% &7Du hast temporaer die Gruppe &e%GROUP%&7 bis zum &e%DATE%&7 zugewiesen bekommen!";
    public static String CMD_PERMISSIONS_USER_SETGROUPTEMP_GROUPNOTEXISTS = "%PREFIX% &7Die Gruppe '&e%GROUP%&7' existiert nicht!";
    public static String CMD_PERMISSIONS_USER_SETGROUPTEMP_USERNOTEXISTS = "%PREFIX% &7Der Spieler '&e%TARGET%&7' existiert nicht!";
    public static String CMD_PERMISSIONS_USER_SETGROUPTEMP_SYNTAX = "%PREFIX% %SYNTAX% &7/permissions user setgrouptemp <Spieler> <Gruppe> [Zeit (Format: 1d)]";
    public static String CMD_RANG_MESSAGE = "%PREFIX% &7Dein aktueller Rang ist &e%GROUP%&7!";
    public static String CMD_RANG_NORANG = "%PREFIX% &7Du besitzt aktuell &ckeine&7 Gruppe!";
    public static String CMD_RANG_TIMEMSG = "%PREFIX% &7Diese besitzt du bis zum &e%DATE%&7!";


    private static Messages_Manager instance;

    public Messages_Manager() {
        instance = this;
        getMessagesFile("de");
        setStandartFiles();
        readMessagesFile("de");
    }

    public static Messages_Manager getInstance() {
        return instance;
    }

    public static File getMessagesFile(String language) {
        switch (language.toLowerCase()) {
            case "de":
                return new File("plugins/Permissions/languages", "de.yml");
            case "en":
                return new File("plugins/Permissions/languages", "en.yml");
            default:
                return new File("plugins/Permissions/languages", "en.yml");
        }
    }

    public static YamlConfiguration getYamlConfig(File messageFile) {
        return YamlConfiguration.loadConfiguration(messageFile);
    }

    public static void setStandartFiles() {
        //German
        YamlConfiguration yamlConfigurationDe = getYamlConfig(getMessagesFile("de"));
        yamlConfigurationDe.options().copyDefaults(true);
        yamlConfigurationDe.addDefault("PLUGIN.PREFIX", PREFIX);
        yamlConfigurationDe.addDefault("PLUGIN.SYNTAXERROR", SYNTAXERROR);
        yamlConfigurationDe.addDefault("PLUGIN.NOPERMISSION", NOPERMISSION);
        yamlConfigurationDe.addDefault("PLUGIN.WRONG_SENDER", WRONG_SENDER);
        yamlConfigurationDe.addDefault("SIGN.MESSAGE", SIGN_CREATE);
        yamlConfigurationDe.addDefault("SIGN.USERERROR", SIGN_CREATE_USER_ERROR);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.JOIN", PLAYER_JOIN_MESSAGE);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.QUIT", PLAYER_QUIT_MESSAGE);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.NORANG", PLAYER_NORANG_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SPACE1", CMD_PERMISSIONS_HELP_SPACE1);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.CREATE", CMD_PERMISSIONS_HELP_CREATE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.DELETE", CMD_PERMISSIONS_HELP_DELETE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.ADDPERMISSION", CMD_PERMISSIONS_HELP_ADDPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.REMOVEPERMISSION", CMD_PERMISSIONS_HELP_REMOVEPERMISSIONS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETPREFIX", CMD_PERMISSIONS_HELP_SETPREFIX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETCOLOR", CMD_PERMISSIONS_HELP_SETCOLOR);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETWEIGHT", CMD_PERMISSIONS_HELP_SETWEIGHT);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETGROUP", CMD_PERMISSIONS_HELP_SETGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETGROUPTEMP", CMD_PERMISSIONS_HELP_SETGROUPTEMP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.REMOVEGROUP", CMD_PERMISSIONS_HELP_REMOVEGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.HELP", CMD_PERMISSIONS_HELP_HELP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.RANG", CMD_PERMISSIONS_HELP_RANG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SPACE2", CMD_PERMISSIONS_HELP_SPACE2);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.RELOADMESSAGES", CMD_PERMISSIONS_RELOADMESSAGES);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.DELETE", CMD_PERMISSIONS_GROUP_DELETE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.CREATE", CMD_PERMISSIONS_GROUP_CREATE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.ADDPERMISSION", CMD_PERMISSIONS_GROUP_ADDPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.REMOVEPERMISSION", CMD_PERMISSIONS_GROUP_REMOVEPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETPREFIX", CMD_PERMISSIONS_GROUP_SETPREFIX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETCOLOR", CMD_PERMISSIONS_GROUP_SETCOLOR);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.MESSAGE", CMD_PERMISSIONS_GROUP_SETWEIGHTING_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.NONUMERIC", CMD_PERMISSIONS_GROUP_SETWEIGHTING_NONUMERIC);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.REMOVEGROUP", CMD_PERMISSIONS_USER_REMOVEGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.MESSAGE", CMD_PERMISSIONS_USER_SETGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.TARGETMESSAGE", CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.GROUPNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUP_GROUPNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.USERNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUP_USERNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.SYNTAX", CMD_PERMISSIONS_USER_SETGROUP_SYNTAX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.MESSAGE", CMD_PERMISSIONS_USER_SETGROUPTEMP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.TARGETMESSAGE", CMD_PERMISSIONS_USER_SETGROUPTEMP_TARGETMSG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.GROUPNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUPTEMP_GROUPNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.USERNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUPTEMP_USERNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.SYNTAX", CMD_PERMISSIONS_USER_SETGROUPTEMP_SYNTAX);
        yamlConfigurationDe.addDefault("COMMAND.RANG.MESSAGE", CMD_RANG_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.RANG.NORANG", CMD_RANG_NORANG);
        yamlConfigurationDe.addDefault("COMMAND.RANG.TIMEMSG", CMD_RANG_TIMEMSG);
        try {
            yamlConfigurationDe.save(getMessagesFile("de"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        //English
        YamlConfiguration yamlConfigurationEn = getYamlConfig(getMessagesFile("en"));
        yamlConfigurationDe.options().copyDefaults(true);
        yamlConfigurationDe.addDefault("PLUGIN.PREFIX", PREFIX);
        yamlConfigurationDe.addDefault("PLUGIN.SYNTAXERROR", SYNTAXERROR);
        yamlConfigurationDe.addDefault("PLUGIN.NOPERMISSION", NOPERMISSION);
        yamlConfigurationDe.addDefault("PLUGIN.WRONG_SENDER", WRONG_SENDER);
        yamlConfigurationDe.addDefault("SIGN.MESSAGE", SIGN_CREATE);
        yamlConfigurationDe.addDefault("SIGN.USERERROR", SIGN_CREATE_USER_ERROR);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.JOIN", PLAYER_JOIN_MESSAGE);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.QUIT", PLAYER_QUIT_MESSAGE);
        yamlConfigurationDe.addDefault("PLAYER.EVENT.NORANG", PLAYER_NORANG_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SPACE1", CMD_PERMISSIONS_HELP_SPACE1);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.CREATE", CMD_PERMISSIONS_HELP_CREATE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.DELETE", CMD_PERMISSIONS_HELP_DELETE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.ADDPERMISSION", CMD_PERMISSIONS_HELP_ADDPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.REMOVEPERMISSION", CMD_PERMISSIONS_HELP_REMOVEPERMISSIONS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETPREFIX", CMD_PERMISSIONS_HELP_SETPREFIX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETCOLOR", CMD_PERMISSIONS_HELP_SETCOLOR);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETWEIGHT", CMD_PERMISSIONS_HELP_SETWEIGHT);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETGROUP", CMD_PERMISSIONS_HELP_SETGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SETGROUPTEMP", CMD_PERMISSIONS_HELP_SETGROUPTEMP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.REMOVEGROUP", CMD_PERMISSIONS_HELP_REMOVEGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.HELP", CMD_PERMISSIONS_HELP_HELP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.RANG", CMD_PERMISSIONS_HELP_RANG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.HELP.SPACE2", CMD_PERMISSIONS_HELP_SPACE2);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.RELOADMESSAGES", CMD_PERMISSIONS_RELOADMESSAGES);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.DELETE", CMD_PERMISSIONS_GROUP_DELETE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.CREATE", CMD_PERMISSIONS_GROUP_CREATE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.ADDPERMISSION", CMD_PERMISSIONS_GROUP_ADDPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.REMOVEPERMISSION", CMD_PERMISSIONS_GROUP_REMOVEPERMISSION);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETPREFIX", CMD_PERMISSIONS_GROUP_SETPREFIX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETCOLOR", CMD_PERMISSIONS_GROUP_SETCOLOR);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.MESSAGE", CMD_PERMISSIONS_GROUP_SETWEIGHTING_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.NONUMERIC", CMD_PERMISSIONS_GROUP_SETWEIGHTING_NONUMERIC);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.REMOVEGROUP", CMD_PERMISSIONS_USER_REMOVEGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.MESSAGE", CMD_PERMISSIONS_USER_SETGROUP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.TARGETMESSAGE", CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.GROUPNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUP_GROUPNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.USERNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUP_USERNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUP.SYNTAX", CMD_PERMISSIONS_USER_SETGROUP_SYNTAX);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.MESSAGE", CMD_PERMISSIONS_USER_SETGROUPTEMP);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.TARGETMESSAGE", CMD_PERMISSIONS_USER_SETGROUPTEMP_TARGETMSG);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.GROUPNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUPTEMP_GROUPNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.USERNOTEXISTS", CMD_PERMISSIONS_USER_SETGROUPTEMP_USERNOTEXISTS);
        yamlConfigurationDe.addDefault("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.SYNTAX", CMD_PERMISSIONS_USER_SETGROUPTEMP_SYNTAX);
        yamlConfigurationDe.addDefault("COMMAND.RANG.MESSAGE", CMD_RANG_MESSAGE);
        yamlConfigurationDe.addDefault("COMMAND.RANG.NORANG", CMD_RANG_NORANG);
        yamlConfigurationDe.addDefault("COMMAND.RANG.TIMEMSG", CMD_RANG_TIMEMSG);
        try {
            yamlConfigurationDe.save(getMessagesFile("en"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMessagesFile(String language) {
        YamlConfiguration yamlConfiguration = getYamlConfig(getMessagesFile(language));
        PREFIX = MessageUtils.translatePrefix(yamlConfiguration.getString("PLUGIN.PREFIX"));
        SYNTAXERROR = MessageUtils.translatePrefix(yamlConfiguration.getString("PLUGIN.SYNTAXERROR"));
        NOPERMISSION = MessageUtils.translatePrefix(yamlConfiguration.getString("PLUGIN.NOPERMISSION"));
        WRONG_SENDER = MessageUtils.translatePrefix(yamlConfiguration.getString("PLUGIN.WRONG_SENDER"));
        SIGN_CREATE = MessageUtils.translatePrefix(yamlConfiguration.getString("SIGN.MESSAGE"));
        SIGN_CREATE_USER_ERROR = MessageUtils.translatePrefix(yamlConfiguration.getString("SIGN.USERERROR"));
        PLAYER_JOIN_MESSAGE = MessageUtils.translatePrefix(yamlConfiguration.getString("PLAYER.EVENT.JOIN"));
        PLAYER_QUIT_MESSAGE = MessageUtils.translatePrefix(yamlConfiguration.getString("PLAYER.EVENT.QUIT"));
        PLAYER_NORANG_MESSAGE = MessageUtils.translatePrefix(yamlConfiguration.getString("PLAYER.EVENT.NORANG"));
        CMD_PERMISSIONS_HELP_SPACE1 = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SPACE1"));
        CMD_PERMISSIONS_HELP_CREATE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.CREATE"));
        CMD_PERMISSIONS_HELP_DELETE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.DELETE"));
        CMD_PERMISSIONS_HELP_ADDPERMISSION = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.ADDPERMISSION"));
        CMD_PERMISSIONS_HELP_REMOVEPERMISSIONS = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.REMOVEPERMISSION"));
        CMD_PERMISSIONS_HELP_SETPREFIX = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SETPREFIX"));
        CMD_PERMISSIONS_HELP_SETCOLOR = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SETCOLOR"));
        CMD_PERMISSIONS_HELP_SETWEIGHT = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SETWEIGHT"));
        CMD_PERMISSIONS_HELP_SETGROUP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SETGROUP"));
        CMD_PERMISSIONS_HELP_SETGROUPTEMP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SETGROUPTEMP"));
        CMD_PERMISSIONS_HELP_REMOVEGROUP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.REMOVEGROUP"));
        CMD_PERMISSIONS_HELP_HELP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.HELP"));
        CMD_PERMISSIONS_HELP_RANG = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.RANG"));
        CMD_PERMISSIONS_HELP_SPACE2 = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.HELP.SPACE2"));
        CMD_PERMISSIONS_RELOADMESSAGES = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.RELOADMESSAGES"));
        CMD_PERMISSIONS_GROUP_DELETE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.DELETE"));
        CMD_PERMISSIONS_GROUP_CREATE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.CREATE"));
        CMD_PERMISSIONS_GROUP_ADDPERMISSION = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.ADDPERMISSION"));
        CMD_PERMISSIONS_GROUP_REMOVEPERMISSION = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.REMOVEPERMISSION"));
        CMD_PERMISSIONS_GROUP_SETPREFIX = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.SETPREFIX"));
        CMD_PERMISSIONS_GROUP_SETCOLOR = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.SETCOLOR"));
        CMD_PERMISSIONS_GROUP_SETWEIGHTING_MESSAGE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.MESSAGE"));
        CMD_PERMISSIONS_GROUP_SETWEIGHTING_NONUMERIC = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.GROUP.SETWEIGHT.NONUMERIC"));
        CMD_PERMISSIONS_USER_REMOVEGROUP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.REMOVEGROUP"));
        CMD_PERMISSIONS_USER_SETGROUP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUP.MESSAGE"));
        CMD_PERMISSIONS_USER_SETGROUP_TARGETMSG = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUP.TARGETMESSAGE"));
        CMD_PERMISSIONS_USER_SETGROUP_GROUPNOTEXISTS = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUP.GROUPNOTEXISTS"));
        CMD_PERMISSIONS_USER_SETGROUP_USERNOTEXISTS = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUP.USERNOTEXISTS"));
        CMD_PERMISSIONS_USER_SETGROUP_SYNTAX = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUP.SYNTAX"));
        CMD_PERMISSIONS_USER_SETGROUPTEMP = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.MESSAGE"));
        CMD_PERMISSIONS_USER_SETGROUPTEMP_TARGETMSG = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.TARGETMESSAGE"));
        CMD_PERMISSIONS_USER_SETGROUPTEMP_GROUPNOTEXISTS = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.GROUPNOTEXISTS"));
        CMD_PERMISSIONS_USER_SETGROUPTEMP_USERNOTEXISTS = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.USERNOTEXISTS"));
        CMD_PERMISSIONS_USER_SETGROUPTEMP_SYNTAX = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.PERMISSIONS.USER.SETGROUPTEMP.SYNTAX"));
        CMD_RANG_MESSAGE = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.RANG.MESSAGE"));
        CMD_RANG_NORANG = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.RANG.NORANG"));
        CMD_RANG_TIMEMSG = MessageUtils.translatePrefix(yamlConfiguration.getString("COMMAND.RANG.TIMEMSG"));
    }

}
