package scenes;
import mainClass.App;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mainClass.Sound;


public class CreditScene {

public ImageView facebookNDL = new ImageView();
public ImageView facebookTBH = new ImageView();
public ImageView facebookVVA = new ImageView();

public ImageView gmailNDL = new ImageView();

public ImageView gmailTBH = new ImageView();
public ImageView gmailVVA = new ImageView();
private BrowserOpener browserOpener= new BrowserOpener();

public Alert musicAlert = new Alert(AlertType.NONE);

public Alert gmailAlert = new Alert(AlertType.INFORMATION);
public void initialize(){
  facebookNDL.setOnMouseClicked(e->facebookOpen("facebookNDL"));
  facebookTBH.setOnMouseClicked(e->facebookOpen("facebookTBH"));
  facebookVVA.setOnMouseClicked(e->facebookOpen("facebookVVA"));
  gmailNDL.setOnMouseClicked(e->gmailOpen("gmailNDL"));
  gmailTBH.setOnMouseClicked(e->gmailOpen("gmailTBH"));
  gmailVVA.setOnMouseClicked(e->gmailOpen("gmailVVA"));
}

public void facebookOpen(String fxId){
  try{
    if(fxId.equals("facebookNDL")){
      browserOpener.open("www.google.com");
    }else{
      browserOpener.open("www.youtube.com");
    }

  }catch (Exception e){
    System.out.println("There is a error in facebookOpen");
  }
  Sound.playInGameSound(6);
}

public void gmailOpen(String fxId){
if(fxId.equals("gmailNDL")){
  gmailAlert.setContentText("Gmail of Ngo Danh Lam is something@gmail.com. Please manually copy this address.");
}else if(fxId.equals("gmailTBH")){
  gmailAlert.setContentText("Gmail of Tran Ba Hoang is something@gmail.com. Please manually copy this address.");
}else{
  gmailAlert.setContentText("Gmail of Vu Viet Anh is something@gmail.com. Please manually copy this address.");
}
gmailAlert.setTitle("Gmail information");
gmailAlert.show();
  Sound.playInGameSound(6);
}
public void switchToMainMenu() throws IOException{
  App.setRoot("/scenes/mainMenu");
}

public void openGithub()throws IOException{
  try{
    browserOpener.open("www.github.com");
  }catch(Exception e){
    System.out.println("There is a error in openGithub");
  }
  Sound.playInGameSound(6);
}

public void openMusic() throws IOException{
  musicAlert.setAlertType(AlertType.INFORMATION);
  musicAlert.setContentText("Music message!");
  musicAlert.show();
  Sound.playInGameSound(6);
}

public void openDocumentation() throws IOException{
  try{
    browserOpener.open("www.docs.google.com");
  }catch(Exception e){
    System.out.println("There is a error in openDocumentation");
  }
  Sound.playInGameSound(6);
}

public void openTreeDiagram() throws IOException{
  try{
    browserOpener.open("https://www.jetbrains.com/help/idea/class-diagram.html");
  }catch(Exception e){
    System.out.println("There is a error in openTreeDiagram");
  }
  Sound.playInGameSound(6);
}




}
