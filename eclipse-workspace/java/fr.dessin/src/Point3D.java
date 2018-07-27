import fr.dessin.Point2D;

public class Point3D extends Point2D {

	private int z;

	public Point3D() {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.dessin.Point2D#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String p = super.toString();

		// virer le "]"
		p = p.substring(0, p.length() - 1);
		p += "," + this.getZ() + "]";
	}

	public Point3D() {

		this.setZ();
	}

	public Point3D(int vX, int vY, int vZ) {
		super(vX, vY);
		this.setZ(vZ);
		// TODO Auto-generated constructor stub
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
	public void setZ(int vZ) {
		this.z = vZ;

	}

//	@Override
//	public void afficher() {
//		System.out.println("[" + this.x + "," + this.y + this.z + "]");
//
//		System.out.println("[" + this.getX() + "," + this.getY() + "," + this.getZ() + "]");
//	} plus besoin de cette methode apres la definition de la methode toString()

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.dessin.Point2D#translater(int, int)
	 */

	public void translater(int dX, int dY, int dZ) {
		// TODO Auto-generated method stub
		super.translater(dX, dY);
		this.setZ(this.getZ() + dZ);
	}

}
