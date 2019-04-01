/*
 
 This app tracks pills taken each day and returns the day's statistics of how many pills were taken. There are many ways I can optimize this app and add to it, this is still only in the beginning stages.
 
 Still the prototype, there are a few bugs still needed to be fixed
    - Need to reset the array each month
    - Gui adapts to new label coming in
    - Need to debug more to figure out other bugs
 
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;


public class pillgui extends Application implements EventHandler<ActionEvent>{
    
    Button pillTaken;
    Button view;
    Button close;
    Label question;
    static Label stats;
    Stage window;
    public static int arr[] = new int[31];
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
       
        window = primaryStage;
        window.setTitle("Pill Application");
        
        window.setOnCloseRequest(e -> closeProgram());
        
        pillTaken = new Button("Pill Taken");
        view = new Button("View Today");
        close = new Button("Close");
        question = new Label("Have You Taken Your Pill?");
        stats = new Label();
        close.setOnAction(e -> closeProgram());
        
        pillTaken.setOnAction(this);
        view.setOnAction(this);
        
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        
        GridPane.setConstraints(question, 25, 10);
        GridPane.setConstraints(stats, 25, 25);
        
        GridPane.setConstraints(pillTaken, 20, 40);
        GridPane.setConstraints(view, 30, 40);
        
        grid.getChildren().addAll(pillTaken, view, question, stats);
        Scene scene = new Scene(grid, 800, 480);
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == pillTaken){
            //event you want to happen
            markPill();
            printDate();
        }
        if(event.getSource() == view){
            printStats();
        }
    }
    
    private void closeProgram(){
        window.close();
    }
    
    public static void markPill(){
        int day = getDate();
        
        arr[day]++;
    }
    
    public static void printDate(){
        int month;
        int day;
        int year;
        int hour;
        int minute;
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        
        stats.setText("Last pill taken at " + month + "." + day + "." + year + " at " + hour + ":" + minute);
    }
    
    public static int getDate(){
        int day;
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        day = calendar.get(Calendar.DAY_OF_MONTH);
        
        return day;
    }
    
    public static void printStats(){
        int day = getDate();
        
        stats.setText("You have taken " + arr[day] + " pill(s) today.");
    }

}
