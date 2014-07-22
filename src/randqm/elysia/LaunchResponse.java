package randqm.elysia;

/**
 * 
 * @author RandQm
 *
 */

public class LaunchResponse {
	
	/**
	 * The response message.
	 */
	private final String responseMessage;
	
	/**
	 * The response launch stage.
	 */
	private final LaunchStages responseStage;
	
	
	/**
	 * The constructor.
	 * 
	 * @param responseMessage The response message.
	 * 
	 * @param responseStage The response launch stage.
	 */
	public LaunchResponse(String responseMessage, LaunchStages responseStage) {
		this.responseMessage = responseMessage;
		this.responseStage = responseStage;
	}
	
	
	/**
	 * Retrieves the response message.
	 * 
	 * @return The response message.
	 */
	public String getResponseMessage() {
		return responseMessage;
	}
	
	/**
	 * Retrieves the launch response stage.
	 * 
	 * @return The launch response stage.
	 */
	public LaunchStages getResponseStage() {
		return responseStage;
	}

}
