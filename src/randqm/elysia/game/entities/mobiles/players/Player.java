package randqm.elysia.game.entities.mobiles.players;

import io.netty.channel.ChannelHandlerContext;
import randqm.elysia.game.entities.Location;
import randqm.elysia.game.entities.mobiles.Mobile;
import randqm.elysia.network.crypto.ISAACCipher;

/**
 * 
 * @author RandQm
 *
 */

public class Player extends Mobile {
	
	/**
	 * The player's username.
	 */
	private final String username;
	
	/**
	 * The player's password.
	 */
	private String password;
	
	/**
	 * The player's network context.
	 */
	private final ChannelHandlerContext context;
	
	
	/**
	 * The constructor.
	 * 
	 * @param username The username of the player.
	 * 
	 * @param password The password of the player.
	 */
	public Player(ChannelHandlerContext context, String username, String password) {
		super(new Location(3500, 3500));
		
		this.context = context;
		this.username = username;
		this.password = password;
	}
	
	
	/**
	 * Retrieves the player's encryption.
	 * 
	 * @return The encryptor.
	 */
	public ISAACCipher getEncryption() {
		return (ISAACCipher) getObjectAttribute("encryptor");
	}

	/**
	 * Retrieves the player's decryption.
	 * 
	 * @return The decryptor.
	 */
	public ISAACCipher getDecryption() {
		return (ISAACCipher) getObjectAttribute("decryptor");
	}
	
	/**
	 * Retrieves the player's username.
	 * 
	 * @return The player's username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Modifies the player's password.
	 * 
	 * @param password The new password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Retrieves the player's password.
	 * 
	 * @return The player's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Retrieves the player's network channel context.
	 * 
	 * @return The context.
	 */
	public ChannelHandlerContext getContext() {
		return context;
	}

}
