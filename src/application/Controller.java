package application;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Controller {
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private ImageView sideDrapeau;
    
    @FXML
    private ImageView sideTimerImage;
    
    @FXML
    private ImageView sideTimerRecordImage;
    
    @FXML
    private TextField sideNombreDrapeau;
    
    @FXML
    private TextField sideTimer;
    
    @FXML
    private TextField sideTimerRecord;
    
    private Timeline timer;   
    private long elapsedTime = 0;
    private boolean timerStarted = false; 
    
    private final int rows = 15;
    private final int cols = 21;
    private Grille grille;
    private int nbDrapeau;
    private boolean premierClic = true;
    
    private long minutes = 0;
    private long seconds = 0;
    private long milliseconds = 0;


    private Image image1_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/1_clair.png");
    private Image image1_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/1_sombre.png");
    
    private Image image2_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/2_clair.png");
    private Image image2_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/2_sombre.png");
    
    private Image image3_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/3_clair.png");
    private Image image3_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/3_sombre.png");
    
    private Image image4_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/4_clair.png");
    private Image image4_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/4_sombre.png");
    
    private Image image5_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/5_clair.png");
    private Image image5_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/5_sombre.png");
    
    private Image image6_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/6_clair.png");
    private Image image6_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/6_sombre.png");
    
    private Image image7_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/7_clair.png");
    private Image image7_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/7_sombre.png");
    
    private Image image8_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/8_clair.png");
    private Image image8_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/8_sombre.png");
    
    private Image imageBombe_clair = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/bombe_clair.png");
    private Image imageBombe_sombre = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/bombe_sombre.png");

    private Image imageDrapeau = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/drapeau.png");
	private Image imageDrapeau2 = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/Drapeau2.png");
	private Image videImage = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/vide.png");
	private Image vide2Image = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/vide2.png");
	
	private Image imageVideCreuse = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/videCreuse.png");
	private Image imageVideCreuse2 = new Image("file:///C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/videCreuse2.png");

	private ColorAdjust colorAdjust = new ColorAdjust();

	private File fileRecord = new File("C:\\Users\\loris\\eclipse-workspace\\Demineur\\src\\ressources\\fichiers\\fileRecord.txt");
	String contenuFichier = lireFichier(fileRecord);
    
	// initialise la grille, ainsi que tous ce qui est dans l'interface
    public void initialize() {
    	grille = new Grille();
        colorAdjust.setBrightness(0);

        
        int i = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                ImageView imageView1 = new ImageView(i % 2 == 0 ? videImage : vide2Image);
                i++;

                imageView1.setFitHeight(31);
                imageView1.setFitWidth(31);

                grid.add(imageView1, col, row);
                
                ajoutActionCaseVide(imageView1);
                ajoutFiltreCaseVide(imageView1);
                
            }

        }
        sideDrapeau.setImage(new Image("file:/C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/sideDrapeau.png"));
        sideTimerImage.setImage(new Image("file:/C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/sideTimer.png"));
        sideTimerRecordImage.setImage(new Image ("file:/C:/Users/loris/eclipse-workspace/Demineur/src/ressources/images/sideTimerRecordImage.png"));
        nbDrapeau = grille.getNB_BOMBES();
        sideNombreDrapeau.setText(String.valueOf(nbDrapeau));
        sideTimerRecord.setText(contenuFichier);
        
        timer = new Timeline();
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
            elapsedTime += 10;
            
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            milliseconds = elapsedTime % 1000;

            sideTimer.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
        });

        timer.getKeyFrames().add(keyFrame); 
        timer.setCycleCount(Timeline.INDEFINITE);
    }
    
   

    
    // Met à jour la grille et l'inteface après chaque clique
    public void MajValeurImageEtGrille(ImageView imageView ,Set<ImageView> processedImages) {
        if (processedImages.contains(imageView)) {
            return;
        }
        processedImages.add(imageView);

        
    	Cases cases = grille.getCase(GridPane.getRowIndex(imageView), GridPane.getColumnIndex(imageView));
        int valeur = cases.getValeur();
        int couleur = cases.getCouleur();  // 1 = clair, 2 = sombre

        switch (valeur) {
            case 1:
                if (couleur == 1) {
                    imageView.setImage(image1_clair);
                } else {
                    imageView.setImage(image1_sombre);
                }
                break;

            case 2:
                if (couleur == 1) {
                    imageView.setImage(image2_clair);
                } else {
                    imageView.setImage(image2_sombre);
                }
                break;

            case 3:
                if (couleur == 1) {
                    imageView.setImage(image3_clair);
                } else {
                    imageView.setImage(image3_sombre);
                }
                break;
                
            case 4:
                if (couleur == 1) {
                    imageView.setImage(image4_clair);
                } else {
                    imageView.setImage(image4_sombre);
                }
                break;

            case 5:
                if (couleur == 1) {
                    imageView.setImage(image5_clair);
                } else {
                    imageView.setImage(image5_sombre);
                }
                break;

            case 6:
                if (couleur == 1) {
                    imageView.setImage(image6_clair);
                } else {
                    imageView.setImage(image6_sombre);
                }
                break;
                
            case 7:
                if (couleur == 1) {
                    imageView.setImage(image7_clair);
                } else {
                    imageView.setImage(image7_sombre);
                }
                break;

            case 8:
                if (couleur == 1) {
                    imageView.setImage(image8_clair);
                } else {
                    imageView.setImage(image8_sombre);
                }
                break;

            case 9:
                if (couleur == 1) {
                    imageView.setImage(imageBombe_clair);
                    ouvreFenetrePerdu();
                } else {
                    imageView.setImage(imageBombe_sombre);
                    ouvreFenetrePerdu();
                }
                break;

            default: 
                if (couleur == 1) {
                    imageView.setImage(imageVideCreuse);  
                } else {
                    imageView.setImage(imageVideCreuse2);  
                }
                break;
        }

        imageView.setDisable(true);
        
        if (valeur == 0) {
            int x = cases.getX();
            int y = cases.getY();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = x + i;
                    int newY = y + j;
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                        ImageView neighborImageView = getImageAt(grid, newY, newX);
                        if (neighborImageView != null) {
                            MajValeurImageEtGrille(neighborImageView, processedImages);
                        }
                    }
                }
            }
        }
        
        grille.getCase(GridPane.getRowIndex(imageView), GridPane.getColumnIndex(imageView)).setRevelee(true);
        if(checkGagne()) {
        	ouvreFenetreGagne();
        }
    }
    
    //permet de poser un petit filtre sur la case où se situe la souris
    public void ajoutFiltreCaseVide(ImageView imageView) {
    	imageView.setOnMouseEntered(event -> {
            colorAdjust.setBrightness(0.17);
            imageView.setEffect(colorAdjust);
        });
    	
    	 imageView.setOnMouseExited(event -> {
             colorAdjust.setBrightness(0);
             imageView.setEffect(null);
         });
    }

    // permet de donner aux cases vides leur actions avec clique
    public void ajoutActionCaseVide(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int rowClicked = GridPane.getRowIndex(imageView);
                int colClicked = GridPane.getColumnIndex(imageView);

                if (premierClic) {
                    grille.initGrille(rowClicked, colClicked);
                    // (Pour tricher ) grille.afficherGrille();
                    premierClic = false;
                    
                    if (!timerStarted) {
                        timerStarted = true;
                        timer.play();
                    }
                    
                }

                MajValeurImageEtGrille(imageView, new HashSet<>());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                changeImageDrapeau(imageView);
            }
        });
    }
	
    // Permet de gerer les actions pour les cases avec un drapeau
    public void ajoutActionCaseDrapeau(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                nbDrapeau += 1;
                sideNombreDrapeau.setText(String.valueOf(nbDrapeau));
                if (GridPane.getRowIndex(imageView) % 2 == 0) {
                    if (GridPane.getColumnIndex(imageView) % 2 == 0) {
                        imageView.setImage(videImage);
                    } else {
                        imageView.setImage(vide2Image);
                    }
                } else {
                    if (GridPane.getColumnIndex(imageView) % 2 == 0) {
                        imageView.setImage(vide2Image);
                    } else {
                        imageView.setImage(videImage);
                    }
                }
                ajoutActionCaseVide(imageView);
            }
        });
    }

    // Permet de changer l'image de la case par un drapeau
    public void changeImageDrapeau(ImageView imageView) {
            nbDrapeau -= 1;
            sideNombreDrapeau.setText(String.valueOf(nbDrapeau));
            if (imageView.getImage().equals(vide2Image)) {
                imageView.setImage(imageDrapeau2);
            } else if (imageView.getImage().equals(videImage)) {
                imageView.setImage(imageDrapeau);
            }
            ajoutActionCaseDrapeau(imageView);
    }

    
    //ouvre la fenetre de defaite
    @FXML
    private void ouvreFenetrePerdu() {
        timer.stop();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fenetre_perdu.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Défaite");
            stage.setScene(new Scene(root));

            desactiverGrille();
            
            stage.setOnCloseRequest(event -> {
                Stage primaryStage = (Stage) sideTimer.getScene().getWindow();
                primaryStage.close();
                stage.close();
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //ouvre la fenetre de victoire
    @FXML
    private void ouvreFenetreGagne() {
    	timer.stop();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fenetre_gagne.fxml"));
            Parent root = loader.load();
            Stage secondStage = new Stage();
            
            secondStage.setTitle("Gagne");
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(new Scene(root));
            secondStage.setResizable(false);
            
            long totalMilliseconds = 0;
            desactiverGrille();
            
            //permet de vérifier si le record n'as pas été battu / si il y n'y a pas de record
            if(!(contenuFichier == null)) {

            	String[] timeParts = contenuFichier.split(":");
            	long minutes = Long.parseLong(timeParts[0]);
            	long seconds = Long.parseLong(timeParts[1]);
            	long milliseconds = Long.parseLong(timeParts[2]);
            	
            	totalMilliseconds = (minutes * 60000) + (seconds * 1000) + milliseconds;
            }

            if(elapsedTime < totalMilliseconds || (contenuFichier == null)) {
                long newMinutes = (elapsedTime / 60000) % 60;
                long newSeconds = (elapsedTime / 1000) % 60;
                long newMilliseconds = elapsedTime % 1000;
                ecrireFichier(fileRecord, String.format("%02d:%02d:%03d", newMinutes, newSeconds, newMilliseconds));
            }
            
            
            secondStage.setOnCloseRequest(event -> {
                Stage primaryStage = (Stage) sideTimer.getScene().getWindow();
                primaryStage.close();
                secondStage.close();
            });
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //permet de verifier si le jeu est gagné ou non
    public boolean checkGagne() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cases currentCase = grille.getCase(row,col);
                if (currentCase.getValeur() != 9 && !currentCase.isRevelee()) {
                    return false; 
                }
            }
        }
        return true;
    }
    

    //permet de récuperer l'image dans la gridpane de coordonnées x y
    public ImageView getImageAt(GridPane gridPane, int column, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer nodeColumn = GridPane.getColumnIndex(node);
            Integer nodeRow = GridPane.getRowIndex(node);

            if (nodeColumn == column && nodeRow == row) {
               return (ImageView) node;
            }
        }
        return null;
    }
    
    // Désactiver toutes les cases de la grille pour empêcher les clics
    private void desactiverGrille() {
        for (Node node : grid.getChildren()) {
            node.setDisable(true);
        }
    }

    
    // Permet de recréer une fenetre, et donc un nouveau jeu
    public void restartGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            
            Stage newStage = new Stage();
            newStage.setTitle("Jeu");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setScene(new Scene(root));
            newStage.setResizable(false);
            newStage.show();
            
            Stage currentStage = (Stage) grid.getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Permet de lire un fichier et de retourner son contenu
    public String lireFichier(File file) {
        String ligne = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ligne = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ligne;
    }

    // Permet d'écrire un fichier avec du nouveau contenu
    public static void ecrireFichier(File file, String contenu) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(contenu); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
