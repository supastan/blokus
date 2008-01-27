package data;

import java.awt.Color;
import java.util.Observable;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants.ColorConstants;
import javax.swing.text.StyleConstants.FontConstants;

import data.Game.MessageType;

public class StatusBoard extends Observable {

	private static StatusBoard _status;
	
	/** Stores game messages */
	private StyledDocument _msg;
	
	private StatusBoard() {
		_msg = new DefaultStyledDocument();
		
	}
	
	public void reset() {
		try {
			_msg.remove(0, _msg.getLength());
		} catch (BadLocationException ex) {
			// do nothing
		}
		
	}
	
	/**
	 * Returns message document.
	 * 
	 * @return message document.
	 */
	public StyledDocument getMessageDoc() {
		return _msg;
	}
	
	/**
	 * Appends String message to the StyledDocument message document.
	 * 
	 * @param type message type
	 * @param str message
	 */
	public void appendMessage(MessageType type, String str) {
		
		MutableAttributeSet attribSet = new SimpleAttributeSet(); 
		FontConstants.setFontSize(attribSet, 14);

		// build AttributeSet appropriate for message type
		switch(type) {
		
		case Error:
			ColorConstants.setForeground(attribSet, Color.RED);
			break;
			
		case GameOver:
			ColorConstants.setForeground(attribSet, Color.DARK_GRAY);
			FontConstants.setFontSize(attribSet, 25);
			FontConstants.setBold(attribSet, true);
			break;
		}
		
		// write message
		try {
			_msg.insertString(_msg.getLength(), str, attribSet);
			_msg.insertString(_msg.getLength(), "\n", attribSet);
		} catch (BadLocationException ex) {
			// do nothing
		}
		
		// notify observers
		setChanged();
		notifyObservers();
	}	

	public static StatusBoard getStatusBoard() {
		if (_status == null) {
			_status = new StatusBoard();
		}
		return _status;
	}


}
