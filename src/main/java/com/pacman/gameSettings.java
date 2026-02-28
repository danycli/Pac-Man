package com.pacman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class gameSettings {
    private static boolean setColor = false;
    private Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),20);

    public void settingPopup(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,300,200);
        

        Button darkMode = GameOver.MenuButtons("Dark Mode",-30);
        darkMode.setFont(font);
        darkMode.setTextFill(Color.WHITE);

        Button lightMode = GameOver.MenuButtons("Light Mode",30);
        lightMode.setFont(font);
        lightMode.setTextFill(Color.BLACK);

        darkMode.setOnAction(e ->{
            EventHandler.setSettingAction(1);
            stage.close();
            Menu.setSetPop(1);
            setColor = true;
        });
        lightMode.setOnAction(e ->{
            EventHandler.setSettingAction(1);
            stage.close();
            Menu.setSetPop(1);
            setColor = false;
        });


        root.getChildren().add(lightMode);
        root.getChildren().add(darkMode);

        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));

        stage.getIcons().add(player);
        stage.setTitle("Settings");
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

    public static Color MainSceneColor(){
        if(setColor == true){
            return Color.rgb(15, 4, 63);
        }else{
            return Color.rgb(186, 233, 153);
        }
    }
}
