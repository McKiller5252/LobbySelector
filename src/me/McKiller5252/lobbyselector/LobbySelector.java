package me.McKiller5252.lobbyselector;

import java.util.Arrays;

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
	
	public ChatColor sColor = ChatColor.GRAY;
	public ChatColor pColor = ChatColor.GOLD;
	public ChatColor dColor = ChatColor.YELLOW;
	
	public String pre = ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "] ";
	public String pre1 = sColor + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.YELLOW + "[" + ChatColor.GOLD + "LobbySelector" + ChatColor.YELLOW + "]" + sColor + "" + ChatColor.STRIKETHROUGH + "-------------------";
	public String pre2 = sColor + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------";
	
	public void onEnable(){
		
		getLogger().info("LobbySelector Plugin Enabled");
		lsgui = new LobbySelectorGUI(this);
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable(){
		 getLogger().info("LobbySelctor Plugin Disabled");
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK | e.getAction() == Action.RIGHT_CLICK_AIR){
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
            	if(args.length == 0){
            	   p.sendMessage(pre1);
            	   p.sendMessage(pColor + "/ls - " + dColor + "Displays this screen");
                   p.sendMessage(pColor + "/ls tool - " + dColor + "Gives you the LobbySelector Tool");
                   p.sendMessage(pre2);
            	
            } else { if(args[0].equalsIgnoreCase("tool") && p.hasPermission("LobbySelector.ls")){
            		p.sendMessage(ChatColor.GOLD + "You have given yourself the " + ChatColor.YELLOW + " LobbySelector Tool");
            		p.getInventory().setItem(8, lobbySelector());
            	}
            	else if(args[0].equalsIgnoreCase("reload") && p.isOp() && p.hasPermission("LobbySelector.reload")){
            		Bukkit.getPluginManager().disablePlugin(this);
            		Bukkit.getPluginManager().enablePlugin(this);
            		sender.sendMessage(ChatColor.GREEN + "Reload Complete");
            		}
            	}
            } 
		}
		return true;
	}
	 
	 
	public ItemStack lobbySelector(){
        ItemStack spawnItem;
        ItemMeta im;
            spawnItem = new ItemStack(Material.COMPASS);
            im = spawnItem.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lobby Selector");
            im.setLore(Arrays.asList(ChatColor.AQUA + "Right click to open Lobby Selector"));
            spawnItem.setItemMeta(im);
           
        return spawnItem;
       
    }

	@EventHandler
	 public void join(PlayerJoinEvent e){
		 Player player = e.getPlayer();
		 if (!player.getInventory().contains(Material.COMPASS)){
			 ItemStack spawnItem = new ItemStack(Material.COMPASS);
			 ItemMeta im =  spawnItem.getItemMeta();
			 im.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lobby Selector (Right Click)");
			 im.setLore(Arrays.asList(ChatColor.AQUA + "Right click to open Lobby Selector", ChatColor.GRAY + "If you lose the Lobby Selector ", ChatColor.GRAY + "Type /ls tool "));
			 spawnItem.setItemMeta(im);
			 player.getInventory().setItem(8, spawnItem);
			 }
		}
}