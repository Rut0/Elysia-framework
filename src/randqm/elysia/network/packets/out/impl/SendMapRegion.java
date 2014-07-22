package randqm.elysia.network.packets.out.impl;

import randqm.elysia.game.entities.mobiles.players.Player;
import randqm.elysia.network.packets.Packet;
import randqm.elysia.network.packets.out.OutgoingPacket;

/**
 * 
 * @author RandQm
 *
 */

public class SendMapRegion implements OutgoingPacket {

	
	@Override
	public void send(Player player) {
		Packet packet = new Packet(player.getContext().alloc().buffer());
		packet.writeHeader(player.getEncryption(), 73);
		packet.writeAdditionalShort(player.getLocation().getRegionX() + 6);
		packet.writeShort(player.getLocation().getRegionY() + 6);
		player.getContext().writeAndFlush(packet);
		player.setLastLocation(player.getLocation());
		System.out.println("Map region sent.");
	}

}
