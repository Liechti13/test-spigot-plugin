package ch.ct.testplugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Main extends JavaPlugin {


    @Override
    public void onDisable() {
        System.out.println("on Disable");
    }

    @Override
    public void onEnable() {
        System.out.println("on Enable");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Random r = new Random();

            if (label.equalsIgnoreCase("teleport")) {
                teleport(args, p, r);
            } else if (label.equalsIgnoreCase("Rüstung")) {
                ruestung(p);
            } else if (label.equalsIgnoreCase("prison")) {
                prison(p);
            } else if (label.equalsIgnoreCase("Dumbass")) {
                dumbass(p);
            } else if (label.equalsIgnoreCase("boom")) {
                boom(p);
            } else if (label.equalsIgnoreCase("countdown")) {
                countdown(p);
            } else {
                return false;
            }
            return true;
        }
        return false;
    }

    private void countdown(Player p) {

        p.setLevel(30);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count += 1;
                int level = p.getLevel();
                p.playSound(p.getLocation(), Sound.BLOCK_WOOD_PRESSUREPLATE_CLICK_OFF, SoundCategory.VOICE, 1, 1);
                p.setLevel(level - 1);
                if (count == 30) {
                    cancel();
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Die Zeit ist um!");
                    boom(p);
                }
            }
        }.runTaskTimer(this,30, 30);
    }


    private void boom(Player p) {
        World world = p.getWorld();

        final int size = 7;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world.spawnEntity(p.getLocation().add(size/2 - i, 20, size/2 - j), EntityType.PRIMED_TNT);
            }
        }
        world.spawnEntity(p.getLocation(), EntityType.FIREWORK);

    }

    private void dumbass(Player p) {
        p.sendMessage(ChatColor.BOLD + "Überrschung!");
        p.setLevel(30);
        p.getWorld().spawnEntity(p.getLocation().add(5, 0, 0), EntityType.DRAGON_FIREBALL);


        p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, SoundCategory.VOICE, 1, 1);
        p.playEffect(p.getLocation(), Effect.FIREWORK_SHOOT, null);
        p.updateInventory();
    }

    private void prison(Player p) {
        p.sendMessage(ChatColor.DARK_RED + "Du wurdest ins Gefängiss gesteckt!");
        Material blockType = Material.OBSIDIAN;
        World world = p.getWorld();

        Block from = world.getBlockAt(p.getLocation().add(0, +2, 0));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(1, 0, 0));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(1, 1, 0));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(-1, 0, 0));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(-1, 1, 0));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(0, 0, 1));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(0, 1, 1));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(0, 0, -1));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(0, 1, -1));
        from.setType(blockType);
        from = world.getBlockAt(p.getLocation().add(0, -1, 0));
        from.setType(blockType);

        p.teleport(from.getLocation().add(0.5, 1, 0.5));
    }

    private void ruestung(Player p) {
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Du hast die Rüstung bekommen!");
        p.setLevel(30);
        p.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
        p.sendMessage(ChatColor.AQUA + "Du hast ein Leben von " + ChatColor.LIGHT_PURPLE + p.getHealth());
        p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.VOICE, 1, 1);
        p.playEffect(p.getLocation(), Effect.PORTAL_TRAVEL, null);
        p.updateInventory();
    }

    private void teleport(String[] args, Player p, Random r) {
        Location location;
        if (args.length > 0 && args[0].equalsIgnoreCase("spawn")) {
            location = new Location(p.getWorld(), 0, 100, 0);
        } else {
            location = new Location(p.getWorld(), r.nextFloat() * 100000, 100, r.nextFloat() * 100000);
        }
        p.teleport(location);
        p.sendMessage(ChatColor.GOLD + "Du wurdest zu der Position " + ChatColor.DARK_RED + "x: "
                + location.getBlockX() + " z: " + location.getBlockZ() + ChatColor.GOLD + " teleportiert.");
        p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.VOICE, 1, 1);
        p.playEffect(p.getLocation(), Effect.DRAGON_BREATH, null);
    }
}
