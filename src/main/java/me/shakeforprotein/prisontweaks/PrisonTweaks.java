package me.shakeforprotein.prisontweaks;

import me.shakeforprotein.prisontweaks.Events.ThrowTnt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


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
                if (args[0].equalsIgnoreCase("setTntThrow") && (sender.hasPermission("prisontweaks.setthrow") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    getConfig().set("tntThrowMultiplier", Double.valueOf(args[1]));
                    sender.sendMessage("Tnt throw distance set ** not saved **");
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") && (sender.hasPermission("prisontweaks.reload") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    reloadConfig();
                    sender.sendMessage("Config reloaded");
                } else if (args[0].equalsIgnoreCase("save") && (sender.hasPermission("prisontweaks.reload") || sender.getName().equalsIgnoreCase("ShakeforProtein"))) {
                    saveConfig();
                    sender.sendMessage("Config saved");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Incorrect usage");
                sender.sendMessage("/prisontweaks reload - reloads config");
                sender.sendMessage("/prisontweaks save -  saves config");
                sender.sendMessage("/prisontweaks setTntThrow <multiplier> - Sets how far players can throw tnt. Is multiplied by default item throw velocity");
            }
        }
        return true;
    }

}
