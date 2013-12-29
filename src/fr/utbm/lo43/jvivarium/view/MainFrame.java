package fr.utbm.lo43.jvivarium.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Game Panel
	 */
	private GamePanel gameP = new GamePanel();
	
	/**
	 * The loading JLabel
	 */
	private JLabel load = new JLabel("Loading");
	
	/**
	 * Initialize the main window
	 * and add the panel of the game
	 * to it
	 */
	public MainFrame()
	{
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		this.setVisible(true);
	}
	
	/**
	 * Create panels thread
	 * for the view and activate
	 * the window
	 */
	public void start()
	{
		this.remove(load);
		
		this.add(this.gameP);
		this.setSize(
				new Dimension((int) this.gameP.getSize().getWidth(),
						(int)(this.gameP.getSize().getHeight() + this.getInsets().top)));
		
		Thread game = new Thread(this.gameP);
		game.start();
	}
	
	/**
	 * Loading view
	 */
	public void loading()
	{		
		this.setSize(100,70);
		this.add(load);
	}

}
