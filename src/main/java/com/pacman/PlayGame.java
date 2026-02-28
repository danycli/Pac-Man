package com.pacman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlayGame {
    private Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),30);
    public void playGame(EventHandler current){
        Stage stage2 = new Stage();
        StackPane root = new StackPane();
        Scene scene2 = new Scene(root, 500,700);

        Text PacMan = new Text("Pac Man");
        PacMan.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),60));
        PacMan.setFill(Color.WHITE);
        PacMan.setTranslateY(-230);

        Button play = GameOver.MenuButtons("Play",-130);
        play.setFont(font);

        Button settings = GameOver.MenuButtons("Settings",-30);
        settings.setFont(font);

        Button store = GameOver.MenuButtons("Store",70);
        store.setFont(font);

        Button Collection = GameOver.MenuButtons("Collection", 170);
        Collection.setFont(font);

        Button exitButton = GameOver.MenuButtons("Quit Game",270);
        exitButton.setFont(font);

        play.setOnAction(e ->{
            stage2.close();
            current.startGame();
        });
        settings.setOnAction(e ->{
            gameSettings set = new gameSettings();
            set.settingPopup();
        });
        exitButton.setOnAction(e ->{
            stage2.close();
        });
        Store showStore = new Store();
        store.setOnAction(e ->{
            showStore.store();
        });
        Collection collection = new Collection();
        Collection.setOnAction(e ->{
            collection.collection();
        });

        root.getChildren().add(play);
        root.getChildren().add(settings);
        root.getChildren().add(exitButton);
        root.getChildren().add(PacMan);
        root.getChildren().add(Collection);
        root.getChildren().add(store);

        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));

        stage2.setScene(scene2);
        stage2.setTitle("Play");
        stage2.getIcons().add(player);
        stage2.setResizable(false);
        stage2.setAlwaysOnTop(true);

        scene2.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ESCAPE){
                stage2.close();
            }
        });
        
        current.deriveScene(scene2);
        stage2.initStyle(StageStyle.UNDECORATED);

        stage2.initStyle(StageStyle.TRANSPARENT);
        scene2.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #0B132B;
            -fx-background-radius: 20;
        """);

        stage2.show();
    }
}
