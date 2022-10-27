package level;

public abstract class LevelLoader {
  protected int width;
  protected int height;
  protected int level;

  protected LevelLoader(int level) {

    loadLevel(level);
  }

  public abstract void loadLevel(int level);

  public abstract void createEntities();

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
