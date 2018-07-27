package fr.dessin;

public class Run {

	public static void main(String[] args) {

		Point2D p1 = new Point2D(3, 4);
		Point2D p2 = new Point2D(2, 5);

		p1.afficher();
		p2.afficher();
		p1.translater(2, 2);
		p1.afficher();

		System.out.println(Point2D.getCompteur());

		Point3D p3 = new Point3D(2, 4, 6);
		p3.afficher();
		p3.translater(1, 3, 5);

		System.out.println(Point2D.getCompteur);
	}

}
