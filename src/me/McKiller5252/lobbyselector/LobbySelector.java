package me.McKiller5252.lobbyselector;

import java.util.Arrays;

import me.McKiller5252.lobbyselector.gui.LobbySelectorGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        if (p.getItemInHand() != null) {
            ItemStack item = p.getItemInHand();
            if (item.getType() == Material.COMPASS) { 
            	lsgui.show(e.getPlayer()); 
                }
            }
        }
	
	 
	//@EventHandler
   //  public void onDrop(PlayerDropItemEvent event) {
      //     if (event.getItemDrop().getItemStack() != null){
        //	   event.setCancelled(true);
        //   }
       //  }
	 
	@EventHandler
	 public void join(PlayerJoinEvent e){
		 Player player = e.getPlayer();
		 if (!player.getInventory().contains(Material.COMPASS))
		 {
			 player.setItemInHand(new ItemStack(Material.COMPASS, (short) 1));
			 ItemStack spawnItem = player.getItemInHand();
			 ItemMeta im =  spawnItem.getItemMeta();
			 im.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lobby Selector");
			 im.setLore(Arrays.asList(ChatColor.AQUA + "Right click to open Lobby Selector"));
			 spawnItem.setItemMeta(im);
			 e.getPlayer().getInventory().setItem(8, spawnItem);
			 }
		}
}