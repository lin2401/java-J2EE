package fr.banque;

public interface ICompteRemunere {

	public double calculerInteret();

	public void verserInteret();

	public double getTaux();

	public void setTaux(double unTaux);

}
