package randqm.elysia.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.security.SecureRandom;
import java.util.List;

import randqm.elysia.network.packets.Packet;
import randqm.logging.LogTypes;
import randqm.logging.Logger;

/**
 * 
 * @author RandQm
 *
 */

public class RS2ConnectionRequestProtocolDecoder extends ReplayingDecoder<ByteBuf> {

	
	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) throws Exception {
		final Channel channel = context.channel();
		final int loginRequestType = buffer.readByte();
		
		if (loginRequestType != 14) {
			Logger.print(LogTypes.WARNING, "Invalid login request type: " + loginRequestType);
			channel.close();
			return;
		}
		buffer.readUnsignedByte();
		
		Packet response = new Packet(context.alloc().buffer());
		response.getBuffer().writeLong(0);
		response.getBuffer().writeByte(0);
		response.getBuffer().writeLong(new SecureRandom().nextLong());
		channel.write(response);
		
		context.pipeline().replace("rs2-connection-request-decoder", "rs2-login-decoder", new RS2LoginProtocolDecoder());
	}

}