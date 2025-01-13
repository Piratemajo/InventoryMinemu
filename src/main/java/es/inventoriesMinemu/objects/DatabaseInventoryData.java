package es.inventoriesMinemu.objects;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 * */


public class DatabaseInventoryData {

    private String rawInv;
    private String rawAr;
    private String syncComplete;
    private String lastSee;

    public DatabaseInventoryData(String rawInventory, String rawArmor, String syncStatus, String lastSeen) {
        this.rawInv = rawInventory;
        this.rawAr = rawArmor;
        this.syncComplete = syncStatus;
        this.lastSee = lastSeen;
    }

    public String getLastSeen() {
        return lastSee;
    }

    public String getSyncStatus() {
        return syncComplete;
    }

    public String getRawArmor() {
        return rawAr;
    }

    public String getRawInventory() {
        return rawInv;
    }

}