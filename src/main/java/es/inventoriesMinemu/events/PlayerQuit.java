package es.inventoriesMinemu.events;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */
import es.inventoriesMinemu.InventoriesMinemu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerQuit implements Listener {

    private InventoriesMinemu inv;

    public PlayerQuit(InventoriesMinemu inv) {
        this.inv = inv;
    }

    @EventHandler
    public void onDisconnect(final PlayerQuitEvent event) {
        if (InventoriesMinemu.isDisabling == false) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(inv, new Runnable() {

                @Override
                public void run() {
                    if (event.getPlayer() != null) {
                        Player p = event.getPlayer();
                        ItemStack[] inventory = inv.getInventoryDataHandler().getInventory(p);
                        ItemStack[] armor = inv.getInventoryDataHandler().getArmor(p);
                        inv.getInventoryDataHandler().onDataSaveFunction(p, true, "true", inventory, armor);
                    }
                }

            }, 2L);
        }
    }

}