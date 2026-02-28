package com.pacman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOver {
    private Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),30);
    public void ShowMenu(EventHandler current, int score, int numOfDiamonds, int totalDiamonds, int highScore){
        Stage stage2 = new Stage();
        StackPane root = new StackPane();
        Scene scene2 = new Scene(root, 500,700);

        Text gameOver = new Text("Game Over");
        gameOver.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),60));
        gameOver.setFill(Color.WHITE);
        gameOver.setTranslateY(-230);

        Button playAgain = MenuButtons("Play Again",-130);
        playAgain.setFont(font);

        Button stats = MenuButtons("View Stats",-30);
        stats.setFont(font);

        Button store = GameOver.MenuButtons("Store",70);
        store.setFont(font);

        Button Collection = GameOver.MenuButtons("Collection", 170);
        Collection.setFont(font);

        Button exitButton = MenuButtons("Quit Game",270);
        exitButton.setFont(font);

        gameOverstats gameStats = new gameOverstats();

        playAgain.setOnAction(e ->{
            stage2.close();
            current.startGame();
        });
        stats.setOnAction(e ->{
            gameStats.gameOverStatsPopup(score, numOfDiamonds, highScore, totalDiamonds);
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

        root.getChildren().add(playAgain);
        root.getChildren().add(stats);
        root.getChildren().add(exitButton);
        root.getChildren().add(gameOver);
        root.getChildren().add(store);
        root.getChildren().add(Collection);

        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));

        stage2.setScene(scene2);
        stage2.setTitle("Game Over");
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
    public static Button MenuButtons(String text, double y){
        Button b = new Button(text);
        b.setTranslateY(y);
        b.setBorder(Border.EMPTY);
        b.setFocusTraversable(false);
        b.setTextFill(Color.WHITE);
        b.setStyle("""
                -fx-background-color: #ffffff43;
                -fx-background-radius:20;
                """);
        b.setOnMouseExited((MouseEvent t) ->{
            b.setStyle("""
                -fx-background-color: #ffffff43;
                -fx-background-radius:20;
                """);
        });
        b.setOnMouseEntered((MouseEvent t) -> {
            b.setStyle("""
                    -fx-background-color: #ffffff79;
                    -fx-background-radius:20;
                    """);
        });
        return b;
    }
}
