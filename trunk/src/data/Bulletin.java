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

public class Bulletin extends Observable {

	private static Bulletin _bulletinBoard;
	
	/** Stores game messages */
	private StyledDocument _msg;
	
	private Bulletin() {
		_msg = new DefaultStyledDocument();
		
	}
	
	public synchronized void reset() {
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
	public synchronized StyledDocument getMessageDoc() {
		return _msg;
	}
	
	/**
	 * Appends String message to the StyledDocument message document.
	 * 
	 * @param type message type
	 * @param str message
	 */
	public synchronized void appendMsg(MessageType type, String str) {
		
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

	public static Bulletin getBoard() {
		if (_bulletinBoard == null) {
			_bulletinBoard = new Bulletin();
		}
		return _bulletinBoard;
	}
}
