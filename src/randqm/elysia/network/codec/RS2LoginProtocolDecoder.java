package randqm.elysia.network.codec;

import java.util.List;

import randqm.elysia.game.entities.mobiles.players.Player;
import randqm.elysia.network.crypto.ISAACCipher;
import randqm.elysia.network.packets.Packet;
import randqm.elysia.network.packets.PacketHandler;
import randqm.elysia.network.packets.out.impl.PlayerUpdate;
import randqm.elysia.network.packets.out.impl.SendMapRegion;
import randqm.elysia.utilities.RS2String;
import randqm.logging.LogTypes;
import randqm.logging.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 
 * @author RandQm
 *
 */

public class RS2LoginProtocolDecoder extends ReplayingDecoder<ByteBuf> {

	
	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) throws Exception {
		final Channel channel = context.channel();
		final int connectionType = buffer.readByte();
		
		if (connectionType != 16 && connectionType != 18) {
			Logger.print(LogTypes.WARNING, "Invalid connection type: " + connectionType);
			channel.close();
			return;
		}
		final int blockLength = buffer.readByte() & 0xff;
		
		if (buffer.readableBytes() < blockLength) {
			Logger.print(LogTypes.WARNING, "Invalid block length.");
			channel.close();
			return;
		}
		if (buffer.readUnsignedByte() != 0xFF) {
			Logger.print(LogTypes.WARNING, "Invalid magic number.");
			channel.close();
			return;
		}
		if (buffer.readShort() != 317) {
			Logger.print(LogTypes.WARNING, "Invalid client version.");
			channel.close();
			return;
		}
		buffer.readUnsignedByte();

		for (int i = 0; i < 9; i ++)
			buffer.readInt();

		buffer.readUnsignedByte();

		if (buffer.readUnsignedByte() != 10) {
			Logger.print(LogTypes.WARNING, "Invalid RSA identifier.");
			channel.close();
			return;
		}
		final long clientHalf = buffer.readLong();
		final long serverHalf = buffer.readLong();
		
		final int[] isaacSeed = { (int) (clientHalf >> 32), (int) clientHalf, (int) (serverHalf >> 32), (int) serverHalf };
		final ISAACCipher crypticIn = new ISAACCipher(isaacSeed);
		
		for (int i = 0; i < isaacSeed.length; i++)
			isaacSeed[i] += 50;
		
		final ISAACCipher crypticOut = new ISAACCipher(isaacSeed);
		
		@SuppressWarnings("unused")
		final int version = buffer.readInt();
		
		final String username = RS2String.formatPlayerName(RS2String.buildRS2String(buffer));
		final String password = RS2String.buildRS2String(buffer);
		
		final int responseCode = getResponseCode(username, password);
		
		Packet response = new Packet(context.alloc().buffer());
		response.getBuffer().writeByte(responseCode);
		response.getBuffer().writeByte(2); //TODO: rights
		response.getBuffer().writeByte(0);
		channel.write(response);
		
		if (responseCode != 2) {
			channel.close();
			return;
		}
		Player player = new Player(context, username, password);
		
		player.putAttribute("decryptor", crypticIn);
		player.putAttribute("encryptor", crypticOut);
		
		//TODO: Load file.

		PacketHandler.sendPacket(player, new SendMapRegion());
		PacketHandler.sendPacket(player, new PlayerUpdate());
		
		context.pipeline().replace("rs2-login-decoder", "rs2-decoder", new RS2ProtocolDecoder(player));
	}
	
	/**
	 * Retrieves the response code for the login protocol.
	 * 
	 * @param username The username of the connection.
	 * 
	 * @param password The password of the connection.
	 * 
	 * @return The resulting response code.
	 */
	private int getResponseCode(final String username, final String password) {
		if (!username.matches("[A-Za-z0-9 ]+") 
				|| username.length() < 3 || username.length() > 12)
			return 3;
		
		//TODO: Check if banned (4)
		//TODO: Already logged in (5)
		//TODO: World full (7)
		//TODO: Multiple connections from same ip (9)
		return 2;
	}

}
