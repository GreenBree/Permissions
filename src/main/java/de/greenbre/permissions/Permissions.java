package de.greenbre.permissions;

import de.greenbre.permissions.commands.Permissions_Command;
import de.greenbre.permissions.commands.Rang_Command;
import de.greenbre.permissions.listener.PlayerChat_Listener;
import de.greenbre.permissions.listener.PlayerJoin_Listener;
import de.greenbre.permissions.listener.PlayerQuit_Listener;
import de.greenbre.permissions.listener.SignChange_Listener;
import de.greenbre.permissions.manager.Config_Manager;
import de.greenbre.permissions.manager.Messages_Manager;
import de.greenbre.permissions.utils.MySQLConnector;
import de.greenbre.permissions.utils.RangUtils;
import de.greenbre.permissions.utils.TablistUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Permissions extends JavaPlugin {

    private static Permissions instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Permissions was enabled!");
        init();
    }

    @Override
    public void onDisable() {
        getLogger().info("Permissions was disabled!");
    }

    public static Permissions getInstance() {
        return instance;
    }

    private void init() {
        new Config_Manager();
        new Messages_Manager();
        registerMySQL();
        TablistUtils.registerScoreboard();
        registerCommands();
        registerListener();
        for(Player a : Bukkit.getOnlinePlayers()) RangUtils.updatePlayer(a);
    }

    private void registerMySQL() {
        new MySQLConnector();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Erstellen von Gruppendatenbank
        MySQLConnector.update("CREATE TABLE IF NOT EXISTS PermissionsGroups(Groupname VARCHAR(50), Prefix VARCHAR(50), ColorCode VARCHAR(5), Weighting VARCHAR(10) NOT NULL DEFAULT '0', Permissions TEXT);");
        //Erstellen von Benutzerdatenbank
        MySQLConnector.update("CREATE TABLE IF NOT EXISTS Permissions(Username VARCHAR(20), UUID VARCHAR(40), Groupname VARCHAR(50), PermissionTemp BOOLEAN, PermissionsTime LONG);");
        RangUtils.createDefaultGroups();
        RangUtils.getAllGroups();
    }

    private void registerCommands() {
        getServer().getPluginCommand("permissions").setExecutor(new Permissions_Command());
        getServer().getPluginCommand("rang").setExecutor(new Rang_Command());
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerJoin_Listener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit_Listener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat_Listener(), this);
        getServer().getPluginManager().registerEvents(new SignChange_Listener(), this);
    }

}
