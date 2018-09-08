package zdobnikova.model;

public class Game {

    private Stone currentPlayer = Stone.BLACK;

    private final int width = 15;
    private final int height = 15;

    private Stone winner = null;

    private Board board = new Board(15, 15);

    static public final int WINNER_POINTS = 5;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    Board getBoard() {
        return board;
    }

    public void makeMove(Point point) {
        if (board.put(point, currentPlayer) && (!foulMax())) {
            currentPlayer = currentPlayer.opposite();
            winner = findWinner();
        }
    }

    static private final Point[] VECTOR_DIR = new Point[]{
            new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(1, -1)
    };

    public boolean foulMax() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                Stone firstStone = board.get(point);
                if (firstStone != Stone.BLACK) continue;
                for (Point dir : VECTOR_DIR) {
                    Point current = point;
                    int gamePoints = 1;
                    for (; gamePoints < 6; gamePoints++) {
                        current = current.sum(dir);
                        if (board.get(current) != firstStone) break;
                    }
                    if (gamePoints == 6)
                        return true;

                }
            }
        }
        return false;
    }

    public boolean foulFork() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                Stone firstStone = board.get(point);
                if (firstStone != Stone.BLACK) continue;
                for (Point dir : VECTOR_DIR) {
                    Point current = point;
                    int gamePoints = 1;
                    int count = 0;
                    for (; gamePoints < 4; gamePoints++) {
                        count++;
                        current = current.sum(dir);
                        if (board.get(current) != firstStone) break;
                    }
                    if ((gamePoints == 4) && (count == 3)) return false;
                    if ((gamePoints + count) == 7) return true;
                }
            }
        }
        return false;
    }

    private Stone findWinner() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                Stone firstStone = board.get(point);
                if (firstStone == null) continue;
                for (Point dir : VECTOR_DIR) {
                    Point current = point;
                    int gamePoints = 1;
                    for (; gamePoints < WINNER_POINTS; gamePoints++) {
                        current = current.sum(dir);
                        if (board.get(current) != firstStone) break;
                    }
                    if (gamePoints == WINNER_POINTS) return firstStone;
                }
            }
        }
        return null;
    }

    public Stone getWinner() {
        return winner;
    }

    public Stone getCurrentPlayer() {
        return currentPlayer;
    }
}
