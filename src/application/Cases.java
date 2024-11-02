package application;

public class Cases {

	private int x;
	private int y;
	private int valeur;
	private int couleur;
	private boolean revelee;
	
	public Cases(int x, int y, int valeur, int couleur, boolean revelee) {
		this.x = x;
		this.y = y;
		this.valeur = valeur;
		this.couleur = couleur;
		this.revelee = revelee;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public int getCouleur() {
		return couleur;
	}
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
	public boolean isRevelee() {
		return revelee;
	}
	public void setRevelee(boolean revelee) {
		this.revelee = revelee;
	}
}
