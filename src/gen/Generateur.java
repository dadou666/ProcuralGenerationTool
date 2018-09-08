package gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Generateur {
	public Map<String, Base> bases = new HashMap<String, Base>();
	public Map<String, Composition> compositions = new HashMap<String, Composition>();

	public Random random = new Random();

	public PlacementArbre generer(String composition, float dx, float dy, int n) {
		Region regionPlacement = new Region();
	
		regionPlacement.dx = dx;
		regionPlacement.dy = dy;
		regionPlacement.x = 0.0f;
		regionPlacement.y = 0.0f;
		regionPlacement.invX = false;
		regionPlacement.invY = false;
		PlacementArbre p=compositions.get(composition).generer(this,  n);
		p.region = regionPlacement;
		return p;

	}
	public int idx(int size) {
		if (size == 1) {
			return 0;
		}
		return random.nextInt(size-1);
	}

}
