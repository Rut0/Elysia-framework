package randqm.elysia.game.entities;

import java.util.LinkedList;
import java.util.List;

import randqm.tasks.Task;

/**
 * 
 * @author RandQm
 *
 */

public abstract class World extends Task {
	
	/**
	 * A list holding the entities in the world.
	 */
	private List<Entity> entities = new LinkedList<>();
	
	
	/**
	 * Registers an entity to the world.
	 * 
	 * @param entity The entity to add.
	 * 
	 * @return Whether we were able to add the entity to the world or not.
	 */
	public abstract boolean registerEntity(Entity entity);
	
	/**
	 * Unregisters a entity for the world.
	 * 
	 * @param entity The entity to remove.
	 */
	public abstract void unregisterEntity(Entity entity);
	
	/**
	 * Disposes the world.
	 */
	public abstract void dispose();
	
	/**
	 * Retrieves the list of entities in the world.
	 * 
	 * @return The list of entities.
	 */
	public List<Entity> getEntities() {
		return entities;
	}

}
