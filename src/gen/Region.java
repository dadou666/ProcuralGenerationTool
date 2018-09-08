package gen;

public class Region {
	public float x;
	public float y;
	public float dx;
	public float dy;
	public boolean invX = false;
	public boolean invY = false;
	public Region copier() {
		Region r = new Region();
		r.x=x;
		r.y=y;
		r.dx=dx;
		r.dy=dy;
		r.invX=invX;
		r.invY=invY;
		return r;
	}

	public Region regionPlacement(Region regionParent) {
		Region regionPlacement = new Region();

		regionPlacement.invX = invX != regionParent.invX;
		regionPlacement.invY = invY != regionParent.invY;
		regionPlacement.dx = dx * regionParent.dx;
		regionPlacement.dy = dy * regionParent.dy;
		if (regionParent.invX) {
			regionPlacement.x = regionParent.x + regionParent.dx - regionParent.dx * x - dx * regionParent.dx;

		} else {
			regionPlacement.x = regionParent.x + regionParent.dx * x;

		}
		if (regionParent.invY) {
			regionPlacement.y = regionParent.y + regionParent.dy - regionParent.dy * y - dy * regionParent.dy;

		} else {
			regionPlacement.y = regionParent.y + regionParent.dy * y;

		}
		return regionPlacement;
	}

	public String toString() {
		return " x=" +  x + " y=" +  y + " dx=" +  dx + " dy=" +  dy;
	}
}
