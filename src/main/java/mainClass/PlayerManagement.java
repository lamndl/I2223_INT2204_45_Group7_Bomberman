package mainClass;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.text.StyledEditorKit.BoldAction;


public class PlayerManagement {

  private static ArrayList<Player> players;


  public PlayerManagement() {

  }

  public static void init(){
    players = new ArrayList<Player>();
    System.out.println("inited");
  }

  public static void addPlayer(Player p) {
    players.add(p);
  }

  public static void removePlayer(Player p) {
    players.remove(p);
  }

  public static Player getPlayer(int index) {
    if (index < players.size()) {
      return players.get(index);
    }
    return null;
  }

  public static void removeAll(){
    players = new ArrayList<Player>();
  }

  public static ArrayList<Player> getArrayListPlayer() {
    return players;
  }

  public static Player createNewPlayer(String username, String password){
    return new Player(username,password);
  }

  public static int checkIfExistPlayer(String username, String password){
    for(Player p : players){
      if(p.getUserName().equals(username)&&p.getPassword().equals(password)){
        return players.indexOf(p);
      }
    }
    return -1;
  }

  public static void writeDataLineByLine(String filePath) {
    // first create file object for file placed at location
    // specified by filepath
    File file = new File("./src/main/resources" + filePath);
    try {
      // create FileWriter object with file as parameter
      FileWriter outputfile = new FileWriter(file);

      // create CSVWriter object filewriter object as parameter
      CSVWriter writer = new CSVWriter(outputfile);

      // adding header to csv
      String[] header = {"username", "password", "highestScore", "longestTimeSurvivalInEndlessMode",
          "secondsPlayed", "enemiesKilled", "numberOfDead", "blocksBroke", "accumulateScore","lastestScore",
      "logged","dummy"};

      writer.writeNext(header);

      for (Player p : players) {
        String[] temp = {p.getUserName(), p.getPassword(), Double.toString(p.getHighestScore()),
            Double.toString(p.getLongestTimeSurvivalInEndlessMode()),
            Integer.toString(p.getSecondsPlayed()),
            Integer.toString(p.getEnemiesKilled()), Integer.toString(p.getNumberOfDead()),
            Integer.toString(p.getBlocksBroke()),
            Long.toString(p.getAccumulateScore()),
        Long.toString(p.getLastestScore()),
        Boolean.toString(p.isLogged()).toLowerCase(),
        Boolean.toString(p.isDummyAccount()).toLowerCase()};
        writer.writeNext(temp);
      }

      System.out.println("Write successfully");
      // closing writer connection
      writer.close();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  public static void readDataLineByLine(String file)
  {

    try {

      // Create an object of filereader
      // class with CSV file as a parameter.
      FileReader filereader = new FileReader("./src/main/resources" +file);

      // create csvReader object passing
      // file reader as a parameter
      CSVReader csvReader = new CSVReader(filereader);
      String[] nextRecord;

      boolean firstLine = true;
      // we are going to read data line by line
      while ((nextRecord = csvReader.readNext()) != null) {
        if(firstLine){
          firstLine= false;
          continue;
        }

        players.add(new Player(nextRecord[0], nextRecord[1], Double.parseDouble(nextRecord[2]),
            Double.parseDouble(nextRecord[3]),Integer.parseInt(nextRecord[4]) ,
            Integer.parseInt(nextRecord[5]), Integer.parseInt(nextRecord[6]),Integer.parseInt(nextRecord[7]),Long.parseLong(nextRecord[8]),
            Long.parseLong(nextRecord[9]),trueOfFalse(nextRecord[10]),
            trueOfFalse(nextRecord[11])));
        System.out.println(players.get(players.size()-1).isLogged());
      }
      System.out.println("read successfully");

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }


  public static String generateRandomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString;
  }

  public static Player getLoggedAccount(){
    for(Player p: players){
      if(p.isLogged()){
        return p;
      }
    }
    return null;
  }

  public static Boolean trueOfFalse(String text){
    if(text.equals("true")||text.equals("TRUE")||text.equals("1")){
      return true;
    }else{
      return false;
    }
  }


}
