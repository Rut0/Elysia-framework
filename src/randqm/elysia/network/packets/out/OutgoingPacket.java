package randqm.elysia.network.packets.out;

import randqm.elysia.game.entities.mobiles.players.Player;

/**
 * 
 * @author RandQm
 *
 */

public interface OutgoingPacket {
	
	
	/**
	 * Sends the outgoing packet.
	 * 
	 * @param player The sending player.
	 */
	public void send(Player player);

}
