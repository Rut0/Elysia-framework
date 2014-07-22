package randqm.elysia.game.entities.mobiles.players;

import randqm.elysia.game.entities.Entity;
import randqm.elysia.game.entities.mobiles.MobileWorld;

/**
 * 
 * @author RandQm
 *
 */

public class PlayerWorld extends MobileWorld {

	
	@Override
	public boolean registerEntity(Entity entity) {
		if (entity instanceof Player/* && getEntities().size() < 2000*/) {
			getEntities().add(entity);
			return true;
		}
		return false;
	}

	@Override
	public void unregisterEntity(Entity entity) {
		getEntities().remove(entity);
	}

	@Override
	protected void execute() {
		for (Entity entity : getEntities()) {
			if (entity != null) {
				Player player = (Player)entity;
				
				//player.getMovement().handleEntityMovement();
				//UpdateHandler.parseMobileUpdates(player);
				//player.clearUpdates();
			}
		}
	}

	@Override
	public void dispose() {
		//TODO
		stop();
	}

}
