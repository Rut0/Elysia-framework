package randqm.elysia;

import randqm.elysia.network.Server;
import randqm.elysia.network.packets.PacketHandler;
import randqm.elysia.ui.ControlFrame;
import randqm.logging.LogTypes;
import randqm.logging.Logger;

/**
 * 
 * @author RandQm
 *
 */

public class Launcher {
	
	
	/**
	 * The pinpoint of the application.
	 * 
	 * @param args The run arguments.
	 */
	public static void main(String[] args) { //TODO: Make this chaos better.
		PacketHandler.registerIncomingPackets();
		
		if (args.length > 3) {
			Logger.print(LogTypes.ERROR, "Invalid amount of run arguments.");
			return;
		}
		if (args.length == 0) {
			new ControlFrame(43594, false).setVisible(true);
			return;
		}
		if (!args[0].equalsIgnoreCase("true") && !args[0].equalsIgnoreCase("false")) {
			Logger.print(LogTypes.ERROR, "Invalid run argument, true or false expected as first argument (panel launch).");
			return;
		}
		int port = 43594;
		boolean panel = args[0].equalsIgnoreCase("true");
		boolean developerMode = false;
		
		if (args.length == 2) {
			try {
				port = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				Logger.print(LogTypes.ERROR, "Invalid run argument, digit expected as second argument (pot number).");
				return;
			}
		}
		if (args.length == 3) {
			if (!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false")) {
				Logger.print(LogTypes.ERROR, "Invalid run argument, true or false expected as third argument (developer mode).");
				return;
			}
			developerMode = args[2].equalsIgnoreCase("true");
		}
		if (panel)
			new ControlFrame(port, developerMode).setVisible(true);
		else
			Server.launch(port, developerMode);
	}

}
