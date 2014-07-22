package randqm.elysia.network.codec;

import java.util.List;

import randqm.elysia.network.packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 
 * @author RandQm
 *
 */

public class RS2ProtocolEncoder extends MessageToMessageEncoder<Packet> {

	
	@Override
	protected void encode(ChannelHandlerContext context, Packet packet, List<Object> out) throws Exception {
		context.writeAndFlush(packet.getBuffer());
	}

}
