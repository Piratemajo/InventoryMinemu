package es.inventoriesMinemu;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url:https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundHandler {

    private InventoriesMinemu pd;

    public SoundHandler(InventoriesMinemu pd) {
        this.pd = pd;
    }

    public void sendPlingSound(Player p) {
        if (pd.getConfigHandler().getBoolean("General.disableSounds") == false) {
            if (InventoriesMinemu.is13Server == true) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3F, 3F);
            } else if (InventoriesMinemu.is19Server == true) {
                p.playSound(p.getLocation(), Sound.valueOf("BLOCK_NOTE_PLING"), 3F, 3F);
            } else {
                p.playSound(p.getLocation(), Sound.valueOf("NOTE_PLING"), 3F, 3F);
            }
        }
    }

    public void sendLevelUpSound(Player p) {
        if (pd.getConfigHandler().getBoolean("General.disableSounds") == false) {
            if (InventoriesMinemu.is19Server == true) {
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
            } else {
                p.playSound(p.getLocation(), Sound.valueOf("LEVEL_UP"), 1F, 1F);
            }
        }
    }

    public void sendArrowHit(Player p) {
        if (pd.getConfigHandler().getBoolean("General.disableSounds") == false) {
            if (InventoriesMinemu.is19Server == true) {
                p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3F, 3F);
            } else {
                p.playSound(p.getLocation(), Sound.valueOf("SUCCESSFUL_HIT"), 3F, 3F);
            }
        }
    }

}