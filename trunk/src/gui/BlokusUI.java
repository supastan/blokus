package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Game;

public class BlokusUI extends ComponentAdapter {

	/** Background color */
	public static final Color BACKGROUND_COLOR = new Color(0, 122, 0);
	
	/** Minimum width of the Frame */ 
	private static final int MINIMUM_WIDTH = 800;
	
	/** Minimum height of the Frame */ 
	private static final int MINIMUM_HEIGHT = 720;
	
	/** Width of the panel that contains discard pile, stock and score panels */ 
	private static final int INNER_PANEL_WIDTH = 600;
	
	/** Height of the panel that contains discard pile, stock and score panels */ 
	private static final int INNER_PANEL_HEIGHT = 200;
	
	/** Frame title */
	private static final String TITLE = "Blokus";
	
	/** Stores reference to Game */
	private Game _game;
	
	/** Stores reference to Frame */
	private JFrame _frame;
	
	/** Stores reference to Players hand panel */
	private HandPanel _compHand;
	
	/** Stores reference to the panel showing status messages */
	private BulletinPanel _statusPanel;
	
	/** Stores reference to menu */
	private BlokusMenu _menu;
	
	/**
	 * Creates user interface for LastCard game.
	 * 
	 * @param board reference to associated board.
	 */
	public BlokusUI(Game game) {
		_game = game;
		
		// create and format frame
		_frame = new JFrame();
		_frame.setTitle(TITLE);
		_frame.setBackground(BACKGROUND_COLOR);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.addComponentListener(this);
		
		// create and add menu
		_menu = new BlokusMenu(game);
		_frame.setJMenuBar(_menu);
		
		// create all sub panels
//		_compScore = new OverviewPanel(board.getPlayer(Player.COMPUTER_PLAYER_INDEX));
//		_userScore = new OverviewPanel(board.getPlayer(Player.HUMAN_PLAYER_INDEX));
//		_compHand = new HandPanel(board.getPlayer(Player.COMPUTER_PLAYER_INDEX).getHand());
//		_userHand = new HandPanel(board.getPlayer(Player.HUMAN_PLAYER_INDEX).getHand());
//		_buttonPanel = new ButtonPanel(_board);
		_statusPanel = new BulletinPanel();
		
		// inner panel contains score panels and card piles
		JPanel innerPanel = new JPanel();
		GridLayout innerLayout = new GridLayout(1,4);
		innerLayout.setHgap(20);
		innerPanel.setLayout(innerLayout);
		innerPanel.setBackground(BACKGROUND_COLOR);
		innerPanel.setPreferredSize(new Dimension(INNER_PANEL_WIDTH, INNER_PANEL_HEIGHT));
//		innerPanel.add(_compScore);
//		innerPanel.add(_discardPile);
//		innerPanel.add(_stockPile);
//		innerPanel.add(_userScore);

		// outer panel contains inner panel and player's hands
		JPanel outerPanel = new JPanel();
		outerPanel.setBackground(BACKGROUND_COLOR);
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
//		outerPanel.add(_compHand);
//		outerPanel.add(innerPanel);
//		outerPanel.add(_userHand);
		
		// board panel contains outer panel and button panel
		JPanel boardPanel = new JPanel();
		boardPanel.setBackground(BACKGROUND_COLOR);
		boardPanel.setLayout(new BorderLayout());
		boardPanel.add(outerPanel, BorderLayout.CENTER);
//		boardPanel.add(_buttonPanel, BorderLayout.SOUTH);
		
		// add panels to frame
		_frame.setLayout(new BorderLayout());
		_frame.add(boardPanel, BorderLayout.CENTER);
		_frame.add(_statusPanel, BorderLayout.SOUTH);
		
		_frame.pack();
	}
	
	/**
	 * Displays LastCardUI.
	 */
	public void display() {
		
		// center frame
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrn = kit.getScreenSize();
		int x = (scrn.width - _frame.getWidth()) / 2;
		int y = (scrn.height - _frame.getHeight()) / 2;
		_frame.setLocation(new Point(x,y));
	
		// show frame
		_frame.setVisible(true);
	}
	
	/**
	 * Enforces frame minimum size.
	 */
	@Override
	public void componentResized(ComponentEvent evt) {
		int width = _frame.getWidth();
		int height = _frame.getHeight();
		if ( width < MINIMUM_WIDTH) {
			width = MINIMUM_WIDTH;
		}
		if (height < MINIMUM_HEIGHT) {
			height = MINIMUM_HEIGHT;
		}
		_frame.setSize(width, height);
	}
	
	/**
	 * Update UI components to reflect game progress.
	 */
	public void refresh() {

		// these panels just need to be repainted
//		_compScore.repaint();
//		_userScore.repaint();
//		_compHand.repaint();
//		_userHand.repaint();
		
		// these panels need to enable or disable components
//		_buttonPanel.refresh();
		_statusPanel.refresh();
		_menu.refresh();

	}
}
