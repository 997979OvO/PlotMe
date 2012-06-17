package com.worldcretornica.plotme;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class PlotListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockBreak(final BlockBreakEvent event) 
	{
		boolean canbuild = PlotMe.checkPerms(event.getPlayer(), "PlotMe.admin");
		
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
			Player p = event.getPlayer();
			
			if(id.equalsIgnoreCase(""))
			{
				if(!canbuild)
				{
					p.sendMessage("You cannot build here.");
					event.setCancelled(true);
				}
			}else{
				Plot plot = PlotManager.getMap(p).plots.get(id);
				
				if (plot == null)
				{
					if(!canbuild)
					{
						p.sendMessage("You cannot build here.");
						event.setCancelled(true);
					}
				}else{
					
					if(!plot.isAllowed(p.getName()))
					{
						if(!canbuild)
						{
							p.sendMessage("You cannot build here.");
							event.setCancelled(true);
						}
					}else{
						plot.resetExpire(PlotManager.getMap(b).DaysToExpiration);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockPlace(final BlockPlaceEvent event)
	{
		boolean canbuild = PlotMe.checkPerms(event.getPlayer(), "PlotMe.admin");

		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
			Player p = event.getPlayer();
			
			if(id.equalsIgnoreCase(""))
			{
				if(!canbuild)
				{
					p.sendMessage("You cannot build here.");
					event.setCancelled(true);
				}
			}else{
				Plot plot = PlotManager.getPlotById(p,id);
				
				if (plot == null)
				{
					if(!canbuild)
					{
						p.sendMessage("You cannot build here.");
						event.setCancelled(true);
					}
				}else{
					if(!plot.isAllowed(p.getName()))
					{
						if(!canbuild)
						{
							p.sendMessage("You cannot build here.");
							event.setCancelled(true);
						}
					}else{
						plot.resetExpire(PlotManager.getMap(b).DaysToExpiration);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event)
	{
		if(!PlotMe.checkPerms(event.getPlayer(), "PlotMe.admin"))
		{
			BlockFace bf = event.getBlockFace();
			Block b = event.getBlockClicked().getLocation().add(bf.getModX(), bf.getModY(), bf.getModZ()).getBlock();
			if(PlotManager.isPlotWorld(b))
			{
				String id = PlotManager.getPlotId(b.getLocation());
				Player p = event.getPlayer();
				
				if(id.equalsIgnoreCase(""))
				{
					p.sendMessage("You cannot build here.");
					event.setCancelled(true);
				}else{
					Plot plot = PlotManager.getPlotById(p,id);
					
					if (plot == null)
					{
						p.sendMessage("You cannot build here.");
						event.setCancelled(true);
					}else{
						if(!plot.isAllowed(p.getName()))
						{
							p.sendMessage("You cannot build here.");
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerBucketFill(final PlayerBucketFillEvent event)
	{
		if(!PlotMe.checkPerms(event.getPlayer(), "PlotMe.admin"))
		{
			Block b = event.getBlockClicked();
			if(PlotManager.isPlotWorld(b))
			{
				String id = PlotManager.getPlotId(b.getLocation());
				Player p = event.getPlayer();
				
				if(id.equalsIgnoreCase(""))
				{
					p.sendMessage("You cannot build here.");
					event.setCancelled(true);
				}else{
					Plot plot = PlotManager.getPlotById(p,id);
					
					if (plot == null)
					{
						p.sendMessage("You cannot build here.");
						event.setCancelled(true);
					}else{
						if(!plot.isAllowed(p.getName()))
						{
							p.sendMessage("You cannot build here.");
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerInteract(final PlayerInteractEvent event)
	{
		boolean canbuild = PlotMe.checkPerms(event.getPlayer(), "PlotMe.admin");
		
		Block b = event.getClickedBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
			Player p = event.getPlayer();
			
			if(id.equalsIgnoreCase(""))
			{
				if(!canbuild)
				{
					p.sendMessage("You cannot build here.");
					event.setCancelled(true);
				}
			}else{
				Plot plot = PlotManager.getPlotById(p,id);
				
				if (plot == null)
				{
					if(!canbuild)
					{
						p.sendMessage("You cannot build here.");
						event.setCancelled(true);
					}
				}else{
					if(!plot.isAllowed(p.getName()))
					{
						if(!canbuild)
						{
							p.sendMessage("You cannot build here.");
							event.setCancelled(true);
						}
					}else{
						plot.resetExpire(PlotManager.getMap(b).DaysToExpiration);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockSpread(final BlockSpreadEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockForm(final BlockFormEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockDamage(final BlockDamageEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockFade(final BlockFadeEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockFromTo(final BlockFromToEvent event)
	{
		Block b = event.getToBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockGrow(final BlockGrowEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockIgnite(final BlockIgniteEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockPhysics(final BlockPhysicsEvent event)
	{
		Block b = event.getBlock();
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockPistonExtend(final BlockPistonExtendEvent event)
	{
		boolean found = false;
		
		for(Block b : event.getBlocks())
		{
			if(found || PlotManager.isPlotWorld(b))
			{
				found = true;
				String id = PlotManager.getPlotId(b.getLocation().add(event.getDirection().getModX(), event.getDirection().getModY(), event.getDirection().getModZ()));
										
				if(id.equalsIgnoreCase(""))
				{
					event.setCancelled(true);
					break;
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockPistonRetract(final BlockPistonRetractEvent event)
	{
		Block b = event.getRetractLocation().getBlock();
		
		if(PlotManager.isPlotWorld(b) && event.getBlock().getType() == Material.PISTON_STICKY_BASE)
		{
			String id = PlotManager.getPlotId(b.getLocation());
									
			if(id.equalsIgnoreCase(""))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onStructureGrow(final StructureGrowEvent event)
	{
		List<BlockState> blocks = event.getBlocks();
		boolean found = false;

		for(int i = 0 ; i < blocks.size(); i++)
		{
			if(found || PlotManager.isPlotWorld(blocks.get(i)))
			{
				found = true;
				String id = PlotManager.getPlotId(blocks.get(i).getLocation());
										
				if(id.equalsIgnoreCase(""))
				{
					event.getBlocks().remove(i);
					i--;
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityExplodeEvent(final EntityExplodeEvent event)
	{
		Entity e = event.getEntity();
		
		if(PlotManager.isPlotWorld(e.getWorld()))
		{
			List<Block> blocks = event.blockList();
			
			Location el = e.getLocation();
			
			Plot plot = PlotManager.getPlotById(el);
			
			if(plot == null)
			{
				event.setCancelled(true);
			}else{
			
				for(int i = 0 ; i < blocks.size();)
				{
					Block b = blocks.get(i);
					
					Location l = b.getLocation();
					
					Plot plotblock = PlotManager.getPlotById(l);
					
					if(plotblock == null || !plot.equals(plotblock))
					{
						blocks.remove(b);
					}else{
						i++;
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onBlockIgniteEvent(final BlockIgniteEvent event)
	{
		Block b = event.getBlock();
		
		if(PlotManager.isPlotWorld(b))
		{
			String id = PlotManager.getPlotId(b.getLocation());
			Player p = event.getPlayer();
			
			if(id.equalsIgnoreCase("") || p == null)
			{
				event.setCancelled(true);
			}else{
				Plot plot = PlotManager.getPlotById(b,id);
				
				if (plot == null)
				{
					event.setCancelled(true);
				}else{
					if(!plot.isAllowed(p.getName()))
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
