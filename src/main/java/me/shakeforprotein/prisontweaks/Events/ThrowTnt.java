package me.shakeforprotein.prisontweaks.Events;

import me.shakeforprotein.prisontweaks.PrisonTweaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Calendar;

public class ThrowTnt implements Listener {

    private PrisonTweaks pl;

    public ThrowTnt(PrisonTweaks pl) {
        this.pl = pl;
    }


    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("pvip.perm")) {
            if (pl.getConfig().getString(p.getName()) == null) {
                pl.getConfig().set(p.getName(), 0);
            }
            if (pl.getConfig().getString(p.getName()) != null) {
                if (((Calendar.getInstance().getTimeInMillis() / 1000) - 60800) > pl.getConfig().getInt(p.getName())) {
                    p.sendMessage("You are presently entitled to a VIP pickaxe.");
                    p.sendMessage("Please collect your VIP items in the commissary");
                }
            }
        }
    }


    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() == Material.TNT) {
            Location loc = e.getItemDrop().getLocation();
            Entity tnt = e.getItemDrop().getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            tnt.setCustomName(ChatColor.GREEN + "4");
            tnt.setCustomNameVisible(true);
            Double newTntVelocity = pl.getConfig().getDouble("tntThrowMultiplier");
            tnt.setVelocity(e.getItemDrop().getVelocity().multiply(newTntVelocity));
            e.getItemDrop().remove();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                public void run() {
                    tnt.setCustomName(ChatColor.GREEN + "3");
                }

            }, 1L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                public void run() {
                    tnt.setCustomName(ChatColor.YELLOW + "2");
                }

            }, 21L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                public void run() {
                    tnt.setCustomName(ChatColor.RED + "1");
                }

            }, 41L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                public void run() {
                    tnt.setCustomName(ChatColor.MAGIC + "BOOM!");
                }

            }, 61L);
        }
    }
}

