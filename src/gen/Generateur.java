package gen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;

import javax.xml.xpath.XPathConstants;

import javax.xml.xpath.XPathExpression;

import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Generateur {
	public Map<String, Base> bases = new HashMap<String, Base>();
	public Map<String, Composition> compositions = new HashMap<String, Composition>();

	public Random random = new Random();

	public static Generateur lireFichierXML(String chemin) throws ParserConfigurationException, SAXException, IOException {
		InputStream is=Files.newInputStream(Paths.get(chemin));
		Generateur gen = lireFluxXML(is);
		is.close();
		return gen;

	}
	public static Generateur lireFluxXML(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentumentBuilderFactory = DocumentBuilderFactory.newInstance();

		documentumentBuilderFactory.setNamespaceAware(true);

		DocumentBuilder documentumentBuilder = documentumentBuilderFactory.newDocumentBuilder();

		Document document = documentumentBuilder.parse(is);
		Generateur gen = new Generateur();
		NodeList nl = document.getChildNodes();
		nl = nl.item(0).getChildNodes();
		String tagName;
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			Composition composition = new Composition();
			 tagName = node.getNodeName();
			if (tagName.equals("composition")) {
				String compositionName=node.getAttributes().getNamedItem("nom").getTextContent();
				gen.compositions.put(compositionName, composition);
				NodeList nl2 = node.getChildNodes();
				for (int j = 0; j < nl2.getLength(); j++) {
					Node node2 = nl2.item(j);
					tagName = node2.getNodeName();
					if (tagName.equals("element")) {
						Element element = new Element();
						String elementName =node2.getAttributes().getNamedItem("nom").getTextContent();
						
						composition.elements.put(elementName, element);
						String s[] = node2.getAttributes().getNamedItem("compositions").getTextContent().split(" ");
						element.ajouterCompositions(s);

						s = node2.getAttributes().getNamedItem("bases").getTextContent().split(" ");
						element.ajouterBases(s);
						System.out.println(" composition = "+compositionName+" element ="+elementName+" compositions="+element.compositions+" bases ="+element.bases);
						NodeList nl3 = node2.getChildNodes();
						for (int k = 0; k < nl3.getLength(); k++) {
							Node node3 = nl3.item(k);
							if (node3.getNodeName().equals("region")) {
								String x = node3.getAttributes().getNamedItem("x").getTextContent();
								String y = node3.getAttributes().getNamedItem("y").getTextContent();
								String dx = node3.getAttributes().getNamedItem("dx").getTextContent();
								String dy = node3.getAttributes().getNamedItem("dy").getTextContent();
								String invX = node3.getAttributes().getNamedItem("invX").getTextContent();
								String invY = node3.getAttributes().getNamedItem("invY").getTextContent();
								Region r = new Region(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(dx),
										Float.parseFloat(dy), Boolean.parseBoolean(invX), Boolean.parseBoolean(invY));
								System.out.println(r);
								element.regions.add(r);
							}
						}
					}
				}
			}

		}
		;
		return gen;

	}
	public PlacementArbre generer(String composition, float dx, float dy, int n) {
		Region regionPlacement = new Region(0, 0, dx, dy, false, false);

		PlacementArbre p = compositions.get(composition).generer(this, n);
		p.region = regionPlacement;
		return p;

	}

	public int idx(int size) {
		if (size == 1) {
			return 0;
		}
		return random.nextInt(size - 1);
	}

}
