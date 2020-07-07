package me.shakeforprotein.prisontweaks.Events;

import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.shakeforprotein.prisontweaks.PrisonTweaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ThrowTnt implements Listener {

    private PrisonTweaks pl;

    public ThrowTnt(PrisonTweaks pl) {
        this.pl = pl;
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
            if(e.getItemDrop().getItemStack().containsEnchantment(Enchantment.LOYALTY)){

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
                        tnt.setCustomName(ChatColor.MAGIC + "KAPLOW!");
                    }

                }, 61L);

            }
            else {
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        List<Material> Leaves = new ArrayList<>();
        Leaves.add(Material.ACACIA_LEAVES);
        Leaves.add(Material.BIRCH_LEAVES);
        Leaves.add(Material.DARK_OAK_LEAVES);
        Leaves.add(Material.JUNGLE_LEAVES);
        Leaves.add(Material.OAK_LEAVES);
        Leaves.add(Material.SPRUCE_LEAVES);

        for(Material leaf : Leaves){
        if(block.getType() == leaf) {
            if(e.isCancelled() == false) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
                    @Override
                    public void run() {
                        block.getLocation().getBlock().setType(leaf);
                    }
                }, 30000L);
            }
        }
        }

        if(block.getType() == Material.WHEAT || block.getType() == Material.WHEAT_SEEDS){
            e.setCancelled(true);
                block.setType(Material.AIR);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.WHEAT, 1));
        }
        if (block.getType() == Material.BEETROOTS || block.getType() == Material.BEETROOT || block.getType() == Material.BEETROOT_SEEDS){
            e.setCancelled(true);
            if (e.getPlayer().hasPermission("group.capo")){
                block.setType(Material.AIR);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.BEETROOT, 1));
            }
        }
        if (block.getType() == Material.POTATO || block.getType() == Material.POTATOES){
            e.setCancelled(true);
            if (e.getPlayer().hasPermission("group.headcapo")){
                block.setType(Material.AIR);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.POTATO, 1));
            }
        }
        if (block.getType() == Material.PUMPKIN){
            e.setCancelled(true);
            if (e.getPlayer().hasPermission("group.treasurer")){
                block.setType(Material.AIR);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.PUMPKIN, 1));
            }
        }
        if (block.getType() == Material.MELON){
            e.setCancelled(true);
            if (e.getPlayer().hasPermission("group.underboss")){
                block.setType(Material.AIR);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.MELON_SLICE, 3));
            }
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e){
        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e){
        ItemStack is = e.getEntity().getItemStack();
        ArrayList<Material> list = new ArrayList<>();
        list.add(Material.WHEAT_SEEDS);
        list.add(Material.PUMPKIN_SEEDS);
        list.add(Material.MELON_SEEDS);
        list.add(Material.BEETROOT_SEEDS);

        for(Material mat : list){
            if(is.getType() == mat){
                e.setCancelled(true);
            }
        }
    }

}


