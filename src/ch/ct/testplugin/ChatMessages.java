package ch.ct.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatMessages implements Listener {
    String serverm = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Server" + ChatColor.DARK_GRAY+ "] ";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        e.setJoinMessage(serverm + ChatColor.AQUA+ " Du hast den Server " + ChatColor.DARK_GRAY + Bukkit.getServer().getServerName() +
                ChatColor.AQUA + " um " + ChatColor.DARK_GRAY + dateFormat.format(date) + ChatColor.AQUA + " betreten!");
        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1, 1 );
        Bukkit.broadcastMessage(serverm + ChatColor.ITALIC + p.getDisplayName() + ChatColor.GOLD + " hat den Server betreten.");
}

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(serverm + ChatColor.BOLD + p.getName() + ChatColor.AQUA+ " hat den Server verlassen!");
        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1, 1 );
        Bukkit.broadcastMessage(serverm + ChatColor.ITALIC + p.getDisplayName() + ChatColor.GOLD + " hat den Server verlassen.");
    }
    @EventHandler
    public void onPlayerDied(PlayerDeathEvent e){
        Player p = e.getEntity();
        p.sendMessage(serverm + ChatColor.LIGHT_PURPLE + "Du bist gestorben" + e.getDeathMessage());
        p.setDisplayName("LOL");
        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_HIT, 1, 1 );

    }

}
