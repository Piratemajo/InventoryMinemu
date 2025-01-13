package es.inventoriesMinemu.objects;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */

import org.bukkit.inventory.ItemStack;

public class InventorySyncData {

    private ItemStack[] backupInv;
    private ItemStack[] backupAr;
    private Boolean syncComplete;

    public InventorySyncData() {
        this.backupInv = null;
        this.backupAr = null;
        this.syncComplete = false;
    }

    public void setSyncStatus(boolean syncStatus) {
        syncComplete = syncStatus;
    }

    public Boolean getSyncStatus() {
        return syncComplete;
    }

    public ItemStack[] getBackupArmor() {
        return backupAr;
    }

    public ItemStack[] getBackupInventory() {
        return backupInv;
    }

    public void setBackupInventory(ItemStack[] backupInventory) {
        backupInv = backupInventory;
    }

    public void setBackupArmor(ItemStack[] backupArmor) {
        backupAr = backupArmor;
    }

}