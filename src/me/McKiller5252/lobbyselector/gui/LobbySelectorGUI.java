package me.McKiller5252.lobbyselector.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class LobbySelectorGUI implements Listener {
	
	private Inventory inv;
	
	private ItemStack a, b, c, d, e;

	public LobbySelectorGUI(Plugin m) {
		
		//Main Icon Panel
		inv = Bukkit.getServer().createInventory(null, 9, ChatColor.LIGHT_PURPLE + "Lobby Selector");
		
		a = createItem(Material.PAPER, ChatColor.RED + "Lobby 1");
		b = createItem(Material.FIREWORK_CHARGE, ChatColor.AQUA + "Lobby 2");
		c = createItem(Material.NAME_TAG, ChatColor.GOLD + "Lobby 3");
	    d = createItem(Material.DIAMOND, ChatColor.BLUE + "Lobby 4");
	    e = createItem(Material.SKULL_ITEM , ChatColor.GREEN + "Lobby 5");
	    
	    inv.setItem(0, a);
		inv.setItem(2, b);
		inv.setItem(4, c);
		inv.setItem(6, d);
		inv.setItem(8, e);
		
		
		Bukkit.getServer().getPluginManager().registerEvents(this, m);
	}
	
	private ItemStack createItem(Material dc, String name){
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}
	
	
	public void show(Player p){
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
		if (e.getCurrentItem().getItemMeta()== null) return;
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby 1"))
		{
			e.setCancelled(true);
		}
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby 2"))
		{
			e.setCancelled(true);
			
		}
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby 3"))
		{
			e.setCancelled(true);
			
		}
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby 4"))
		{
			e.setCancelled(true);
			
		}
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Lobby 5"))
		{
			e.setCancelled(true);
			
		}
		
		
	}

}