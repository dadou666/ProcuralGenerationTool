package gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Composition {
	public Map<String,Element> elements = new HashMap<>();

	public PlacementArbre generer(Generateur gen, int n) {
		PlacementArbre r = new PlacementArbre();
		for (Map.Entry<String,Element> e : elements.entrySet()) {
			e.getValue().generer(gen, r, n);

		}
		return r;

	}
}
