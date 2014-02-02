package me.McKiller5252.lobbyselector;

import java.util.ArrayList;
import java.util.List;

import me.McKiller5252.lobbyselector.gui.LobbySelectorGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySelector extends JavaPlugin implements Listener {
	
	private  LobbySelectorGUI lsgui;
	
	public void onEnable(){
		
		getLogger().info("LobbySelector Plugin Enabled");
		lsgui = new LobbySelectorGUI(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable(){
		 getLogger().info("LobbySelctor Plugin Disabled");
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
        if (p.getItemInHand() != null) {
            ItemStack item = p.getItemInHand();
            if (item.getType() == Material.COMPASS) { 
            	lsgui.show(e.getPlayer()); 
                }
            }
        }
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("ls")) {
            	p.sendMessage(ChatColor.GOLD + "You have gave yourself the " + ChatColor.YELLOW + " LobbySelctor Tool");
            	p.getInventory().setItem(8, lobbySelector());
            }
        }
        return true;
    }
	
	 
	 
	public ItemStack lobbySelector(){
        ItemStack spawnItem;
        ItemMeta im;
            spawnItem = new ItemStack(Material.COMPASS);
            im = spawnItem.getItemMeta();
            im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lobby Selector");
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.AQUA + "Right click to open Lobby Selector");
            im.setLore(lore);
            spawnItem.setItemMeta(im);
           
        return spawnItem;
       
    }

	@EventHandler
	 public void join(PlayerJoinEvent e){
		 Player player = e.getPlayer();
		 if (!player.getInventory().contains(Material.COMPASS))
		 {
			 player.setItemInHand(new ItemStack(Material.COMPASS, (short) 1));
			 ItemStack spawnItem = player.getItemInHand();
			 ItemMeta im =  spawnItem.getItemMeta();
			 im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lobby Selector");
			 List<String> lore = new ArrayList<String>();
			 lore.add(ChatColor.AQUA + "Right click to open Lobby Selector");
			 lore.add(ChatColor.WHITE + "If you lose the Lobby Selector, Type /ls.");
			 spawnItem.setItemMeta(im);
			 e.getPlayer().getInventory().setItem(8, spawnItem);
			 }
		}
}