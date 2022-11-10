package mainClass;

public class Player {

  private String username;
  private String password;
  private double highestScore; // ok
  private double longestTimeSurvivalInEndlessMode;
  private int secondsPlayed; // ok
  private int enemiesKilled; // ok
  private int numberOfDead; // ok
  private int blocksBroke; // ok
  private long accumulateScore;
  private long latestScore; // ok
  private boolean logged; // must be set individually
  private boolean dummyAccount;// not dummy anymore if scored.

  public Player(String userName, String password) {
    this.username = userName;
    this.password = password;
    this.highestScore = 0.0;
    longestTimeSurvivalInEndlessMode = 0.0;
    secondsPlayed = 0;
    enemiesKilled = 0;
    numberOfDead = 0;
    blocksBroke = 0;
    accumulateScore = 0;
    latestScore = 0;
    logged = false;
    dummyAccount = false;
  }

  /**
   * dummy account creation
   */
  public Player(String u1, String p1, boolean da) {
    this.username = u1;
    this.password = p1;
    this.highestScore = 0.0;
    longestTimeSurvivalInEndlessMode = 0.0;
    secondsPlayed = 0;
    numberOfDead = 0;
    blocksBroke = 0;
    accumulateScore = 0;
    latestScore = 0;
    logged = false;
    dummyAccount = da;
  }

  public Player(String u1, String p1, double hs, double lts, int sp, int ek, int nod, int bb,
      long as, long ls, boolean log, boolean dum) {
    this.username = u1;
    this.password = p1;
    this.highestScore = hs;
    longestTimeSurvivalInEndlessMode = lts;
    enemiesKilled = ek;
    secondsPlayed = sp;
    numberOfDead = nod;
    blocksBroke = bb;
    accumulateScore = as;
    latestScore = ls;
    logged = log;
    dummyAccount = dum;
  }

  public Player(Player p) {
    this.username = p.getUsername();
    this.password = p.getPassword();
    this.highestScore = p.getHighestScore();
    longestTimeSurvivalInEndlessMode = p.getLongestTimeSurvivalInEndlessMode();
    enemiesKilled = p.getEnemiesKilled();
    secondsPlayed = p.getSecondsPlayed();
    numberOfDead = p.getNumberOfDead();
    blocksBroke = p.getBlocksBroke();
    accumulateScore = p.getAccumulateScore();
    latestScore = p.getLastestScore();
    logged = p.isLogged();
    dummyAccount = p.isDummyAccount();
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

  public long getLastestScore() {
    return latestScore;
  }

  public void setLastestScore(long lastestScore) {
    this.latestScore = lastestScore;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isLogged() {
    return logged;
  }

  public void setLogged(boolean logged) {
    this.logged = logged;
  }

  public boolean isDummyAccount() {
    return dummyAccount;
  }

  public void setDummyAccount(boolean dummyAccount) {
    this.dummyAccount = dummyAccount;
  }

  @Override
  public String toString() {
    return "username='" + username + '\'' + "\n password='" + password + '\'' + "\n highestScore="
        + highestScore + "\n longestTimeSurvivalInEndlessMode=" + longestTimeSurvivalInEndlessMode
        + "\n secondsPlayed=" + secondsPlayed + "\n enemiesKilled=" + enemiesKilled
        + "\n numberOfDead=" + numberOfDead + "\n blocksBroke=" + blocksBroke
        + "\n accumulateScore=" + accumulateScore;
  }
}
