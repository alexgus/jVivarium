/**
 * 
 */
package fr.utbm.lo43.jvivarium.mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.utbm.lo43.jvivarium.core.Chunk;

/**
 * Display the map in a JFrame
 * 
 * @author Alexandre Guyon
 */
public class EditorPanel extends JPanel implements Runnable
{
	private final int FPS = 35;
	/**
	 * List of chunks to display
	 */
	private List<Chunk> lChunk;
	
	//****************************** Constructors *******************
	
	/**
	 * Constructors of the class.
	 * Load the map into the object and configure the frame
	 * @param list List of the map's chunks
	 */
	public EditorPanel(List<Chunk> list, EventListener mouse)
	{
		this.lChunk = list;
		int x=0,y=0;
		
		for (Iterator<Chunk> it = lChunk.iterator(); it.hasNext();)
		{
			Chunk c = it.next();
			
			if(c.getArea().getPosition().getX() + c.getArea().getSize().getX() > x)
				x = c.getArea().getPosition().getX() + c.getArea().getSize().getX();
			if(c.getArea().getPosition().getY() + c.getArea().getSize().getY() > y)
				y = c.getArea().getPosition().getY() + c.getArea().getSize().getY();
		}
		this.addMouseListener((MouseListener)mouse);
		this.addMouseMotionListener((MouseMotionListener)mouse);
		this.setSize(x, y);
		//this.setPreferredSize(new Dimension(x, y));
	}
	
	//******************************** run ***************************/
	
	@Override
	public void run()
	{
		while(true)
		{
			this.repaint();
			try
			{
				Thread.sleep(1000/FPS);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//***************************** Methods *************************
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		for (Iterator<Chunk> it = lChunk.iterator(); it.hasNext();)
		{
			Chunk c = it.next();
			
			g.setColor(new Color(0, 0, 0));
			g.drawRect(c.getArea().getPosition().getX(), c.getArea().getPosition().getY(), c.getArea().getSize().getX(), c.getArea().getSize().getY());
			switch(c.getFieldType())
			{
				case GRASS:
					g.setColor(new Color(0, 255, 0));
					break;
				case WATER:
					g.setColor(new Color(0, 0, 255));
					break;
				case ROCK:
					g.setColor(new Color(125, 125, 125));
					break;
			}
			g.fillRect(c.getArea().getPosition().getX() + 1, c.getArea().getPosition().getY() + 1, c.getArea().getSize().getX() - 1, c.getArea().getSize().getY() - 1);
		}
	}
}
