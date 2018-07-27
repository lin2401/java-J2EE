package fr.dessin;

public class Point3DBis {
	private int z;
	private Point2D p2d;

	public Point3DBis() {
		this.(0.0.0);

	}

public Point3DBis(int pX, int pY, int pZ) {

	this.z = pZ;
	this.p2d = new Point2D
}

	public void translater(int dX, int dY, int dZ) {
		this.p2d.translater(dX, dY);
		this.z += dZ;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return the p2d
	 */
	public Point2D getP2d() {
		return this.p2d;
	}

	/**
	 * @param p2d the p2d to set
	 */
	public void setP2d(Point2D p2d) {
		this.p2d = p2d;
	}

}
