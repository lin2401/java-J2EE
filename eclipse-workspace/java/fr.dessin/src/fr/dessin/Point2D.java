package fr.dessin;

public class Point2D {

	private int x;
	private int y;
	private static int compteur;

	public Point2D() {
		// ici chainage le consructeur le plus bete apelle le consructeur le plus
		// inteligent et sur this F3

		this(0, 0);

	}

	public Point2D(int vX, int vY) {

		this.x = vX;// cest possible aussi de faire : this.setX(vX);
		this.y = vY;// this.setY(vY);

		Point2D.compteur++; // pour un element static on doit faire nomclasse.atribut static

	}

	public int getX() {
		return this.x;
	}

	public void setX(int valX) {
		this.x = valX;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int valY) {
		this.y = valY;
	}

	public static int getCompteur() {

		return Point2D.compteur;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point2D [x=");
		builder.append(this.x);
		builder.append(", y=");
		builder.append(this.y);
		builder.append("]");
		return builder.toString();
	}

	// toString cest pour fabriquer une chaine de caractere

	public void translater(int dX, int dY) {

		this.x = this.x + dX;
		this.y = this.y + dY;
		// on peut faire aussi : x+= dx y+= dy ou setX(getX()) + dX); setY(getY() + dY);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	public void afficher() {

		System.out.println(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
