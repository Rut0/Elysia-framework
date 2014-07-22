package randqm.elysia.network.packets.in.impl;

import randqm.elysia.game.entities.mobiles.players.Player;
import randqm.elysia.network.packets.Packet;
import randqm.elysia.network.packets.in.IncomingPacket;
import randqm.logging.LogTypes;
import randqm.logging.Logger;

/**
 * 
 * @author RandQm
 *
 */

public class CameraMovement implements IncomingPacket {

	
	@Override
	public void handle(Player player, Packet packet) {
		Logger.print(LogTypes.INFO, "Camera movement packet handled.");
	}

}
