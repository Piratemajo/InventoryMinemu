package es.inventoriesMinemu.database;

/**
 *  Este Plugin es un Fork de "Mysql Inventory Brige"
 *  url: https://www.spigotmc.org/resources/mysql-inventory-bridge.7849/
 *  Autor: Piratemajo
 *  Actualizaciones a versiones mas nuevas y a Folia
 *
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import es.inventoriesMinemu.InventoriesMinemu;
import es.inventoriesMinemu.objects.DatabaseInventoryData;

public class InvMysqlInterface {

    private InventoriesMinemu inv;

    public InvMysqlInterface(InventoriesMinemu inv) {
        this.inv = inv;
    }

    public boolean hasAccount(Player player) {
        PreparedStatement preparedUpdateStatement = null;
        ResultSet result = null;
        Connection conn = inv.getDatabaseManager().getConnection();
        if (conn != null) {
            try {
                String sql = "SELECT `player_uuid` FROM `" + inv.getConfigHandler().getString("database.mysql.tableName") + "` WHERE `player_uuid` = ? LIMIT 1";
                preparedUpdateStatement = conn.prepareStatement(sql);
                preparedUpdateStatement.setString(1, player.getUniqueId().toString());

                result = preparedUpdateStatement.executeQuery();
                while (result.next()) {
                    return true;
                }
            } catch (SQLException e) {
                InventoriesMinemu.log.warning("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }
                    if (preparedUpdateStatement != null) {
                        preparedUpdateStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean createAccount(Player player) {
        PreparedStatement preparedStatement = null;
        Connection conn = inv.getDatabaseManager().getConnection();
        if (conn != null) {
            try {
                String sql = "INSERT INTO `" + inv.getConfigHandler().getString("database.mysql.tableName") + "`(`player_uuid`, `player_name`, `inventory`, `armor`, `sync_complete`, `last_seen`) " + "VALUES(?, ?, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, player.getUniqueId().toString());
                preparedStatement.setString(2, player.getName());
                preparedStatement.setString(3, "none");
                preparedStatement.setString(4, "none");
                preparedStatement.setString(5, "true");
                preparedStatement.setString(6, String.valueOf(System.currentTimeMillis()));

                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                InventoriesMinemu.log.warning("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean setData(Player player, String inventory, String armor, String syncComplete) {
        if (!hasAccount(player)) {
            createAccount(player);
        }
        PreparedStatement preparedUpdateStatement = null;
        Connection conn = inv.getDatabaseManager().getConnection();
        if (conn != null) {
            try {
                String data = "UPDATE `" + inv.getConfigHandler().getString("database.mysql.tableName") + "` " + "SET `player_name` = ?" + ", `inventory` = ?" + ", `armor` = ?" + ", `sync_complete` = ?" + ", `last_seen` = ?" + " WHERE `player_uuid` = ?";
                preparedUpdateStatement = conn.prepareStatement(data);
                preparedUpdateStatement.setString(1, player.getName());
                preparedUpdateStatement.setString(2, inventory);
                preparedUpdateStatement.setString(3, armor);
                preparedUpdateStatement.setString(4, syncComplete);
                preparedUpdateStatement.setString(5, String.valueOf(System.currentTimeMillis()));
                preparedUpdateStatement.setString(6, player.getUniqueId().toString());

                preparedUpdateStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                InventoriesMinemu.log.warning("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (preparedUpdateStatement != null) {
                        preparedUpdateStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean setSyncStatus(Player player, String syncStatus) {
        PreparedStatement preparedUpdateStatement = null;
        Connection conn = inv.getDatabaseManager().getConnection();
        if (conn != null) {
            try {
                String data = "UPDATE `" + inv.getConfigHandler().getString("database.mysql.tableName") + "` " + "SET `sync_complete` = ?" + ", `last_seen` = ?" + " WHERE `player_uuid` = ?";
                preparedUpdateStatement = conn.prepareStatement(data);
                preparedUpdateStatement.setString(1, syncStatus);
                preparedUpdateStatement.setString(2, String.valueOf(System.currentTimeMillis()));
                preparedUpdateStatement.setString(3, player.getUniqueId().toString());

                preparedUpdateStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                InventoriesMinemu.log.warning("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (preparedUpdateStatement != null) {
                        preparedUpdateStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public DatabaseInventoryData getData(Player player) {
        if (!hasAccount(player)) {
            createAccount(player);
        }
        PreparedStatement preparedUpdateStatement = null;
        ResultSet result = null;
        Connection conn = inv.getDatabaseManager().getConnection();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM `" + inv.getConfigHandler().getString("database.mysql.tableName") + "` WHERE `player_uuid` = ? LIMIT 1";
                preparedUpdateStatement = conn.prepareStatement(sql);
                preparedUpdateStatement.setString(1, player.getUniqueId().toString());

                result = preparedUpdateStatement.executeQuery();
                while (result.next()) {
                    return new DatabaseInventoryData(result.getString("inventory"), result.getString("armor"), result.getString("sync_complete"), result.getString("last_seen"));
                }
            } catch (SQLException e) {
                InventoriesMinemu.log.warning("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }
                    if (preparedUpdateStatement != null) {
                        preparedUpdateStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
