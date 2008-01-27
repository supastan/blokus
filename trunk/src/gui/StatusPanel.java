package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import data.StatusBoard;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel implements Observer {

	/** Preferred width of status panel */
	private static final int PREFERRED_WIDTH = 600;
	
	/** Preferred height of status panel */
	private static final int PREFERRED_HEIGHT = 120;
	
	/** Stores reference to JTextPane */
	private JTextPane _txtPane;
	
	/**
	 * Creates a StatusPanel tied to given Document.
	 * 
	 * @param doc StyleDocument to be associated with the JTextPane.
	 */
	public StatusPanel() {
	
		StatusBoard status = StatusBoard.getStatusBoard();
		StyledDocument doc = status.getMessageDoc();
		_txtPane = new JTextPane(doc);
		_txtPane.setEditable(false);
		JScrollPane scrlPane = new JScrollPane(_txtPane);
		scrlPane.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		
		setLayout(new BorderLayout());
		add(scrlPane, BorderLayout.CENTER);
	}
	
	/**
	 * ScrollDown to bottom of the document.
	 */
	public void refresh() {
		_txtPane.setCaretPosition(_txtPane.getDocument().getLength());
	}

	@Override
	public void update(Observable obj, Object arg) {
		refresh();
	}
}
