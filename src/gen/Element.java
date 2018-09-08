package gen;

import java.util.ArrayList;
import java.util.List;

public class Element {
	public List<String> bases = new ArrayList<>();
	public List<String> compositions = new ArrayList<>();
	public List<Region> regions = new ArrayList<>();


	public void generer(Generateur gen, PlacementArbre arbre, int n) {
		if ((n <= 0 && !bases.isEmpty()) ) {
			String nomBase = this.bases.get(gen.idx(bases.size()));
			Base base = gen.bases.get(nomBase);
			for (Region region : regions) {
				PlacementFeuille pf = new PlacementFeuille();

				pf.base = base;
				pf.region = region.copier();
				arbre.placements.add(pf);
				pf.parent = arbre;
			}
			return;

		}
		if (compositions.isEmpty()) {
			return;
		}
		String nomComposition = this.compositions.get(gen.idx(compositions.size()));
		Composition composition = gen.compositions.get(nomComposition);
		PlacementArbre placement = composition.generer(gen, n-1);
		
		for(Region region:regions) {
			Placement p = placement.copier();
			p.parent = arbre;
			p.region =region.copier();
			arbre.placements.add(p);
			
		}

	}

}
