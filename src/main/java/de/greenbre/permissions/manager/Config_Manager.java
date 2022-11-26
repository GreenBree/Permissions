package de.greenbre.permissions.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config_Manager {

    public static boolean ENABLED;
    public static String LANGUAGE;

    public static String MYSQL_HOSTNAME;
    public static String MYSQL_USERNAME;
    public static String MYSQL_PASSWORD;
    public static String MYSQL_DATABASE;
    public static int MYSQL_PORT;

    private static Config_Manager instance;

    public Config_Manager() {
        instance = this;
        getConfigFile();
        setStandartConfig();
        readConfig();
    }

    public static Config_Manager getInstance() {
        return instance;
    }

    public static File getConfigFile() {
        return new File("plugins/Permissions", "config.yml");
    }

    public static YamlConfiguration getYamlConfig() {
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public static void setStandartConfig() {
        YamlConfiguration yamlConfiguration = getYamlConfig();
        yamlConfiguration.options().copyDefaults(true);
        yamlConfiguration.addDefault("ENABLED", true);
        yamlConfiguration.addDefault("LANGUAGE", "de");
        yamlConfiguration.addDefault("MYSQL.HOSTNAME", "localhost");
        yamlConfiguration.addDefault("MYSQL.USERNAME", "root");
        yamlConfiguration.addDefault("MYSQL.PASSWORD", "pass123");
        yamlConfiguration.addDefault("MYSQL.DATABASE", "minecraft");
        yamlConfiguration.addDefault("MYSQL.PORT", 3306);
        try {
            yamlConfiguration.save(getConfigFile());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readConfig() {
        YamlConfiguration yamlConfiguration = getYamlConfig();
        ENABLED = yamlConfiguration.getBoolean("ENABLED");
        LANGUAGE = yamlConfiguration.getString("LANGUAGE");
        MYSQL_HOSTNAME = yamlConfiguration.getString("MYSQL.HOSTNAME");
        MYSQL_USERNAME = yamlConfiguration.getString("MYSQL.USERNAME");
        MYSQL_PASSWORD = yamlConfiguration.getString("MYSQL.PASSWORD");
        MYSQL_DATABASE = yamlConfiguration.getString("MYSQL.DATABASE");
        MYSQL_PORT = yamlConfiguration.getInt("MYSQL.PORT");
    }

}
