package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import data.Hand;
import data.Piece;

@SuppressWarnings("serial")
public class HandPanel extends JPanel {

	/** Default width */
	private static final int DEFAULT_WIDTH = 700;
	
	/** Default height */
	private static final int DEFAULT_HEIGHT = 130;
	
	/** Selection border color */
	private static final Color SELECTED_COLOR = Color.GREEN;
	
	/** Selection border width */
	private static final int SELECTION_BORDER_WIDTH = 5;
	
	/** Stores reference to Hand */
	private Hand _hand;
	
	/** Width of card image in pixels */
	private int _cardWidth;
	
	/** Height of card image in pixels */
	private int _cardHeight;
	
	/**
	 * Returns the x coordinate of the upper left hand corner of the
	 * first card to be displayed.
	 * 
	 * @return x coordinate of the upper left hand corner of the
	 * first card to be displayed.
	 */
//	private int getCardXCoord() {
//
//		int x_coord = 0;
//
//		int width = getWidth();
//		int size = _hand.size();
//		if (width > size * _cardWidth) {
//			x_coord = (width - size * _cardWidth) / (size + 1);
//		} 
//		
//		return x_coord;
//	}

	/**
	 * Returns the y coordinate of the upper left hand corner of the
	 * first card to be displayed.
	 * 
	 * @return y coordinate of the upper left hand corner of the
	 * first card to be displayed.
	 */
	private int getCardYCoord() {
		return (getHeight() - _cardHeight) / 2;
	}

	/**
	 * Returns the horizontal gap between two adjacent cards.  This value is negative
	 * if the cards overlap.
	 * 
	 * @return horizontal gap with between two adjacent cards.
	 */
//	private int getGapWidth() {
//		
//		int gapWidth = 0;
//
//		int width = getWidth();
//		int size = _hand.size();
//		if (width > size * _cardWidth) {
//			gapWidth = (width - size * _cardWidth) / (size + 1);
//		} else {
//			gapWidth = (width - size * _cardWidth) / (size - 1);
//		}
//		
//		return gapWidth;
//	}
	
	/**
	 * Paints hand.
	 * 
	 * @param g2 reference to Graphics2D
	 * @return true if hands were painted without encountering exception.
	 */
//	private boolean paintHand(Graphics2D g2) {
//		
//		int x_coord = getCardXCoord();
//		int y_coord = getCardYCoord();
//		int gapWidth = getGapWidth();
//		
//		try {
//			Iterator<Piece> iter = _hand.iterator();
//			int count = 0; 	// count of cards
//			while (iter.hasNext()) {
//				Piece card = iter.next();
//				Image image = null;
//				if (_hand.isShowFace()) {
//					
//					// show card face
//					image = card.getImageIcon().getImage();
//					int x = x_coord + (gapWidth + _cardWidth) * count;
//					int y = y_coord;
//					g2.drawImage(image, x, y, null);
//					
//					// if card is selected, draw a rectangular frame
//					if (count ==  _hand.getSelectedCardIndex() &&
//						_hand.isBorderSelected()) {
//						Rectangle2D rect = new Rectangle.Double(x-1, y-1, _cardWidth+2, _cardHeight+2);
//						g2.setColor(SELECTED_COLOR);
//						g2.setStroke(new BasicStroke(SELECTION_BORDER_WIDTH));
//						g2.draw(rect);
//					}
//				} else {
//					// show back image
//					image = Piece.getBackImageIcon().getImage(); 
//					g2.drawImage(image, x_coord + (gapWidth + _cardWidth) * count, y_coord, null);
//				}
//				count ++;
//			}		
//			
//			return true;
//		} catch (ConcurrentModificationException ex) {
//			
//			// hands were updated before we had change to paint them
//			return false;
//		}
//	}
	
	/**
	 * Creates HandPanel that shows the cards in the provided hand.
	 * 
	 * @param hand hand to display
	 * @param showFace indicates if the face should be displayed. if not, back side 
	 * is displayed.
	 */
	public HandPanel(Hand hand) {
		_hand = hand;
		
		// obtain card dimensions from the back side image
//		_cardWidth = Piece.getBackImageIcon().getIconWidth();
//		_cardHeight = Piece.getBackImageIcon().getIconHeight();
		
		setBackground(BlokusUI.BACKGROUND_COLOR);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		// add mouse listeners only if we're showing faces
		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}
	
	
	/**
	 * Paints the cards and a rectangular border around the selected card.
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		boolean result = false;
		while (!result) {
//			result = paintHand((Graphics2D) g);
		}
	}
	
	/**
	 * Class to handle mouse events.
	 */
	class MouseHandler extends MouseInputAdapter {
		
		@Override
		public void mouseClicked(MouseEvent evt) {

//			// get positions and measurements
//			int x_coord = getCardXCoord();
//			int y_coord = getCardYCoord();
//			int gapWidth = getGapWidth();
//			int size = _hand.size();
//			
//			// see if mouse has clicked a card
//			Point loc = evt.getPoint();
//			boolean cardSelected = false;
//			for (int i = 0; !cardSelected && i < size; i++) {
//				
//				// calculate x coor of left edge of i-th card
//				int x = x_coord + (gapWidth + _cardWidth) * i;
//				if (gapWidth < 0 && i != size-1) {
//					
//					// if cards overlap (but it is not the last card), then click must be
//					// between two left edges of cards
//					if ( (x < loc.x) && (loc.x < x + _cardWidth + gapWidth) &&
//							 (y_coord < loc.y) && (loc.y < y_coord + _cardHeight) ) {
//							cardSelected = true;
//							_hand.setSelectedCardIndex(i);
//						}
//				} else {
//					
//					// cards don't overlap (or it's the last card in overlapped cards), then
//					// click must be between left edge and right edge of the card
//					if ( (x < loc.x) && (loc.x < x + _cardWidth) &&
//							 (y_coord < loc.y) && (loc.y < y_coord + _cardHeight) ) {
//							cardSelected = true;
//							_hand.setSelectedCardIndex(i);
//						}
//				}
//			}
			
			repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent evt) {
			
			// get card locations
//			int x_coord = getCardXCoord();
//			int y_coord = getCardYCoord();
//			int gapWidth = getGapWidth();
//			int size = _hand.size();
//			
//			// see if mouse has moved over a card
//			Point loc = evt.getPoint();
//			boolean enteredCard = false;
//			for (int i = 0; !enteredCard && i < size; i++) {
//				int x = x_coord + (gapWidth + _cardWidth) * i;
//				if ( (x < loc.x) && (loc.x < x + _cardWidth) &&
//					 (y_coord < loc.y) && (loc.y < y_coord + _cardHeight) ) {
//					enteredCard = true;
//				}
//			}
//			
//			// change cursor
//			if (enteredCard) {
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			} else {
//				setCursor(Cursor.getDefaultCursor());
//			}
		}
	}
}
