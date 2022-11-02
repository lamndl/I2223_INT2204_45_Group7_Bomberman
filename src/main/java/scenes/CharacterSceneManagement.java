package scenes;

import java.net.URL;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class CharacterSceneManagement {
private ArrayList<Image> characterImageList = new ArrayList<Image>();
private ArrayList<String> characterNameList = new ArrayList<String>();

public CharacterSceneManagement(){
  addImageAndText("/Pictures/Characters/green.png","Green");
  addImageAndText("/Pictures/Characters/red.png","Red");
  addImageAndText("/Pictures/Characters/blu.png","Blue");

}

public void addImageAndText(String url, String text){
  Image ss = new Image(getClass().getResourceAsStream(url));
  characterImageList.add(ss);
  characterNameList.add(text);

}

public void removeImageAndText(String text){
  int index = characterNameList.indexOf(text);
  if(index==-1){
    System.out.println("Do not found the character in arraylist");
    return;
  }
  characterImageList.remove(index);
  characterNameList.remove(index);
}

public Image getImageFromIndex(int index){
  return characterImageList.get(index);
}

public String getTextFromIndex(int index){
  return characterNameList.get(index);
}

public int getCurrentLength(){
  return Math.min(characterImageList.size(),characterNameList.size());
  //prevent bugs if cause
}

}
