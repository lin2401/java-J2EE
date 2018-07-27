package fr.banque;

public interface ICompteASeuil {

	public void retirer(double uneValeur) throws BanqueException;

	public double getSeuil();

	public void setSeuil(double unSeuil);
}