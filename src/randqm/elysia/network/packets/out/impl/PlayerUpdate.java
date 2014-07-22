package randqm.elysia.network.packets.out.impl;

import randqm.elysia.game.entities.mobiles.players.Player;
import randqm.elysia.network.packets.Packet;
import randqm.elysia.network.packets.PacketHeaderTypes;
import randqm.elysia.network.packets.out.OutgoingPacket;

/**
 * 
 * @author RandQm
 *
 */

public class PlayerUpdate implements OutgoingPacket {

	
	@Override
	public void send(Player player) {
		Packet packet = new Packet(player.getContext().alloc().buffer());
		packet.writeHeader(PacketHeaderTypes.VARIABLE_SHORT, player.getEncryption(), 81);
		packet.initiateBitAccess();
		packet.writeBits(1, 1);
		packet.writeBits(2, 3);
		packet.writeBits(2, 0);
		packet.writeBits(1, 1);
		packet.writeBits(1, 1);	
		packet.writeBits(7, player.getLocation().getLocalY(player.getLocation()));	
		packet.writeBits(7, player.getLocation().getLocalX(player.getLocation()));	
		packet.writeBits(8, 0);
		packet.writeBits(11, 2047);
		
		packet.initiateByteAccess();
		packet.writeByte(0);
		packet.finishPacket(PacketHeaderTypes.VARIABLE_SHORT);
		
		player.getContext().writeAndFlush(packet);
		
		System.out.println("Player update sent.");
	}

}
