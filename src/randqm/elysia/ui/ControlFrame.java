package randqm.elysia.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;

import randqm.elysia.network.Server;
import randqm.tasks.Task;
import randqm.tasks.TaskFactory;
import randqm.tasks.TaskManager;
import randqm.tasks.TaskProperties;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import java.awt.Cursor;

/**
 * 
 * @author RandQm
 *
 */

@SuppressWarnings("serial")
public class ControlFrame extends JFrame {
	
	/**
	 * The port the server will run on.
	 */
	private int port;
	
	/**
	 * Whether details are toggled or not.
	 */
	private boolean detailsToggled;
	
	/**
	 * The time the server was launched.
	 */
	private Date launchTime;
	
	/**
	 * The console frame.
	 */
	private ConsoleFrame console;

	/**
	 * The panel content pane.
	 */
	private JPanel contentPane;
	
	/**
	 * The button to toggle the server.
	 */
	private JButton toggleServerButton;
	
	/**
	 * The text field with the server port.
	 */
	private JTextField portTextField;
	
	/**
	 * A label for displaying notes.
	 */
	private JLabel noteLabel;
	
	/**
	 * Whether the server runs in developer mode or not.
	 */
	private JCheckBox developerModeCheckBox;
	
	/**
	 * Toggles details about the server.
	 */
	private JButton toggleDetailsButton;
	
	/**
	 * Displays the server's up time.
	 */
	private JLabel uptimeLabel;
	
	/**
	 * Displays the online players on the server.
	 */
	private JLabel playersLabel;


