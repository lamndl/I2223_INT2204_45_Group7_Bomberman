package level;

import entity.animated.Flame;
import entity.animated.mob.Balloom;
import entity.animated.mob.Bomber;
import entity.animated.mob.Oneal;
import entity.tile.Brick;
import entity.tile.Grass;
import entity.tile.Portal;
import entity.tile.PowerUpBomb;
import entity.tile.PowerUpFlame;
import entity.tile.PowerUpSpeed;
import entity.tile.Wall;
import java.io.FileReader;
import java.util.Scanner;
import mainClass.Board;
import mainClass.App;

public class FileLevelLoader extends LevelLoader {

  /**
   * Ma trận chứa thông tin bản đồ, đọc từ tệp cấu hình .
   */
  private char[][] map;

  public FileLevelLoader(int level) {
    super(level);
  }

  @Override
  public void loadLevel(int level) {
    // TODO: đọc dữ liệu từ tệp cấu hình src/main/resources/levels/Level{level}.txt
    // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
    try {
      FileReader file =
          new FileReader("src/main/resources/levels/Level" + Integer.toString(level) + ".txt");
      Scanner sc = new Scanner(file);
      level = sc.nextInt();
      height = sc.nextInt();
      width = sc.nextInt();
      map = new char[height][width];
      sc.nextLine();

      for (int i = 0; i < height; i++) {
        String line = sc.nextLine();
        for (int j = 0; j < width; j++) {
          map[i][j] = line.charAt(j);
        }
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
    // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game
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

          //add portal: cong ket thuc game
          case 'x':
            Board.addEntity(new Portal(x * 32, y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;

          case 'p':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new Bomber(x*32,y*32));
            break;

          case 'f':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new PowerUpFlame(x * 32,y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;

          case 's':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new PowerUpSpeed(x * 32,y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;

          case 'b':
            Board.addEntity(new Grass(x * 32, y * 32));
            Board.addEntity(new PowerUpBomb(x * 32,y * 32));
            Board.addEntity(new Brick(x * 32, y * 32));
            break;
          // //add Bomb Item: vat pham tang so luong bom
          // case 'b':
          // _board.addEntity(pos,
          // new LayeredEntity(x, y,
          // new Grass(x, y, Sprite.grass),
          // new BombItem(x, y, Sprite.powerup_bombs),
          // new Brick(x, y, Sprite.brick)
          // )
          // );
          // break;
          // //add Flame Item: vat pham tang suc cong pha
          // case 'f':
          // _board.addEntity(pos,
          // new LayeredEntity(x, y,
          // new Grass(x, y, Sprite.grass),
          // new FlameItem(x, y, Sprite.powerup_flames),
          // new Brick(x, y, Sprite.brick)
          // )
          // );
          // break;
          // //add Speed Item: vat pham tang toc do
          // case 's':
          // _board.addEntity(pos,
          // new LayeredEntity(x, y,
          // new Grass(x, y, Sprite.grass),
          // new BombItem(x, y, Sprite.powerup_speed),
          // new Brick(x, y, Sprite.brick)
          // )
          // );
          // break;
          // //con lai la grass
          default:
            Board.addEntity(new Grass(x * 32, y * 32));
            break;
        }
      }
    }
  }
}
