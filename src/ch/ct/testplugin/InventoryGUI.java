package ch.ct.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryGUI implements CommandExecutor {
    ItemMeta itemMeta2;
    ItemMeta itemMeta3;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("Inventory")) {
                createMenu(p);



            }


        }

        return false;
    }
    //Inv Konfiguration
    public void createMenu(Player p){
        Inventory inv = Bukkit.getServer().createInventory(null, 9, ChatColor.GOLD + "Serverinventory");

        //Item 1
        ItemStack item1 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = item1.getItemMeta();

        ArrayList<String> itemlore = new ArrayList<String>();
        itemlore.add(ChatColor.ITALIC + "It`s a diamond!");
        itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Blackhole");
        itemMeta.setLore(itemlore);
        item1.setItemMeta(itemMeta);
        inv.setItem(0, item1);

        //Item 2
        ItemStack item2 = new ItemStack(Material.PORK);
        itemMeta2 = item2.getItemMeta();

        ArrayList<String> itemlore2 = new ArrayList<String>();
        itemlore2.add(ChatColor.ITALIC + "Minigun");
        itemMeta2.setDisplayName(ChatColor.DARK_PURPLE + "Oink");
        itemMeta2.setLore(itemlore2);
        item2.setItemMeta(itemMeta2);
        inv.setItem(1, item2);

        //Item 3
        ItemStack item3 = new ItemStack(Material.DIAMOND_PICKAXE);
        itemMeta3 = item3.getItemMeta();

        ArrayList<String> itemlore3 = new ArrayList<String>();
        itemlore3.add(ChatColor.ITALIC + "Is it a bird or a plane? No its a Minigun!");
        itemMeta3.setDisplayName(ChatColor.DARK_GRAY + "Minigun");
        itemMeta3.setLore(itemlore3);
        item3.setItemMeta(itemMeta3);
        inv.setItem(2, item3);

        //Open Inv
        p.openInventory(inv);


    }



}
