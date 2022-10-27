package mainClass;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

  public static ArrayList<Player> getArrayListPlayer() {
    return players;
  }

  public static Player createNewPlayer(String username, String password){
    return new Player(username,password);
  }

  public static void writeDataLineByLine(String filePath) {
    // first create file object for file placed at location
    // specified by filepath
    File file = new File(filePath);
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
//      // add data to csv
//      String[] data1 = { "Aman", "10", "620" };
//      writer.writeNext(data1);
//      String[] data2 = { "Suraj", "10", "630" };
//      writer.writeNext(data2);
      System.out.println("Write successfully");
      // closing writer connection
      writer.close();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }


}
