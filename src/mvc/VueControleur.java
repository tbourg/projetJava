
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import gestionnaireLabyrinthe.Case;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public void start(Stage primaryStage) {

        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Label[][] tab = new Label[3][3];

        Text affichage = new Text("Rejoignez les symboles identiques:");
        affichage.setFont(Font.font("Verdana", 15));
        affichage.setFill(Color.BLACK);
        border.setTop(affichage);

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // TODO
                List<Node> list = new ArrayList<>(gPane.getChildren());
                gPane.getChildren().clear();
                for (int column = 0; column < 3; column++) {
                    for (int row = 0; row < 3; row++) {
                        Label l = (Label) list.get(3 * column + row);
                        Case c = m.grille.tab[column][row];
                        switch (c.etat) {
                            case 0:
                                l.setText("");
                                break;
                            case 1:
                                l.setText(String.valueOf(c.symboleId));
                                break;
                            case 2:
                                l.setText(c.d_1 + "->" + c.d_2);
                                break;
                        }
                        gPane.add(l, column, row);
                    }
                }
            }
        });

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {

                final int fColumn = column;
                final int fRow = row;

                final Label l = new Label("");
                l.setMinHeight(100);
                l.setMinWidth(100);
                if (m.grille.tab[column][row].estSymbole()) {
                    l.setText(String.valueOf(m.grille.tab[column][row].symboleId));
                }
                l.setBackground(new Background(new BackgroundFill(((((3 * column + row) % 2) == 0) ? Color.LAVENDER : Color.BLANCHEDALMOND), CornerRadii.EMPTY, Insets.EMPTY)));
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


        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("Labyrinthe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
