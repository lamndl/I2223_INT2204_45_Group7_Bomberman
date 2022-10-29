package mainClass;

public class Player {

  private String username;
  private String password;
  private double highestScore;
  private double longestTimeSurvivalInEndlessMode;
  private int secondsPlayed;
  private int enemiesKilled;
  private int numberOfDead;
  private int blocksBroke;
  private long accumulateScore;

  public Player(String userName, String password) {
    this.username = userName;
    this.password = password;
    this.highestScore = 0.0;
    longestTimeSurvivalInEndlessMode = 0.0;
    secondsPlayed = 0;
    numberOfDead = 0;
    blocksBroke = 0;
    accumulateScore = 0;

  }

  public Player(String u1, String p1, double hs, double lts, int sp, int nod,
      int bb, int as){
    this.username = u1;
    this.password = p1;
    this.highestScore = hs;
    longestTimeSurvivalInEndlessMode = lts;
    secondsPlayed = sp;
    numberOfDead = nod;
    blocksBroke = bb;
    accumulateScore = as;
  }

  public String getUserName() {
    return username;
  }

  public void setUserName(String userName) {
    this.username = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public double getHighestScore() {
    return highestScore;
  }

  public void setHighestScore(double highestScore) {
    this.highestScore = highestScore;
  }

  public double getLongestTimeSurvivalInEndlessMode() {
    return longestTimeSurvivalInEndlessMode;
  }

  public void setLongestTimeSurvivalInEndlessMode(double longestTimeSurvivalInEndlessMode) {
    this.longestTimeSurvivalInEndlessMode = longestTimeSurvivalInEndlessMode;
  }

  public int getSecondsPlayed() {
    return secondsPlayed;
  }

  public void setSecondsPlayed(int secondsPlayed) {
    this.secondsPlayed = secondsPlayed;
  }

  public int getEnemiesKilled() {
    return enemiesKilled;
  }

  public void setEnemiesKilled(int enemiesKilled) {
    this.enemiesKilled = enemiesKilled;
  }

  public int getNumberOfDead() {
    return numberOfDead;
  }

  public void setNumberOfDead(int numberOfDead) {
    this.numberOfDead = numberOfDead;
  }

  public int getBlocksBroke() {
    return blocksBroke;
  }

  public void setBlocksBroke(int blocksBroke) {
    this.blocksBroke = blocksBroke;
  }

  public long getAccumulateScore() {
    return accumulateScore;
  }

  public void setAccumulateScore(long accumulateScore) {
    this.accumulateScore = accumulateScore;
  }

  @Override
  public String toString() {
    return "Player{" +
        "username='" + username + '\'' +
        "\n password='" + password + '\'' +
        "\n highestScore=" + highestScore +
        "\n longestTimeSurvivalInEndlessMode=" + longestTimeSurvivalInEndlessMode +
        "\n secondsPlayed=" + secondsPlayed +
        "\n enemiesKilled=" + enemiesKilled +
        "\n numberOfDead=" + numberOfDead +
        "\n blocksBroke=" + blocksBroke +
        "\n accumulateScore=" + accumulateScore +
        '}';
  }
}
