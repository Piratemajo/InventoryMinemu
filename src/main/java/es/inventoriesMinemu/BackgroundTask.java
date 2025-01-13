package es.inventoriesMinemu;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BackgroundTask {

    private InventoriesMinemu m;

    public BackgroundTask(InventoriesMinemu m) {
        this.m = m;
        runTask();
        }

        private void runTask() {
        if (m.getConfigHandler().getBoolean("General.saveDataTask.enabled") == true) {
            InventoriesMinemu.log.info("Data save task is enabled.");
            long interval = m.getConfigHandler().getInteger("General.saveDataTask.interval") * 60 * 20L;
            if (interval > 0) {
                Bukkit.getGlobalRegionScheduler().runAtFixedRate(m, task -> {
                    runSaveData();
                }, 20, interval);
            } else {
                InventoriesMinemu.log.warning("Invalid interval for data save task. Interval must be greater than 0.");
            }
        } else {
            InventoriesMinemu.log.info("Data save task is disabled.");
     }
        }

        private void runSaveData() {
        if (m.getConfigHandler().getBoolean("General.saveDataTask.enabled") == true) {
            if (Bukkit.getOnlinePlayers().isEmpty() == false) {
                List<Player> onlinePlayers = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                if (m.getConfigHandler().getBoolean("General.saveDataTask.hideLogMessages") == false) {
                    InventoriesMinemu.log.info("Saving online players data...");
                }
                for (Player p : onlinePlayers) {
                    if (p.isOnline() == true) {
                        m.getInventoryDataHandler().onDataSaveFunction(p, false, "false", null, null);
                    }
                }
                if (m.getConfigHandler().getBoolean("General.saveDataTask.hideLogMessages") == false) {
                    InventoriesMinemu.log.info("Data save complete for " + onlinePlayers.size() + " players.");
                }
                onlinePlayers.clear();
            }
        }
    }

    public void onShutDownDataSave() {
        InventoriesMinemu.log.info("Saving online players data...");
        List<Player> onlinePlayers = new ArrayList<Player>(Bukkit.getOnlinePlayers());

        for (Player p : onlinePlayers) {
            if (p.isOnline() == true) {
                m.getInventoryDataHandler().onDataSaveFunction(p, false, "true", null, null);
            }
        }
        InventoriesMinemu.log.info("Data save complete for " + onlinePlayers.size() + " players.");
    }

}