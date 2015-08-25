package eu.shotwar.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import eu.shotwar.Main;

public class Grapple implements CommandExecutor {
	Main plugin;
	public Grapple() {	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("grapple") || cmd.getName().equalsIgnoreCase("hook")) {
				PlayerInventory inv = p.getInventory();
				ItemStack is = new ItemStack(Material.FISHING_ROD);
				
				if (!inv.contains(is)) {
					inv.addItem(is);
				} else {
					p.sendMessage(plugin.getConfig().getString("Config.Spravy.Prefix").replace('&', 'ß') + "Prep·Ë, ale uû jeden Grapple m·ö. :)");
				}
			}
		} else {
			sender.sendMessage("[Shotwar.eu] Tento prikaz funguje iba hracom na servery. ;)");
		}
		return false;
	}
}
