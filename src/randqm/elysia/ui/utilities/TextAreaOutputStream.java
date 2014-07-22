package randqm.elysia.ui.utilities;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Some guy on stackoverflow
 *
 */

public class TextAreaOutputStream extends OutputStream {
	
	private String title;

	private final JTextArea textArea;
	
	private final StringBuilder sb = new StringBuilder();
	

	public TextAreaOutputStream(final JTextArea textArea, String title) {
		this.textArea = textArea;
		this.title = title;
		sb.append(title + "> ");
	}

	@Override
	public void flush() { }

	@Override
	public void close() { }

	@Override
	public void write(int b) throws IOException {
		if (b == '\r')
			return;

		if (b == '\n') {
			final String text = sb.toString() + "\n";
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.append(text);
				}
			});
			sb.setLength(0);
			sb.append(title + "> ");
			return;
		}
		sb.append((char) b);
	}
	
}
