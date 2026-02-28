package com.pacman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Store {
    private int diamondsTOTAL = 0;
    private Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),30);

    public void store(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,600,800);

        HashMap<String, Integer> priceMap = new HashMap<>();
        priceMap.put("1multicolor_PacMan.png", 500);
        priceMap.put("2underCover_PacMan.png", 400);
        priceMap.put("3badass_PacMan.png", 350);
        priceMap.put("4teeth_PacMan.png",250);
        priceMap.put("5female_PacMan.png", 200);
        priceMap.put("6pixelated_PacMan.png", 300);
        priceMap.put("7Pac_Man_Favicon.png", 150);

        ArrayList<ImageView> images = new ArrayList<>();
        ArrayList<String> collectedItems = new ArrayList<>();
        ArrayList<String> stats = new ArrayList<>();
        ArrayList<Text> pricesOfItems = new ArrayList<>();
        ArrayList<ImageView> diamondOnScreen = new ArrayList<>();
        ArrayList<Button> buy = new ArrayList<>();
        ArrayList<Integer> prices = new ArrayList<>();

        String[] skinFiles = {
                "1multicolor_PacMan.png",
                "2underCover_PacMan.png",
                "3badass_PacMan.png",
                "4teeth_PacMan.png",
                "5female_PacMan.png",
                "6pixelated_PacMan.png"
        };
            for(String file : skinFiles){
                Image im = new Image("/Images/PacSkins/"+file);
                ImageView view = new ImageView(im);
                view.setId(""+file);
                images.add(view);
            }

        try{
            File cItems = SaveManager.getCollectionFile();
            cItems.createNewFile();
            BufferedReader read = new BufferedReader(new FileReader(cItems));
            String line = null;
            while ((line = read.readLine()) != null) {
                for(int z = 0; z < images.size(); z++){
                    if(images.get(z).getId().equals(line)){
                        collectedItems.add(line);
                        images.remove(z);
                    }
                }
            }
            read.close();
        }
        catch(IOException e){
            System.out.println("Something went wrong while checking for collected items in store");
        }

        Text text = new Text("Items Sold Out");
        text.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),40));
        text.setFill(Color.WHITE);
        text.setTranslateX(10);

        if(images.size() == 0){
            root.getChildren().add(text);
        }

        int y_axis = 0;
                
        Button quit = GameOver.MenuButtons("Back",0);
        quit.setFont(font);
        quit.setTranslateY(((images.size()/2) * 80) + 80);

        Text storeTxt = new Text("Store");
        storeTxt.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),60));
        storeTxt.setFill(Color.WHITE);
        storeTxt.setTranslateX(10);
        storeTxt.setTranslateY(-(((images.size()/2) * 80) + 80));
        root.getChildren().add(storeTxt);

        Image diamond = new Image("/Images/OnScreenDiamond.png");
        ImageView totalDiamondCount = new ImageView(diamond);
        totalDiamondCount.setFitHeight(30);
        totalDiamondCount.setFitWidth(30);
        totalDiamondCount.setTranslateX(165);
        totalDiamondCount.setTranslateY(-370);
        root.getChildren().add(totalDiamondCount);

        quit.setOnAction(e ->{
            stage.close();
        });

            try{
                File file = SaveManager.getStatsFile();
                if(file.createNewFile()){
                    BufferedWriter write = new BufferedWriter(new FileWriter(file));
                    write.write("Score = 0");
                    write.newLine();
                    write.write("Total Diamonds = 0");
                    write.newLine();
                    write.write("7Pac_Man_Favicon.png");
                    write.close();
                }
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line = null;
                while((line = read.readLine()) != null){
                    stats.add(line);
                    if(line.startsWith("Total")){
                        String[] sp = line.split(" ");
                            diamondsTOTAL = Integer.parseInt(sp[sp.length-1]);
                        }
                    }
                    read.close();
                }
                catch(IOException b){
                    System.out.print("Something Went wrong while getting diamonds in store");
                }
        int counttt = diamondsTOTAL;
        int u = 1;
        while(counttt != 0 ){
            counttt /=10;
            u++;
        }

        Text countTheDiamonds = new Text(""+diamondsTOTAL);
        countTheDiamonds.setFont(font);
        countTheDiamonds.setFill(Color.WHITE);
        countTheDiamonds.setTranslateX(totalDiamondCount.getTranslateX() + 35 + u*5);
        countTheDiamonds.setTranslateY(-367);
        root.getChildren().add(countTheDiamonds);

        for(int i = 0 ; i < images.size() ; i++){
            int highPrice = 0;
            highPrice = priceMap.get(images.get(i).getId());

                for(int j = 0 ; j < i; j++){
                    if(i % 2 != 0){
                        y_axis += 80;
                    }else{
                        y_axis -= 80;
                    }
                }
                Button buyNow = GameOver.MenuButtons("Buy", y_axis);
                buyNow.setFont(font);

                Text price = new Text("" + highPrice);
                price.setFont(Font.loadFont(getClass().getResourceAsStream("/Font/Minecrafter_Alt.ttf"),35));
                price.setFill(Color.WHITE);
                price.setTranslateX(10);
                price.setTranslateY(y_axis + 5);

                ImageView Diamond = new ImageView(diamond);
                Diamond.setTranslateY(y_axis);
                Diamond.setTranslateX(-55);

                images.get(i).setTranslateY(y_axis);
                images.get(i).setTranslateX(-150);
                images.get(i).setFitHeight(50);
                images.get(i).setFitWidth(50);
                root.getChildren().add(images.get(i));
                buyNow.setTranslateX(150);
                root.getChildren().add(buyNow);
                root.getChildren().add(price);
                root.getChildren().add(Diamond);

                buy.add(buyNow);
                prices.add(highPrice);
                pricesOfItems.add(price);
                diamondOnScreen.add(Diamond);
        }

                int count = 0;
                while(count < images.size()){
                    final int index = count;
                    buy.get(index).setOnAction(r ->{

                        int currentIndex = buy.indexOf(r.getSource());
                        if(currentIndex == -1) return;
                        PopUp pop = new PopUp();
                        if(diamondsTOTAL >= prices.get(currentIndex)){
                            diamondsTOTAL -= prices.get(currentIndex);
                            countTheDiamonds.setText(""+diamondsTOTAL);
                        try{
                            File file = SaveManager.getCollectionFile();
                            if(file.createNewFile()){
                                BufferedWriter write = new BufferedWriter(new FileWriter(file));
                                 for(String l : collectedItems){
                                    write.write(l);
                                    write.newLine();
                                 }
                                 write.close();
                            }
                            BufferedWriter write = new BufferedWriter(new FileWriter(file,true));
                            write.newLine();
                            write.write(images.get(currentIndex).getId());
                            write.close();
                        }
                        catch(IOException t){
                            System.out.println("Something went wrong while adding bought item");
                        }

                        ImageView im = images.get(currentIndex);
                        Button btn = buy.get(currentIndex);
                        Text pr = pricesOfItems.get(currentIndex);
                        ImageView dim = diamondOnScreen.get(currentIndex);
                        Integer pric = prices.get(currentIndex);

                        root.getChildren().removeAll(im,btn,pr,dim);

                        images.remove(im);
                        buy.remove(btn);
                        prices.remove(pric);
                        pricesOfItems.remove(pr);
                        diamondOnScreen.remove(dim);

                        pop.cautionPopUp("Successfuly\n  Purchased");
                        }else{
                            pop.cautionPopUp("Insufficient\n  Diamonds");
                        }
                        
                        try{
                            File file = SaveManager.getStatsFile();
                            if(file.createNewFile()){
                                BufferedWriter write = new BufferedWriter(new FileWriter(file));
                                write.write("Score = 0");
                                write.newLine();
                                write.write("Total Diamonds = 0");
                                write.newLine();
                                write.write("7Pac_Man_Favicon.png");
                                write.close();
                            }
                            BufferedWriter write = new BufferedWriter(new FileWriter(file));
                            int numOfLine = 0;
                            while(numOfLine < stats.size()){
                                if(stats.get(numOfLine).startsWith("Total")){
                                    write.write("Total Diamonds = "+diamondsTOTAL);
                                }else{
                                    write.write(stats.get(numOfLine));
                                }
                                write.newLine();
                                numOfLine++;
                            }
                            write.close();
                        }
                        catch(IOException y){
                            System.out.println("Something went wrong while updating the diamonds after purchase");
                        }
                    });
                    count++;
                }

        root.getChildren().add(quit);

        Image player = new Image(getClass().getResourceAsStream("/Images/PacSkins/7Pac_Man_Favicon.png"));

        stage.setScene(scene);
        stage.setTitle("Store");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.getIcons().add(player);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT );
        root.setStyle("""
            -fx-background-color: #0B132B;
            -fx-background-radius: 20;
        """);
        stage.show();
    }
}