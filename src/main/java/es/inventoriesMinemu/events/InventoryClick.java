package es.inventoriesMinemu.events;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url: https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */

import es.inventoriesMinemu.InventoriesMinemu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private InventoriesMinemu pd;

    public InventoryClick(InventoriesMinemu pd) {
        this.pd = pd;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (pd.getInventoryDataHandler().isSyncComplete(p) == false) {
            event.setCancelled(true);
        }
    }

}