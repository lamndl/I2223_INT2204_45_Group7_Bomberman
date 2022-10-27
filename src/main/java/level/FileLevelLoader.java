package level;

import entity.animated.mob.Bomber;
import entity.tile.Brick;
import entity.tile.Grass;
import entity.tile.Wall;
import exceptions.LoadLevelException;
import mainClass.App;
import mainClass.Board;
import sprite.Screen;
import sprite.Sprite;

import java.io.FileReader;
import java.util.Scanner;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, đọc từ tệp cấu hình
     */
    private static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) {
        // TODO: đọc dữ liệu từ tệp cấu hình src/main/resources/levels/Level{level}.txt
        // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
        try {
            FileReader file = new FileReader("src/main/resources/levels/Level" + Integer.toString(level) + ".txt");
            Scanner sc = new Scanner(file);
            _level = sc.nextInt();
            _height = sc.nextInt();
            _width = sc.nextInt();
            _map = new char[_height][_width];
            sc.nextLine();

            for (int i = 0; i < _height; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < _width; j++)
                    _map[i][j] = line.charAt(j);
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

        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                int pos = x + y * getWidth();
                char c = _map[y][x];
                switch (c) {
                    //them wall
                    case '#':
                        Sprite sprite = Sprite.wall;
                        _board.addEntity(pos, new Wall(x, y, sprite));
                        break;
                    //add Brick (gach)
                    case '*':
                        _board.addEntity(pos, new Brick(x, y, Sprite.brick));
                        break;
                    //add Bomber
//                    case 'p':
//                        _board.addEntity(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + App.TILES_SIZE, _board));
//                        Screen.setOffset(0, 0);
//                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
//                        break;
                    //add enemy 1
//                    case '1':
//                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
//                        break;
//                    //add enemy 2
//                    case '2':
//                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
//                        break;
//                    //add portal: cong ket thuc game
//                    case 'x':
//                        _board.addEntity(pos,
//                                new LayeredEntity(x, y,
//                                        new Grass(x, y, Sprite.grass),
//                                        new Portal(x, y, Sprite.portal, _board),
//                                        new Brick(x, y, Sprite.brick)
//                                )
//                        );
//                        break;
//                    //add Bomb Item: vat pham tang so luong bom
//                    case 'b':
//                        _board.addEntity(pos,
//                                new LayeredEntity(x, y,
//                                        new Grass(x, y, Sprite.grass),
//                                        new BombItem(x, y, Sprite.powerup_bombs),
//                                        new Brick(x, y, Sprite.brick)
//                                )
//                        );
//                        break;
//                    //add Flame Item: vat pham tang suc cong pha
//                    case 'f':
//                        _board.addEntity(pos,
//                                new LayeredEntity(x, y,
//                                        new Grass(x, y, Sprite.grass),
//                                        new FlameItem(x, y, Sprite.powerup_flames),
//                                        new Brick(x, y, Sprite.brick)
//                                )
//                        );
//                        break;
//                    //add Speed Item: vat pham tang toc do
//                    case 's':
//                        _board.addEntity(pos,
//                                new LayeredEntity(x, y,
//                                        new Grass(x, y, Sprite.grass),
//                                        new BombItem(x, y, Sprite.powerup_speed),
//                                        new Brick(x, y, Sprite.brick)
//                                )
//                        );
//                        break;
//                    //con lai la grass
                    default:
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                }
            }
        }
    }
}
