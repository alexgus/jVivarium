/**
 * 
 */
package fr.utbm.lo43.jvivarium.mapeditor;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import fr.utbm.lo43.jvivarium.controller.XMLLoader;

/**
 * Master class of the map editor.
 * @author Alexandre Guyon
 */
public class MapEditor extends JFrame
{
	/**
	 * Serialize number
	 */
	private static final long serialVersionUID = 1L;

	//*************************** Variable **********************/
			//************* [View] ******************/
	
	/**
	 * Editor's panel
	 */
	private EditorPanel editor;
	
	/**
	 * Menu's Panel
	 */
	private MenuPanel menu;	
	
	//*************************** Construcor ***********************/
	
	public MapEditor()
	{
		super();
		
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		//**** XML ****
		XMLLoader xml = new XMLLoader();
		Thread xmlT = new Thread(xml);
		xmlT.start();
		
		//**** View ****
		
			// Editor
		this.editor = new EditorPanel();
		this.add(this.editor);
		this.editor.setBounds(MenuPanel.XPANEL, 0, 400, 300);
		this.setFocusable(true);
		this.addKeyListener(editor);
		
			// Menu
		this.menu = new MenuPanel(this.editor);
		this.add(menu);
		this.menu.setBounds(0, 0, MenuPanel.XPANEL, MenuPanel.YPANEL);
		
			// Resize JFrame
		int y;
		
		if(this.menu.getSize().height > this.editor.getSize().height)
			y = this.menu.getSize().height;
		else
			y = this.editor.getSize().height;
		
		this.setSize(this.menu.getSize().width + this.editor.getSize().width, y);
		
		// Add a listener to resize window;
		this.addComponentListener(new ComponentListener()
		{			
			@Override
			public void componentShown(ComponentEvent e)
			{
				
			}
			
			@Override
			public void componentResized(ComponentEvent e)
			{
				Dimension d = e.getComponent().getSize();
				int xEditor = 0 , yEditor=0;
				
				// Width
				if(d.width <= menu.getSize().width)
				{
					xEditor = 0;
				}
				else
				{
					xEditor = d.width - menu.getSize().width;
				}
				
				// Height
				yEditor = d.height;
				
				// Set
				editor.setSize(xEditor, yEditor);
				menu.setSize(menu.getWidth(), yEditor);
			}
			
			@Override
			public void componentMoved(ComponentEvent e)
			{
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e)
			{
				
			}
		});
	}
	//***************************** Methods *************************/
	
	public void start()
	{
		// Menu
		Thread me = new Thread(this.menu);
		me.start();
		
		// Editor
		Thread ed = new Thread(this.editor);
		ed.start();
		
		this.setVisible(true);
	}
}
