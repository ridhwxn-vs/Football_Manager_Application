import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.control.*;
import javafx.scene.text.Font.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.text.*; 
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.control.Alert.*;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javax.swing.JOptionPane;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Collections;


class PlayerRegCount
{
    static int PlayerCount;
    PlayerRegCount()
    {
        PlayerCount=0;
    }
}
class UserIDs
{
    static int UserID;
    static int TeamID;
    static String UName;
    static int PlayerID;
    static boolean Add;
    UserIDs()
    {
        UserID=-1;
        TeamID=-1;
        UName="";
        PlayerID=-1;
        Add=false;
    }
    UserIDs(int i,int j)
    {
        UserID=i;
        TeamID=j;
    }
    String generateAbbreviation(String input) 
    {
        input=input.toUpperCase();
        String[] words = input.split("\\s+");
        StringBuilder abbreviation = new StringBuilder();

        for (String word : words) 
        {
            abbreviation.append(word.charAt(0));
        }

        if (!input.contains("FOOTBALL CLUB")) {
            abbreviation.append("FC");
        }
        return(abbreviation.toString());
    }
}
public class FootballManagerApp extends Application
{
    public static void main(String args[])
    {
        launch(args);
    }
    public void start(Stage mainStage) throws FileNotFoundException
    {  
        UserIDs UserDetails=new UserIDs();
        PlayerRegCount PC=new PlayerRegCount();
        mainStage.setTitle("Football Manager");
        BorderPane Mainbg = new BorderPane();
        VBox BottomNode=new VBox();
        HBox TopNode = new HBox();
        VBox CenterNode=new VBox();
        HBox BTS1=new HBox();
        HBox BTS2=new HBox();
        TopNode.setPadding(new javafx.geometry.Insets(5, 0, 0, 10));
        TopNode.setSpacing(10);
        TopNode.setAlignment(Pos.CENTER_RIGHT);
        BTS1.setPadding(new javafx.geometry.Insets(0, 25, 0, 25));
        BTS1.setSpacing(25);
        BTS1.setAlignment(Pos.CENTER);
        BTS2.setPadding(new javafx.geometry.Insets(0, 25, 0, 25));
        BTS2.setSpacing(25);
        BTS2.setAlignment(Pos.CENTER);
        BackgroundImage BGimg=new BackgroundImage(new Image("Graphics/Main Page Bg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Mainbg.setBackground(new Background(BGimg));
        CenterNode.setSpacing(12); 
        CenterNode.setPadding(new Insets(-17, 0, 0, 0));
        CenterNode.setAlignment(Pos.CENTER);
        BottomNode.setSpacing(10); 
        BottomNode.setPadding(new Insets(0, 0, 0, 0));
        BottomNode.setAlignment(Pos.CENTER);
        Label Welcome=new Label();
        Button ManageBTmain=new Button("");
        Button ManageBT2=new Button("");
        Button MatchesBT=new Button("");
        Button SettingsBT=new Button("");
        Button RandomTeamGenBT=new Button("");
        Button LineUpBT=new Button("");
        Button LogoutBT=new Button("");
        Button ContactBT=new Button("");
        Scene mainScene=new Scene(Mainbg,501,500);
        mainStage.setScene(mainScene);

        Welcome.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,25));
        Welcome.setText("WELCOME !");
        Welcome.setTextAlignment(TextAlignment.CENTER);
        Welcome.setStyle("-fx-text-fill: black;");

        ManageBTmain.setPrefWidth(482.3);
        ManageBTmain.setPrefHeight(162.3);
        SettingsBT.setPrefWidth(105.7);
        SettingsBT.setPrefHeight(95.1);
        MatchesBT.setPrefWidth(105.7);
        MatchesBT.setPrefHeight(95.1);
        LineUpBT.setPrefWidth(105.7);
        LineUpBT.setPrefHeight(95.1);
        ManageBT2.setPrefWidth(130.5);
        ManageBT2.setPrefHeight(95.1);
        RandomTeamGenBT.setPrefWidth(130.5);
        RandomTeamGenBT.setPrefHeight(95.1);
        LogoutBT.setPrefWidth(80.4);
        LogoutBT.setPrefHeight(26);
        ContactBT.setPrefWidth(80.4);
        ContactBT.setPrefHeight(26);

        Image MyTeamMainImg = new Image("Graphics/MyTeamMainBT.png");
        ImageView MyTeamMainBT = new ImageView(MyTeamMainImg);
        MyTeamMainBT.setFitWidth(482.3);
        MyTeamMainBT.setPreserveRatio(true);
        ManageBTmain.setGraphic(MyTeamMainBT);
        ManageBTmain.setStyle("-fx-background-color: #7ED957;");

        Image MyTeamMainImgHover = new Image("Graphics/MyTeamMainBThover.png");
        ImageView MyTeamMainBTHover = new ImageView(MyTeamMainImgHover);
        MyTeamMainBTHover.setFitWidth(482.3);
        MyTeamMainBTHover.setPreserveRatio(true);

        ManageBTmain.setOnMouseEntered(e -> ManageBTmain.setGraphic(MyTeamMainBTHover));
        ManageBTmain.setOnMouseExited(e -> ManageBTmain.setGraphic(MyTeamMainBT));

        Image MyTeamImg = new Image("Graphics/MyTeamBT.png");
        ImageView MyTeamBT = new ImageView(MyTeamImg);
        MyTeamBT.setFitWidth(130.5);
        MyTeamBT.setPreserveRatio(true);
        ManageBT2.setGraphic(MyTeamBT);
        ManageBT2.setStyle("-fx-background-color: #7ED957;");

        Image MyTeamImgHover = new Image("Graphics/MyTeamBThover.png");
        ImageView MyTeamBTHover = new ImageView(MyTeamImgHover);
        MyTeamBTHover.setFitWidth(130.5);
        MyTeamBTHover.setPreserveRatio(true);

        ManageBT2.setOnMouseEntered(e -> ManageBT2.setGraphic(MyTeamBTHover));
        ManageBT2.setOnMouseExited(e -> ManageBT2.setGraphic(MyTeamBT));

        Image MatchesImg = new Image("Graphics/MatchesBT.png");
        ImageView MatchesBTview = new ImageView(MatchesImg);
        MatchesBTview.setFitWidth(105.7);
        MatchesBTview.setPreserveRatio(true);
        MatchesBT.setGraphic(MatchesBTview);
        MatchesBT.setStyle("-fx-background-color: #7ED957;");

        Image MatchesImgHover = new Image("Graphics/MatchesBThover.png");
        ImageView MatchesBTHover = new ImageView(MatchesImgHover);
        MatchesBTHover.setFitWidth(105.7);
        MatchesBTHover.setPreserveRatio(true);

        MatchesBT.setOnMouseEntered(e -> MatchesBT.setGraphic(MatchesBTHover));
        MatchesBT.setOnMouseExited(e -> MatchesBT.setGraphic(MatchesBTview));

        Image LineupImg = new Image("Graphics/LineupBT.png");
        ImageView LineupBTview = new ImageView(LineupImg);
        LineupBTview.setFitWidth(105.7);
        LineupBTview.setPreserveRatio(true);
        LineUpBT.setGraphic(LineupBTview);
        LineUpBT.setStyle("-fx-background-color: #7ED957;");

        Image LineupImgHover = new Image("Graphics/LineupBThover.png");
        ImageView LineupBTHover = new ImageView(LineupImgHover);
        LineupBTHover.setFitWidth(105.7);
        LineupBTHover.setPreserveRatio(true);

        LineUpBT.setOnMouseEntered(e -> LineUpBT.setGraphic(LineupBTHover));
        LineUpBT.setOnMouseExited(e -> LineUpBT.setGraphic(LineupBTview));

        Image SettingsImg = new Image("Graphics/SettingsBT.png");
        ImageView SettingsBTview = new ImageView(SettingsImg);
        SettingsBTview.setFitWidth(105.7);
        SettingsBTview.setPreserveRatio(true);
        SettingsBT.setGraphic(SettingsBTview);
        SettingsBT.setStyle("-fx-background-color: #7ED957;");

        Image SettingsImgHover = new Image("Graphics/SettingsBThover.png");
        ImageView SettingsBTHover = new ImageView(SettingsImgHover);
        SettingsBTHover.setFitWidth(105.7);
        SettingsBTHover.setPreserveRatio(true);

        SettingsBT.setOnMouseEntered(e -> SettingsBT.setGraphic(SettingsBTHover));
        SettingsBT.setOnMouseExited(e -> SettingsBT.setGraphic(SettingsBTview));

        Image RandomTGImg = new Image("Graphics/RandomTGBT.png");
        ImageView RandomTGBTview = new ImageView(RandomTGImg);
        RandomTGBTview.setFitWidth(130.5);
        RandomTGBTview.setPreserveRatio(true);
        RandomTeamGenBT.setGraphic(RandomTGBTview);
        RandomTeamGenBT.setStyle("-fx-background-color: #7ED957;");

        Image RandomTGImgHover = new Image("Graphics/RandomTGBThover.png");
        ImageView RandomTGBTHover = new ImageView(RandomTGImgHover);
        RandomTGBTHover.setFitWidth(130.5);
        RandomTGBTHover.setPreserveRatio(true);

        RandomTeamGenBT.setOnMouseEntered(e -> RandomTeamGenBT.setGraphic(RandomTGBTHover));
        RandomTeamGenBT.setOnMouseExited(e -> RandomTeamGenBT.setGraphic(RandomTGBTview));

        Image LogoutImg = new Image("Graphics/LogOutBT.png");
        ImageView LogoutBTview = new ImageView(LogoutImg);
        LogoutBTview.setFitWidth(80.4);
        LogoutBTview.setPreserveRatio(true);
        LogoutBT.setGraphic(LogoutBTview);
        LogoutBT.setStyle("-fx-background-color: #7ED957;");

        Image LogoutImgHover = new Image("Graphics/LogOutBThover.png");
        ImageView LogoutBTHover = new ImageView(LogoutImgHover);
        LogoutBTHover.setFitWidth(80.4);
        LogoutBTHover.setPreserveRatio(true);

        LogoutBT.setOnMouseEntered(e -> LogoutBT.setGraphic(LogoutBTHover));
        LogoutBT.setOnMouseExited(e -> LogoutBT.setGraphic(LogoutBTview));

        Image ContactImg = new Image("Graphics/ContactBT.png");
        ImageView ContactBTview = new ImageView(ContactImg);
        ContactBTview.setFitWidth(80.4);
        ContactBTview.setPreserveRatio(true);
        ContactBT.setGraphic(ContactBTview);
        ContactBT.setStyle("-fx-background-color: #7ED957;");

        Image ContactImgHover = new Image("Graphics/ContactBThover.png");
        ImageView ContactBTHover = new ImageView(ContactImgHover);
        ContactBTHover.setFitWidth(80.4);
        ContactBTHover.setPreserveRatio(true);

        ContactBT.setOnMouseEntered(e -> ContactBT.setGraphic(ContactBTHover));
        ContactBT.setOnMouseExited(e -> ContactBT.setGraphic(ContactBTview));

        TopNode.getChildren().addAll(LogoutBT, ContactBT);
        CenterNode.getChildren().addAll(ManageBTmain, Welcome);
        BTS1.getChildren().addAll(MatchesBT,LineUpBT,SettingsBT);
        BTS2.getChildren().addAll(RandomTeamGenBT,ManageBT2);
        BottomNode.getChildren().addAll(BTS1,BTS2);
        Mainbg.setTop(TopNode);
        Mainbg.setCenter(CenterNode);
        Mainbg.setBottom(BottomNode);
        // My Team Page

        Stage MyTeamStage=new Stage();
        MyTeamStage.setTitle("My Team");
        BorderPane MyTeamStagebg = new BorderPane();
        BackgroundImage MyteamBG=new BackgroundImage(new Image("Graphics/MyTeamBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        MyTeamStagebg.setBackground(new Background(MyteamBG));
        Scene MyTeamScene=new Scene(MyTeamStagebg,501,500);
        MyTeamStage.setScene(MyTeamScene);
        
        Image MainMenuImg = new Image("Graphics/MainMenuBT.png");
        ImageView MainMenuImgview = new ImageView(MainMenuImg);
        MainMenuImgview.setFitWidth(112.2);
        MainMenuImgview.setPreserveRatio(true);
        Image MainMenuImgHover = new Image("Graphics/MainMenuBThover.png");
        ImageView MainMenuImgviewHover = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover.setFitWidth(112.2);
        MainMenuImgviewHover.setPreserveRatio(true);

        Button MainMenuBT1=new Button("");
        MainMenuBT1.setPrefWidth(112.2);
        MainMenuBT1.setPrefHeight(33.3);
        MainMenuBT1.setGraphic(MainMenuImgview);
        MainMenuBT1.setStyle("-fx-background-color: #7ED957;");
        MainMenuBT1.setOnMouseEntered(e -> MainMenuBT1.setGraphic(MainMenuImgviewHover));
        MainMenuBT1.setOnMouseExited(e -> MainMenuBT1.setGraphic(MainMenuImgview));

        HBox MyTeamTop=new HBox();
        MyTeamTop.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        MyTeamTop.setSpacing(10);
        MyTeamTop.setAlignment(Pos.CENTER_RIGHT);

        Label TeamName=new Label("Vibes FC Manipal");
        TeamName.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,30));
        TeamName.setTextAlignment(TextAlignment.CENTER);
        TeamName.setStyle("-fx-text-fill: black;");
        TeamName.setPrefWidth(285);

        Image TeamLogo = new Image("Graphics/TeamLogo1.jpeg");
        ImageView TeamLogoView = new ImageView(TeamLogo);
        TeamLogoView.setX(10);
        TeamLogoView.setY(10);
        TeamLogoView.setFitWidth(135.1);
        TeamLogoView.setFitHeight(155.1);
        TeamLogoView.setPreserveRatio(true);

        HBox MyTeamCenter=new HBox();
        MyTeamCenter.setPadding(new javafx.geometry.Insets(-283, 5, 5, 5));
        MyTeamCenter.setSpacing(20);
        MyTeamCenter.setAlignment(Pos.CENTER);

        ImageView LineupBTview2 = new ImageView(LineupImg);
        LineupBTview2.setFitWidth(105.7);
        LineupBTview2.setPreserveRatio(true);
        ImageView LineupBTHover2 = new ImageView(LineupImgHover);
        LineupBTHover2.setFitWidth(105.7);
        LineupBTHover2.setPreserveRatio(true);
        Button LineUpBT2=new Button("");
        LineUpBT2.setPrefWidth(105.7);
        LineUpBT2.setPrefHeight(95.1);
        LineUpBT2.setGraphic(LineupBTview2);
        LineUpBT2.setStyle("-fx-background-color: #7ED957;");
        LineUpBT2.setOnMouseEntered(e -> LineUpBT2.setGraphic(LineupBTHover2));
        LineUpBT2.setOnMouseExited(e -> LineUpBT2.setGraphic(LineupBTview2));

        ImageView MatchesBTview2 = new ImageView(MatchesImg);
        MatchesBTview2.setFitWidth(105.7);
        MatchesBTview2.setPreserveRatio(true);
        ImageView MatchesBTHover2 = new ImageView(MatchesImgHover);
        MatchesBTHover2.setFitWidth(105.7);
        MatchesBTHover2.setPreserveRatio(true);
        Button MatchesBT2=new Button("");
        MatchesBT2.setPrefWidth(105.7);
        MatchesBT2.setPrefHeight(95.1);
        MatchesBT2.setGraphic(MatchesBTview2);
        MatchesBT2.setStyle("-fx-background-color: #7ED957;");
        MatchesBT2.setOnMouseEntered(e -> MatchesBT2.setGraphic(MatchesBTHover2));
        MatchesBT2.setOnMouseExited(e -> MatchesBT2.setGraphic(MatchesBTview2));

        HBox MyTeamBTS=new HBox();
        MyTeamBTS.setPadding(new javafx.geometry.Insets(0, 25, 0, 25));
        MyTeamBTS.setSpacing(25);
        MyTeamBTS.setAlignment(Pos.CENTER);
        MyTeamBTS.getChildren().addAll(MatchesBT2,LineUpBT2);

        Image MyPlayersImg = new Image("Graphics/MyPlayersBT.png");
        Image MyPlayersImgHover = new Image("Graphics/MyPlayersBThover.png");
        ImageView MyTeamBTview2 = new ImageView(MyPlayersImg);
        MyTeamBTview2.setFitWidth(161.6);
        MyTeamBTview2.setPreserveRatio(true);
        ImageView MyTeamBTHover2 = new ImageView(MyPlayersImgHover);
        MyTeamBTHover2.setFitWidth(161.6);
        MyTeamBTHover2.setFitHeight(117.7);
        MyTeamBTHover2.setPreserveRatio(true);
        Button SettingsBT2=new Button("");
        SettingsBT2.setPrefWidth(161.6);
        SettingsBT2.setPrefHeight(117.7);
        SettingsBT2.setGraphic(MyTeamBTview2);
        SettingsBT2.setStyle("-fx-background-color: #7ED957;");
        SettingsBT2.setOnMouseEntered(e -> SettingsBT2.setGraphic(MyTeamBTHover2));
        SettingsBT2.setOnMouseExited(e -> SettingsBT2.setGraphic(MyTeamBTview2));

        VBox MyTeamBottom=new VBox();
        MyTeamBottom.setSpacing(12); 
        MyTeamBottom.setPadding(new Insets(-320, 0, 0, 0));
        MyTeamBottom.setAlignment(Pos.CENTER);
        MyTeamBottom.getChildren().addAll(MyTeamBTS,SettingsBT2);

        MyTeamCenter.getChildren().addAll(TeamName,TeamLogoView);
        MyTeamTop.getChildren().addAll(MainMenuBT1);
        MyTeamStagebg.setTop(MyTeamTop);
        MyTeamStagebg.setCenter(MyTeamCenter);
        MyTeamStagebg.setBottom(MyTeamBottom);

        MainMenuBT1.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                MyTeamStage.close();
            }
        });

        ManageBTmain.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                String query = "SELECT TEAMNAME, TEAM_LOGO FROM TEAM WHERE TEAMID = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password");
                    PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                    // Set parameter for the prepared statement
                    statement.setInt(1, UserDetails.TeamID);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) 
                    {
                        if (resultSet.next()) 
                        {
                            // Retrieve team name and logo from the result set
                            String teamName = resultSet.getString("TEAMNAME");
                            Blob teamLogoBlob = resultSet.getBlob("TEAM_LOGO");
                            byte[] teamLogoBytes = teamLogoBlob.getBytes(1, (int) teamLogoBlob.length());

                            // Update the label and image view with retrieved values
                            TeamName.setText(teamName);
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(teamLogoBytes);
                            Image teamLogoImage = new Image(byteArrayInputStream);
                            TeamLogoView.setImage(teamLogoImage);
                        } 
                        else 
                        {
                            System.out.println("No team found for the given TeamID.");
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                }
                MyTeamStage.show();
            }
        });

         ManageBT2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                String query = "SELECT TEAMNAME, TEAM_LOGO FROM TEAM WHERE TEAMID = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password");
                    PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                    // Set parameter for the prepared statement
                    statement.setInt(1, UserDetails.TeamID);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) 
                    {
                        if (resultSet.next()) 
                        {
                            // Retrieve team name and logo from the result set
                            String teamName = resultSet.getString("TEAMNAME");
                            Blob teamLogoBlob = resultSet.getBlob("TEAM_LOGO");
                            byte[] teamLogoBytes = teamLogoBlob.getBytes(1, (int) teamLogoBlob.length());

                            // Update the label and image view with retrieved values
                            TeamName.setText(teamName);
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(teamLogoBytes);
                            Image teamLogoImage = new Image(byteArrayInputStream);
                            TeamLogoView.setImage(teamLogoImage);
                        } 
                        else 
                        {
                            System.out.println("No team found for the given TeamID.");
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                }
                MyTeamStage.show();
            }
        });

        Stage MatchesStage=new Stage();
        MatchesStage.setTitle("Recent and UpComing Fixtures");
        BorderPane MatchesStagebg = new BorderPane();
        BackgroundImage MatchesBG=new BackgroundImage(new Image("Graphics/MatchesBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        MatchesStagebg.setBackground(new Background(MatchesBG));
        Scene MatchesScene=new Scene(MatchesStagebg,501,500);
        MatchesStage.setScene(MatchesScene);

        ImageView MainMenuImgview3= new ImageView(MainMenuImg);
        MainMenuImgview3.setFitWidth(112.2);
        MainMenuImgview3.setPreserveRatio(true);
        ImageView MainMenuImgviewHover3 = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover3.setFitWidth(112.2);
        MainMenuImgviewHover3.setPreserveRatio(true);
        Button MainMenuBT3=new Button("");
        MainMenuBT3.setPrefWidth(112.2);
        MainMenuBT3.setPrefHeight(33.3);
        MainMenuBT3.setGraphic(MainMenuImgview);
        MainMenuBT3.setStyle("-fx-background-color: #7ED957;");
        MainMenuBT3.setOnMouseEntered(e -> MainMenuBT3.setGraphic(MainMenuImgviewHover3));
        MainMenuBT3.setOnMouseExited(e -> MainMenuBT3.setGraphic(MainMenuImgview3));

        HBox MatchesTop=new HBox();
        MatchesTop.setPadding(new javafx.geometry.Insets(0, 5, 5, 5));
        MatchesTop.setSpacing(10);
        MatchesTop.setAlignment(Pos.CENTER_RIGHT);

        VBox MatchesCenter=new VBox();
        MatchesCenter.setPadding(new javafx.geometry.Insets(-317, 5, 5, 5));
        MatchesCenter.setSpacing(1);
        MatchesCenter.setAlignment(Pos.CENTER);

        HBox ScoreBox=new HBox();
        ScoreBox.setPadding(new javafx.geometry.Insets(2, 5, 5, 5));
        ScoreBox.setSpacing(26);
        ScoreBox.setAlignment(Pos.CENTER);

        Label Score1=new Label("-");
        Score1.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 20px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
    
        Score1.setAlignment(Pos.CENTER);
        Score1.setMaxWidth(40);

        Label Score2=new Label("-");
        Score2.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 20px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
    
        Score2.setAlignment(Pos.CENTER);
        Score2.setMaxWidth(40);

        HBox TeamBox=new HBox();
        TeamBox.setPadding(new javafx.geometry.Insets(0, 5, 5, 5));
        TeamBox.setSpacing(20);
        TeamBox.setAlignment(Pos.CENTER);

        Label Team1=new Label("-");
        Team1.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 18px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
    
        Team1.setAlignment(Pos.CENTER);
        Team1.setPrefWidth(130);

        Label Team2=new Label("-");
        Team2.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 18px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
    
        Team2.setAlignment(Pos.CENTER);
        Team2.setPrefWidth(130);
        
        VBox MatchesBottom=new VBox();
        MatchesBottom.setPadding(new javafx.geometry.Insets(-380, 5, 5, 5));
        MatchesBottom.setSpacing(66);
        MatchesBottom.setAlignment(Pos.CENTER);

        Label MatchSummary=new Label("No Recent Matches, Please Contact League DB Manager to Update Matches");
        MatchSummary.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 13px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        MatchSummary.setAlignment(Pos.CENTER);
        MatchSummary.setPrefWidth(428);
        MatchSummary.setPrefHeight(73);

        ObservableList<String> MatchesArray = FXCollections.observableArrayList("Loading...,Please Wait");
        ListView<String> MatchesList = new ListView<String>(MatchesArray);
        MatchesList.setMaxWidth(428);
        MatchesList.setPrefHeight(130);
        MatchesList.setStyle("-fx-background-color: white; " +
                    "-fx-text-fill: black; " +
                    "-fx-font-size: 15px; " +
                    "-fx-font-family: 'Comic Sans MS'; " +
                    "-fx-font-weight: bold; " +
                    "-fx-alignment: center; ");

        MatchesBottom.getChildren().addAll(MatchSummary,MatchesList);
        ScoreBox.getChildren().addAll(Score1,Score2);
        TeamBox.getChildren().addAll(Team1,Team2);
        MatchesCenter.getChildren().addAll(ScoreBox,TeamBox);
        MatchesTop.getChildren().addAll(MainMenuBT3);
        MatchesStagebg.setTop(MatchesTop);
        MatchesStagebg.setCenter(MatchesCenter);
        MatchesStagebg.setBottom(MatchesBottom);
        
        MatchesBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT TEAM1SCORE, TEAM2SCORE, TEAM1ID, TEAM2ID, HIGHLIGHTS FROM (SELECT * FROM matches WHERE (TEAM1ID = ? OR TEAM2ID = ?) AND MATCHDATE <= (TO_DATE(?,'DD-MON-YYYY')) ORDER BY MATCHDATE DESC) WHERE ROWNUM = 1"; 
                    try (PreparedStatement statement = connection.prepareStatement(sql)) 
                    {
                        int teamID1 = UserDetails.TeamID;
                        int teamID2 = UserDetails.TeamID;
                        Date currentDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                        String dateString = dateFormat.format(currentDate);
                        statement.setInt(1, teamID1);
                        statement.setInt(2, teamID2);
                        statement.setString(3, dateString);
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            if (resultSet.next()) 
                            {
                                int score1 = resultSet.getInt("TEAM1SCORE");
                                int score2 = resultSet.getInt("TEAM2SCORE");
                                int team1_id = resultSet.getInt("TEAM1ID");
                                int team2_id = resultSet.getInt("TEAM2ID");
                                String highlights = resultSet.getString("HIGHLIGHTS");
                                
                                String sql1 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statement1 = connection.prepareStatement(sql1)) 
                                {
                                    statement1.setInt(1, team1_id);
                                    try (ResultSet resultSet1 = statement1.executeQuery()) 
                                    {
                                        if (resultSet1.next()) 
                                        {
                                            Team1.setText(UserDetails.generateAbbreviation(resultSet1.getString("TEAMNAME")));
                                        } 
                                        else 
                                        {
                                            Team1.setText("No team with " + teamID1);
                                        }
                                    }
                                }
                                String sql2 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statement2 = connection.prepareStatement(sql2)) 
                                {
                                    statement2.setInt(1, team2_id);
                                    try (ResultSet resultSet2 = statement2.executeQuery()) 
                                    {
                                        if (resultSet2.next()) 
                                        {
                                            Team2.setText(UserDetails.generateAbbreviation(resultSet2.getString("TEAMNAME")));
                                        } 
                                        else 
                                        {
                                            Team2.setText("No team with " + teamID2);
                                        }
                                    }
                                }
                                System.out.println(score1+score2+team1_id+team2_id+highlights);
                                Score1.setText(String.valueOf(score1));
                                Score2.setText(String.valueOf(score2));
                                MatchSummary.setText(highlights);
                            } 
                            else 
                            {
                                MatchSummary.setText("No Recent Matches, Please Contact League DB Manager to Update Matches");
                                Team1.setText("-");
                                Team2.setText("-");
                                Score1.setText("-");
                                Score2.setText("-");
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT TEAM1ID, TEAM2ID, MATCHDATE FROM matches WHERE (TEAM1ID = ? OR TEAM2ID = ?) AND MATCHDATE > ?";

                    try (PreparedStatement statement = connection.prepareStatement(sql)) 
                    {
                        int teamID = UserDetails.TeamID;
                        Date currentDate = new Date(System.currentTimeMillis());
                        
                        statement.setInt(1, teamID);
                        statement.setInt(2, teamID);
                        statement.setDate(3, new java.sql.Date(currentDate.getTime()));
                        MatchesList.getItems().clear();
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            while (resultSet.next()) 
                            {
                                int team1_id = resultSet.getInt("TEAM1ID");
                                int team2_id = resultSet.getInt("TEAM2ID");
                                Date matchDate = resultSet.getDate("MATCHDATE");

                                String teamName1 = "No team with " + team1_id;
                                String teamName2 = "No team with " + team2_id;

                                String sqlTeam1 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statementTeam1 = connection.prepareStatement(sqlTeam1)) 
                                {
                                    statementTeam1.setInt(1, team1_id);
                                    try (ResultSet resultSetTeam1 = statementTeam1.executeQuery()) 
                                    {
                                        if (resultSetTeam1.next()) 
                                        {
                                            teamName1 = resultSetTeam1.getString("TEAMNAME");
                                        }
                                    }
                                }

                                String sqlTeam2 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statementTeam2 = connection.prepareStatement(sqlTeam2)) 
                                {
                                    statementTeam2.setInt(1, team2_id);
                                    try (ResultSet resultSetTeam2 = statementTeam2.executeQuery()) 
                                    {
                                        if (resultSetTeam2.next()) 
                                        {
                                            teamName2 = resultSetTeam2.getString("TEAMNAME");
                                        }
                                    }
                                }

                                String matchDetails = teamName1 + " vs " + teamName2 + " - " + matchDate.toString();
                                MatchesArray.add(matchDetails);
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                MatchesStage.show();
            }
        });
        MainMenuBT3.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                MatchesStage.close();
            }
        });

        Stage LineUpStage=new Stage();
        LineUpStage.setTitle("LineUp and Players");
        BorderPane LineUpStagebg = new BorderPane();
        BackgroundImage LineUpBG=new BackgroundImage(new Image("Graphics/LineUpBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        LineUpStagebg.setBackground(new Background(LineUpBG));
        Scene LineUpScene=new Scene(LineUpStagebg,501,500);
        LineUpStage.setScene(LineUpScene);

        ImageView MainMenuImgview2 = new ImageView(MainMenuImg);
        MainMenuImgview2.setFitWidth(112.2);
        MainMenuImgview2.setPreserveRatio(true);
        ImageView MainMenuImgviewHover2 = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover2.setFitWidth(112.2);
        MainMenuImgviewHover2.setPreserveRatio(true);
        Button MainMenuBT2=new Button("");
        MainMenuBT2.setPrefWidth(112.2);
        MainMenuBT2.setPrefHeight(33.3);
        MainMenuBT2.setGraphic(MainMenuImgview);
        MainMenuBT2.setStyle("-fx-background-color: #7ED957;");
        MainMenuBT2.setOnMouseEntered(e -> MainMenuBT2.setGraphic(MainMenuImgviewHover2));
        MainMenuBT2.setOnMouseExited(e -> MainMenuBT2.setGraphic(MainMenuImgview2));

        HBox LineUpTop=new HBox();
        LineUpTop.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        LineUpTop.setSpacing(10);
        LineUpTop.setAlignment(Pos.CENTER_RIGHT);

        ObservableList<String> PlayerNames = FXCollections.observableArrayList("Some Error Has Occured","Please Report it by Contacting Us");
        ListView<String> LineUpList = new ListView<String>(PlayerNames);
        LineUpList.setMaxSize(288, 321);
        LineUpList.setStyle("-fx-background-color: white; " +
                    "-fx-text-fill: black; " +
                    "-fx-font-size: 15px; " +
                    "-fx-font-family: 'Comic Sans MS'; " +
                    "-fx-font-weight: bold; " +
                    "-fx-alignment: center; ");

        Image PlayerImage = new Image("Graphics/PlayerImage.jpg");
        ImageView PlayerImageView = new ImageView(PlayerImage);
        PlayerImageView.setX(10);
        PlayerImageView.setY(10);
        PlayerImageView.setFitWidth(133.5);
        PlayerImageView.setFitHeight(133.5);
        PlayerImageView.setPreserveRatio(true);

        Label PName=new Label("Select Player");
        PName.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 15px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        PName.setAlignment(Pos.CENTER);

        Label PAge=new Label("");
        PAge.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 15px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';"+
                        "-fx-pref-width: 130; "
                    );
        PAge.setAlignment(Pos.CENTER);

        Label PPos=new Label("");
        PPos.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 15px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        PPos.setAlignment(Pos.CENTER);

        Label PNat=new Label("");                    
        PNat.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 15px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        PNat.setAlignment(Pos.CENTER);

        Label PKit=new Label("");
        PKit.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 15px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        PKit.setAlignment(Pos.CENTER);

        Label POv=new Label("");
        POv.setStyle("-fx-text-fill: black; " +
                       "-fx-font-size: 35px;" +
                       "-fx-font-weight: bold; " +
                       "-fx-font-family: 'Comic Sans MS';");
        POv.setAlignment(Pos.CENTER);
        
        HBox LineUpCenter=new HBox();
        LineUpCenter.setPadding(new javafx.geometry.Insets(-25, 5, 5, 50));
        LineUpCenter.setSpacing(42);
        LineUpCenter.setAlignment(Pos.CENTER_LEFT); 

        VBox LineUpRight=new VBox();
        LineUpRight.setPadding(new javafx.geometry.Insets(-27, 5, 5, 5));
        LineUpRight.setSpacing(3);
        LineUpRight.setAlignment(Pos.CENTER);

        VBox PlayerDetailsRight=new VBox();
        PlayerDetailsRight.setPadding(new javafx.geometry.Insets(8, 5, 25, 5));
        PlayerDetailsRight.setSpacing(8);
        PlayerDetailsRight.setAlignment(Pos.CENTER);

        PlayerDetailsRight.getChildren().addAll(PName,PAge,PPos,PNat,PKit);
        LineUpRight.getChildren().addAll(PlayerImageView,PlayerDetailsRight,POv);
        LineUpCenter.getChildren().addAll(LineUpList,LineUpRight);
        LineUpTop.getChildren().addAll(MainMenuBT2);
        LineUpStagebg.setTop(LineUpTop);
        LineUpStagebg.setCenter(LineUpCenter);
        
        LineUpBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String query = "SELECT NAME FROM player WHERE TEAMID = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                        statement.setInt(1, UserDetails.TeamID); // Set the team ID parameter
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            ObservableList<String> playerNames = FXCollections.observableArrayList();
                            while (resultSet.next()) 
                            {
                                String playerName = resultSet.getString("NAME");
                                playerNames.add(playerName);
                            }
                            LineUpList.setItems(playerNames);
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    // Handle SQL exception
                }
                LineUpStage.show();
            }
        });

        LineUpList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
        {
            // newValue contains the selected player name
            if (newValue != null) 
            {
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String query = "SELECT * FROM player WHERE NAME = ? AND TEAMID = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                        statement.setString(1, newValue);
                        statement.setInt(2, UserDetails.TeamID); 
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            if (resultSet.next()) 
                            {
                                // Retrieve player details
                                String playerName = resultSet.getString("NAME");
                                int playerAge = resultSet.getInt("AGE");
                                String playerPosition = resultSet.getString("POSITION");
                                String playerNation = resultSet.getString("NATION");
                                int playerKit = resultSet.getInt("JERSEYNO");
                                int playerOverall = resultSet.getInt("OVERALL");
                                InputStream inputStream = resultSet.getBinaryStream("PLAYER_IMG");
                                if (inputStream != null) 
                                {
                                    Image playerImage = new Image(inputStream);
                                    PlayerImageView.setImage(playerImage);
                                }
                                PName.setText(playerName);
                                PAge.setText(Integer.toString(playerAge));
                                PPos.setText(playerPosition);
                                PNat.setText(playerNation);
                                PKit.setText(Integer.toString(playerKit));
                                POv.setText(Integer.toString(playerOverall));
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    // Handle SQL exception
                }
            }
        });


        MainMenuBT2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                PName.setText("");
                PAge.setText("");
                PPos.setText("");
                PNat.setText("");
                PKit.setText("");
                POv.setText("");
                PlayerImageView.setImage(PlayerImage);
                LineUpStage.close();
            }
        });

        Stage RandomTeamGenStage=new Stage();
        RandomTeamGenStage.setTitle("Random Team Generator");
        BorderPane RandomTeamGenStagebg = new BorderPane();
        BackgroundImage RandomTeamGenBG=new BackgroundImage(new Image("Graphics/RandomTeamGenBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        RandomTeamGenStagebg.setBackground(new Background(RandomTeamGenBG));
        Scene RandomTeamGenScene=new Scene(RandomTeamGenStagebg,501,500);
        RandomTeamGenStage.setScene(RandomTeamGenScene);

        ImageView MainMenuImgview6= new ImageView(MainMenuImg);
        MainMenuImgview6.setFitWidth(112.2);
        MainMenuImgview6.setPreserveRatio(true);
        ImageView MainMenuImgviewHover6 = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover6.setFitWidth(112.2);
        MainMenuImgviewHover6.setPreserveRatio(true);
        Button MainMenuBT6=new Button("");
        MainMenuBT6.setPrefWidth(112.2);
        MainMenuBT6.setPrefHeight(33.3);
        MainMenuBT6.setGraphic(MainMenuImgview);
        MainMenuBT6.setStyle("-fx-background-color: #7ED967;");
        MainMenuBT6.setOnMouseEntered(e -> MainMenuBT6.setGraphic(MainMenuImgviewHover6));
        MainMenuBT6.setOnMouseExited(e -> MainMenuBT6.setGraphic(MainMenuImgview6));

        HBox RandomTeamGenTop=new HBox();
        RandomTeamGenTop.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        RandomTeamGenTop.setSpacing(10);
        RandomTeamGenTop.setAlignment(Pos.CENTER_RIGHT);

        TextField PlayerRTGTB = new TextField("");
        PlayerRTGTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 11px;");
        PlayerRTGTB.setAlignment(Pos.CENTER);
        PlayerRTGTB.setMaxWidth(290);
        PlayerRTGTB.setMaxHeight(17);

        Image AddPlayerRTGImg = new Image("Graphics/AddPlayerRTGBT.png");
        Image AddPlayerRTGImgHover = new Image("Graphics/AddPlayerRTGBThover.png");
        ImageView AddPlayerRTGBTview = new ImageView(AddPlayerRTGImg);
        AddPlayerRTGBTview.setFitWidth(91);
        AddPlayerRTGBTview.setPreserveRatio(true);
        ImageView AddPlayerRTGBThover = new ImageView(AddPlayerRTGImgHover);
        AddPlayerRTGBThover.setFitWidth(91);
        AddPlayerRTGBThover.setPreserveRatio(true);
        Button AddPlayerRTGBT=new Button("");
        AddPlayerRTGBT.setPrefWidth(91);
        AddPlayerRTGBT.setPrefHeight(27);
        AddPlayerRTGBT.setGraphic(AddPlayerRTGBTview);
        AddPlayerRTGBT.setStyle("-fx-background-color: #337018;");
        AddPlayerRTGBT.setOnMouseEntered(e -> AddPlayerRTGBT.setGraphic(AddPlayerRTGBThover));
        AddPlayerRTGBT.setOnMouseExited(e -> AddPlayerRTGBT.setGraphic(AddPlayerRTGBTview));

        Image ShuffleImg = new Image("Graphics/ShuffleBT.png");
        Image ShuffleImgHover = new Image("Graphics/ShuffleBThover.png");
        ImageView ShuffleBTview = new ImageView(ShuffleImg);
        ShuffleBTview.setFitWidth(91);
        ShuffleBTview.setPreserveRatio(true);
        ImageView ShuffleBThover = new ImageView(ShuffleImgHover);
        ShuffleBThover.setFitWidth(91);
        ShuffleBThover.setPreserveRatio(true);
        Button ShuffleBT=new Button("");
        ShuffleBT.setPrefWidth(91);
        ShuffleBT.setPrefHeight(27);
        ShuffleBT.setGraphic(ShuffleBTview);
        ShuffleBT.setStyle("-fx-background-color: #337018;");
        ShuffleBT.setOnMouseEntered(e -> ShuffleBT.setGraphic(ShuffleBThover));
        ShuffleBT.setOnMouseExited(e -> ShuffleBT.setGraphic(ShuffleBTview));

        HBox RandomTeamGenC2=new HBox();
        RandomTeamGenC2.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        RandomTeamGenC2.setSpacing(30);
        RandomTeamGenC2.setAlignment(Pos.CENTER);

        VBox RandomTeamGenCenter=new VBox();
        RandomTeamGenCenter.setPadding(new javafx.geometry.Insets(-309, 5, 5, 5));
        RandomTeamGenCenter.setSpacing(0);
        RandomTeamGenCenter.setAlignment(Pos.CENTER);

        ObservableList<String> RTGTeam1Array = FXCollections.observableArrayList("Team 1 Players Appear here","Add Names & press Shuffle");
        ListView<String> RTGTeam1List = new ListView<String>(RTGTeam1Array);
        RTGTeam1List.setMaxWidth(199);
        RTGTeam1List.setMaxHeight(235);
        RTGTeam1List.setStyle("-fx-background-color: white; " +
                    "-fx-text-fill: black; " +
                    "-fx-font-size: 12px; " +
                    "-fx-font-family: 'Comic Sans MS'; " +
                    "-fx-font-weight: bold; " +
                    "-fx-alignment: center; ");

        ObservableList<String> RTGTeam2Array = FXCollections.observableArrayList("Team 2 Players Appear here","Add Names & press Shuffle");
        ListView<String> RTGTeam2List = new ListView<String>(RTGTeam2Array);
        RTGTeam2List.setMaxWidth(199);
        RTGTeam2List.setMaxHeight(235);
        RTGTeam2List.setStyle("-fx-background-color: white; " +
                    "-fx-text-fill: black; " +
                    "-fx-font-size: 12px; " +
                    "-fx-font-family: 'Comic Sans MS'; " +
                    "-fx-font-weight: bold; " +
                    "-fx-alignment: center; ");

        HBox RandomTeamGenBottom=new HBox();
        RandomTeamGenBottom.setPadding(new javafx.geometry.Insets(-360, 5, 5, 5));
        RandomTeamGenBottom.setSpacing(40);
        RandomTeamGenBottom.setAlignment(Pos.CENTER);

        RandomTeamGenC2.getChildren().addAll(AddPlayerRTGBT,ShuffleBT);
        RandomTeamGenCenter.getChildren().addAll(PlayerRTGTB,RandomTeamGenC2);
        RandomTeamGenBottom.getChildren().addAll(RTGTeam1List,RTGTeam2List);
        RandomTeamGenTop.getChildren().addAll(MainMenuBT6);
        RandomTeamGenStagebg.setTop(RandomTeamGenTop);
        RandomTeamGenStagebg.setCenter(RandomTeamGenCenter);
        RandomTeamGenStagebg.setBottom(RandomTeamGenBottom);

        ArrayList<String> shuffleArray = new ArrayList<>();
        MainMenuBT6.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                RandomTeamGenStage.close();
                shuffleArray.clear();
            }
        });
        
        RandomTeamGenBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                RandomTeamGenStage.show();
            }
        });

        AddPlayerRTGBT.setOnAction(event -> 
        {
            String playerName = PlayerRTGTB.getText().trim();
            if (!playerName.isEmpty()) 
            {
                shuffleArray.add(playerName);
                PlayerRTGTB.clear();
            }
        });

        ShuffleBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                Collections.shuffle(shuffleArray);

                int teamSize = shuffleArray.size() / 2;

                ArrayList<String> team1 = new ArrayList<>(shuffleArray.subList(0, teamSize));
                ArrayList<String> team2 = new ArrayList<>(shuffleArray.subList(teamSize, shuffleArray.size()));

                RTGTeam1Array.clear();
                RTGTeam2Array.clear();
                RTGTeam1Array.addAll(team1);
                RTGTeam2Array.addAll(team2);
                RTGTeam1List.setItems(RTGTeam1Array);
                RTGTeam2List.setItems(RTGTeam2Array);
            }
        });

        Stage LoginStage=new Stage();
        LoginStage.setTitle("Login / Register");
        BorderPane Loginbg = new BorderPane();
        Loginbg.setBackground(new Background(BGimg));
        BackgroundImage LoginBG=new BackgroundImage(new Image("Graphics/LoginBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Loginbg.setBackground(new Background(LoginBG));
        Scene LoginScene=new Scene(Loginbg,501,500);
        LoginStage.setScene(LoginScene);

        Image SignInImg = new Image("Graphics/SignInBT.png");
        Image SignInImgHover = new Image("Graphics/SignInBThover.png");
        ImageView SignInBTview = new ImageView(SignInImg);
        SignInBTview.setFitWidth(121.7);
        SignInBTview.setPreserveRatio(true);
        ImageView SignInBThover = new ImageView(SignInImgHover);
        SignInBThover.setFitWidth(121.7);
        SignInBThover.setPreserveRatio(true);
        Button SignInBT=new Button("");
        SignInBT.setPrefWidth(121.7);
        SignInBT.setPrefHeight(44);
        SignInBT.setGraphic(SignInBTview);
        SignInBT.setStyle("-fx-background-color: #337018;");
        SignInBT.setOnMouseEntered(e -> SignInBT.setGraphic(SignInBThover));
        SignInBT.setOnMouseExited(e -> SignInBT.setGraphic(SignInBTview));

        Image NewRegisterImg = new Image("Graphics/NewRegisterBT.png");
        Image NewRegisterImgHover = new Image("Graphics/NewRegisterBThover.png");
        ImageView NewRegisterBTview = new ImageView(NewRegisterImg);
        NewRegisterBTview.setFitWidth(121.7);
        NewRegisterBTview.setPreserveRatio(true);
        ImageView NewRegisterBThover = new ImageView(NewRegisterImgHover);
        NewRegisterBThover.setFitWidth(121.7);
        NewRegisterBThover.setPreserveRatio(true);
        Button NewRegisterBT=new Button("");
        NewRegisterBT.setPrefWidth(121.7);
        NewRegisterBT.setPrefHeight(44);
        NewRegisterBT.setGraphic(NewRegisterBTview);
        NewRegisterBT.setStyle("-fx-background-color: #337018;");
        NewRegisterBT.setOnMouseEntered(e -> NewRegisterBT.setGraphic(NewRegisterBThover));
        NewRegisterBT.setOnMouseExited(e -> NewRegisterBT.setGraphic(NewRegisterBTview));

        TextField LoginUsernameTB = new TextField("");
        LoginUsernameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 15px;");
        LoginUsernameTB.setAlignment(Pos.CENTER);
        LoginUsernameTB.setMaxWidth(270);
        LoginUsernameTB.setMaxHeight(15);

        PasswordField LoginPasswordTB = new PasswordField();
        LoginPasswordTB.setStyle("-fx-text-fill: black; " +
                                 "-fx-font-weight: bold; " +
                                 "-fx-font-family: 'Comic Sans MS'; " +
                                 "-fx-font-size: 15px;");
        LoginPasswordTB.setAlignment(Pos.CENTER);
        LoginPasswordTB.setMaxWidth(270);
        LoginPasswordTB.setMaxHeight(15);

        VBox LoginTBS=new VBox();
        LoginTBS.setPadding(new javafx.geometry.Insets(85, 0, 5, 145));
        LoginTBS.setSpacing(8);
        LoginTBS.setAlignment(Pos.CENTER);

        HBox LoginBTS=new HBox();
        LoginBTS.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
        LoginBTS.setSpacing(20);
        LoginBTS.setAlignment(Pos.CENTER);

        VBox LoginCenter=new VBox();
        LoginCenter.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        LoginCenter.setSpacing(5);
        LoginCenter.setAlignment(Pos.CENTER);

        LoginTBS.getChildren().addAll(LoginUsernameTB,LoginPasswordTB);
        LoginBTS.getChildren().addAll(SignInBT,NewRegisterBT);
        LoginCenter.getChildren().addAll(LoginTBS,LoginBTS);
        Loginbg.setCenter(LoginCenter);

        LoginStage.show();

        LogoutBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                UserDetails.UserID=-1;
                UserDetails.TeamID=-1;
                UserDetails.PlayerID=-1;
                UserDetails.UName="";
                UserDetails.Add=false;
                RTGTeam1Array.clear();
                RTGTeam2Array.clear();
                LoginStage.show();
            }
        });
        SignInBT.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                // Retrieve username and password from text fields
                String username = LoginUsernameTB.getText();
                String password = LoginPasswordTB.getText();

                // Check if username and password fields are empty
                if (username.isEmpty() || password.isEmpty()) 
                {
                    System.out.println("Please enter both username and password.");
                    return;
                }

                // Prepare database connection
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    // Query to check if username and password match in the Manager table
                    String query = "SELECT * FROM Manager WHERE Username = ? AND Password = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                        // Set parameters for the prepared statement
                        statement.setString(1, username);
                        statement.setString(2, password);

                        // Execute the query
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            // If a row is returned, login is successful
                            if (resultSet.next()) 
                            {
                                System.out.println("Login successful!");
                                // Retrieve user details if needed
                                UserDetails.UserID = resultSet.getInt("ID");
                                UserDetails.UName = resultSet.getString("NAME");
                                UserDetails.TeamID = resultSet.getInt("TEAMID");
                                String WelcomeMsg="WELCOME "+UserDetails.UName.toUpperCase();
                                Welcome.setText(WelcomeMsg);
                                mainStage.show();
                                LoginStage.close();
                                LoginUsernameTB.clear();
                                LoginPasswordTB.clear();
                            } 
                            else 
                            {
                                System.out.println("Incorrect password. Please try again.");
                                
                                LoginUsernameTB.clear();
                                LoginPasswordTB.clear();
                                
                                JOptionPane.showMessageDialog(null, "Incorrect Password / Username. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                }
            }
        });

        MatchesBT2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                MyTeamStage.close();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT TEAM1SCORE, TEAM2SCORE, TEAM1ID, TEAM2ID, HIGHLIGHTS FROM (SELECT * FROM matches WHERE (TEAM1ID = ? OR TEAM2ID = ?) AND MATCHDATE <= (TO_DATE(?,'DD-MON-YYYY')) ORDER BY MATCHDATE DESC) WHERE ROWNUM = 1"; 
                    try (PreparedStatement statement = connection.prepareStatement(sql)) 
                    {
                        int teamID1 = UserDetails.TeamID;
                        int teamID2 = UserDetails.TeamID;
                        Date currentDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                        String dateString = dateFormat.format(currentDate);
                        statement.setInt(1, teamID1);
                        statement.setInt(2, teamID2);
                        statement.setString(3, dateString);
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            if (resultSet.next()) 
                            {
                                int score1 = resultSet.getInt("TEAM1SCORE");
                                int score2 = resultSet.getInt("TEAM2SCORE");
                                int team1_id = resultSet.getInt("TEAM1ID");
                                int team2_id = resultSet.getInt("TEAM2ID");
                                String highlights = resultSet.getString("HIGHLIGHTS");
                                
                                String sql1 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statement1 = connection.prepareStatement(sql1)) 
                                {
                                    statement1.setInt(1, team1_id);
                                    try (ResultSet resultSet1 = statement1.executeQuery()) 
                                    {
                                        if (resultSet1.next()) 
                                        {
                                            Team1.setText(UserDetails.generateAbbreviation(resultSet1.getString("TEAMNAME")));
                                        } 
                                        else 
                                        {
                                            Team1.setText("No team with " + teamID1);
                                        }
                                    }
                                }
                                String sql2 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statement2 = connection.prepareStatement(sql2)) 
                                {
                                    statement2.setInt(1, team2_id);
                                    try (ResultSet resultSet2 = statement2.executeQuery()) 
                                    {
                                        if (resultSet2.next()) 
                                        {
                                            Team2.setText(UserDetails.generateAbbreviation(resultSet2.getString("TEAMNAME")));
                                        } 
                                        else 
                                        {
                                            Team2.setText("No team with " + teamID2);
                                        }
                                    }
                                }
                                System.out.println(score1+score2+team1_id+team2_id+highlights);
                                Score1.setText(String.valueOf(score1));
                                Score2.setText(String.valueOf(score2));
                                MatchSummary.setText(highlights);
                            } 
                            else 
                            {
                                MatchSummary.setText("No Recent Matches, Please Contact League DB Manager to Update Matches");
                                Team1.setText("-");
                                Team2.setText("-");
                                Score1.setText("-");
                                Score2.setText("-");
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT TEAM1ID, TEAM2ID, MATCHDATE FROM matches WHERE (TEAM1ID = ? OR TEAM2ID = ?) AND MATCHDATE > ?";

                    try (PreparedStatement statement = connection.prepareStatement(sql)) 
                    {
                        int teamID = UserDetails.TeamID;
                        Date currentDate = new Date(System.currentTimeMillis());
                        
                        statement.setInt(1, teamID);
                        statement.setInt(2, teamID);
                        statement.setDate(3, new java.sql.Date(currentDate.getTime()));
                        MatchesList.getItems().clear();
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            while (resultSet.next()) 
                            {
                                int team1_id = resultSet.getInt("TEAM1ID");
                                int team2_id = resultSet.getInt("TEAM2ID");
                                Date matchDate = resultSet.getDate("MATCHDATE");

                                String teamName1 = "No team with " + team1_id;
                                String teamName2 = "No team with " + team2_id;

                                String sqlTeam1 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statementTeam1 = connection.prepareStatement(sqlTeam1)) 
                                {
                                    statementTeam1.setInt(1, team1_id);
                                    try (ResultSet resultSetTeam1 = statementTeam1.executeQuery()) 
                                    {
                                        if (resultSetTeam1.next()) 
                                        {
                                            teamName1 = resultSetTeam1.getString("TEAMNAME");
                                        }
                                    }
                                }

                                String sqlTeam2 = "SELECT TEAMNAME FROM team WHERE TEAMID = ?";
                                try (PreparedStatement statementTeam2 = connection.prepareStatement(sqlTeam2)) 
                                {
                                    statementTeam2.setInt(1, team2_id);
                                    try (ResultSet resultSetTeam2 = statementTeam2.executeQuery()) 
                                    {
                                        if (resultSetTeam2.next()) 
                                        {
                                            teamName2 = resultSetTeam2.getString("TEAMNAME");
                                        }
                                    }
                                }

                                String matchDetails = teamName1 + " vs " + teamName2 + " - " + matchDate.toString();
                                MatchesArray.add(matchDetails);
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                MatchesStage.show();
            }
        });

        LineUpBT2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                MyTeamStage.close();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String query = "SELECT NAME FROM player WHERE TEAMID = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                        statement.setInt(1, UserDetails.TeamID); // Set the team ID parameter
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            ObservableList<String> playerNames = FXCollections.observableArrayList();
                            while (resultSet.next()) 
                            {
                                String playerName = resultSet.getString("NAME");
                                playerNames.add(playerName);
                            }
                            LineUpList.setItems(playerNames);
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    // Handle SQL exception
                }
                LineUpStage.show();
            }
        });

        Stage SettingsStage=new Stage();
        SettingsStage.setTitle("Recent and UpComing Fixtures");
        BorderPane SettingsStagebg = new BorderPane();
        BackgroundImage SettingsBG=new BackgroundImage(new Image("Graphics/SettingsBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        SettingsStagebg.setBackground(new Background(SettingsBG));
        Scene SettingsScene=new Scene(SettingsStagebg,501,500);
        SettingsStage.setScene(SettingsScene);
        
        ImageView MainMenuImgview4= new ImageView(MainMenuImg);
        MainMenuImgview4.setFitWidth(112.2);
        MainMenuImgview4.setPreserveRatio(true);
        ImageView MainMenuImgviewHover4 = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover4.setFitWidth(112.2);
        MainMenuImgviewHover4.setPreserveRatio(true);
        Button MainMenuBT4=new Button("");
        MainMenuBT4.setPrefWidth(112.2);
        MainMenuBT4.setPrefHeight(33.3);
        MainMenuBT4.setGraphic(MainMenuImgview);
        MainMenuBT4.setStyle("-fx-background-color: #7ED957;");
        MainMenuBT4.setOnMouseEntered(e -> MainMenuBT4.setGraphic(MainMenuImgviewHover4));
        MainMenuBT4.setOnMouseExited(e -> MainMenuBT4.setGraphic(MainMenuImgview4));

        HBox SettingsTop=new HBox();
        SettingsTop.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        SettingsTop.setSpacing(10);
        SettingsTop.setAlignment(Pos.CENTER_RIGHT);

        VBox SettingsCenter=new VBox();
        SettingsCenter.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        SettingsCenter.setSpacing(0);
        SettingsCenter.setAlignment(Pos.CENTER);

        VBox UserTBS=new VBox();
        UserTBS.setPadding(new javafx.geometry.Insets(-282, -160, 5, 5));
        UserTBS.setSpacing(2);
        UserTBS.setAlignment(Pos.CENTER);

        TextField UNameTB = new TextField("Ridhwan Shameem");
        UNameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        UNameTB.setAlignment(Pos.CENTER);
        UNameTB.setMaxWidth(280);
        UNameTB.setMaxHeight(9);
        UNameTB.setEditable(false);

        TextField UsernameTB = new TextField("ridhwxn_vs");
        UsernameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        UsernameTB.setAlignment(Pos.CENTER);
        UsernameTB.setMaxWidth(280);
        UsernameTB.setMaxHeight(9);
        UsernameTB.setEditable(false);

        TextField PasswordTB = new TextField("ahmed93");
        PasswordTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PasswordTB.setAlignment(Pos.CENTER);
        PasswordTB.setMaxWidth(280);
        PasswordTB.setMaxHeight(9);
        PasswordTB.setEditable(false);

        TextField TeamNameTB = new TextField("Vibes FC");
        TeamNameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        TeamNameTB.setAlignment(Pos.CENTER);
        TeamNameTB.setMaxWidth(280);
        TeamNameTB.setMaxHeight(9);
        TeamNameTB.setEditable(false);

        TextField EmailTB = new TextField("ridhwanvs04@gmail.com");
        EmailTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        EmailTB.setAlignment(Pos.CENTER);
        EmailTB.setMaxWidth(280);
        EmailTB.setMaxHeight(9);
        EmailTB.setEditable(false);

        HBox UserSettingsBTS1=new HBox();
        UserSettingsBTS1.setPadding(new javafx.geometry.Insets(-131, 0, 0, 0));
        UserSettingsBTS1.setSpacing(10);
        UserSettingsBTS1.setAlignment(Pos.CENTER);

        HBox UserSettingsBTS2=new HBox();
        UserSettingsBTS2.setPadding(new javafx.geometry.Insets(-131, 0, 0, 30));
        UserSettingsBTS2.setSpacing(10);
        UserSettingsBTS2.setAlignment(Pos.CENTER);

        Image EditDetailsImg = new Image("Graphics/EditDetailsBT.png");
        Image EditDetailsImgHover = new Image("Graphics/EditDetailsBThover.png");
        ImageView EditDetailsBTview = new ImageView(EditDetailsImg);
        EditDetailsBTview.setFitWidth(139.6);
        EditDetailsBTview.setPreserveRatio(true);
        ImageView EditDetailsBThover = new ImageView(EditDetailsImgHover);
        EditDetailsBThover.setFitWidth(139.6);
        EditDetailsBThover.setPreserveRatio(true);
        Button EditDetailsBT=new Button("");
        EditDetailsBT.setPrefWidth(139.6);
        EditDetailsBT.setPrefHeight(28.1);
        EditDetailsBT.setGraphic(EditDetailsBTview);
        EditDetailsBT.setStyle("-fx-background-color: #337018;");
        EditDetailsBT.setOnMouseEntered(e -> EditDetailsBT.setGraphic(EditDetailsBThover));
        EditDetailsBT.setOnMouseExited(e -> EditDetailsBT.setGraphic(EditDetailsBTview));

        Image SettingsLogoutImg = new Image("Graphics/SettingsLogoutBT.png");
        Image SettingsLogoutImgHover = new Image("Graphics/SettingsLogoutBThover.png");
        ImageView SettingsLogoutBTview = new ImageView(SettingsLogoutImg);
        SettingsLogoutBTview.setFitWidth(87.5);
        SettingsLogoutBTview.setPreserveRatio(true);
        ImageView SettingsLogoutBThover = new ImageView(SettingsLogoutImgHover);
        SettingsLogoutBThover.setFitWidth(87.5);
        SettingsLogoutBThover.setPreserveRatio(true);
        Button SettingsLogoutBT=new Button("");
        SettingsLogoutBT.setPrefWidth(87.5);
        SettingsLogoutBT.setPrefHeight(28.1);
        SettingsLogoutBT.setGraphic(SettingsLogoutBTview);
        SettingsLogoutBT.setStyle("-fx-background-color: #337018;");
        SettingsLogoutBT.setOnMouseEntered(e -> SettingsLogoutBT.setGraphic(SettingsLogoutBThover));
        SettingsLogoutBT.setOnMouseExited(e -> SettingsLogoutBT.setGraphic(SettingsLogoutBTview));

        Image DeleteAccountImg = new Image("Graphics/DeleteAccountBT.png");
        Image DeleteAccountImgHover = new Image("Graphics/DeleteAccountBThover.png");
        ImageView DeleteAccountBTview = new ImageView(DeleteAccountImg);
        DeleteAccountBTview.setFitWidth(139.6);
        DeleteAccountBTview.setPreserveRatio(true);
        ImageView DeleteAccountBThover = new ImageView(DeleteAccountImgHover);
        DeleteAccountBThover.setFitWidth(139.6);
        DeleteAccountBThover.setPreserveRatio(true);
        Button DeleteAccountBT=new Button("");
        DeleteAccountBT.setPrefWidth(139.6);
        DeleteAccountBT.setPrefHeight(28.1);
        DeleteAccountBT.setGraphic(DeleteAccountBTview);
        DeleteAccountBT.setStyle("-fx-background-color: #337018;");
        DeleteAccountBT.setOnMouseEntered(e -> DeleteAccountBT.setGraphic(DeleteAccountBThover));
        DeleteAccountBT.setOnMouseExited(e -> DeleteAccountBT.setGraphic(DeleteAccountBTview));

        Image UserSaveImg = new Image("Graphics/SaveEditsBT.png");
        Image UserSaveImgHover = new Image("Graphics/SaveEditsBThover.png");
        ImageView UserSaveBTview = new ImageView(UserSaveImg);
        UserSaveBTview.setFitWidth(55.1);
        UserSaveBTview.setPreserveRatio(true);
        ImageView UserSaveBThover = new ImageView(UserSaveImgHover);
        UserSaveBThover.setFitWidth(55.1);
        UserSaveBThover.setPreserveRatio(true);
        Button UserSaveBT=new Button("");
        UserSaveBT.setPrefWidth(55.1);
        UserSaveBT.setPrefHeight(28.1);
        UserSaveBT.setGraphic(UserSaveBTview);
        UserSaveBT.setStyle("-fx-background-color: #337018;");
        UserSaveBT.setOnMouseEntered(e -> UserSaveBT.setGraphic(UserSaveBThover));
        UserSaveBT.setOnMouseExited(e -> UserSaveBT.setGraphic(UserSaveBTview));
        UserSaveBT.setVisible(false);

        Image UserCancelImg = new Image("Graphics/CancelEditsBT.png");
        Image UserCancelImgHover = new Image("Graphics/CancelEditsBThover.png");
        ImageView UserCancelBTview = new ImageView(UserCancelImg);
        UserCancelBTview.setFitWidth(91.3);
        UserCancelBTview.setPreserveRatio(true);
        ImageView UserCancelBThover = new ImageView(UserCancelImgHover);
        UserCancelBThover.setFitWidth(91.3);
        UserCancelBThover.setPreserveRatio(true);
        Button UserCancelBT=new Button("");
        UserCancelBT.setPrefWidth(91.3);
        UserCancelBT.setPrefHeight(28.1);
        UserCancelBT.setGraphic(UserCancelBTview);
        UserCancelBT.setStyle("-fx-background-color: #337018;");
        UserCancelBT.setOnMouseEntered(e -> UserCancelBT.setGraphic(UserCancelBThover));
        UserCancelBT.setOnMouseExited(e -> UserCancelBT.setGraphic(UserCancelBTview));
        UserCancelBT.setVisible(false);

        VBox SettingsBottom=new VBox();
        SettingsBottom.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        SettingsBottom.setSpacing(0);
        SettingsBottom.setAlignment(Pos.CENTER);

        VBox PlayerTBS=new VBox();
        PlayerTBS.setPadding(new javafx.geometry.Insets(-280, -160, 5, 5));
        PlayerTBS.setSpacing(2);
        PlayerTBS.setAlignment(Pos.CENTER);

        Label space=new Label("");
        space.setStyle("fx-max-height: 4px; ");
        
        ComboBox<String> SelectPlayerBox = new ComboBox<>(PlayerNames);
        SelectPlayerBox.setStyle("-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 12px; " +
            "-fx-font-family: 'Arial'; " +
            "-fx-font-weight: bold; " +
            "-fx-border-color: gray; " +
            "-fx-border-width: 1px; " +
            "-fx-border-style: solid; "+
            "-fx-max-width:280px; ");
        
        //SelectPlayerBox.setOnAction(event -> {String selectedPlayer = SelectPlayerBox.getSelectionModel().getSelectedItem();
        //System.out.println("Selected player: " + selectedPlayer);});

        TextField PlayernameTB = new TextField("Select Player");
        PlayernameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayernameTB.setAlignment(Pos.CENTER);
        PlayernameTB.setMaxWidth(280);
        PlayernameTB.setMaxHeight(9);
        PlayernameTB.setEditable(false);

        TextField PlayerAgeTB = new TextField("-");
        PlayerAgeTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerAgeTB.setAlignment(Pos.CENTER);
        PlayerAgeTB.setMaxWidth(280);
        PlayerAgeTB.setMaxHeight(9);
        PlayerAgeTB.setEditable(false);

        TextField PlayerPositionTB = new TextField("-");
        PlayerPositionTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerPositionTB.setAlignment(Pos.CENTER);
        PlayerPositionTB.setMaxWidth(280);
        PlayerPositionTB.setMaxHeight(9);
        PlayerPositionTB.setEditable(false);

        TextField PlayerNationTB = new TextField("-");
        PlayerNationTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerNationTB.setAlignment(Pos.CENTER);
        PlayerNationTB.setMaxWidth(280);
        PlayerNationTB.setMaxHeight(9);
        PlayerNationTB.setEditable(false);

        TextField PlayerKitTB = new TextField("-");
        PlayerKitTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerKitTB.setAlignment(Pos.CENTER);
        PlayerKitTB.setMaxWidth(280);
        PlayerKitTB.setMaxHeight(9);
        PlayerKitTB.setEditable(false);

        TextField PlayerOVTB = new TextField("-");
        PlayerOVTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerOVTB.setAlignment(Pos.CENTER);
        PlayerOVTB.setMaxWidth(280);
        PlayerOVTB.setMaxHeight(9);
        PlayerOVTB.setEditable(false);

        HBox PlayerSettingsBTS1=new HBox();
        PlayerSettingsBTS1.setPadding(new javafx.geometry.Insets(-60, 0, 0, 0));
        PlayerSettingsBTS1.setSpacing(10);
        PlayerSettingsBTS1.setAlignment(Pos.CENTER);

        HBox PlayerSettingsBTS2=new HBox();
        PlayerSettingsBTS2.setPadding(new javafx.geometry.Insets(-60, 0, 0, 30));
        PlayerSettingsBTS2.setSpacing(10);
        PlayerSettingsBTS2.setAlignment(Pos.CENTER);

        Image EditPlayerImg = new Image("Graphics/EditPlayerBT.png");
        Image EditPlayerImgHover = new Image("Graphics/EditPlayerBThover.png");
        ImageView EditPlayerBTview = new ImageView(EditPlayerImg);
        EditPlayerBTview.setFitWidth(120);
        EditPlayerBTview.setPreserveRatio(true);
        ImageView EditPlayerBThover = new ImageView(EditPlayerImgHover);
        EditPlayerBThover.setFitWidth(120);
        EditPlayerBThover.setPreserveRatio(true);
        Button EditPlayerBT=new Button("");
        EditPlayerBT.setPrefWidth(120);
        EditPlayerBT.setPrefHeight(28.1);
        EditPlayerBT.setGraphic(EditPlayerBTview);
        EditPlayerBT.setStyle("-fx-background-color: #337018;");
        EditPlayerBT.setOnMouseEntered(e -> EditPlayerBT.setGraphic(EditPlayerBThover));
        EditPlayerBT.setOnMouseExited(e -> EditPlayerBT.setGraphic(EditPlayerBTview));

        Image AddPlayerImg = new Image("Graphics/AddPlayerBT.png");
        Image AddPlayerImgHover = new Image("Graphics/AddPlayerBThover.png");
        ImageView AddPlayerBTview = new ImageView(AddPlayerImg);
        AddPlayerBTview.setFitWidth(120);
        AddPlayerBTview.setPreserveRatio(true);
        ImageView AddPlayerBThover = new ImageView(AddPlayerImgHover);
        AddPlayerBThover.setFitWidth(120);
        AddPlayerBThover.setPreserveRatio(true);
        Button AddPlayerBT=new Button("");
        AddPlayerBT.setPrefWidth(120);
        AddPlayerBT.setPrefHeight(28.1);
        AddPlayerBT.setGraphic(AddPlayerBTview);
        AddPlayerBT.setStyle("-fx-background-color: #337018;");
        AddPlayerBT.setOnMouseEntered(e -> AddPlayerBT.setGraphic(AddPlayerBThover));
        AddPlayerBT.setOnMouseExited(e -> AddPlayerBT.setGraphic(AddPlayerBTview));

        Image DeletePlayerImg = new Image("Graphics/DeletePlayerBT.png");
        Image DeletePlayerImgHover = new Image("Graphics/DeletePlayerBThover.png");
        ImageView DeletePlayerBTview = new ImageView(DeletePlayerImg);
        DeletePlayerBTview.setFitWidth(120);
        DeletePlayerBTview.setPreserveRatio(true);
        ImageView DeletePlayerBThover = new ImageView(DeletePlayerImgHover);
        DeletePlayerBThover.setFitWidth(120);
        DeletePlayerBThover.setPreserveRatio(true);
        Button DeletePlayerBT=new Button("");
        DeletePlayerBT.setPrefWidth(120);
        DeletePlayerBT.setPrefHeight(28.1);
        DeletePlayerBT.setGraphic(DeletePlayerBTview);
        DeletePlayerBT.setStyle("-fx-background-color: #337018;");
        DeletePlayerBT.setOnMouseEntered(e -> DeletePlayerBT.setGraphic(DeletePlayerBThover));
        DeletePlayerBT.setOnMouseExited(e -> DeletePlayerBT.setGraphic(DeletePlayerBTview));

        Image PlayerSaveImg = new Image("Graphics/SaveEditsBT.png");
        Image PlayerSaveImgHover = new Image("Graphics/SaveEditsBThover.png");
        ImageView PlayerSaveBTview = new ImageView(PlayerSaveImg);
        PlayerSaveBTview.setFitWidth(55.1);
        PlayerSaveBTview.setPreserveRatio(true);
        ImageView PlayerSaveBThover = new ImageView(PlayerSaveImgHover);
        PlayerSaveBThover.setFitWidth(55.1);
        PlayerSaveBThover.setPreserveRatio(true);
        Button PlayerSaveBT=new Button("");
        PlayerSaveBT.setPrefWidth(55.1);
        PlayerSaveBT.setPrefHeight(28.1);
        PlayerSaveBT.setGraphic(PlayerSaveBTview);
        PlayerSaveBT.setStyle("-fx-background-color: #337018;");
        PlayerSaveBT.setOnMouseEntered(e -> PlayerSaveBT.setGraphic(PlayerSaveBThover));
        PlayerSaveBT.setOnMouseExited(e -> PlayerSaveBT.setGraphic(PlayerSaveBTview));
        PlayerSaveBT.setVisible(false);

        Image PlayerCancelImg = new Image("Graphics/CancelEditsBT.png");
        Image PlayerCancelImgHover = new Image("Graphics/CancelEditsBThover.png");
        ImageView PlayerCancelBTview = new ImageView(PlayerCancelImg);
        PlayerCancelBTview.setFitWidth(91.3);
        PlayerCancelBTview.setPreserveRatio(true);
        ImageView PlayerCancelBThover = new ImageView(PlayerCancelImgHover);
        PlayerCancelBThover.setFitWidth(91.3);
        PlayerCancelBThover.setPreserveRatio(true);
        Button PlayerCancelBT=new Button("");
        PlayerCancelBT.setPrefWidth(91.3);
        PlayerCancelBT.setPrefHeight(28.1);
        PlayerCancelBT.setGraphic(PlayerCancelBTview);
        PlayerCancelBT.setStyle("-fx-background-color: #337018;");
        PlayerCancelBT.setOnMouseEntered(e -> PlayerCancelBT.setGraphic(PlayerCancelBThover));
        PlayerCancelBT.setOnMouseExited(e -> PlayerCancelBT.setGraphic(PlayerCancelBTview));
        PlayerCancelBT.setVisible(false);

        PlayerSettingsBTS1.getChildren().addAll(EditPlayerBT,AddPlayerBT,DeletePlayerBT);
        PlayerSettingsBTS2.getChildren().addAll(PlayerSaveBT,PlayerCancelBT);
        PlayerTBS.getChildren().addAll(SelectPlayerBox,space,PlayernameTB,PlayerAgeTB,PlayerPositionTB,PlayerNationTB,PlayerKitTB,PlayerOVTB);

        UserSettingsBTS1.getChildren().addAll(EditDetailsBT,SettingsLogoutBT,DeleteAccountBT);
        UserSettingsBTS2.getChildren().addAll(UserSaveBT,UserCancelBT);
        UserTBS.getChildren().addAll(UsernameTB,PasswordTB,UNameTB,TeamNameTB,EmailTB);
        SettingsCenter.getChildren().addAll(UserTBS,UserSettingsBTS1,UserSettingsBTS2);
        SettingsBottom.getChildren().addAll(PlayerTBS,PlayerSettingsBTS1,PlayerSettingsBTS2);
        SettingsTop.getChildren().addAll(MainMenuBT4);
        SettingsStagebg.setTop(SettingsTop);
        SettingsStagebg.setCenter(SettingsCenter);
        SettingsStagebg.setBottom(SettingsBottom);

        SettingsBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                SettingsStage.show();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT * FROM Manager WHERE ID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, UserDetails.UserID);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) 
                    {
                        UNameTB.setText(resultSet.getString("NAME"));
                        UsernameTB.setText(resultSet.getString("USERNAME"));
                        PasswordTB.setText(resultSet.getString("PASSWORD"));
                        EmailTB.setText(resultSet.getString("EMAIL"));
                    }
                    resultSet.close();
                    statement.close();

                    String teamSql = "SELECT TEAMNAME FROM Team WHERE TEAMID = ?";
                    PreparedStatement teamStatement = connection.prepareStatement(teamSql);
                    teamStatement.setInt(1, UserDetails.TeamID);

                    ResultSet teamResultSet = teamStatement.executeQuery();

                    if (teamResultSet.next()) 
                    {
                        TeamNameTB.setText(teamResultSet.getString("TEAMNAME"));
                    }

                    teamResultSet.close();
                    teamStatement.close();

                    String query = "SELECT NAME FROM player WHERE TEAMID = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(query)) 
                    {
                        statement2.setInt(1, UserDetails.TeamID); // Set the team ID parameter
                        try (ResultSet resultset = statement2.executeQuery()) 
                        {
                            ObservableList<String> playerNames = FXCollections.observableArrayList();
                            while (resultset.next()) 
                            {
                                String playerName = resultset.getString("NAME");
                                playerNames.add(playerName);
                            }
                            SelectPlayerBox.setItems(playerNames);
                        }
                    }
                    connection.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        });
        SelectPlayerBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
        {
            if (newValue != null) 
            {
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String query = "SELECT * FROM player WHERE NAME = ? AND TEAMID = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) 
                    {
                        statement.setString(1, newValue);
                        statement.setInt(2, UserDetails.TeamID); 
                        try (ResultSet resultSet = statement.executeQuery()) 
                        {
                            if (resultSet.next()) 
                            {
                                String playerName = resultSet.getString("NAME");
                                int playerAge = resultSet.getInt("AGE");
                                String playerPosition = resultSet.getString("POSITION");
                                String playerNation = "  " + resultSet.getString("NATION");
                                int playerKit = resultSet.getInt("JERSEYNO");
                                int playerOverall = resultSet.getInt("OVERALL");
                                UserDetails.PlayerID= resultSet.getInt("PLAYERID");
                                PlayernameTB.setText(playerName);
                                PlayerAgeTB.setText(Integer.toString(playerAge));
                                PlayerPositionTB.setText(playerPosition);
                                PlayerNationTB.setText(playerNation);
                                PlayerKitTB.setText(Integer.toString(playerKit));
                                PlayerOVTB.setText(Integer.toString(playerOverall));
                            }
                        }
                    }
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                    // Handle SQL exception
                }
            }
        });
        MainMenuBT4.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                SettingsStage.close();
                PlayerAgeTB.setText("-");
                PlayernameTB.setText("Select Player");
                PlayerOVTB.setText("-");
                PlayerNationTB.setText("-");
                PlayerKitTB.setText("-");
                PlayerPositionTB.setText("-");
                SelectPlayerBox.setValue(null);
            }
        });

        SettingsLogoutBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                LoginStage.show();
                UserDetails.UserID=-1;
                UserDetails.TeamID=-1;
                UserDetails.PlayerID=-1;
                UserDetails.UName="";
                UserDetails.Add=false;
                RTGTeam1Array.clear();
                RTGTeam2Array.clear();
                SettingsStage.close();
            }
        });
        DeleteAccountBT.setOnAction(event -> {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete your account? This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
            {
                String deleteManagerSql = "DELETE FROM Manager WHERE ID = ?";
                PreparedStatement managerStatement = connection.prepareStatement(deleteManagerSql);
                managerStatement.setInt(1, UserDetails.UserID);
                int managerRowsDeleted = managerStatement.executeUpdate();

                String deletePlayerSql = "DELETE FROM Player WHERE TEAMID = ?";
                PreparedStatement playerStatement = connection.prepareStatement(deletePlayerSql);
                playerStatement.setInt(1, UserDetails.TeamID);
                int playerRowsDeleted = playerStatement.executeUpdate();

                if (managerRowsDeleted > 0 || playerRowsDeleted > 0) 
                {
                    System.out.println("Account deleted successfully!");
                    LoginStage.show();
                    UserDetails.UserID=-1;
                    UserDetails.TeamID=-1;
                    UserDetails.PlayerID=-1;
                    UserDetails.UName="";
                    UserDetails.Add=false;
                    SettingsStage.close();
                } 
                else 
                {
                    System.out.println("No records found or failed to delete.");
                }
                managerStatement.close();
                playerStatement.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    });
        EditDetailsBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditDetailsBT.setVisible(false);
                SettingsLogoutBT.setVisible(false);
                DeleteAccountBT.setVisible(false);
                UserCancelBT.setVisible(true);
                UserSaveBT.setVisible(true);
                PasswordTB.setEditable(true);
                TeamNameTB.setEditable(true);
                EmailTB.setEditable(true);

            }
        });
        UserSaveBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            { 
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String updatePasswordSql = "UPDATE Manager SET PASSWORD = ? WHERE ID = ?";
                    PreparedStatement updatePasswordStatement = connection.prepareStatement(updatePasswordSql);
                    updatePasswordStatement.setString(1, PasswordTB.getText());
                    updatePasswordStatement.setInt(2, UserDetails.UserID);
                    updatePasswordStatement.executeUpdate();
                    updatePasswordStatement.close();

                    String updateTeamNameSql = "UPDATE Team SET TEAMNAME = ? WHERE TEAMID = ?";
                    PreparedStatement updateTeamNameStatement = connection.prepareStatement(updateTeamNameSql);
                    updateTeamNameStatement.setString(1, TeamNameTB.getText());
                    updateTeamNameStatement.setInt(2, UserDetails.TeamID);
                    updateTeamNameStatement.executeUpdate();
                    updateTeamNameStatement.close();

                    String updateEmailSql = "UPDATE Manager SET EMAIL = ? WHERE ID = ?";
                    PreparedStatement updateEmailStatement = connection.prepareStatement(updateEmailSql);
                    updateEmailStatement.setString(1, EmailTB.getText());
                    updateEmailStatement.setInt(2, UserDetails.UserID);
                    updateEmailStatement.executeUpdate();
                    updateEmailStatement.close();

                    System.out.println("Updates saved successfully!");
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }   
                EditDetailsBT.setVisible(true);
                SettingsLogoutBT.setVisible(true);
                DeleteAccountBT.setVisible(true);
                UserCancelBT.setVisible(false);
                UserSaveBT.setVisible(false);
                PasswordTB.setEditable(false);
                TeamNameTB.setEditable(false);
                EmailTB.setEditable(false);
            }
        });
        UserCancelBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditDetailsBT.setVisible(true);
                SettingsLogoutBT.setVisible(true);
                DeleteAccountBT.setVisible(true);
                UserCancelBT.setVisible(false);
                UserSaveBT.setVisible(false);
                PasswordTB.setEditable(false);
                TeamNameTB.setEditable(false);
                EmailTB.setEditable(false);
                //Initialise()
            }
        });
        EditPlayerBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditPlayerBT.setVisible(false);
                AddPlayerBT.setVisible(false);
                DeletePlayerBT.setVisible(false);
                PlayerCancelBT.setVisible(true);
                PlayerSaveBT.setVisible(true);
                PlayerAgeTB.setEditable(true);
                PlayernameTB.setEditable(true);
                PlayerOVTB.setEditable(true);
                PlayerNationTB.setEditable(true);
                PlayerKitTB.setEditable(true);
                PlayerPositionTB.setEditable(true);
                UserDetails.Add=false;
            }
        });
        AddPlayerBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditPlayerBT.setVisible(false);
                AddPlayerBT.setVisible(false);
                DeletePlayerBT.setVisible(false);
                PlayerCancelBT.setVisible(true);
                PlayerSaveBT.setVisible(true);
                PlayerAgeTB.setEditable(true);
                PlayerAgeTB.setText("");
                PlayernameTB.setEditable(true);
                PlayernameTB.setText("");
                PlayerOVTB.setEditable(true);
                PlayerOVTB.setText("");
                PlayerNationTB.setEditable(true);
                PlayerNationTB.setText("");
                PlayerKitTB.setEditable(true);
                PlayerKitTB.setText("");
                PlayerPositionTB.setEditable(true);
                PlayerPositionTB.setText("");
                UserDetails.Add=true;
            }
        });
        PlayerSaveBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditPlayerBT.setVisible(true);
                AddPlayerBT.setVisible(true);
                DeletePlayerBT.setVisible(true);
                PlayerCancelBT.setVisible(false);
                PlayerSaveBT.setVisible(false);
                PlayerAgeTB.setEditable(false);
                PlayernameTB.setEditable(false);
                PlayerOVTB.setEditable(false);
                PlayerNationTB.setEditable(false);
                PlayerKitTB.setEditable(false);
                PlayerPositionTB.setEditable(false);
                if (!UserDetails.Add)
                {
                    try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                    {
                        String playerName = PlayernameTB.getText();
                        int age = Integer.parseInt(PlayerAgeTB.getText());
                        String position = PlayerPositionTB.getText();
                        String nation = PlayerNationTB.getText();
                        int jerseyNo = Integer.parseInt(PlayerKitTB.getText());
                        int overall = Integer.parseInt(PlayerOVTB.getText());

                        String sql = "UPDATE Player SET NAME = ?, AGE = ?, POSITION = ?, NATION = ?, JERSEYNO = ?, OVERALL = ? WHERE PLAYERID = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, playerName);
                        statement.setInt(2, age);
                        statement.setString(3, position);
                        statement.setString(4, nation);
                        statement.setInt(5, jerseyNo);
                        statement.setInt(6, overall);
                        statement.setInt(7, UserDetails.PlayerID); 

                        int rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) 
                        {
                            System.out.println("Player information updated successfully!");
                        } 
                        else 
                        {
                            System.out.println("Player not found or no changes made.");
                        }
                        statement.close();
                    } 
                    catch (SQLException | NumberFormatException e) 
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                    {
                        FileInputStream inputStream = new FileInputStream("Graphics/PlayerImage.jpg");
                        String playerName = PlayernameTB.getText();
                        int age = Integer.parseInt(PlayerAgeTB.getText());
                        String position = PlayerPositionTB.getText();
                        String nation = PlayerNationTB.getText();
                        int jerseyNo = Integer.parseInt(PlayerKitTB.getText());
                        int overall = Integer.parseInt(PlayerOVTB.getText());

                        String insertSql = "INSERT INTO Player (TEAMID, NAME, AGE, POSITION, NATION, JERSEYNO, OVERALL, PLAYER_IMG) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(insertSql);
                        statement.setInt(1, UserDetails.TeamID);
                        statement.setString(2, playerName);
                        statement.setInt(3, age);
                        statement.setString(4, position);
                        statement.setString(5, nation);
                        statement.setInt(6, jerseyNo);
                        statement.setInt(7, overall);
                        statement.setBlob(8, inputStream);

                        int rowsInserted = statement.executeUpdate();

                        if (rowsInserted > 0) 
                        {
                            System.out.println("New player added successfully!");
                        } 
                        else 
                        {
                            System.out.println("Failed to add new player.");
                        }

                        inputStream.close();
                        statement.close();
                    } 
                    catch (SQLException | IOException e) 
                    {
                        e.printStackTrace();
                    }
                    UserDetails.Add=false;
                }
            }
        });
        PlayerCancelBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                EditPlayerBT.setVisible(true);
                AddPlayerBT.setVisible(true);
                DeletePlayerBT.setVisible(true);
                PlayerCancelBT.setVisible(false);
                PlayerSaveBT.setVisible(false);
                PlayerAgeTB.setEditable(false);
                PlayernameTB.setEditable(false);
                PlayerOVTB.setEditable(false);
                PlayerNationTB.setEditable(false);
                PlayerKitTB.setEditable(false);
                PlayerPositionTB.setEditable(false);
                UserDetails.Add=false;
                //Initialise()
            }
        });
        DeletePlayerBT.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this player?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) 
            {
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String deleteSql = "DELETE FROM Player WHERE PLAYERID = ?";
                    PreparedStatement statement = connection.prepareStatement(deleteSql);
                    statement.setInt(1, UserDetails.PlayerID); 

                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) 
                    {
                        System.out.println("Player deleted successfully!");
                        PlayerAgeTB.setText("-");
                        PlayernameTB.setText("Select Player");
                        PlayerOVTB.setText("-");
                        PlayerNationTB.setText("-");
                        PlayerKitTB.setText("-");
                        PlayerPositionTB.setText("-");
                    } 
                    else 
                    {
                        System.out.println("Player not found or failed to delete.");
                    }

                    statement.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        });

        SettingsBT2.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                MyTeamStage.close();
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    String sql = "SELECT * FROM Manager WHERE ID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, UserDetails.UserID);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) 
                    {
                        UNameTB.setText(resultSet.getString("NAME"));
                        UsernameTB.setText(resultSet.getString("USERNAME"));
                        PasswordTB.setText(resultSet.getString("PASSWORD"));
                        EmailTB.setText(resultSet.getString("EMAIL"));
                    }
                    resultSet.close();
                    statement.close();

                    String teamSql = "SELECT TEAMNAME FROM Team WHERE TEAMID = ?";
                    PreparedStatement teamStatement = connection.prepareStatement(teamSql);
                    teamStatement.setInt(1, UserDetails.TeamID);

                    ResultSet teamResultSet = teamStatement.executeQuery();

                    if (teamResultSet.next()) 
                    {
                        TeamNameTB.setText(teamResultSet.getString("TEAMNAME"));
                    }

                    teamResultSet.close();
                    teamStatement.close();

                    String query = "SELECT NAME FROM player WHERE TEAMID = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(query)) 
                    {
                        statement2.setInt(1, UserDetails.TeamID); // Set the team ID parameter
                        try (ResultSet resultset = statement2.executeQuery()) 
                        {
                            ObservableList<String> playerNames = FXCollections.observableArrayList();
                            while (resultset.next()) 
                            {
                                String playerName = resultset.getString("NAME");
                                playerNames.add(playerName);
                            }
                            SelectPlayerBox.setItems(playerNames);
                        }
                    }
                    connection.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                SettingsStage.show();
            }
        });

        Stage UserRegStage=new Stage();
        UserRegStage.setTitle("Recent and UpComing Fixtures");
        BorderPane UserRegStagebg = new BorderPane();
        BackgroundImage UserRegBG=new BackgroundImage(new Image("Graphics/UserRegBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        UserRegStagebg.setBackground(new Background(UserRegBG));
        Scene UserRegScene=new Scene(UserRegStagebg,501,500);
        UserRegStage.setScene(UserRegScene);

        Image CreateAccountImg = new Image("Graphics/CreateAccountBT.png");
        Image CreateAccountImgHover = new Image("Graphics/CreateAccountBThover.png");
        ImageView CreateAccountBTview = new ImageView(CreateAccountImg);
        CreateAccountBTview.setFitWidth(203);
        CreateAccountBTview.setPreserveRatio(true);
        ImageView CreateAccountBThover = new ImageView(CreateAccountImgHover);
        CreateAccountBThover.setFitWidth(203);
        CreateAccountBThover.setPreserveRatio(true);
        Button CreateAccountBT=new Button("");
        CreateAccountBT.setPrefWidth(203);
        CreateAccountBT.setPrefHeight(41);
        CreateAccountBT.setGraphic(CreateAccountBTview);
        CreateAccountBT.setStyle("-fx-background-color: #337018;");
        CreateAccountBT.setOnMouseEntered(e -> CreateAccountBT.setGraphic(CreateAccountBThover));
        CreateAccountBT.setOnMouseExited(e -> CreateAccountBT.setGraphic(CreateAccountBTview));

        VBox RegUserTBS=new VBox();
        RegUserTBS.setPadding(new javafx.geometry.Insets(68, 0, 5, 172));
        RegUserTBS.setSpacing(2);
        RegUserTBS.setAlignment(Pos.CENTER);

        TextField RegUNameTB = new TextField("");
        RegUNameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        RegUNameTB.setAlignment(Pos.CENTER);
        RegUNameTB.setMaxWidth(280);
        RegUNameTB.setMaxHeight(9);

        TextField RegUsernameTB = new TextField("");
        RegUsernameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        RegUsernameTB.setAlignment(Pos.CENTER);
        RegUsernameTB.setMaxWidth(280);
        RegUsernameTB.setMaxHeight(9);

        TextField RegPasswordTB = new TextField("");
        RegPasswordTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        RegPasswordTB.setAlignment(Pos.CENTER);
        RegPasswordTB.setMaxWidth(280);
        RegPasswordTB.setMaxHeight(9);

        TextField RegTeamNameTB = new TextField("");
        RegTeamNameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        RegTeamNameTB.setAlignment(Pos.CENTER);
        RegTeamNameTB.setMaxWidth(280);
        RegTeamNameTB.setMaxHeight(9);

        TextField RegEmailTB = new TextField("");
        RegEmailTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        RegEmailTB.setAlignment(Pos.CENTER);
        RegEmailTB.setMaxWidth(280);
        RegEmailTB.setMaxHeight(9);

        Image RegTeamLogo = new Image("Graphics/TeamLogo1.jpeg");
        ImageView RegTeamLogoView = new ImageView(RegTeamLogo);
        RegTeamLogoView.setX(10);
        RegTeamLogoView.setY(10);
        RegTeamLogoView.setFitWidth(114.1);
        RegTeamLogoView.setFitHeight(82.1);
        RegTeamLogoView.setPreserveRatio(true);

        Image UploadTeamLogoImg = new Image("Graphics/UploadTeamLogoBT.png");
        Image UploadTeamLogoImgHover = new Image("Graphics/UploadTeamLogoBThover.png");
        ImageView UploadTeamLogoBTview = new ImageView(UploadTeamLogoImg);
        UploadTeamLogoBTview.setFitWidth(74);
        UploadTeamLogoBTview.setPreserveRatio(true);
        ImageView UploadTeamLogoBThover = new ImageView(UploadTeamLogoImgHover);
        UploadTeamLogoBThover.setFitWidth(74);
        UploadTeamLogoBThover.setPreserveRatio(true);
        Button UploadTeamLogoBT=new Button("");
        UploadTeamLogoBT.setPrefWidth(74);
        UploadTeamLogoBT.setPrefHeight(32);
        UploadTeamLogoBT.setGraphic(UploadTeamLogoBTview);
        UploadTeamLogoBT.setStyle("-fx-background-color: #4E9F2A;");
        UploadTeamLogoBT.setOnMouseEntered(e -> UploadTeamLogoBT.setGraphic(UploadTeamLogoBThover));
        UploadTeamLogoBT.setOnMouseExited(e -> UploadTeamLogoBT.setGraphic(UploadTeamLogoBTview));

        HBox RegBox1=new HBox();
        RegBox1.setPadding(new javafx.geometry.Insets(5, 0, 20, -30));
        RegBox1.setSpacing(40);
        RegBox1.setAlignment(Pos.CENTER);

        RegBox1.getChildren().addAll(RegTeamLogoView,UploadTeamLogoBT);
        RegUserTBS.getChildren().addAll(RegUsernameTB,RegPasswordTB,RegUNameTB,RegTeamNameTB,RegEmailTB,RegBox1,CreateAccountBT);
        UserRegStagebg.setCenter(RegUserTBS);
    
        NewRegisterBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                LoginStage.close();
                UserRegStage.show();
            }
        });

        Stage PlayerRegStage=new Stage();
        PlayerRegStage.setTitle("Recent and UpComing Fixtures");
        BorderPane PlayerRegStagebg = new BorderPane();
        BackgroundImage PlayerRegBG=new BackgroundImage(new Image("Graphics/PlayerRegBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        PlayerRegStagebg.setBackground(new Background(PlayerRegBG));
        Scene PlayerRegScene=new Scene(PlayerRegStagebg,501,500);
        PlayerRegStage.setScene(PlayerRegScene);

        Image FinishRegImg = new Image("Graphics/FinishRegBT.png");
        Image FinishRegImgHover = new Image("Graphics/FinishRegBThover.png");
        ImageView FinishRegBTview = new ImageView(FinishRegImg);
        FinishRegBTview.setFitWidth(230);
        FinishRegBTview.setPreserveRatio(true);
        ImageView FinishRegBThover = new ImageView(FinishRegImgHover);
        FinishRegBThover.setFitWidth(230);
        FinishRegBThover.setPreserveRatio(true);
        Button FinishRegBT=new Button("");
        FinishRegBT.setPrefWidth(230);
        FinishRegBT.setPrefHeight(35);
        FinishRegBT.setGraphic(FinishRegBTview);
        FinishRegBT.setStyle("-fx-background-color: #337018;");
        FinishRegBT.setOnMouseEntered(e -> FinishRegBT.setGraphic(FinishRegBThover));
        FinishRegBT.setOnMouseExited(e -> FinishRegBT.setGraphic(FinishRegBTview));
        FinishRegBT.setVisible(false);

        VBox PlayerRegUserTBS=new VBox();
        PlayerRegUserTBS.setPadding(new javafx.geometry.Insets(158, 0, 88, 172));
        PlayerRegUserTBS.setSpacing(2);
        PlayerRegUserTBS.setAlignment(Pos.CENTER);

        TextField PlayerRegNameTB = new TextField("");
        PlayerRegNameTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegNameTB.setAlignment(Pos.CENTER);
        PlayerRegNameTB.setMaxWidth(280);
        PlayerRegNameTB.setMaxHeight(9);

        TextField PlayerRegAgeTB = new TextField("");
        PlayerRegAgeTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegAgeTB.setAlignment(Pos.CENTER);
        PlayerRegAgeTB.setMaxWidth(280);
        PlayerRegAgeTB.setMaxHeight(9);

        TextField PlayerRegPositionTB = new TextField("");
        PlayerRegPositionTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegPositionTB.setAlignment(Pos.CENTER);
        PlayerRegPositionTB.setMaxWidth(280);
        PlayerRegPositionTB.setMaxHeight(9);

        TextField PlayerRegNationTB = new TextField("");
        PlayerRegNationTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegNationTB.setAlignment(Pos.CENTER);
        PlayerRegNationTB.setMaxWidth(280);
        PlayerRegNationTB.setMaxHeight(9);

        TextField PlayerRegKitTB = new TextField("");
        PlayerRegKitTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegKitTB.setAlignment(Pos.CENTER);
        PlayerRegKitTB.setMaxWidth(280);
        PlayerRegKitTB.setMaxHeight(9);

        TextField PlayerRegOVRTB = new TextField("");
        PlayerRegOVRTB.setStyle("-fx-text-fill: black; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-family: 'Comic Sans MS';" +
                         "-fx-font-size: 9px;");
        PlayerRegOVRTB.setAlignment(Pos.CENTER);
        PlayerRegOVRTB.setMaxWidth(280);
        PlayerRegOVRTB.setMaxHeight(9);

        ImageView PlayerImageView2 = new ImageView(PlayerImage);
        PlayerImageView2.setX(10);
        PlayerImageView2.setY(10);
        PlayerImageView2.setFitWidth(110);
        PlayerImageView2.setFitHeight(80);
        PlayerImageView2.setPreserveRatio(true);

        Image UploadPlayerImg = new Image("Graphics/UploadTeamLogoBT.png");
        Image UploadPlayerImgHover = new Image("Graphics/UploadTeamLogoBThover.png");
        ImageView UploadPlayerBTview = new ImageView(UploadPlayerImg);
        UploadPlayerBTview.setFitWidth(74);
        UploadPlayerBTview.setPreserveRatio(true);
        ImageView UploadPlayerBThover = new ImageView(UploadPlayerImgHover);
        UploadPlayerBThover.setFitWidth(74);
        UploadPlayerBThover.setPreserveRatio(true);
        Button UploadPlayerBT=new Button("");
        UploadPlayerBT.setPrefWidth(74);
        UploadPlayerBT.setPrefHeight(29);
        UploadPlayerBT.setGraphic(UploadPlayerBTview);
        UploadPlayerBT.setStyle("-fx-background-color: #4E9F2A;");
        UploadPlayerBT.setOnMouseEntered(e -> UploadPlayerBT.setGraphic(UploadPlayerBThover));
        UploadPlayerBT.setOnMouseExited(e -> UploadPlayerBT.setGraphic(UploadPlayerBTview));

        Image NextPlayerImg = new Image("Graphics/NextPlayerBT.png");
        Image NextPlayerImgHover = new Image("Graphics/NextPlayerBThover.png");
        ImageView NextPlayerBTview = new ImageView(NextPlayerImg);
        NextPlayerBTview.setFitWidth(60);
        NextPlayerBTview.setPreserveRatio(true);
        ImageView NextPlayerBThover = new ImageView(NextPlayerImgHover);
        NextPlayerBThover.setFitWidth(60);
        NextPlayerBThover.setPreserveRatio(true);
        Button NextPlayerBT=new Button("");
        NextPlayerBT.setPrefWidth(60);
        NextPlayerBT.setPrefHeight(25);
        NextPlayerBT.setGraphic(NextPlayerBTview);
        NextPlayerBT.setStyle("-fx-background-color: #337018;");
        NextPlayerBT.setOnMouseEntered(e -> NextPlayerBT.setGraphic(NextPlayerBThover));
        NextPlayerBT.setOnMouseExited(e -> NextPlayerBT.setGraphic(NextPlayerBTview));

        HBox PlayerRegBox1=new HBox();
        PlayerRegBox1.setPadding(new javafx.geometry.Insets(5, 0, 20, -55));
        PlayerRegBox1.setSpacing(40);
        PlayerRegBox1.setAlignment(Pos.CENTER);

        VBox PlayerRegBottom=new VBox();
        PlayerRegBottom.setPadding(new javafx.geometry.Insets(-145, 0, 0, 0));
        PlayerRegBottom.setSpacing(4);
        PlayerRegBottom.setAlignment(Pos.CENTER);

        PlayerRegBottom.getChildren().addAll(NextPlayerBT,FinishRegBT);
        PlayerRegBox1.getChildren().addAll(PlayerImageView2,UploadPlayerBT);
        PlayerRegUserTBS.getChildren().addAll(PlayerRegNameTB,PlayerRegAgeTB,PlayerRegPositionTB,PlayerRegNationTB,PlayerRegKitTB,PlayerRegOVRTB,PlayerRegBox1);
        PlayerRegStagebg.setCenter(PlayerRegUserTBS);
        PlayerRegStagebg.setBottom(PlayerRegBottom);

        FinishRegBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                String WelcomeMsg="WELCOME "+UserDetails.UName.toUpperCase();
                Welcome.setText(WelcomeMsg);
                mainStage.show();
                PlayerRegStage.close();
                PC.PlayerCount=0;
                FinishRegBT.setVisible(false);
            }
        });
        

        UploadPlayerBT.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose Team Logo");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(UserRegStage);
                if (selectedFile != null) 
                {
                    Image selectedImage = new Image(selectedFile.toURI().toString());
                    PlayerImageView2.setImage(selectedImage);
                }
            }
        });

        NextPlayerBT.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                // Retrieve values from text fields
                String playerName = PlayerRegNameTB.getText();
                int playerAge = Integer.parseInt(PlayerRegAgeTB.getText());
                String playerPosition = PlayerRegPositionTB.getText();
                String playerNation = PlayerRegNationTB.getText();
                int playerJerseyNo = Integer.parseInt(PlayerRegKitTB.getText());
                int playerOverall = Integer.parseInt(PlayerRegOVRTB.getText());

                Image playerImage = PlayerImageView2.getImage();
                byte[] playerImageBytes = null;

                if (playerImage != null) 
                {
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(playerImage, null);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try 
                    {
                        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                        playerImageBytes = byteArrayOutputStream.toByteArray();
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }

                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    // Insert a new row into the Player table
                    String insertPlayerQuery = "INSERT INTO Player (TeamID, Name, Age, Position, Nation, JerseyNo, Overall, Player_Img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) 
                    {
                        // Set parameters for the prepared statement
                        statement.setInt(1, UserDetails.TeamID); 
                        statement.setString(2, playerName);
                        statement.setInt(3, playerAge);
                        statement.setString(4, playerPosition);
                        statement.setString(5, playerNation);
                        statement.setInt(6, playerJerseyNo);
                        statement.setInt(7, playerOverall);
                        statement.setBytes(8, playerImageBytes);

                        // Execute the query
                        int rowsInserted = statement.executeUpdate();

                        if (rowsInserted > 0) 
                        {
                            System.out.println("A new player was registered successfully!");
                        }
                    }
                } catch (SQLException e) 
                {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                }

                // Clear text fields
                PlayerRegNameTB.clear();
                PlayerRegAgeTB.clear();
                PlayerRegPositionTB.clear();
                PlayerRegNationTB.clear();
                PlayerRegKitTB.clear();
                PlayerRegOVRTB.clear();

                // Increment player count
                PC.PlayerCount++;

                // Check if maximum players are registered
                if (PC.PlayerCount == 5)
                    FinishRegBT.setVisible(true);
            }
        });

        // After DB Connections

        UploadTeamLogoBT.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose Team Logo");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(UserRegStage);
                if (selectedFile != null) 
                {
                    Image selectedImage = new Image(selectedFile.toURI().toString());
                    RegTeamLogoView.setImage(selectedImage);
                }
            }
        });

        CreateAccountBT.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // Retrieve values from text fields
                String username = RegUsernameTB.getText();
                String password = RegPasswordTB.getText();
                String name = RegUNameTB.getText();
                String teamName = RegTeamNameTB.getText();
                String email = RegEmailTB.getText();

                int teamId = -1;
                int userId = -1;

                Image teamLogoImage = RegTeamLogoView.getImage();
                byte[] teamLogoBytes = null;

                if (teamLogoImage != null) 
                {
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(teamLogoImage, null); // BufferedImage datatype to store images can be converted to bytes
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();   // to convert to bytes
                    try 
                    {
                        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                        teamLogoBytes = byteArrayOutputStream.toByteArray();
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }

                // Establish database connection
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin: <jdbc location>", "username", "password")) 
                {
                    // Insert a new row into the Team table
                    String insertTeamQuery = "INSERT INTO Team (TeamName, Team_Logo) VALUES (?, ?)";
                    try (PreparedStatement insertTeamStatement = connection.prepareStatement(insertTeamQuery, new String[]{"TEAMID"}))  
                    {
                        // new String parameter used to tell JDBC to check the generated key for the column TEAMID
                        insertTeamStatement.setString(1, teamName); 
                        insertTeamStatement.setBytes(2, teamLogoBytes);
                        int rowsInserted = insertTeamStatement.executeUpdate();
                        if (rowsInserted > 0) 
                        {
                            System.out.println("New team inserted successfully!");
                            // Retrieve the generated team ID
                            try (ResultSet generatedKeys = insertTeamStatement.getGeneratedKeys())  
                            {
                                if (generatedKeys.next())  // To get Generated key by the Trigger
                                {
                                    teamId = generatedKeys.getInt(1);
                                    System.out.println("Generated Team ID: " + teamId); // Print the generated team ID
                                } 
                                else 
                                {
                                    System.out.println("No generated keys found");
                                }
                            } 
                            catch (SQLException e) 
                            {
                                e.printStackTrace(); // Handle any SQL exceptions
                            }
                        }
                    }
                    if (teamId == -1) 
                    {
                        System.out.println("Failed to generate Team ID. Please try again or select a team.");
                        UserRegStage.close();
                        return; // Exit the handle method to prevent further execution
                    }

                    String insertManagerQuery = "INSERT INTO Manager (Username, Password, Name, TeamID, Email) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement insertManagerStatement = connection.prepareStatement(insertManagerQuery, new String[]{"ID"})) 
                    {
                        // Set parameters for the prepared statement
                        insertManagerStatement.setString(1, username);
                        insertManagerStatement.setString(2, password);
                        insertManagerStatement.setString(3, name);
                        insertManagerStatement.setInt(4, teamId);
                        insertManagerStatement.setString(5, email);

                        // Execute the query
                        int rowsInserted = insertManagerStatement.executeUpdate();

                        if (rowsInserted > 0) 
                        {
                            System.out.println("A new user was registered successfully!");
                            try (ResultSet generatedKeys = insertManagerStatement.getGeneratedKeys()) 
                            {
                                if (generatedKeys.next()) 
                                {
                                    userId = generatedKeys.getInt(1);
                                    System.out.println("Generated User ID: " + userId);
                                } 
                                else 
                                {
                                    System.out.println("No generated keys found");
                                }
                            } 
                            catch (SQLException e) 
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (userId == -1) 
                    {
                        System.out.println("Failed to generate User ID.");
                        return;
                    }
                    UserRegStage.close();
                    PlayerRegStage.show();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
                UserDetails.UName=name;
                UserDetails.UserID=userId;
                UserDetails.TeamID=teamId;
            }
        });

        Stage AboutUsStage=new Stage();
        AboutUsStage.setTitle("About Us and Contact");
        BorderPane AboutUsStagebg = new BorderPane();
        BackgroundImage AboutUsBG=new BackgroundImage(new Image("Graphics/AboutUsBg.png"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        AboutUsStagebg.setBackground(new Background(AboutUsBG));
        Scene AboutUsScene=new Scene(AboutUsStagebg,501,500);
        AboutUsStage.setScene(AboutUsScene);

        ImageView MainMenuImgview5= new ImageView(MainMenuImg);
        MainMenuImgview5.setFitWidth(112.2);
        MainMenuImgview5.setPreserveRatio(true);
        ImageView MainMenuImgviewHover5 = new ImageView(MainMenuImgHover);
        MainMenuImgviewHover5.setFitWidth(112.2);
        MainMenuImgviewHover5.setPreserveRatio(true);
        Button MainMenuBT5=new Button("");
        MainMenuBT5.setPrefWidth(112.2);
        MainMenuBT5.setPrefHeight(33.3);
        MainMenuBT5.setGraphic(MainMenuImgview);
        MainMenuBT5.setStyle("-fx-background-color: #7ED957;");
        MainMenuBT5.setOnMouseEntered(e -> MainMenuBT5.setGraphic(MainMenuImgviewHover5));
        MainMenuBT5.setOnMouseExited(e -> MainMenuBT5.setGraphic(MainMenuImgview5));

        HBox AboutUsTop=new HBox();
        AboutUsTop.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        AboutUsTop.setSpacing(10);
        AboutUsTop.setAlignment(Pos.CENTER_RIGHT);

        AboutUsTop.getChildren().addAll(MainMenuBT5);
        AboutUsStagebg.setTop(AboutUsTop);

        MainMenuBT5.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.show();
                AboutUsStage.close();
            }
        });
        ContactBT.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) 
            {    
                mainStage.close();
                AboutUsStage.show();
            }
        });

    }
}
