package randqm.elysia.game.entities;

/**
 * 
 * @author RandQm
 *
 */

public class Location {
	
	/**
	 * The x coordinate.
	 */
	private int x;
	
	/**
	 * The y coordinate.
	 */
	private int y;
	
	/**
	 * The z coordinate.
	 */
	private int z;
	
	
	/**
	 * The default constructor.
	 */
	public Location() {
		this(3500, 3500);
	}
	
	/**
	 * The constructor.
	 * 
	 * @param x The x coordinate.
	 * 
	 * @param y The y coordinate.
	 */
	public Location(int x, int y) {
		this(x, y, 0);
	}
	
	/**
	 * The constructor.
	 * 
	 * @param x The x coordinate.
	 * 
	 * @param y The y coordinate.
	 * 
	 * @param z The z coordinate.
	 */
	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Modifies the location.
	 * 
	 * @param x The new x coordinate.
	 * 
	 * @param y The new y coordinate.
	 */
	public void setLocation(int x, int y) {
		setLocation(x, y, z);
	}
	
	/**
	 * Modifies the location.
	 * 
	 * @param x The new x coordinate.
	 * 
	 * @param y The new y coordinate.
	 * 
	 * @param z The new z coordinate.
	 */
	public void setLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Modifies the location to another location.
	 * 
	 * @param location The new location.
	 */
	public void setLocation(Location location) {
		setLocation(location.getX(), location.getY(), location.getZ());
	}
	
	/**
	 * Moves the location.
	 * 
	 * @param x The addition on the x axis.
	 * 
	 * @param y The addition on the y axis.
	 * 
	 * @param z The addition on the height plane.
	 */
	public void move(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	/**
	 * Modifies the x coordinate.
	 * 
	 * @param x The new x coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Retrieves the x coordinate.
	 * 
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Retrieves the x region.
	 * 
	 * @return The x region.
	 */
	public int getRegionX() {
		return (x >> 3) - 6;
	}
	
	/**
	 * Retrieves the local x.
	 * 
	 * @return The local x.
	 */
	public int getLocalX() {
		return getLocalX(this);
	}
	
	/**
	 * Modifies the y coordinate.
	 * 
	 * @param y The new y coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Retrieves the y coordinate.
	 * 
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Retrieves the y region.
	 * 
	 * @return The y region.
	 */
	public int getRegionY() {
		return (y >> 3) - 6;
	}
	
	/**
	 * Retrieves the local y.
	 * 
	 * @return The local y.
	 */
	public int getLocalY() {
		return getLocalY(this);
	}
	
	/**
	 * Retrieves the local x position.
	 * 
	 * @param location The base location.
	 * 
	 * @return The position.
	 */
	public int getLocalX(Location location) {
		return x - 8 * location.getRegionX();
	}
	
	/**
	 * Retrieves the local y position.
	 * 
	 * @param location The base location.
	 * 
	 * @return The position.
	 */
	public int getLocalY(Location location) {
		return y - 8 * location.getRegionY();
	}
	
	/**
	 * Modifies the z coordinate.
	 * 
	 * @param z The new z coordinate.
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Retrieves the z coordinate.
	 * 
	 * @return The z coordinate.
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Retrieves whether a location is within a specific distance of another location.
	 * 
	 * @param location The other location.
	 * 
	 * @param distance The distance.
	 * 
	 * @return The result of the operation.
	 */
	public boolean withinDistance(Location location, int distance) { 
        if (getZ() != location.getZ()) 
            return false; 
        
        int deltaX = location.getX() - getX(), deltaY = location.getY() - getY(); 
        return deltaX <= distance && deltaX >= -distance && deltaY <= distance && deltaY >= -distance; 
    } 
  
	/**
	 * Retrieves whether a location is within a default distance of another location.
	 * 
	 * @param location The other location.
	 * 
	 * @return The result of the operation.
	 */
    public boolean withinDistance(Location location) { 
        if (getZ() != location.getZ()) 
            return false;
        
        int deltaX = location.getX() - getX(), deltaY = location.getY() - getY(); 
        return deltaX <= 14 && deltaX >= -15 && deltaY <= 14 && deltaY >= -15; 
    } 
    
    /**
     * Retrieves whether a location equals this location or not.
     * 
     * @param location The location.
     * 
     * @return The result of the operation.
     */
    public boolean equals(Location location) {
    	return (x == location.getX() && y == location.getY() && z == location.getZ());
    }
  
    @Override
    public String toString() { 
        return "X: [" + getX() + "] Y: [" + getY() + "] Z: [" + getZ() + "]."; 
    } 

}
