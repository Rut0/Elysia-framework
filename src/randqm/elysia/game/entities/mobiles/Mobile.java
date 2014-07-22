package randqm.elysia.game.entities.mobiles;

import randqm.elysia.game.entities.Entity;
import randqm.elysia.game.entities.Location;

/**
 * 
 * @author RandQm
 *
 */

public class Mobile extends Entity {
	
	/**
	 * The mobile's location.
	 */
	private Location location;
	
	/**
	 * The mobile's last location.
	 */
	private Location lastLocation;
	
	
	/**
	 * The constructor.
	 * 
	 * @param location The location of the mobile.
	 */
	public Mobile(Location location) {
		this.location = location;
		this.lastLocation = location;
	}
	
	
	/**
	 * Modifies the last location of the mobile.
	 * 
	 * @param location The last location.
	 */
	public void setLastLocation(Location location) {
		this.lastLocation = location;
	}
	
	/**
	 * Retrieves the last location of the mobile.
	 * 
	 * @return The last location.
	 */
	public Location getLastLocation() {
		return lastLocation;
	}
	
	/**
	 * The location of the mobile.
	 * 
	 * @return The mobile's location.
	 */
	public Location getLocation() {
		return location;
	}

}
