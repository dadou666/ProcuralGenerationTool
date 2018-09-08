package gen;

import java.util.ArrayList;
import java.util.List;

public class Composition {
	public List<Element> elements = new ArrayList<Element>();

	public PlacementArbre generer(Generateur gen, int n) {
		PlacementArbre r = new PlacementArbre();
		for (Element element : elements) {
			element.generer(gen, r, n);

		}
		return r;

	}
}
