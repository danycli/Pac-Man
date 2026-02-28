package com.pacman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventHandler extends Application{
    private boolean upPressed ;
    private boolean downPressed ;
    private boolean leftPressed ;
    private boolean rightPressed ;
    private double rotate ;
    private int score ;
    private static int pauseAction ;
    private static int settingAction ;
    private boolean SceneChecking ;
    private AnimationTimer gameLoop = null;
    private double enemySpeed ;
    private int NumofDiamondsCollected ;
    private int highScore ;
    private int totalDiamonds;
    private long startTime;
    private long endTime;
    private long pauseTime;
    private int countDiamond;

    @Override
    public void start(Stage arg0) throws Exception {
        PlayGame play = new PlayGame();
        play.playGame(EventHandler.this);
    }
    public void startGame(){
        startTime = System.nanoTime();
        endTime = 0;
        pauseTime = 0;
        if (gameLoop != null) gameLoop.stop();
        enemySpeed = 1;
        ArrayList<Double> enemyDirX = new ArrayList<>();
        ArrayList<Double> enemyDirY = new ArrayList<>();
        score = 0;
        rotate = 0;
        pauseAction = 1;
        settingAction = 1;
        gameLoop = null;
        upPressed = downPressed = leftPressed = rightPressed = false;
        SceneChecking = false;
        NumofDiamondsCollected = 0;
        FileHandling();
        countDiamond = totalDiamonds;

        Stage stage = new Stage();

        Group root = new Group();

        Scene scene = new Scene(root);

        String skinName = "7Pac_Man_Favicon.png";

        try{
            File file = SaveManager.getStatsFile();
            if(file.createNewFile()){
                BufferedWriter write = new BufferedWriter(new FileWriter(file));
                write.write("Score = 0");
                write.newLine();
                write.write("Total Diamond = 0");
                write.newLine();
                write.write("7Pac_Man_Favicon.png");
                write.close();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = null;
            int count = 0;

            while((line = read.readLine()) != null){
                if(line.endsWith(".png") || count == 2){
                    skinName = line;
                    break;
                }
                count++;
            }
            read.close();
        }
        catch(IOException j){
            System.out.println("Something Went Wrong while getting the skin name");
        }

        Image skin = new Image(getClass().getResourceAsStream("/Images/PacSkins/"+skinName));
        ImageView circle = new ImageView(skin);
        circle.setRotate(rotate);
        circle.setPreserveRatio(true);

        double CircleRadius = circle.getLayoutBounds().getWidth();
        //Spawning at diff locations

        int x_axis = (int)(Math.random()*(1000 - CircleRadius + 1) + CircleRadius);
        int y_axis = (int)(Math.random()*(1000 - CircleRadius + 1) + CircleRadius);
        circle.setTranslateX(x_axis);
        circle.setTranslateY(y_axis);
        Text ScoreCard = new Text("Score "+ score);
        ScoreCard.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),50));
        ScoreCard.setTranslateY(50);
        ScoreCard.setTranslateX(5);
        ScoreCard.setFill(Color.WHITE);
        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setRadius(10);
        ScoreCard.setEffect(textShadow);

        Image diamondIcon = new Image(getClass().getResourceAsStream("/Images/OnScreenDiamond.png"));
        ImageView diamond = new ImageView(diamondIcon);
        diamond.setTranslateX(400);
        diamond.setTranslateY(15);
        diamond.setEffect(textShadow);

        Text diamondCount = new Text(""+countDiamond);
        diamondCount.setFill(Color.WHITE);
        diamondCount.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),40));
        textShadow.setRadius(10);
        diamondCount.setEffect(textShadow);
        diamondCount.setTranslateX(diamond.getTranslateX()+ 55);
        diamondCount.setTranslateY(diamond.getTranslateY() + 35);

        Rectangle rec = new Rectangle();
        rec.setTranslateX(0);
        rec.setTranslateY(0);
        rec.setWidth(ScoreCard.getLayoutBounds().getWidth() + 10);
        rec.setHeight(ScoreCard.getLayoutBounds().getHeight() + 10);
        rec.setArcWidth(50);
        rec.setArcHeight(50);
        rec.setFill(Color.YELLOW);

        Image settingsIcon = new Image(getClass().getResourceAsStream("/Images/setting.png"));
        ImageView icon = new ImageView(settingsIcon);

        Image pause = new Image(getClass().getResourceAsStream("/Images/PauseButton.png"));
        ImageView pauseView = new ImageView(pause);
        pauseView.setFitHeight(40);
        pauseView.setFitWidth(35);

        Button pauseButton = new Button();
        pauseButton.setGraphic(pauseView);
        pauseButton.setTranslateY(7);
        pauseButton.setFocusTraversable(false);
        pauseButton.setBackground(Background.fill(Color.YELLOW));

        Button settings = new Button();
        settings.setGraphic(icon);
        settings.setTranslateY(3);
        settings.setFocusTraversable(false);
        settings.setBackground(Background.fill(Color.YELLOW));

        Rectangle topRect = new Rectangle();
        topRect.setHeight(62);
        topRect.setFill(Color.YELLOW);
        textShadow.setRadius(30);
        topRect.setEffect(textShadow);

        Text HIGHScore = new Text("High Score "+highScore);
        HIGHScore.setFill(Color.WHITE);
        HIGHScore.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),50));
        textShadow.setRadius(10);
        HIGHScore.setEffect(textShadow);

        root.getChildren().add(topRect);
        root.getChildren().add(rec);
        root.getChildren().add(circle);
        root.getChildren().add(diamond);
        root.getChildren().add(diamondCount);
        root.getChildren().add(HIGHScore);
        root.getChildren().add(ScoreCard);
        root.getChildren().add(settings);
        root.getChildren().add(pauseButton);
        

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Pac Man");
        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));
        stage.getIcons().add(player);
        stage.show(); 
        HIGHScore.setTranslateX(stage.getWidth() - 650);
        HIGHScore.setTranslateY(50);
        root.setFocusTraversable(true);
        root.requestFocus();
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());

        topRect.setWidth(stage.getWidth());
        settings.setTranslateX(stage.getWidth()-75);
        pauseButton.setTranslateX(stage.getWidth()-130);

        GameOver gameOver = new GameOver();
        Menu menu = new Menu();
        Pickables maze = new Pickables();
        ArrayList<ImageView> axisOfBait = maze.generatingMaze(stage,root,scene,rec.getHeight(),CircleRadius);

        Entities entities = new Entities();
        ArrayList<ImageView> allEnimies = entities.spawnEnemies(stage,root,scene,rec.getHeight(),CircleRadius);

        enemyDirX.clear();
        enemyDirY.clear();

        for(int i = 0; i < allEnimies.size(); i++){

            double dx = Math.random()*2 - 1;
            double dy = Math.random()*2 - 1;

            double length = Math.sqrt(dx*dx + dy*dy);

            dx /= length;
            dy /= length;

            enemyDirX.add(dx);
            enemyDirY.add(dy);
        }

        scene.setOnKeyPressed(e ->{
            switch(e.getCode()){
                case W -> {
                    upPressed = true;
                }
                case S -> {
                    downPressed = true;
                }
                case D -> {
                    rightPressed = true;
                }
                case A -> {
                    leftPressed = true;
                }
                case ESCAPE -> {
                    if(pauseAction == 1 && settingAction == 1){
                        stage.setX(0);
                        stage.setY(0);
                        menu.ShowMenu();
                        pauseAction++;
                    }
                }
                case F11 -> {
                    stage.setFullScreen(true);
                }

                default -> System.out.println();
            }
        });

        scene.setOnKeyReleased(e ->{
            switch(e.getCode()){
            case W -> upPressed = false;
            case S -> downPressed = false;
            case D -> rightPressed = false;
            case A -> leftPressed = false;

            default -> System.out.println();
            }
        });

        settings.setOnAction(e ->{

            stage.setFullScreen(false);
            stage.setX(0);
            stage.setY(0);
            if(settingAction == 1 && pauseAction == 1){
                settingAction++;
                gameSettings set = new gameSettings();
                set.settingPopup();
            }
        });
        pauseButton.setOnAction(e ->{
            stage.setFullScreen(false);
            stage.setX(0);
            stage.setY(0);
            if(pauseAction == 1 && settingAction == 1){
                pauseAction++;
                menu.ShowMenu();
            }
        });

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double x = circle.getTranslateX();
                double y = circle.getTranslateY();
                double move = 10;

                if(SceneChecking == true){
                    upPressed = false;
                    downPressed = false;
                    rightPressed = false;
                    leftPressed = false;
                    pauseTime += System.nanoTime();
                }
                
            
                if (upPressed && !downPressed && !rightPressed && !leftPressed){
                    circle.setScaleX(1);
                    rotate = -90;
                    circle.setRotate(rotate);
                    y -= move;
                }
                else if (downPressed && !upPressed && !rightPressed && !leftPressed) {
                    circle.setScaleX(1);
                    rotate = 90;
                    circle.setRotate(rotate);
                    y += move;
                }
                else if (leftPressed && !downPressed && !upPressed && !rightPressed){
                    rotate = 0;
                    circle.setRotate(rotate);
                    circle.setScaleX(-1);
                    x -= move;
                } 
                else if (rightPressed && !leftPressed && !downPressed && !upPressed){
                    circle.setScaleX(1);
                    rotate = 0;
                    circle.setRotate(rotate);
                    x += move;
                }
                
                // Boundary checking
                double radius = CircleRadius;
                double Swidth = stage.getWidth();
                double Sheight = stage.getHeight();

                x = Math.max(0, Math.min(x, Swidth - radius));
                y = Math.max(radius+13, Math.min(y, Sheight - radius));

                circle.setTranslateX(x);
                circle.setTranslateY(y);

                // Euclian Distance Formula for Distance between two points = √[(x₂ - x₁)² + (y₂ - y₁)²]
                for(int i = axisOfBait.size() - 1 ; i >= 0; i--) {
                    double distance = Math.sqrt(Math.pow(circle.getTranslateX() - axisOfBait.get(i).getTranslateX(), 2) + Math.pow(circle.getTranslateY() - axisOfBait.get(i).getTranslateY(), 2));
                    distance += 20;
                        //if the distance bw bait and the player is less than the sum of their radii so remove the bait
                        if(distance < CircleRadius + axisOfBait.get(i).getLayoutBounds().getWidth()) {
                        root.getChildren().remove(axisOfBait.get(i));
                        if(axisOfBait.get(i).getId().equals("Cherry")){
                            score+=2;
                        }else if(axisOfBait.get(i).getId().equals("Diamond")){
                            score += 5;
                            countDiamond++;
                            diamondCount.setText(""+countDiamond);
                            NumofDiamondsCollected++;
                        }
                        ScoreCard.setText("Score "+score);
                        axisOfBait.remove(i);
                    }
                }
                

                for(int i = allEnimies.size()-1; i >= 0; i--){

                    double distance = Math.sqrt(Math.pow(circle.getTranslateX() - allEnimies.get(i).getTranslateX(), 2) + Math.pow(circle.getTranslateY() - allEnimies.get(i).getTranslateY(), 2));
                    distance += 50;


                    if(((pauseAction == 1 && settingAction ==1) || (pauseAction != 1 && settingAction !=1)) && distance < 400){
                        double dx = circle.getTranslateX() - allEnimies.get(i).getTranslateX();
                        double dy = circle.getTranslateY() - allEnimies.get(i).getTranslateY();

                        double displacement = Math.sqrt(dx*dx + dy*dy);

                        if(displacement > 10){
                        dx /= displacement;
                        dy /= displacement;
                        }

                        dx += (Math.random() - 0.5) * 0.3;
                        dy += (Math.random() - 0.5) * 0.3;

                        allEnimies.get(i).setTranslateX(allEnimies.get(i).getTranslateX() + dx * enemySpeed);
                        allEnimies.get(i).setTranslateY(allEnimies.get(i).getTranslateY() + dy * enemySpeed);
                    }else if (((pauseAction == 1 && settingAction ==1) || (pauseAction != 1 && settingAction !=1))){
                        double x_axis = allEnimies.get(i).getTranslateX();
                        double y_axis = allEnimies.get(i).getTranslateY();

                        // move enemy
                        x_axis += enemyDirX.get(i) * enemySpeed;
                        y_axis += enemyDirY.get(i) * enemySpeed;

                        double width = stage.getWidth();
                        double height = stage.getHeight();

                        if(x_axis <= 0 || x_axis >= width-40)
                        enemyDirX.set(i, -enemyDirX.get(i));

                        if(y_axis <= 60 || y_axis >= height-40)
                        enemyDirY.set(i, -enemyDirY.get(i));

                        allEnimies.get(i).setTranslateX(x_axis);
                        allEnimies.get(i).setTranslateY(y_axis);
                    }
                    if(enemySpeed < 8){
                        enemySpeed += 0.0008;
                    }
                    
                    if(distance < CircleRadius + allEnimies.get(i).getLayoutBounds().getWidth() || axisOfBait.size() == 0) {
                        endTime = System.nanoTime();
                        startTime += pauseTime;
                        int ExtraScore = (int)((endTime - startTime)/1e9);
                        ExtraScore /= 3;
                        score += ExtraScore;
                        FileHandling();
                        gameLoop.stop();
                        gameOver.ShowMenu(EventHandler.this, score, NumofDiamondsCollected, totalDiamonds, highScore);
                        stage.close();
                        break;
                    }
                }

                scene.setFill(gameSettings.MainSceneColor());
                if(Menu.isCloseGame()){
                stage.close();
            }
            }
        };
        gameLoop.start();
    }
    public void deriveScene(Scene scene){
        if(scene != null){
            SceneChecking = true;
        }else{
            SceneChecking = false;
        }
    }
    public static void setPauseAction(int PauseAction) {
        pauseAction = PauseAction;
    }
    public static void setSettingAction(int settingAction) {
        EventHandler.settingAction = settingAction;
    }

    private void FileHandling(){
        try{
            ArrayList<String> lines = new ArrayList<>();
            File stats = SaveManager.getStatsFile();
            if(stats.createNewFile()){
                BufferedWriter write = new BufferedWriter(new FileWriter(stats));
                write.write("Score = 0");
                write.newLine();
                write.write("Total Diamonds = 0");
                write.newLine();
                write.write("7Pac_Man_Favicon.png");
                write.close();
            }
            BufferedReader read = new BufferedReader(new FileReader(stats));
            // int line = 1;
            String line = null;
            int lineIndex = 1;
            while((line = read.readLine()) != null){
                    String[] split = line.split(" ");
                    if(lineIndex == 1 && line.startsWith("Score") ) {
                        if(score <= highScore) {
                            highScore = Integer.parseInt(split[split.length - 1]);
                        }else {
                            highScore = score;
                            line = "Score = "+highScore;
                        }
                    }else if(lineIndex == 2 && line.startsWith("Total")){
                        totalDiamonds = (Integer.parseInt(split[split.length-1])) + NumofDiamondsCollected;
                        line = "Total Diamonds = "+totalDiamonds;
                    }
                    lines.add(line);
                    lineIndex++;
            }
            BufferedWriter Bwrite = new BufferedWriter(new FileWriter(stats));
            for(String text : lines){
                Bwrite.write(text);
                Bwrite.newLine();
            }
            Bwrite.close();
            read.close();
        }
        catch(IOException e){
            System.out.println("Something went Wrong while getting and updating the diamonds and the score in the Event Handler");
        }
    }
}
