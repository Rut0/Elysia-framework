package randqm.elysia.network;

import randqm.elysia.network.codec.RS2ConnectionRequestProtocolDecoder;
import randqm.elysia.network.codec.RS2ProtocolEncoder;
import randqm.logging.LogTypes;
import randqm.logging.Logger;
import randqm.tasks.TaskFactory;
import randqm.tasks.TaskManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author RandQm
 *
 */

public class Server {
	
	/**
	 * Whether the server is online or not.
	 */
	private static boolean online;
	
	/**
	 * The server's task manager.
	 */
	private static TaskManager taskManager = TaskFactory.create(new TaskManager(7));
	
	/**
	 * The boss even loop group.
	 */
	private static EventLoopGroup bossGroup = new NioEventLoopGroup();
	
	/**
	 * The worker event loop group.
	 */
	private static EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	
	/**
	 * Launches the server.
	 * 
	 * @param port the pot number to bind the server too.
	 * 
	 * @param developerMode Whether the server has to run in developer mode or not.
	 */
	public static boolean launch(int port, boolean developerMode) {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline().addLast("rs2-encoder", new RS2ProtocolEncoder());
					channel.pipeline().addLast("rs2-connection-request-decoder", new RS2ConnectionRequestProtocolDecoder());
				}
			});
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(port);
			future.sync();
			Channel channel = future.channel();
			channel.closeFuture();
			future.sync();
			Logger.print(LogTypes.INFO, "Server bound to port " + port);
			online = true;
			return true;
		} catch (InterruptedException e) {
			Logger.print(LogTypes.ERROR, e.getMessage());
			return false;
		}
	}
	
	/**
	 * Shuts the server down.
	 */
	public static void shutdown() { //TODO: Disconnect active channels.
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		taskManager.dispose();
		online = false;
	}
	
	/**
	 * Retrieves whether the server is online or not.
	 * 
	 * @return The server's online status.
	 */
	public static boolean isOnline() {
		return online;
	}
	
	/**
	 * Retrieves the server's task manager.
	 * 
	 * @return The task manager.
	 */
	public static TaskManager getTaskManager() {
		return taskManager;
	}

}
