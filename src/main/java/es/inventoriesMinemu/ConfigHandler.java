package es.inventoriesMinemu;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */

import java.io.File;

public class ConfigHandler {


    private InventoriesMinemu inv;

    public ConfigHandler(InventoriesMinemu inv) {
        this.inv = inv;
        loadConfig();
    }

    public void loadConfig() {
        File pluginFolder = new File(inv.getDataFolder().getAbsolutePath());
        if (pluginFolder.exists() == false) {
            pluginFolder.mkdir();
        }
        File configFile = new File(inv.getDataFolder() + System.getProperty("file.separator") + "config.yml");
        if (configFile.exists() == false) {
            InventoriesMinemu.log.info("No config file found! Creating new one...");
            inv.saveDefaultConfig();
        }
        try {
            InventoriesMinemu.log.info("Loading the config file...");
            inv.getConfig().load(configFile);
        } catch (Exception e) {
            InventoriesMinemu.log.severe("Could not load the config file! You need to regenerate the config! Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        if (!inv.getConfig().contains(key)) {
            inv.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + InventoriesMinemu.pluginName + " folder! (Try generating a new one by deleting the current)");
            return "errorCouldNotLocateInConfigYml:" + key;
        } else {
            return inv.getConfig().getString(key);
        }
    }

    public String getStringWithColor(String key) {
        if (!inv.getConfig().contains(key)) {
            inv.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + InventoriesMinemu.pluginName + " folder! (Try generating a new one by deleting the current)");
            return "errorCouldNotLocateInConfigYml:" + key;
        } else {
            return inv.getConfig().getString(key).replaceAll("&", "�");
        }
    }

    public Integer getInteger(String key) {
        if (!inv.getConfig().contains(key)) {
            inv.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + InventoriesMinemu.pluginName + " folder! (Try generating a new one by deleting the current)");
            return null;
        } else {
            return inv.getConfig().getInt(key);
        }
    }

    public Boolean getBoolean(String key) {
        if (!inv.getConfig().contains(key)) {
            inv.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + InventoriesMinemu.pluginName + " folder! (Try generating a new one by deleting the current)");
            return null;
        } else {
            return inv.getConfig().getBoolean(key);
        }
    }

}