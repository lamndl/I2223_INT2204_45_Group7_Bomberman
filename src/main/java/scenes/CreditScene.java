package scenes;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import mainClass.App;
import mainClass.Sound;


public class CreditScene {

  public ImageView facebookNDL = new ImageView();
  public ImageView facebookTBH = new ImageView();
  public ImageView facebookVVA = new ImageView();

  public ImageView gmailNDL = new ImageView();

  public ImageView gmailTBH = new ImageView();
  public ImageView gmailVVA = new ImageView();
  private BrowserOpener browserOpener = new BrowserOpener();

  public Alert musicAlert = new Alert(AlertType.NONE);

  public Alert gmailAlert = new Alert(AlertType.INFORMATION);

  public void initialize() {
    facebookNDL.setOnMouseClicked(e -> facebookOpen("facebookNDL"));
    facebookTBH.setOnMouseClicked(e -> facebookOpen("facebookTBH"));
    facebookVVA.setOnMouseClicked(e -> facebookOpen("facebookVVA"));
    gmailNDL.setOnMouseClicked(e -> gmailOpen("gmailNDL"));
    gmailTBH.setOnMouseClicked(e -> gmailOpen("gmailTBH"));
    gmailVVA.setOnMouseClicked(e -> gmailOpen("gmailVVA"));
  }

  public void facebookOpen(String fxId) {
    try {
      if (fxId.equals("facebookNDL")) {
        browserOpener.open("https://www.facebook.com/clumsyonce");
      } else if (fxId.equals("facebookTBH")) {
        browserOpener.open("https://www.facebook.com/hoang.bet.90");
      } else {
        browserOpener.open("https://www.facebook.com/vitkondz");
      }

    } catch (Exception e) {
      System.out.println("There is a error in facebookOpen");
    }
    Sound.playInGameSound(6);
  }

  public void gmailOpen(String fxId) {
    if (fxId.equals("gmailNDL")) {
      gmailAlert.setContentText(
          "Gmail of Ngo Danh Lam is something@gmail.com. Please manually copy this address.");
    } else if (fxId.equals("gmailTBH")) {
      gmailAlert.setContentText(
          "Gmail of Tran Ba Hoang is something@gmail.com. Please manually copy this address.");
    } else {
      gmailAlert.setContentText(
          "Gmail of Vu Viet Anh is something@gmail.com. Please manually copy this address.");
    }
    gmailAlert.setTitle("Gmail information");
    gmailAlert.show();
    Sound.playInGameSound(6);
  }

  public void switchToMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }

  public void openGithub() throws IOException {
    try {
      browserOpener.open("https://github.com/lamndl/I2223_INT2204_45_Group7_Bomberman");
    } catch (Exception e) {
      System.out.println("There is a error in openGithub");
    }
    Sound.playInGameSound(6);
  }

  public void openMusic() throws IOException {
    musicAlert.setAlertType(AlertType.INFORMATION);
    musicAlert.setContentText("Music message!");
    musicAlert.show();
    Sound.playInGameSound(6);
  }

  public void openDocumentation() throws IOException {
    try {
      browserOpener.open(
          "https://docs.google.com/document/d/1Tn18yhuzQIhZt_ZR80_5EUIo4GPmHcR5u_6tk8Q__qE/edit");
    } catch (Exception e) {
      System.out.println("There is a error in openDocumentation");
    }
    Sound.playInGameSound(6);
  }

  public void openTreeDiagram() throws IOException {
    try {
      browserOpener.open("https://www.jetbrains.com/help/idea/class-diagram.html");
    } catch (Exception e) {
      System.out.println("There is a error in openTreeDiagram");
    }
    Sound.playInGameSound(6);
  }



}
