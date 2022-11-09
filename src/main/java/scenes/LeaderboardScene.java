package scenes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import mainClass.App;
import mainClass.Player;
import mainClass.PlayerManagement;

public class LeaderboardScene {
public ComboBox combobox = new ComboBox<>();
public Text usernameText1= new Text();
  public Text usernameText2= new Text();
  public Text usernameText3= new Text();
  public Text usernameText4= new Text();
  public Text usernameText5= new Text();
  public Text scoreText1= new Text();
  public Text scoreText2= new Text();
  public Text scoreText3= new Text();
  public Text scoreText4= new Text();
  public Text scoreText5= new Text();
  private ArrayList<Text> usernameText = new ArrayList<>();
  private ArrayList<Text> scoreText = new ArrayList<>();
  public void initialize(){
    usernameText1.setText("");
    usernameText2.setText("");
    usernameText3.setText("");
    usernameText4.setText("");
    usernameText5.setText("");
    scoreText1.setText("");
    scoreText2.setText("");
    scoreText3.setText("");
    scoreText4.setText("");
    scoreText5.setText("");
    combobox.getItems().addAll("highest score", "longest time survival", "enemies killed", "accumulate score");
    usernameText.add(usernameText1);
    usernameText.add(usernameText2);
    usernameText.add(usernameText3);
    usernameText.add(usernameText4);
    usernameText.add(usernameText5);
    scoreText.add(scoreText1);
    scoreText.add(scoreText2);
    scoreText.add(scoreText3);
    scoreText.add(scoreText4);
    scoreText.add(scoreText5);

  }

  public void comboboxChange(){
    ArrayList<Player> toPrintOut = new ArrayList<>();
    if(combobox.getValue().equals("highest score")){
      toPrintOut = PlayerManagement.sortedLeaderboardList(1);
    }else if(combobox.getValue().equals("longest time survival")){
      toPrintOut = PlayerManagement.sortedLeaderboardList(2);
    }else if(combobox.getValue().equals("enemies killed")){
      toPrintOut = PlayerManagement.sortedLeaderboardList(3);
    }else if(combobox.getValue().equals("accumulate score")){
      toPrintOut = PlayerManagement.sortedLeaderboardList(4);
    }
//    for(int i = 0 ; i< toPrintOut.size();i++){
//      usernameText.get(i).setText(toPrintOut.get(i).getUsername());
//      scoreText.get(i).setText(toPrintOut.get(i).);
//    }
  }

  public void goBackMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }



}
