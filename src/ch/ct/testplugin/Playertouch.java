package ch.ct.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Playertouch implements Listener {
    String serverm = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Server" + ChatColor.DARK_GRAY+ "] ";
    InventoryGUI igui;
    Main main;

    public Playertouch(InventoryGUI igui, Main main){
        this.igui = igui;
        this.main = main;
    }

    @EventHandler
    public void OnClickInvItem(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        if(e.getItem().getItemMeta().equals(igui.itemMeta2)){
            main.boom(p);
        }
        else {
            p.sendMessage(serverm + ChatColor.GOLD + "Error 404");
        }
    }
}
