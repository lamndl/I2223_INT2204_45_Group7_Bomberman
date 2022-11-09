package level;

import java.util.HashMap;
import java.util.Map;
import mainClass.Board;

public abstract class LevelLoader {

  protected int width;
  protected int height;
  protected int level;

  protected Map<Character, Integer> randomList = new HashMap<Character, Integer>();

  protected LevelLoader(int level) {

    loadLevel(level);
  }

  public void clearAll() {
    Board.getBombList().clear();
    Board.getTileList().clear();
    Board.getEnemyList().clear();
    Board.getFlameList().clear();
    Board.getPowerUpList().clear();
  }

  public abstract void loadLevel(int level);

  public abstract void createEntities();

  public void createEntities(int n) {
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getLevel() {
    return level;
  }

}
