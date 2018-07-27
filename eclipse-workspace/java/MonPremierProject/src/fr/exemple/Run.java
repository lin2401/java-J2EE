package fr.exemple;

import fr.Personne;

public class Run {

	public static void main(String[] args) {
		// indiquer une instance de Personne
		// c'est aussi une variable locale de ma methode main

		Personne p1 = new Personne("DUPONT", "John", 25, 1.80);

		Personne p2 = new Personne();
		p2.setAge(45);
		p2.setNom("DURANT");
		p2.setPrenom("Arthur");
		p2.setTaille(1.70);

		// a gauche du signe = je peux mettre n'importe quel parent de ce qui est a
		// droite

		Personne e1 = new Employe(); // ou Object a la place de personne Object e1 = new Employe
		e1.setNom("DUPONT");
		((Employe) e1).setEntreprise("Harvest");
		e1.marcher();

		if (e1 instanceof Employe) {

			System.out.println("ok");
		}
//pas de polymorhisme sans avir fait lheritage

		// tout les objets en java ont une methode getClass
		// System.out.println(e1.getClass);

	}

	int[] tabEntier = new int[5];// c mieux de le declarer de cette façon
	// mon tableau commence de [0...4]
	tabEntier[0]=5; // [0]=5

	System.out.println(tabEntier[25]); // erreur car il n y a pas de valeur qui correspond a la case 25 du tableau
	}

	Personne[] tabPers = new Personne[5];

	tabPers[0]=new Personne("","",5,3D)

	int[] tab2 = { 4, 12, 23 };// 3 cases initialisées

	System.out.println(Arrays.toString(tab2));

}}
