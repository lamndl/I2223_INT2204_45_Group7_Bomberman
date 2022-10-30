package mainClass;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class PlayerManagement {

  private static ArrayList<Player> players = new ArrayList<Player>();

  public PlayerManagement() {

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
          "secondsPlayed", "enemiesKilled", "numberOfDead", "blocksBroke", "accumulateScore"};
      writer.writeNext(header);

      for (Player p : players) {
        String[] temp = {p.getUserName(), p.getPassword(), Double.toString(p.getHighestScore()),
            Double.toString(p.getLongestTimeSurvivalInEndlessMode()),
            Integer.toString(p.getSecondsPlayed()),
            Integer.toString(p.getEnemiesKilled()), Integer.toString(p.getNumberOfDead()),
            Integer.toString(p.getBlocksBroke()),
            Long.toString(p.getAccumulateScore())};
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
            Integer.parseInt(nextRecord[5]), Integer.parseInt(nextRecord[6]),
            Integer.parseInt(nextRecord[7])));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }




}
