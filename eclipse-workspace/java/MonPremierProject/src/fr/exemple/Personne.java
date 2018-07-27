package fr.exemple;

public class Personne {

	
		// attributs
		//visbilité / typage / nom
		
		private String nom; 
		private String prenom; 
		private int age; 
		private double taille; 
public Personne() {} ; 
		
		
		
public Personne (String pNom, String pPrenom, int pAge, double pTaille) {
	    this.nom = nom; // age = pAge;
	    this.prenom = prenom; // nom = pNom;
	    this.age = age;//       prenom = pPrenom;
	    this.taille = taille;//  taille = pTaille;
	
	
}
		//metodes 
		//visibilité/code retour/nom/paramaetres
		
		
		
		public void parler (String message) { 
			
		System.out.println("message");
			
		}
		
		public int marcher() { 
			return 1;
		}
		
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		
		public double getTaille() {
			return taille;
		}

		public void setTaille(double taille) {
			this.taille = taille;
		}

		
	}


