package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import data.Player;

@SuppressWarnings("serial")
public class OverviewPanel extends JPanel {

	/** Font for score */
	private static final Font SCORE_FONT = new Font("SansSerif", Font.BOLD, 25);
	
	/** Score font color */
	private static final Color FONT_COLOR = Color.BLACK;
	
	/** Stores reference to player */
	private Player _player;
	
	/** 
	 * Creates an instance of ScorePanel.
	 * 
	 * @param player reference to associated player.
	 */
	public OverviewPanel(Player player) {
		_player = player;
		
		// set background to match overall UI
		setBackground(BlokusUI.BACKGROUND_COLOR);
	}
	
	/**
	 * Shows player's name and scores.
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// get dimensions
		int height = getHeight();
		int width = getWidth();

		// set font and color
		g.setFont(SCORE_FONT);
		g.setColor(FONT_COLOR);
		
		// draw player's name
		String name = _player.getName();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = SCORE_FONT.getStringBounds(name, context);
		int vGap = (int) ((height - 2 * bounds.getHeight()) / 2);
		int hGap = (int) ((width - bounds.getWidth()) / 2);
		g.drawString(name, hGap, (int) (vGap + bounds.getHeight()));

		// draw score
		String score = "" + _player.getScore();
		bounds = SCORE_FONT.getStringBounds(score, context);
		hGap = (int) ((width - bounds.getWidth()) / 2);
		g.drawString(score, hGap, (int) (vGap + 2* bounds.getHeight()));
	}
}
