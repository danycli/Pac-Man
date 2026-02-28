package com.pacman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class gameOverstats {
    private Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),30);

    public void gameOverStatsPopup(int score, int NumofDiamondsCollected, int highScore, int totalDiamonds){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,450,500);
        
        Text totalScore = new Text("Score "+score);
        totalScore.setFont(font);
        totalScore.setFill(Color.WHITE);
        totalScore.setTranslateY(-110);

        Text numOfDiamonds = new Text("Diamonds "+NumofDiamondsCollected);
        numOfDiamonds.setFont(font);
        numOfDiamonds.setFill(Color.WHITE);
        numOfDiamonds.setTranslateY(-60);

        Text diamondstotal = new Text("Total Diamonds "+totalDiamonds);
        diamondstotal.setFont(font);
        diamondstotal.setFill(Color.WHITE);
        diamondstotal.setTranslateY(40);

        Text HighScore = new Text("High Score "+highScore);
        HighScore.setFont(font);
        HighScore.setFill(Color.WHITE);
        HighScore.setTranslateY(-10);

        Button close = GameOver.MenuButtons("Close",110);
        close.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),20));

        close.setOnAction(e ->{
            stage.close();
        });

        root.getChildren().add(numOfDiamonds);
        root.getChildren().add(close);
        root.getChildren().add(totalScore);
        root.getChildren().add(diamondstotal);
        root.getChildren().add(HighScore);

        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));

        stage.getIcons().add(player);
        stage.setTitle("Stats");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);

        EventHandler der = new EventHandler();
        der.deriveScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #16203f;
            -fx-background-radius: 30;
        """);

        stage.show();
    }
}
