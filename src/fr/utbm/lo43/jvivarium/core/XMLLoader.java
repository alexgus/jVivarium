/**
 * 
 */
package fr.utbm.lo43.jvivarium.core;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class is used for load the map
 * and others objects describes in the XML, 
 * into the memory.
 * 
 * @author Alexandre Guyon
 */
public class XMLLoader
{
	//******************* Constant ********************/
	
	/**
	 * This constant point to the XML file
	 */
	private static final String FILENAME = "./res/map.xml";
	
	//******************* Variable ********************/
	
	/**
	 * DOM document
	 */
	Document doc;
	
	//******************* Constructor ********************/
	
	/**
	 * Constructor of the class.
	 * Used for initialize the XML parser
	 */
	public XMLLoader()
	{		
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			File xml = new File(FILENAME);
			doc = constructeur.parse(xml);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Begin the parsing of xml
	 */
	public void startParse()
	{
		Element root = doc.getDocumentElement();
		NodeList list = root.getChildNodes();
		int i;
		
		for(i = 0 ; i < list.getLength() ; i++)
		{
			switch (list.item(i).getNodeName())
			{
				case "map":
					this.parseMap(list.item(i).getChildNodes());
					break;
				case "espece":
					this.parseEspece();
				default:
					break;
			}
		}
	}
	
	/**
	 * Parse the map XML
	 * @param l	(NodeList) The node which contains chunks
	 */
	private void parseMap(NodeList l)
	{
		int i;
		
		for(i = 0 ; i < l.getLength() ; i++)
		{
			if(l.item(i).getNodeName() == "chunk")
				//lChunk.put(new Chunk(l.item(i).getChildNodes()));
				System.out.print("later");
		}
	}
	
	/**
	 * Parse the espece XML
	 * @param l	(NodeList) The node which contains espece
	 */
	private void parseEspece()
	{
		System.out.println("Parse espece");
	}
}