	/**
	 * Create the frame.
	 */
	public ControlFrame(int originalPort, boolean developerMode) {
		setResizable(false);
		this.port = originalPort;
		this.console = new ConsoleFrame();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControlFrame.class.getResource("/Icons/RandqmSoftwareLogo.png")));
		setTitle("Elysia Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 208);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		toggleServerButton = new JButton("Launch server");
		toggleServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleServer();
			}
		});
		toggleServerButton.setBackground(Color.BLACK);
		toggleServerButton.setFont(new Font("crayon kids", Font.PLAIN, 16));
		toggleServerButton.setForeground(Color.WHITE);
		toggleServerButton.setBounds(26, 71, 239, 44);
		contentPane.add(toggleServerButton);
		
		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("crayon kids", Font.PLAIN, 14));
		lblNewLabel.setBounds(26, 43, 41, 14);
		contentPane.add(lblNewLabel);
		
		portTextField = new JTextField();
		portTextField.setForeground(Color.DARK_GRAY);
		portTextField.setFont(new Font("crayon kids", Font.PLAIN, 14));
		portTextField.setBounds(66, 40, 59, 20);
		portTextField.setText(Integer.toString(originalPort));
		portTextField.setSelectionStart(portTextField.getText().length());
		contentPane.add(portTextField);
		portTextField.setColumns(10);
		
		noteLabel = new JLabel("");
		noteLabel.setForeground(Color.RED);
		noteLabel.setFont(new Font("crayon kids", Font.PLAIN, 14));
		noteLabel.setBounds(26, 126, 249, 14);
		contentPane.add(noteLabel);
		
		developerModeCheckBox = new JCheckBox("Developer mode");
		developerModeCheckBox.setBackground(Color.DARK_GRAY);
		developerModeCheckBox.setForeground(Color.WHITE);
		developerModeCheckBox.setFont(new Font("crayon kids", Font.PLAIN, 14));
		developerModeCheckBox.setSelected(developerMode);
		developerModeCheckBox.setBounds(144, 39, 131, 23);
		contentPane.add(developerModeCheckBox);
		
		toggleDetailsButton = new JButton("Details: off");
		toggleDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleDetails();
			}
		});
		toggleDetailsButton.setForeground(Color.WHITE);
		toggleDetailsButton.setFont(new Font("crayon kids", Font.PLAIN, 16));
		toggleDetailsButton.setBackground(Color.BLACK);
		toggleDetailsButton.setBounds(26, 149, 239, 23);
		contentPane.add(toggleDetailsButton);
		
		JLabel lblPlayers = new JLabel("Players:");
		lblPlayers.setForeground(Color.WHITE);
		lblPlayers.setFont(new Font("crayon kids", Font.PLAIN, 14));
		lblPlayers.setBounds(26, 183, 59, 14);
		contentPane.add(lblPlayers);
		
		JLabel lblUptime = new JLabel("Uptime:");
		lblUptime.setForeground(Color.WHITE);
		lblUptime.setFont(new Font("crayon kids", Font.PLAIN, 14));
		lblUptime.setBounds(26, 208, 59, 14);
		contentPane.add(lblUptime);
		
		uptimeLabel = new JLabel("0h 0m 0s");
		uptimeLabel.setForeground(Color.WHITE);
		uptimeLabel.setFont(new Font("crayon kids", Font.PLAIN, 14));
		uptimeLabel.setBounds(86, 208, 179, 14);
		contentPane.add(uptimeLabel);
		
		playersLabel = new JLabel("0");
		playersLabel.setForeground(Color.WHITE);
		playersLabel.setFont(new Font("crayon kids", Font.PLAIN, 14));
		playersLabel.setBounds(86, 183, 179, 14);
		contentPane.add(playersLabel);
		
		JLabel toggleConsoleIcon = new JLabel("");
		toggleConsoleIcon.setToolTipText("Toggle console");
		toggleConsoleIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toggleConsoleIcon.setIcon(new ImageIcon(ControlFrame.class.getResource("/Icons/consoleIcon.png")));
		toggleConsoleIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				console.setVisible(!console.isVisible());
			}
		});
		toggleConsoleIcon.setBounds(10, 11, 24, 24);
		contentPane.add(toggleConsoleIcon);
	}
	
	/**
	 * Handles the detail updating.
	 */
	private void updateDetails() {
		TaskFactory.create(new TaskManager()).submitTask(new Task(1000, TaskProperties.EXECUTE_INSTANTLY) {
			@Override
			protected void execute() {
				if (!detailsToggled || !Server.isOnline()) {
					playersLabel.setText("0");
					uptimeLabel.setText("0h 0m 0s");
					stop();
					return;
				} //TODO
				playersLabel.setText("1");
				
				long difference = new Date().getTime() - launchTime.getTime();
				long secs = difference / 1000 % 60;  
				long mins = difference / (60 * 1000) % 60;         
				long hrs = difference / (60 * 60 * 1000);  
				uptimeLabel.setText(hrs + "h " + mins + "m " + secs + "s");
			}
		});
	}
	
	/**
	 * Toggles the details.
	 */
	private void toggleDetails() {
		detailsToggled = !detailsToggled;
		toggleDetailsButton.setText(detailsToggled ? "Details: on" : "Details: off");
		final Point originalLocation = getLocation();
		setBounds(100, 100, 307, detailsToggled ? 261 : 208);
		setLocation(originalLocation);
		
		if (Server.isOnline() && detailsToggled)
			updateDetails();
	}
	
	/**
	 * Attempts to toggle the server.
	 */
	private void toggleServer() {
		if (validPort()) {
			if (!Server.isOnline()) {
				if (!Server.launch(port, developerModeCheckBox.isSelected())) {
					updateNoteLabel("Error ocurred, check console.", true);
					return;
				}
				this.launchTime = new Date();
				
				if (detailsToggled)
					updateDetails();
			} else
				Server.shutdown();
			
			toggleServerButton.setText(Server.isOnline() ? "Shutdown server" : "Launch server");
			portTextField.setEnabled(!Server.isOnline());
			developerModeCheckBox.setEnabled(!Server.isOnline());
			updateNoteLabel(Server.isOnline() ? "Launched the server." : "Stopped the server", false);
		}
	}
	
	/**
	 * Retrieves whether the given port is valid.
	 * 
	 * @return The result of the operation.
	 */
	private boolean validPort() {
		try {
			this.port = Integer.parseInt(portTextField.getText());
		} catch (NumberFormatException e) {
			updateNoteLabel("Invalid port input. Expecting a digit.", true);
			return false;
		}
		if (port < 0 || port > 65535) {
			updateNoteLabel("Port has to be between 0 and 65535.", true);
			return false;
		}
		updateNoteLabel("", false);
		return true;
	}
	
	/**
	 * Updates the note label.
	 * 
	 * @param text The note text.
	 * 
	 * @param error Whether it's an error message or not.
	 */
	private void updateNoteLabel(String text, boolean error) {
		noteLabel.setForeground(error ? Color.RED : Color.GREEN);
		noteLabel.setText(text);
	}
	
}
