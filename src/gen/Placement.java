package gen;

import java.util.List;

abstract public class Placement {
	public PlacementArbre parent;

	abstract public Placement copier();

	public Region region;
	private Region regionAbsolu;

	public Region regionAbsolu() {
		if (regionAbsolu == null) {
			if (parent == null) {
				regionAbsolu = region;
			} else {
				
				regionAbsolu = region.regionPlacement(parent.regionAbsolu());
			}
		}
		return regionAbsolu;
	}
	abstract public void donnerPlacementFeuille(List<PlacementFeuille> list);

}
