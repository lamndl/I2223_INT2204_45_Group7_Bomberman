package level;

import entity.animated.mob.Balloom;
import entity.animated.mob.Oneal;
import entity.tile.Brick;
import entity.tile.Grass;
import entity.tile.Portal;
import entity.tile.Wall;
import entity.tile.powerup.PowerUpBomb;
import entity.tile.powerup.PowerUpFlame;
import entity.tile.powerup.PowerUpSpeed;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import mainClass.Board;
import mainClass.App;

public class FileLevelLoader extends LevelLoader {

  /**
   * Ma trận chứa thông tin bản đồ, đọc từ tệp cấu hình .
   */
  private char[][] map;
  Random random = new Random();

  public FileLevelLoader(int level) {
    super(level);
    this.level = level;
  }

  @Override
  public void loadLevel(int level) {
    // TODO: đọc dữ liệu từ tệp cấu hình src/main/resources/levels/Level{level}.txt
    // TODO: cập nhật các giá trị đọc được vào width, height, level, map
    try {
      FileReader file =
          new FileReader("src/main/resources/levels/Level" + Integer.toString(level) + ".txt");
      Scanner sc = new Scanner(file);
      level = sc.nextInt();
      height = sc.nextInt();
      width = sc.nextInt();
      map = new char[height][width];
      randomList.clear();
      sc.nextLine();

      for (int i = 0; i < height; i++) {
        String line = sc.nextLine();
        for (int j = 0; j < width; j++) {
          map[i][j] = line.charAt(j);
        }
      }
      while (true) {
        String line = sc.nextLine();
        if (line.isEmpty() || line.charAt(0) == ' ') {
          break;
        }

        randomList.put(line.charAt(0), (int) (line.charAt(2) - '0'));
      }

      sc.close();
      file.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void createEntities() {
    // TODO: tạo các Entity của màn chơi
    // TODO: sau khi tạo xong, gọi Board.addEntity() để thêm Entity vào game
    Board.setHeight(App.HEIGHT);
    Board.setWidth(App.WIDTH);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        char c = map[y][x];
        switch (c) {
          case '#':
            Board.addEntity(new Wall(x * 32, y * 32));
            break;
          case '*':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;

          // add enemy 1
          case '1':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Balloom(x * 32, y * 32));
            break;

          case '2':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Oneal(x * 32, y * 32));
            break;

          // add portal: cong ket thuc game
          case 'x':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Portal(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;
          // //add Bomb Item: vat pham tang so luong bom
          case 'b':
            Board.addEntity(new PowerUpBomb(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;
          // add Flame Item: vat pham tang suc cong pha
          case 'f':
            Board.addEntity(new PowerUpFlame(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;
          // add Speed Item: vat pham tang toc do
          case 's':
            Board.addEntity(new PowerUpSpeed(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;
          case 'p':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.getBomber().setLocation(x * 32, y * 32);
            Board.addEntity(Board.getBomber());
          default:
            Board.addEntity(new Grass(x * 32, y * 32));
            break;
        }
      }
    }
  }

  public void createEntities(int n) {
    // TODO: random entities
    Board.setHeight(App.HEIGHT);
    Board.setWidth(App.WIDTH);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        char c = map[y][x];
        Boolean isAdd = false;
        switch (c) {
          case '#':
            Board.addEntity(new Wall(x * 32, y * 32));
            isAdd = true;
            break;
          case 'p':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.getBomber().setLocation(x * 32, y * 32);
            Board.addEntity(Board.getBomber());
            isAdd = true;
          default:
            Board.addEntity(new Grass(x * 32, y * 32));
            break;
        }

        if (!isAdd) {
          if (randomList.get('1') > 0) {
            int ran = random.nextInt(0, 50);
            if (ran == 0 && !(x < 5 && y < 5)) {
              // add enemy 1
              Board.addEntity(new Grass(x * 32, y * 32));
              Board.addEntity(new Balloom(x * 32, y * 32));
              randomList.replace('1', randomList.get('1'), randomList.get('1') - 1);
              System.out.println("1 " + randomList.get('1'));
              continue;
            }
          }

          if (randomList.get('2') > 0) {
            int ran = random.nextInt(0, 50);
            if (ran == 0 && !(x < 5 && y < 5)) {
              // add enemy 2
              Board.addEntity(new Grass(x * 32, y * 32));
              Board.addEntity(new Oneal(x * 32, y * 32));
              randomList.replace('2', randomList.get('2'), randomList.get('2') - 1);
              continue;
            }
          }

          if (randomList.get('x') > 0) {
            int ran = random.nextInt(0, 150);
            if (ran == 0 && x > 5 && y > 5) {
              // add portal
              Board.addEntity(new Grass(x * 32, y * 32));
              Board.addEntity(new Portal(x * 32, y * 32));
              Board.addEntity(new Brick(x * 32, y * 32));
              randomList.replace('x', randomList.get('x'), randomList.get('x') - 1);
              continue;
            }
          }

          // add portal to last location if not yet added
          if (x == width - 2 && y == height - 2 && randomList.get('x') > 0) {
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Portal(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
          }

          // add Brick
          int ran = random.nextInt(0, 3);
          if (ran == 1 && !(x == 1 && y == 1) && !(x == 1 && y == 2) && !(x == 2 && y == 1)) {
            Board.addEntity(new Brick(x * 32, y * 32));
          }
        }

      }
    }
  }

}
