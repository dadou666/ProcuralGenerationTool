package gen;

public class Region {
	public float x;
	public float y;
	public float dx;
	public float dy;
	public boolean invX = false;
	public boolean invY = false;
	public Region() {
		
	}
	public Region(float x,float y,float dx,float dy,boolean invX,boolean invY) {
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.invX = invX;
		this.invY = invY;
	}
	public Region copier() {
		Region r = new Region(x,y,dx,dy,invX,invY);

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
