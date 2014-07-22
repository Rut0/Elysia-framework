package randqm.elysia.network.packets.in;

import randqm.elysia.game.entities.mobiles.players.Player;
import randqm.elysia.network.packets.Packet;

/**
 * 
 * @author RandQm
 *
 */

public interface IncomingPacket {
	
	
	/**
	 * Handles a received packet.
	 * 
	 * @param player The player that received the packet.
	 * 
	 * @param packet The received packet.
	 */
	public void handle(Player player, Packet packet);

}
