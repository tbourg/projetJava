
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import gestionnaireLabyrinthe.Case;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    Modele m;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        BorderPane pane = new BorderPane();

        ComboBox box = new ComboBox();
        box.setTooltip(new Tooltip("Quelle taille désirez-vous?"));
        
        box.setValue(3);

        box.getItems().add(3);
        box.getItems().add(4);
        box.getItems().add(5);
        
        Button button = new Button("Jouer");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    Stage game = new Stage();
                    game.setTitle("Partie de " + box.getValue() + "x" + box.getValue());
                    game.setScene(buildGame((int) box.getValue()));
                    game.show();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        /*box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
            }
        });*/
        pane.setCenter(box);

        pane.setLeft(new Text("Sélectionnez la taille de la grille:"));
        
        pane.setBottom(button);
        
        button.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(pane, Color.LIGHTBLUE);

        primaryStage.setTitle("Labyrinthe");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }

    public Scene buildGame(int size) throws FileNotFoundException {
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        Text affichage = new Text("Rejoignez les symboles identiques:");
        affichage.setFont(Font.font("Verdana", 15));
        affichage.setFill(Color.BLACK);
        border.setTop(affichage);

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Label[][] tab = new Label[size][size];
        
        int lvl = (int) (Math.random() * ( 3 - 0 ));
        // initialisation du modèle que l'on souhaite utiliser
        switch (size) {
            case 3:
                switch (lvl){
                    case 0:    
                        m = new Modele(size, 0, 0, 1, 2, 2, 0, 2, 2);
                        break;
                    case 1:
                        m = new Modele(size, 0, 2, 2, 0, 1, 0, 1, 1);
                        break;
                    case 2:
                        m = new Modele(size, 1, 0, 2, 2, 1, 1, 2, 0);
                        break;
                }
                break;
            case 4:
                switch (lvl){
                    case 0:    
                        m = new Modele(size, 0, 0, 3, 0, 1, 0, 2, 2);
                        break;
                    case 1:
                        m = new Modele(size, 0, 0, 2, 3, 1, 0, 3, 3);
                        break;
                    case 2:
                        m = new Modele(size, 1, 0, 0, 3, 2, 0, 1, 3);
                        break;
                }
                break;
            case 5:
                switch (lvl){
                    case 0:    
                        m = new Modele(size, 0, 0, 1, 1, 1, 2, 3, 3);
                        break;
                    case 1:
                        m = new Modele(size, 0, 0, 4, 4, 4, 0, 4, 3);
                        break;
                    case 2:
                        m = new Modele(size, 1, 4, 3, 3, 2, 0, 0, 4);
                        break;
                }
                break;
        }

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // TODO
                if (!m.grille.win) {
                    List<Node> list = new ArrayList<>(gPane.getChildren());
                    gPane.getChildren().clear();
                    for (int column = 0; column < size; column++) {
                        for (int row = 0; row < size; row++) {
                            try {
                                Label l = (Label) list.get(size * column + row);
                                Case c = m.grille.tab[column][row];
                                ImageView img;
                                switch (c.etat) {
                                    case 0:
                                        l.setGraphic(null);
                                        break;
                                    case 1:
                                        img = new ImageView(new Image(new FileInputStream(c.symbole.getPath())));
                                        img.setFitHeight(100);
                                        img.setFitWidth(100);
                                        l.setGraphic(img);
                                        break;
                                    case 2:

                                        img = new ImageView(new Image(new FileInputStream(c.lien.getPath())));
                                        img.setFitHeight(100);
                                        img.setFitWidth(100);
                                        l.setGraphic(img);
                                        break;
                                }
                                gPane.add(l, column, row);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    gPane.getChildren().clear();
                    gPane.add(new Text("VICTOIRE !!!"), 0, 0, size, size);
                    gPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });

        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {

                final int fColumn = column;
                final int fRow = row;

                final Label l = new Label("");
                l.setMinHeight(100);
                l.setMinWidth(100);
                if (m.grille.tab[column][row].estSymbole()) {
                    ImageView img = new ImageView(new Image(new FileInputStream(m.grille.tab[column][row].symbole.getPath())));
                    img.setFitHeight(100);
                    img.setFitWidth(100);
                    l.setGraphic(img);
                }
                l.setBackground(new Background(new BackgroundFill(Color.rgb(223, 135, 134), CornerRadii.EMPTY, Insets.EMPTY)));
                tab[column][row] = l;
                l.setFont(Font.font("Verdana", 25));

                l.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        Dragboard db = l.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(""); // non utilisé actuellement
                        db.setContent(content);
                        event.consume();
                        m.startDD(fColumn, fRow);
                    }
                });

                l.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {

                        m.parcoursDD(fColumn, fRow);
                        event.consume();
                    }
                });

                l.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {

                        // attention, le setOnDragDone est déclenché par la source du Drag&Drop
                        m.stopDD(fColumn, fRow);

                    }
                });

                gPane.add(tab[column][row], column, row);
            }
        }

        gPane.setHgap(5);
        gPane.setVgap(5);
        gPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
