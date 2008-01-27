package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import data.Game;

@SuppressWarnings("serial")
public class BlokusMenu extends JMenuBar {

	/** Menu string constants */
	private static final String FILE_MENU = "File";
	private static final String NEW_MENUITEM = "New";
	private static final String QUIT_MENUITEM = "Quit";
	private static final String EXIT_MENUITEM = "Exit";
	private static final String LEVEL_MENU = "Level";
	private static final String EASY_MENUITEM = "Easy";
	private static final String HARD_MENUITEM = "Hard";
	private static final String HELP_MENU = "Help";
	private static final String ABOUT_MENUITEM = "About";
	
	/** Menus and MenuItems */
	private JMenu _fileMenu;
	private JMenuItem _newMenuItem;
	private JMenuItem _quitMenuItem;
	private JMenuItem _exitMenuItem;
	private JMenu _levelMenu;
	private JRadioButtonMenuItem _easyMenuItem;
	private JRadioButtonMenuItem _hardMenuItem;
	private JMenu _helpMenu;
	private JMenuItem _aboutMenuItem;
	
	/** Stores reference to Game */
	private Game _game;
	
	/** 
	 * Creates an instance of LastCard menu.l
	 * 
	 * @param board reference to Board.
	 */
	public BlokusMenu(Game game) {
		
		_game = game;
		
		// all menu items will share a single event handler
		MenuHandler handler = new MenuHandler();
		
		// build menu structure
		_fileMenu = new JMenu(FILE_MENU);
		_fileMenu.setMnemonic(KeyEvent.VK_F);
		
		_newMenuItem = new JMenuItem(NEW_MENUITEM);
		_newMenuItem.setMnemonic(KeyEvent.VK_N);
		_newMenuItem.addActionListener(handler);
		
		_quitMenuItem = new JMenuItem(QUIT_MENUITEM);
		_quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		_quitMenuItem.addActionListener(handler);
		
		_exitMenuItem = new JMenuItem(EXIT_MENUITEM);
		_exitMenuItem.setMnemonic(KeyEvent.VK_X);
		_exitMenuItem.addActionListener(handler);
		
		_fileMenu.add(_newMenuItem);
		_fileMenu.add(_quitMenuItem);
		_fileMenu.addSeparator();
		_fileMenu.add(_exitMenuItem);
		
		_levelMenu = new JMenu(LEVEL_MENU);
		_levelMenu.setMnemonic(KeyEvent.VK_L);
		
		_easyMenuItem = new JRadioButtonMenuItem(EASY_MENUITEM);
		_easyMenuItem.setMnemonic(KeyEvent.VK_E);
		_easyMenuItem.addActionListener(handler);
		
		_hardMenuItem = new JRadioButtonMenuItem(HARD_MENUITEM);
		_hardMenuItem.setMnemonic(KeyEvent.VK_H);
		_hardMenuItem.addActionListener(handler);
		_hardMenuItem.setEnabled(false);
		
		// group easy menu item and the hard menu item into 
		// radio button groups
		ButtonGroup group = new ButtonGroup();
		group.add(_easyMenuItem);
		group.add(_hardMenuItem);
		
		_levelMenu.add(_easyMenuItem);
		_levelMenu.add(_hardMenuItem);

		_helpMenu = new JMenu(HELP_MENU);
		_helpMenu.setMnemonic(KeyEvent.VK_H);
		
		_aboutMenuItem = new JMenuItem(ABOUT_MENUITEM);
		_aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		_aboutMenuItem.addActionListener(handler);
		
		_helpMenu.add(_aboutMenuItem);

		// add menus to the menubar
		add(_fileMenu);
		add(_levelMenu);
		add(_helpMenu);
		
		// set defaults
		_easyMenuItem.setSelected(true);
	}
	
	/**
	 * Update menu status based on game progress.
	 *
	 */
	public void refresh() {
		// disable Quit menu is game is over or round is over
		// it is a little odd that user can't quit when
		// round is over, but else Quit will put a Move into
		// BlockingQueue when the thread has terminated.
		if (_game.isGameOver() ) {
			_quitMenuItem.setEnabled(false);
		} else {
			_quitMenuItem.setEnabled(true);
		}
	}
	
	/**
	 * Handles all menu actions for LastCardMenu.
	 */
	class MenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			
			String actcmd = evt.getActionCommand();
			
			if (actcmd.equals(NEW_MENUITEM)) {

				_game.reset();
				_game.start();
				
			} else if (actcmd.equals(QUIT_MENUITEM)) {
				
				_game.abort();
			} else if (actcmd.equals(EXIT_MENUITEM)) {
				
				System.exit(0);
				
			} else if (actcmd.equals(EASY_MENUITEM)) {
				
				_game.setGameLevel(Game.GameLevel.Easy);
				
			} else if (actcmd.equals(HARD_MENUITEM)) {
				
				_game.setGameLevel(Game.GameLevel.Hard);
				
			} else if (actcmd.equals(ABOUT_MENUITEM)) {
				
				StringBuilder msg = new StringBuilder();
				msg.append("       TCSS 435 Project\n");
				msg.append("            Version 1.0\n");
				msg.append("Yong Shin, Brian Fish ");
				msg.append("\u00A9");
				msg.append(" 2008");
				JOptionPane.showMessageDialog(null, 
						msg.toString(),
						"Blockus",
						JOptionPane.INFORMATION_MESSAGE);
				
			} else {
				
				throw new IllegalStateException("Unknown menu command: " + actcmd);
				
			}
		}
	}
}
