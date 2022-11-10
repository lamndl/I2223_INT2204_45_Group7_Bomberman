package entity.animated.mob;

import static mainClass.App.KB;

import ai.Node;
import entity.animated.Bomb;
import entity.animated.mob.enemy.Enemy;
import entity.tile.Portal;
import entity.tile.Tile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.App;
import mainClass.Board;
import mainClass.Sound;
import sprite.Sprite;

public class Bomber extends Mob {

  private int bombCount = 1;
  private int flameLength = 2;
  private double speedMultiplier = 1;
  protected int timer = 120;
  public List<Node> path = new ArrayList<>();

  public void placeBomb() {
    if (bombCount > 0) {
      Sound.playInGameSound(4);
      bombCount--;
      Board.addEntity(new Bomb((x + 13) / 32 * 32, (y + 15) / 32 * 32));
      allowThroughBomb = true;
    }
  }

  public Bomber(int x, int y) {
    super(x, y);
    hp = 3;
  }

  public void aiCalculateMove() {
    if (!path.isEmpty() && path.size() >= 2) {
      Node nearestNode = path.get(1);
      if (nearestNode.isBrick()) {
        placeBomb();
        return;
      }
      if (path.size() <= 3 && !Board.getEnemyList().isEmpty() && Board.getBombList().isEmpty()) {
        placeBomb();
        return;
      }
      int xx = x - nearestNode.getCol() * 32;
      int yy = y - nearestNode.getRow() * 32;
      if (xx < 0) {
        direction = 1;
        velocityX = 2;
        moving = 1;
      } else if (xx > 0) {
        direction = 0;
        velocityX = -2;
        moving = 1;
      }
      if (yy > 0) {
        direction = 2;
        velocityY = -2;
        moving = 1;
      } else if (yy < 0) {
        direction = 3;
        velocityY = 2;
        moving = 1;
      }
    }
    //in-game pause menu
    Iterator<KeyCode> iterator = Board.input.iterator();
    while (iterator.hasNext()) {
      KeyCode i = iterator.next();
      if (i == KB.getInGameMenu()) {
        if (App.coe) {
          Board.goIngameMenu();
        }
      }
    }
  }

  public void calculateMove() {
    Iterator<KeyCode> iterator = Board.input.iterator();
    while (iterator.hasNext()) {
      KeyCode i = iterator.next();
      if (i == KB.getMoveUp()) {
        direction = 2;
        velocityY = -2;
        moving = 1;
      } else if (i == KB.getMoveDown()) {
        direction = 3;
        velocityY = 2;
        moving = 1;
      } else if (i == KB.getMoveLeft()) {
        direction = 0;
        velocityX = -2;
        moving = 1;
      } else if (i == KB.getMoveRight()) {
        direction = 1;
        velocityX = 2;
        moving = 1;
      } else if (i == KB.getBombPlacement()) {
        placeBomb();
        iterator.remove();

      } else if (i == KB.getInGameMenu()) {
        if (App.coe) {
          Board.goIngameMenu();
        }
      } else if (i == KeyCode.F5) {
        Board.nextLevel();
      }
    }

    if (allowThroughBomb) {
      boolean fl = false;
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          fl = true;
          break;
        }
      }
      if (!fl && !Board.getBombList().isEmpty()) {
        allowThroughBomb = false;
      }
    }
  }

  public int getFlameLength() {
    return flameLength;
  }

  public void update() {
    if (alive) {

      checkHit();
      velocityX = 0;
      velocityY = 0;
      moving = 0;
      if (App.toggleAI) {
        aiCalculateMove();
      }
      calculateMove();


      move();
    } else {
      die();
    }

  }

  @Override
  protected void checkHit() {
    super.checkHit();
    for (Enemy i : Board.getEnemyList()) {
      if (isCollidedWith(i)) {
        alive = false;
        Sound.playInGameSound(2);
        return;
      }
    }
  }

  @Override
  public void die() {
    timer--;
    if (timer == 0) {
      hp--;
      if (hp > 0) {
        alive = true;
        timer = 120;
        x = 32;
        y = 32;
        return;
      }
      Board.removeEntity(this);
      // check score
      if (App.currentPlayer.getHighestScore() < App.currentPlayer.getLastestScore()) {
        App.currentPlayer.setHighestScore(App.currentPlayer.getLastestScore());
      }
      App.currentPlayer.setAccumulateScore(
          App.currentPlayer.getAccumulateScore() + App.currentPlayer.getLastestScore());
      App.currentPlayer.setNumberOfDead(App.currentPlayer.getNumberOfDead() + 1);
      App.currentPlayer.setLastestScore(0);
      // go to scene end game and replay

      if (!App.coe) {
        if (App.currentPlayer.getLongestTimeSurvivalInEndlessMode() < Board.unresetFrame / 60) {
          App.currentPlayer.setLongestTimeSurvivalInEndlessMode(Board.unresetFrame / 60);
        }
      }
      Board.goEndGame();
    }

  }

  @Override
  public Image getImage() {
    if (alive) {
      if (Board.getPlayerNumber() == 0) {
        return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
      } else if (Board.getPlayerNumber() == 2) {
        return Sprite.player1[(int) (direction * 3 + moving * (Board.frame / 20))];
      } else if (Board.getPlayerNumber() == 1) {
        return Sprite.player2[(int) (direction * 3 + moving * (Board.frame / 20))];
      } else {
        return Sprite.player3[(int) (direction * 3 + moving * (Board.frame / 20))];
      }
    } else {
      if (timer > 80) {
        return Sprite.player_dead1;
      }
      if (timer > 40) {
        return Sprite.player_dead2;
      }
      return Sprite.player_dead3;
    }
  }

  @Override
  protected void move() {
    x += velocityX;
    checkWin();
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
      }
    }
    y += velocityY;
    checkWin();
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        y -= velocityY;
        return;
      }
    }

    if (!allowThroughBomb) {
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          x -= velocityX;
          y -= velocityY;
          return;
        }
      }
    }
  }


  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 5, 17, 24);
  }

  public void increaseBomb() {
    bombCount++;
  }

  public void increaseFlamLength() {
    flameLength++;
  }

  public void increaseSpeed() {
    speedMultiplier += 0.2;
  }

  public void checkWin() {
    if (!Board.getEnemyList().isEmpty()) {
      return;
    }
    for (Tile i : Board.getTileList()) {
      if ((i instanceof Portal) && this.isCollidedWith(i)) {
        App.currentPlayer.setLastestScore(App.currentPlayer.getLastestScore() + 500);
        // to next level
        Board.nextLevel();
        return;
      }
    }
  }

}
