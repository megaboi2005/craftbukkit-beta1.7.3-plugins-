import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.Math;
import org.bukkit.World;

public class Main extends JavaPlugin{

	public void onEnable() {
		Bukkit.getServer().getLogger().info("Better tp loaded without issues");
	}	
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("Better tp gone :(");
	}
    public static int getaxis(int i,Player player) {
        Location location = player.getLocation();
        switch (i) {
            case 0:
                return (int)(Math.round(location.getX()));
            case 1:
                return (int)(Math.round(location.getY()));
            case 2:
                return (int)(Math.round(location.getZ()));
        }
        return 0;       
    }
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
        
	    Player player = (Player) sender;
        World world = player.getWorld();
		if (cmd.getName().equalsIgnoreCase("tp")) {
			if (!(args.length == 3)) {
				return false;
			} else {
                int posvec[];
                posvec = new int[3];
                int output;
                double playeraxis = 0.0;
                for (int i=0;i<args.length;i++) {
                    if (args[i].charAt(0) == '~' && args[i].length() > 1) {

                        playeraxis = getaxis(i,player);
                        output = (int)(Math.round(playeraxis)) + Integer.parseInt(args[i].substring(1));
                    } else {
                        if (args[i].charAt(0) == '~') {
                            playeraxis = getaxis(i,player);
                            output = (int)(Math.round(playeraxis));
                        } else {
                            output = Integer.parseInt(args[i]);
                        }
                        
                    }                                   
                    posvec[i] = output;
                    
                }
                player.teleport(new Location(world,posvec[0],posvec[1],posvec[2],player.getLocation().getYaw(),player.getLocation().getPitch()));
            }
            return true;

		}
        return false;
	}
}
