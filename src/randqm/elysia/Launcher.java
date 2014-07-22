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
	 * The network port to bind the server to.
	 */
	private static int networkPort = 43594;
	
	/**
	 * Whether the panel is used as control or not.
	 */
	private static boolean panel = true;
	
	/**
	 * Whether we run in developer mode or not.
	 */
	private static boolean developerMode;
	
	
	/**
	 * The pinpoint of the application.
	 * 
	 * @param args The run arguments.
	 */
	public static void main(String[] args) {
		LaunchStages currentStage = LaunchStages.STATIC_DATA_INITIALIZATION;
		
		while (currentStage != LaunchStages.FINISHED && currentStage != LaunchStages.FAILED) {
			LaunchResponse response = startup(args, currentStage);
			currentStage = response.getResponseStage();
			Logger.print(currentStage == LaunchStages.FAILED ? LogTypes.ERROR : LogTypes.INFO, response.getResponseMessage());
		}
	}
	
	/**
	 * Starts up the application in stages.
	 * 
	 * @param args The run arguments of the application.
	 * 
	 * @param currentStage The current launch stage of the application.
	 * 
	 * @return The launch stage response.
	 */
	@SuppressWarnings("incomplete-switch")
	private static LaunchResponse startup(String[] args, LaunchStages currentStage) {
		switch (currentStage) {
		
		case STATIC_DATA_INITIALIZATION:
			initializeStaticData();
			return new LaunchResponse("Initialized static application data.", LaunchStages.ARGUMENTS_CONTROL);
			
		case ARGUMENTS_CONTROL:
			if (args.length > 3)
				return new LaunchResponse("Invalid amount of run arguments.", LaunchStages.FAILED);
			
			if (args.length == 0)
				return new LaunchResponse("No run arguments found, launching by defaults...", LaunchStages.SERVER_LAUNCH_MODE);
			
			if (!args[0].equalsIgnoreCase("true") && !args[0].equalsIgnoreCase("false"))
				return new LaunchResponse("Invalid run argument, true or false expected as first argument (panel launch).", LaunchStages.FAILED);
			
			panel = args[0].equalsIgnoreCase("true");
			
			if (args.length == 2) {
				try {
					networkPort = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					return new LaunchResponse("Invalid run argument, digit expected as second argument (pot number).", LaunchStages.FAILED);
				}
			}
			if (args.length == 3) {
				if (!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false"))
					return new LaunchResponse("Invalid run argument, true or false expected as third argument (developer mode).", LaunchStages.FAILED);
				developerMode = args[2].equalsIgnoreCase("true");
			}
			return new LaunchResponse("Run arguments obtained.", LaunchStages.SERVER_LAUNCH_MODE);
			
		case SERVER_LAUNCH_MODE:
			if (panel)
				new ControlFrame(networkPort, developerMode).setVisible(true);
			else
				Server.launch(networkPort, developerMode);
			return new LaunchResponse("The application has been launched.", LaunchStages.FINISHED);
		}
		return new LaunchResponse("Unexpected error.", LaunchStages.FAILED);
	}
	
	/**
	 * Initializes data that would not change at world reboots.
	 */
	private static void initializeStaticData() {
		PacketHandler.registerIncomingPackets();
	}

}
