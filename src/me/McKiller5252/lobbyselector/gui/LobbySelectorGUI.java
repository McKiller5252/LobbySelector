package me.McKiller5252.lobbyselector.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class LobbySelectorGUI implements Listener {
	
	//Main
	private Inventory inv;
	//Spawm
	private Inventory swinv;
	//Builder
	private Inventory buildinv;
	//Plots
	private Inventory plotinv;
	//Architect
	private Inventory arcinv;
	
	private ItemStack a, b, c, h, i, j, k,  g, d, e, f, l, p, r, s, t;

	
	public LobbySelectorGUI(Plugin m) {
		
		//Main Inv
		inv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Lobby Selector");
		
		a = createItem(Material.GRASS, ChatColor.GREEN + "Spawn");
		b = createItem(Material.STONE, ChatColor.AQUA + "Plot");
		c = createItem(Material.LOG, ChatColor.GOLD + "Builder");
		g = createItem(Material.QUARTZ_BLOCK, ChatColor.BLUE + "Architect");
	    
	    inv.setItem(0, a);
		inv.setItem(1, b);
		inv.setItem(2, c);
		inv.setItem(3, g);
		
		
		//Spawn Inv
		swinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Spawn Teleport Selector");
		
		d = createItem(Material.BREAD, ChatColor.DARK_AQUA + "Teleport To Spawn");
		e = createItem(Material.BLAZE_ROD, ChatColor.GOLD + "Member Hall");
		r = createItem(Material.BAKED_POTATO, ChatColor.GOLD + "Tutorial");
		f = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		swinv.setItem(0, d);
		swinv.setItem(1, e);
		swinv.setItem(2, r);
		swinv.setItem(8, f);
		
		//Plots Inv
		plotinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Plots Teleport Selector");
		
		l = createItem(Material.REDSTONE, ChatColor.DARK_AQUA + "Teleport To Plots");
		s = createItem(Material.DIAMOND_SWORD, ChatColor.GOLD + "Create Plot");
		t = createItem(Material.WOOD_DOOR, ChatColor.GOLD + "Plot Home");
		p = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		plotinv.setItem(0, l);
		plotinv.setItem(3, s);
		plotinv.setItem(5, t);
		plotinv.setItem(8, p);
		
		//Builder Inv
		buildinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Builder Teleport Selector");
		
		h = createItem(Material.ACACIA_STAIRS, ChatColor.YELLOW + "Teleport To Builder");
		i = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		
		buildinv.setItem(0, h);
		buildinv.setItem(8, i);
		
		//Architect Inv
		arcinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Architect Teleport Selector");
		
		j = createItem(Material.IRON_FENCE, ChatColor.YELLOW + "Teleport To Architect");
		k = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		arcinv.setItem(0, j);
		arcinv.setItem(8, k);
		
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
	
	@EventHandler(priority = EventPriority.HIGH)
	public void click(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)){
			
		if (e.getInventory().getSize() == 9) {
			e.setCancelled(true);
		
		if(e.getCurrentItem().getType() == Material.GRASS){
			
			//Spawn inv
			p.openInventory(swinv);
			
		}
		if (e.getCurrentItem().getType() == Material.BREAD){
			
			//Spawn Inv
			p.closeInventory();
            p.sendMessage(ChatColor.GREEN + "Teleporting to Spawn . . .");
			p.performCommand("warp spawn");
			
		}
		if (e.getCurrentItem().getType() == Material.BLAZE_ROD){
			
			//Spawn Inv
			p.closeInventory();
			p.performCommand("warp members");
		}
		if (e.getCurrentItem().getType() == Material.BAKED_POTATO){
			
			//Spawn Inv
			p.closeInventory();
			p.performCommand("warp tutorials");
			
		}
		if(e.getCurrentItem().getType() == Material.STONE){
			
			//Plot Inv
			p.openInventory(plotinv);
		}
		if (e.getCurrentItem().getType() == Material.REDSTONE){
		
			//Plot Inv
			p.closeInventory();
			p.performCommand("warp Rplot");
			
		}
		if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
			
			//Plot Inv
			p.performCommand("plotme auto");
			p.closeInventory();
		}
		if(e.getCurrentItem().getType() == Material.WOOD_DOOR){
			
			//Plot Inv
			p.performCommand("plotme home");
			p.closeInventory();
		}
		if (e.getCurrentItem().getType() == Material.LOG){
			
			//Builder inv
			p.openInventory(buildinv);
			
		}
		
		if(e.getCurrentItem().getType() == Material.ACACIA_STAIRS){
			
			//Builder Inv
			p.closeInventory();
			if(p.hasPermission("LobbySelector.builder")){
			p.performCommand("warp Bplot");
			}else {
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must first pass Recruit to go here!");
			}
		}
		if (e.getCurrentItem().getType() == Material.QUARTZ_BLOCK){
			
			//Architect inv
			p.openInventory(arcinv);
			
		}
		
		if(e.getCurrentItem().getType() == Material.IRON_FENCE){
			
			//Architect inv
			p.closeInventory();
			if(p.hasPermission("LobbySelector.architect")){
			p.performCommand("warp Aplot");
		  }else {
			  p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You must first pass Builder And Recruit to go here!");
		  }
		}
		if (e.getCurrentItem().getType() == Material.ARROW){
			
		   // Back
			p.openInventory(inv);
			
		}
		
		else {
			return;
			}
		}
	}
	}
}