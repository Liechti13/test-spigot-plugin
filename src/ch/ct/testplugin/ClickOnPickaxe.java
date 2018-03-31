package ch.ct.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class ClickOnPickaxe implements Listener {
    String serverm = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Server" + ChatColor.DARK_GRAY + "] ";
    InventoryGUI igui;
    Main main;


    public ClickOnPickaxe(InventoryGUI igui, Main main) {
        this.igui = igui;
        this.main = main;
    }

    @EventHandler
    public void onClickItem(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (!e.isCancelled()) {

            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.getItemMeta() != null) {
                if (p.isSneaking() && item.getItemMeta().equals(igui.itemMeta3)) {
                    p.getWorld().spawnEntity(p.getLocation().add(-1, 0, 0), EntityType.DRAGON_FIREBALL);
                    p.playSound(p.getLocation(), Sound.BLOCK_METAL_HIT, 1, 1);

                }
            }
        }
    }
}


