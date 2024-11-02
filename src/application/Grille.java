package application;
import java.util.ArrayList;
import java.util.Random;

public class Grille {

    private ArrayList<ArrayList<Cases>> grille;
    private final int rows = 15;
    private final int cols = 21;
    private int valeurCouleur;
    Random random = new Random();
    private final int NB_BOMBES = 40;
    
    public Grille() {
        grille = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            ArrayList<Cases> ListesLignes = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
            	trouverValeurCouleur(row, col);
                ListesLignes.add(new Cases(row, col, 0, valeurCouleur, false));
            }
            grille.add(ListesLignes);
        }
        
    }
    
    public void initGrille(int rowClicked, int colClicked) {
        placerBombe(rowClicked, colClicked);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grille.get(row).get(col).getValeur() == 0) {
                    placerValeur(grille.get(row).get(col));
                }
            }
        }
    }


    
    public int getNB_BOMBES() {
	    return NB_BOMBES;
    }
    public void placerBombe(int rowClicked, int colClicked) {
        int i = 0;
        ArrayList<Integer> listeBombe = new ArrayList<>();
        while (i < NB_BOMBES) {
            int valeurCasesAléatoire = random.nextInt(rows * cols);
            int row = valeurCasesAléatoire / cols;
            int col = valeurCasesAléatoire % cols;

            if (!listeBombe.contains(valeurCasesAléatoire) && !pasAutour(row, col, rowClicked, colClicked)) {
                listeBombe.add(valeurCasesAléatoire);
                i++;
                grille.get(row).get(col).setValeur(9);
            }
        }
    }

    private boolean pasAutour(int row, int col, int rowClicked, int colClicked) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row == rowClicked + i && col == colClicked + j) {
                    return true;
                }
            }
        }
        return false;
    }

    
    public void placerValeur(Cases cases) {
    	int x = cases.getX();
    	int y = cases.getY();
    	int bombeCollé = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x + i < 0 || x + i >= rows || y + j < 0 || y + j >= cols) {
					continue;
				}
				if (grille.get(x + i).get(y + j).getValeur() == 9) {
						bombeCollé++;
					
				}
			}
		}
		cases.setValeur(bombeCollé);
    	
    }
    
    // 1 = gris claire
    // 2 = gris fonce
    
	public void trouverValeurCouleur(int row, int col) {
		if (row % 2 == 0) {
			if (col % 2 == 0) {
				valeurCouleur = 1;
			} else {
				valeurCouleur = 2;
			}
		} else {
			if (col % 2 == 0) {
				valeurCouleur = 2;
			} else {
				valeurCouleur = 1;
			}
		}
	}
	
	public Cases getCase(int row, int col) {
		return grille.get(row).get(col);
	}
	
	
    
	
	// TEST AFFICHER GRILLE
	public void afficherGrille() {
	    for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            System.out.print(grille.get(row).get(col).getValeur() + " ");
	        }
	        System.out.println();
	    }
	}

	
	
}
