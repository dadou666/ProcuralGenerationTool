package gen;

import java.util.ArrayList;
import java.util.List;

public class PlacementArbre extends Placement {
	public List<Placement> placements = new ArrayList<>();

	@Override
	public Placement copier() {
		PlacementArbre r = new PlacementArbre();
		if (region != null) {
			r.region = region.copier();
		}
		for (Placement p : this.placements) {
			Placement copie = p.copier();
			copie.parent = r;
			r.placements.add(copie);
		}
		return r;
	}

	@Override
	public void donnerPlacementFeuille(List<PlacementFeuille> list) {
		for (Placement p : placements) {
			p.donnerPlacementFeuille(list);
		}

	}

}
