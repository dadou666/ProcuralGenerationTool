package gen.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gen.Carre;
import gen.Composition;
import gen.Element;
import gen.Generateur;
import gen.Placement;
import gen.PlacementArbre;
import gen.PlacementFeuille;
import gen.Region;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		Generateur gen = gen();
		PlacementArbre p = gen.generer("A", 128, 128, 0);
		List<PlacementFeuille> list= new ArrayList<>();
		p.donnerPlacementFeuille(list);
		for(PlacementFeuille pf:list) {
			pf.regionAbsolu();
			
		}
		
		
	}
	@org.junit.jupiter.api.Test
	void testXML() throws ParserConfigurationException, SAXException, IOException {
		Generateur.lireFichierXML("src/gen.xml");
		
	}
	static public Generateur gen() {
		Generateur gen = new Generateur();
		gen.bases.put("Rouge", new Carre(Color.red));
		gen.bases.put("Bleue", new Carre(Color.blue));
		gen.bases.put("Green", new Carre(Color.green));
		Composition c ;
		Element e;
		Region a;
		c= new Composition();
		gen.compositions.put("A", c);
		
		e= new Element();
		c.elements.put("A",e);
		e.bases.add("Rouge");
		e.bases.add("Bleue");
		e.compositions.add("A");
	

		
		a= new Region();
		a.x=0;
		a.y=0;
		a.dx=0.5f;
		a.dy=0.5f;
		a.invX = false;
		a.invY = false;
		e.regions.add(a);
		
		a= new Region();
		a.x=0.5f;
		a.y=0;
		a.dx=0.5f;
		a.dy=0.5f;
		a.invX = true;
		a.invY = false;
		e.regions.add(a);
		
		a= new Region();
		a.x=0.5f;
		a.y=0.5f;
		a.dx=0.5f;
		a.dy=0.5f;
		a.invX = true;
		a.invY = true;
		e.regions.add(a);
		
		a= new Region();
		a.x=0.0f;
		a.y=0.5f;
		a.dx=0.5f;
		a.dy=0.5f;
		a.invX = false;
		a.invY = true;
		e.regions.add(a);
		
		
	
		
		
		return gen;

	}

}
