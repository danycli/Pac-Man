package com.pacman;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Entities {
    
    public ArrayList<ImageView> spawnEnemies(Stage stage,Group root, Scene scene,double base, double radius){
        ArrayList<ImageView> allEnimies = new ArrayList<>();
        
        Image enemy1 = new Image(getClass().getResourceAsStream("/Images/yellow_Enemy.png"));
        ImageView yellowEnemy = enimies(enemy1,stage,root,scene,base,radius);
        allEnimies.add(yellowEnemy);

        Image enemy2 = new Image(getClass().getResourceAsStream("/Images/blue_Enemy.png"));
        ImageView blueEnemy = enimies(enemy2,stage,root,scene,base,radius);
        allEnimies.add(blueEnemy);

        Image enemy3 = new Image(getClass().getResourceAsStream("/Images/cyan_Enemy.png"));
        ImageView cyanEnemy = enimies(enemy3,stage,root,scene,base,radius);
        allEnimies.add(cyanEnemy);

        Image enemy4 = new Image(getClass().getResourceAsStream("/Images/purple_Enemy.png"));
        ImageView purpleEnemy = enimies(enemy4,stage,root,scene,base,radius);
        allEnimies.add(purpleEnemy);

        Image enemy5 = new Image(getClass().getResourceAsStream("/Images/red_Enemy.png"));
        ImageView redEnemy = enimies(enemy5,stage,root,scene,base,radius);
        allEnimies.add(redEnemy);

        return allEnimies;
    }

    private ImageView enimies(Image image,Stage stage,Group root, Scene scene,double base, double radius){
        ImageView en = new ImageView(image);

        double x_axis = 0;
        double y_axis = 0;
            
        x_axis = (Math.random()*(stage.getWidth()));
        y_axis = (Math.random()*(stage.getHeight()));

        en.setTranslateX(x_axis);
        en.setTranslateY(y_axis);

        double x = en.getTranslateX();
        double y = en.getTranslateY();

        double Swidth = stage.getWidth();
        double Sheight = stage.getHeight();

        x = Math.max(radius, Math.min(x, Swidth - radius));
        y = Math.max(radius+base, Math.min(y, Sheight - radius));


        en.setTranslateX(x);
        en.setTranslateY(y);
        root.getChildren().add(en);

        return en;
    } 
}
