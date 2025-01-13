package es.inventoriesMinemu.events;


/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url: https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */

import es.inventoriesMinemu.InventoriesMinemu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {

    private InventoriesMinemu pd;

    public DropItem(InventoriesMinemu pd) {
        this.pd = pd;
    }

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        if (pd.getInventoryDataHandler().isSyncComplete(event.getPlayer()) == false) {
            event.setCancelled(true);
        }
    }

}
