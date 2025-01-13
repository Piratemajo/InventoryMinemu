package es.inventoriesMinemu.events;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url: https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */


import es.inventoriesMinemu.InventoriesMinemu;
import es.inventoriesMinemu.objects.SyncCompleteTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private InventoriesMinemu inv;

    public PlayerJoin(InventoriesMinemu inv) {
        this.inv = inv;
    }

    @EventHandler
    public void onLogin(final PlayerJoinEvent event) {
        if (InventoriesMinemu.isDisabling == false) {
            final Player p = event.getPlayer();
            Bukkit.getScheduler().runTaskLaterAsynchronously(inv, new Runnable() {

                @Override
                public void run() {
                    if (p != null) {
                        if (p.isOnline() == true) {
                            inv.getInventoryDataHandler().onJoinFunction(p);
                            new SyncCompleteTask(inv, System.currentTimeMillis(), p).runTaskTimerAsynchronously(inv, 5L, 20L);
                        }
                    }
                }

            }, 5L);
        }
    }

}