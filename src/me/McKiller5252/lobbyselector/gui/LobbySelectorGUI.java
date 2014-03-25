package me.McKiller5252.lobbyselector.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
	
	private Inventory inv;
	private Inventory swinv;
	private Inventory twinv;
	private Inventory bwinv;
	
	private ItemStack a, b, c;
	private ItemStack d, e, f;
	private ItemStack l, q, p, r;
	
	 World spawnWorld = Bukkit.getServer().getWorld("SpawnWorld");
	 World buildWorld = Bukkit.getServer().getWorld("BuildWorldOld");
	 
	 
	 Location spawn = new Location(spawnWorld, -7 ,64 ,160, 90, 0);
	 Location spawn1 = new Location(spawnWorld,  -20, 62 ,151, 179, 0);
	 Location spawn2 = new Location(spawnWorld, -20, 62 ,169);
	 
	 Location build = new Location(buildWorld,  0, 65, 0);
	 Location build1 = new Location(buildWorld, -650.0, 100.0, -962.0);


	public LobbySelectorGUI(Plugin m) {
		
		//Main Panel
		inv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Lobby Selector");
		
		a = createItem(Material.GRASS, ChatColor.GREEN + "Spawn World");
		b = createItem(Material.STONE, ChatColor.AQUA + "Test World");
		c = createItem(Material.LOG, ChatColor.GOLD + "Build World");
	    
	    inv.setItem(0, a);
		inv.setItem(1, b);
		inv.setItem(2, c);
		
		
		//Spawn World  Panel
		swinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Spawn World Teleport Selector");
		
		d = createItem(Material.BREAD, ChatColor.DARK_AQUA + "Teleport To Spawn World");
		e = createItem(Material.BLAZE_ROD, ChatColor.GOLD + "Member Hall");
		r = createItem(Material.BAKED_POTATO, ChatColor.GOLD + "Tour");
		f = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		swinv.setItem(0, d);
		swinv.setItem(1, e);
		swinv.setItem(2, r);
		swinv.setItem(8, f);
		
	
		
		//Build world panel
		bwinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Build World Teleport Selector");
		
		l = createItem(Material.REDSTONE, ChatColor.DARK_AQUA + "Teleport To Build World");
		q = createItem(Material.DIAMOND, ChatColor.GOLD + "Regium");
		p = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		bwinv.setItem(0, l);
		bwinv.setItem(1, q);
		bwinv.setItem(8, p);
		
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
			
			//SpawnWorld inv
			p.openInventory(swinv);
			
		}
		if (e.getCurrentItem().getType() == Material.STONE){
			
			//Test World inv
			p.openInventory(twinv);
			
		}
		if (e.getCurrentItem().getType() == Material.LOG){
			
			//Build world inv
			p.openInventory(bwinv);
			
		}
		if (e.getCurrentItem().getType() == Material.BREAD){
			
			//spawnworld World Tp
			p.closeInventory();
            p.sendMessage(ChatColor.GREEN + "Teleporting to SpawnWorld . . .");
			p.teleport(spawn);
			
		}
		if (e.getCurrentItem().getType() == Material.BLAZE_ROD){
			
			//Buildworld location
			p.closeInventory();
			p.teleport(spawn1);
		}
		if (e.getCurrentItem().getType() == Material.ARROW){
			
		   // Back
			p.openInventory(inv);
			
		}
		
		if (e.getCurrentItem().getType() == Material.REDSTONE){
		
			p.closeInventory();
			p.sendMessage(ChatColor.GREEN + "Teleporting to BuildWorld . . .");
			p.teleport(build);
			
		}
		
		if (e.getCurrentItem().getType() == Material.DIAMOND){
			
			//Regium
			p.closeInventory();
			p.teleport(build1);
			
		}
		
		if (e.getCurrentItem().getType() == Material.BAKED_POTATO){
			
			p.closeInventory();
			p.teleport(spawn2);
			
		}
		else {
			return;
			}
		}
	}
	}
}