package gen;

import java.util.List;

public class PlacementFeuille extends Placement {
	public Base base;

	@Override
	public Placement copier() {
		PlacementFeuille r = new PlacementFeuille();
		r.base = base;
		r.region = region.copier();
		

		return r;
	}

	@Override
	public void donnerPlacementFeuille(List<PlacementFeuille> list) {
		list.add(this);
		
	}

}
