package eu.shotwar.listeners;

import org.bukkit.Location; 
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import eu.shotwar.Main;

public class Grapplee implements Listener {
	
	Main plugin;
	public Grapplee() {	}
	
	@EventHandler
    public void onScoutGrapple(PlayerFishEvent e) {
        Player player = e.getPlayer();
        player.getItemInHand().setDurability((short) -10);
        
        if (!(player.getItemInHand().getType() == Material.FISHING_ROD))
            return;

        Location hookLoc = e.getHook().getLocation();
        Location playerLoc = player.getLocation();

        double hookX = (int) hookLoc.getX();
        double hookY = (int) hookLoc.getY();
        double hookZ = (int) hookLoc.getZ();

        Material inType = hookLoc.getWorld().getBlockAt(hookLoc).getType();
        if (inType == Material.AIR || inType == Material.WATER
                || inType == Material.LAVA) {
            Material belowType = hookLoc.getWorld()
                    .getBlockAt((int) hookX, (int) (hookY - 0.1), (int) hookZ)
                    .getType();
            if (belowType == Material.AIR || inType == Material.WATER
                    || inType == Material.LAVA)
                return;
        }

        playerLoc.setY(playerLoc.getY() + 0.5);
        player.teleport(playerLoc);

        Vector diff = hookLoc.toVector().subtract(playerLoc.toVector());
        Vector vel = new Vector();
        double d = hookLoc.distance(playerLoc);
        vel.setX((1.0 + 0.07 * d) * diff.getX() / d);
        vel.setY((1.0 + 0.03 * d) * diff.getY() / d + 0.04 * d);
        vel.setZ((1.0 + 0.07 * d) * diff.getZ() / d);
        player.setVelocity(vel);
        player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 5.0F, 10.0F);
    }
}
