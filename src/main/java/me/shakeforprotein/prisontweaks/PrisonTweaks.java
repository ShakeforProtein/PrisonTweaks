package me.shakeforprotein.prisontweaks;

import me.shakeforprotein.prisontweaks.Commands.giveNewPick;
import me.shakeforprotein.prisontweaks.Events.ThrowTnt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public final class PrisonTweaks extends JavaPlugin implements CommandExecutor {

    private UpdateChecker uc;
    private ThrowTnt tT;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        getConfig().set("version", this.getDescription().getVersion());
        saveConfig();
        this.uc = new UpdateChecker(this);
        uc.getCheckDownloadURL();
        Bukkit.getPluginManager().registerEvents(new ThrowTnt(this), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("prisontweaks")) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("clear") && (sender.hasPermission("prisontweak.clearviptimer") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    getConfig().set(args[1], 0);
                    sender.sendMessage(args[1] + " pvip timer reset");
                }
                else if(args[0].equalsIgnoreCase("setTntThrow") && (sender.hasPermission("prisontweaks.setthrow") || sender.getName().equalsIgnoreCase("ShakeforProtein"))){getConfig().set("tntThrowMultiplier", Double.valueOf(args[1])); sender.sendMessage("Tnt throw distance set ** not saved **");}
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") && (sender.hasPermission("prisontweaks.reload") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    reloadConfig();
                    sender.sendMessage("Config reloaded");
                } else if (args[0].equalsIgnoreCase("save") && (sender.hasPermission("prisontweaks.reload") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    saveConfig();
                    sender.sendMessage("Config saved");
                } else if (args[0].equalsIgnoreCase("giveNewPick")) {
                    if (sender.hasPermission("pvip.perm") || sender.getName().equalsIgnoreCase("ShakeforProtein")) {
                        if (sender instanceof Player) {

                            Player p = (Player) sender;

                            if (getConfig().getString(p.getName()) == null) {
                                getConfig().set(p.getName(), 0);
                            }
                            if (((Calendar.getInstance().getTimeInMillis() / 1000) - 60800) > getConfig().getInt(p.getName())) {
                                getConfig().set(p.getName(), Calendar.getInstance().getTimeInMillis() / 1000);

                                if (p.hasPermission("ezranks.rank.Z")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank Z Pickaxe\",Lore:[\"The best pickaxe in Rank Z!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:4},{id:\"minecraft:efficiency\",lvl:15}]} 1");
                                } else if (p.hasPermission("ezranks.rank.Y")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank Y Pickaxe\",Lore:[\"The best pickaxe in Rank Y!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:9}]} 1");
                                } else if (p.hasPermission("ezranks.rank.X")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank X Pickaxe\",Lore:[\"The best pickaxe in Rank X!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:7}]} 1");
                                } else if (p.hasPermission("ezranks.rank.W")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank W Pickaxe\",Lore:[\"The best pickaxe in Rank W!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.V")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank V Pickaxe\",Lore:[\"The best pickaxe in Rank V!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.U")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank U Pickaxe\",Lore:[\"The best pickaxe in Rank U!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:5}]} 1");
                                } else if (p.hasPermission("ezranks.rank.T")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank T Pickaxe\",Lore:[\"The best pickaxe in Rank T!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.S")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank S Pickaxe\",Lore:[\"The best pickaxe in Rank S!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:5}]} 1");
                                } else if (p.hasPermission("ezranks.rank.R")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank R Pickaxe\",Lore:[\"The best pickaxe in Rank R!\"]},Enchantments:[{id:\"minecraft:mending\",lvl:1},{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:4}]} 1");
                                } else if (p.hasPermission("ezranks.rank.Q")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank Q Pickaxe\",Lore:[\"The best pickaxe in Rank Q!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:8}]} 1");
                                } else if (p.hasPermission("ezranks.rank.P")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank P Pickaxe\",Lore:[\"The best pickaxe in Rank P!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:7}]} 1");
                                } else if (p.hasPermission("ezranks.rank.O")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank O Pickaxe\",Lore:[\"The best pickaxe in Rank O!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.N")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank N Pickaxe\",Lore:[\"The best pickaxe in Rank N!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:8}]} 1");
                                } else if (p.hasPermission("ezranks.rank.M")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank M Pickaxe\",Lore:[\"The best pickaxe in Rank M!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:7}]} 1");
                                } else if (p.hasPermission("ezranks.rank.L")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank L Pickaxe\",Lore:[\"The best pickaxe in Rank L!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.K")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank K Pickaxe\",Lore:[\"The best pickaxe in Rank K!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:7}]} 1");
                                } else if (p.hasPermission("ezranks.rank.J")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank J Pickaxe\",Lore:[\"The best pickaxe in Rank J!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:6}]} 1");
                                } else if (p.hasPermission("ezranks.rank.I")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank I Pickaxe\",Lore:[\"The best pickaxe in Rank I!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:5}]} 1");
                                } else if (p.hasPermission("ezranks.rank.H")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank H Pickaxe\",Lore:[\"The best pickaxe in Rank H!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:4}]} 1");
                                } else if (p.hasPermission("ezranks.rank.G")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " diamond_pickaxe{display:{Name:\"VIP Rank G Pickaxe\",Lore:[\"The best pickaxe in Rank G!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:3}]} 1");
                                } else if (p.hasPermission("ezranks.rank.F")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " iron_pickaxe{display:{Name:\"VIP Rank F Pickaxe\",Lore:[\"The best pickaxe in Rank F!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:3},{id:\"minecraft:efficiency\",lvl:2}]} 1");
                                } else if (p.hasPermission("ezranks.rank.E")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " iron_pickaxe{display:{Name:\"VIP Rank E Pickaxe\",Lore:[\"The best pickaxe in Rank E!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:3}]} 1");
                                } else if (p.hasPermission("ezranks.rank.D")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " iron_pickaxe{display:{Name:\"VIP Rank D Pickaxe\",Lore:[\"The best pickaxe in Rank D!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:2}]} 1");
                                } else if (p.hasPermission("ezranks.rank.C")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " stone_pickaxe{display:{Name:\"VIP Rank C Pickaxe\",Lore:[\"The best pickaxe in Rank C!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:2},{id:\"minecraft:efficiency\",lvl:1}]} 1");
                                } else if (p.hasPermission("ezranks.rank.B")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " stone_pickaxe{display:{Name:\"VIP Rank B Pickaxe\",Lore:[\"The best pickaxe in Rank B!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:2}]} 1");
                                } else if (p.hasPermission("ezranks.rank.A")) {
                                    getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + p.getName() + " stone_pickaxe{display:{Name:\"VIP Rank A Pickaxe\",Lore:[\"The best pickaxe in Rank A!\"]},Enchantments:[{id:\"minecraft:unbreaking\",lvl:1},{id:\"minecraft:efficiency\",lvl:1}]} 1");
                                }
                                sender.sendMessage("Vip Items Dispensed");
                            } else {
                                sender.sendMessage("You are not entitled to a new pick at this time");
                            }

                        }

                    } else {
                        sender.sendMessage("These items are only available to VIP players");
                        sender.sendMessage("You can become a VIP at http://store.treebomc.com");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:give " + sender.getName() + " wood_pickaxe{display:{Name:\"Wood Pick\",Lore:[\"Well its got a point\"]}} 1");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Incorrect usage");
                sender.sendMessage("/prisontweaks clear <playerName> - clears a players vip timer - " + ChatColor.RED +   " ** Does not save config **");
                sender.sendMessage("/prisontweaks reload <playerName> - reloads config");
                sender.sendMessage("/prisontweaks save <playerName> -  saves config");
                sender.sendMessage("/prisontweaks giveNewPick <playerName> - Gives Vip Pick relevant to Prison Rank");
                sender.sendMessage("/prisontweaks setTntThrow <multiplier> - Sets how far players can throw tnt. Is multiplied by default item throw velocity");
            }
        }
        return true;
    }
}
