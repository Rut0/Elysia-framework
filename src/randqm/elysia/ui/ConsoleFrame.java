package randqm.elysia.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import randqm.elysia.ui.utilities.TextAreaOutputStream;

import java.awt.Color;
import java.awt.Point;
import java.io.PrintStream;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

/**
 * 
 * @author RandQm
 *
 */

@SuppressWarnings("serial")
public class ConsoleFrame extends JFrame {

	/**
	 * The console's content pane.
	 */
	private JPanel contentPane;
	
	/**
	 * The console output.
	 */
	private TextAreaOutputStream consoleOutput;
	
	/**
	 * The screen location of the frame.
	 */
	private Point frameLocation = null;
	
	/**
	 * The text area for the console output.
	 */
	private JTextArea consoleTextArea;
	

	/**
	 * Create the frame.
	 */
	public ConsoleFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsoleFrame.class.getResource("/Icons/RandqmSoftwareLogo.png")));
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				frameLocation = getLocation();
			}
			@Override
			public void componentShown(ComponentEvent arg0) {
				if (frameLocation != null)
					setLocation(frameLocation);
			}
		});
		setTitle("Elysia console");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 463, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setForeground(Color.WHITE);
		consoleTextArea.setBorder(new LineBorder(Color.WHITE));
		consoleTextArea.setBackground(Color.DARK_GRAY);
		consoleTextArea.setBounds(10, 11, 434, 237);
		contentPane.add(consoleTextArea);
		
		consoleOutput = new TextAreaOutputStream(consoleTextArea, "");
		
		JButton clearButton = new JButton("Clear console");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consoleTextArea.setText("");
			}
		});
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.WHITE);
		clearButton.setFont(new Font("crayon kids", Font.PLAIN, 14));
		clearButton.setBounds(289, 257, 155, 23);
		contentPane.add(clearButton);
		
		JScrollPane scrollPane = new JScrollPane(consoleTextArea);
		scrollPane.setBounds(10, 11, 434, 237);
		contentPane.add(scrollPane);
		System.setOut(new PrintStream(consoleOutput));
	}
}
