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
	//private Inventory bwinv;
	
	private ItemStack a, b, c;
	private ItemStack d, e, f;
	private ItemStack g, h, i, j, k;
	
	 World spawnWorld = Bukkit.getServer().getWorld("world");
	 World buildWorld = Bukkit.getServer().getWorld("BuildWorld");
	 World testWorld = Bukkit.getServer().getWorld("TestWorld");
	 
	 
	 Location spawn = new Location(spawnWorld, 32.5, 64.5, 166.5);
	 
	 Location test = new Location(testWorld, 106.5, 78.5, 92.5);
	 Location test1 = new Location(testWorld, 244.5, 128.5, 234.5);   
	 Location test2 = new Location(testWorld,  286.5, 50.5 ,134.5);
	 Location test3 = new Location(testWorld,  0.5, 90.5 ,0.5);   
	 
	 Location build = new Location(buildWorld, 0.0, 100.5, 0.0);
	 Location build1 = new Location(buildWorld, -10.0, 100.0, 100.0);


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
		e = createItem(Material.BLAZE_ROD, ChatColor.GOLD + "Location 1");
		f = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		swinv.setItem(0, d);
		swinv.setItem(1, e);
		swinv.setItem(8, f);
		
		//Test World Panel
		twinv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_RED + "Test World Teleport Selector");
		
		g = createItem(Material.REDSTONE_TORCH_ON, ChatColor.DARK_AQUA + "Teleport To Test World");
		h = createItem(Material.APPLE, ChatColor.GOLD + "Mount Netherest");
		i = createItem(Material.CLAY_BALL, ChatColor.GOLD + "Forlorn");
		j = createItem(Material.RED_ROSE, ChatColor.GOLD + "Crypt");
		k = createItem(Material.ARROW, ChatColor.BLUE + "Go Back");
		
		twinv.setItem(0, g);
		twinv.setItem(1, h);
		twinv.setItem(2, i);
		twinv.setItem(3, j);
		twinv.setItem(8, k);
		
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
			p.closeInventory();
            p.sendMessage(ChatColor.GREEN + "Teleporting to BuildWorld . . .");
			p.teleport(build);
			
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
			p.teleport(build1);
		}
		if (e.getCurrentItem().getType() == Material.RED_ROSE){
			
			//Testworld location3
			p.closeInventory();
			p.teleport(test3);
		}

		if (e.getCurrentItem().getType() == Material.APPLE){
		
			//Testworld location2
			p.closeInventory();
			p.teleport(test2);
			
		}
		if (e.getCurrentItem().getType() == Material.CLAY_BALL){
			
			//Testworld location1
			p.closeInventory();
			p.teleport(test1);
			
		}
		if (e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON){
			
			//Testworld location
			p.closeInventory();
			p.sendMessage(ChatColor.GREEN + "Teleporting to TestWorld . . .");
			p.teleport(test);
			
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