package es.inventoriesMinemu.objects;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */


import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import es.inventoriesMinemu.InventoriesMinemu;

public class SyncCompleteTask extends BukkitRunnable {

    private InventoriesMinemu pd;
    private long startTime;
    private Player p;
    private boolean inProgress = false;

    public SyncCompleteTask(InventoriesMinemu pd, long start, Player player) {
        this.pd = pd;
        this.startTime = start;
        this.p = player;
    }

    @Override
    public void run() {
        if (inProgress == false) {
            if (p != null) {
                if (p.isOnline() == true) {
                    inProgress = true;
                    if (pd.getInventoryDataHandler().isSyncComplete(p) == true) {
                        if (pd.getConfigHandler().getString("ChatMessages.syncComplete").matches("") == false) {
                            p.sendMessage(pd.getConfigHandler().getStringWithColor("ChatMessages.syncComplete"));
                        }
                        pd.getSoundHandler().sendLevelUpSound(p);
                        this.cancel();
                    } else {
                        if (System.currentTimeMillis() - startTime >= 20 * 1000) {
                            //Set sync to true in database to force sync data after 20 sec
                            pd.getInvMysqlInterface().setSyncStatus(p, "true");
                        } else if (System.currentTimeMillis() - startTime >= 40 * 1000) {
                            //Stop task after 40 sec
                            this.cancel();
                        }

                    }
                } else {
                    //inProgress = false;
                    this.cancel();
                }
            } else {
                //inProgress = false;
                this.cancel();
            }
        }
    }



}