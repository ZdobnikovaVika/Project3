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

    public boolean makeMove(Point point) {
        if ((!foulMax(point)) && (!fork(point))) {
            if ((board.get(new Point(7, 7)) == Stone.BLACK) || (point.equals(new Point(7, 7)))) {
                if (board.put(point, currentPlayer)) {
                    currentPlayer = currentPlayer.opposite();
                    winner = findWinner();
                    return true;
                }
            }
        }
        return false;
    }


    static private final Point[] VECTOR_DIR = new Point[]{
            new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(1, -1)
    };

    public boolean foulMax(Point curPoint) {
        board.put(curPoint, Stone.BLACK);
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
                    if (gamePoints == 6) {
                        board.remove(curPoint);
                        return true;
                    }

                }
            }
        }
        board.remove(curPoint);
        return false;
    }

    static private final Point[] FOUL_DIR = new Point[]{
            new Point(1, 0), new Point(-1, 0),
            new Point(0, 1), new Point(1, 1),
            new Point(0, -1), new Point(-1, -1),
            new Point(1, -1), new Point(-1, 1)
    };


    public boolean fork(Point point) {

        //for (Point dir : FOUL_DIR) {
        //Point search = foulPoint;
        // for (int i = 0; i < 4; i++) {
        board.put(point, Stone.BLACK);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point foulPoint = new Point(x, y);
                if (board.get(foulPoint) == Stone.BLACK) {
                    int forkCounter = 0;
                    int horizontal = 1 + countStones(new Point(1, 0), foulPoint) + countStones(new Point(-1, 0), foulPoint);
                    System.out.println(horizontal);
                    int vertical = 1 + countStones(new Point(0, 1), foulPoint) + countStones(new Point(0, -1), foulPoint);
                    int firstDiag = 1 + countStones(new Point(1, 1), foulPoint) + countStones(new Point(-1, -1), foulPoint);
                    int secDiag = 1 + countStones(new Point(1, -1), foulPoint) + countStones(new Point(-1, 1), foulPoint);
                    if (horizontal > 2) forkCounter += horizontal;
                    if (vertical > 2) forkCounter += vertical;
                    if (firstDiag > 2) forkCounter += firstDiag;
                    if (secDiag > 2) forkCounter += secDiag;
                    System.out.println(forkCounter);
                    if ((forkCounter > 5) && (forkCounter != 7)) {
                        board.remove(point);
                        return true;
                    }
                    //foulPoint = foulPoint.sum(dir);
                }
            }
        }
        board.remove(point);
        return false;

    }

    private int countStones(Point dir, Point startPoint) {
        int gamePoints = 0;
        int emptyPoints = 0;
        Point current = startPoint;
        int k = 0;
        for (; gamePoints < WINNER_POINTS; gamePoints++) {
            current = current.sum(dir);
            if (current == new Point(7, 7)) {
                k++;
                break;
            }
            if (board.get(current) == Stone.WHITE) break;
            if (board.get(current) == null) emptyPoints++;
        }
        return gamePoints - emptyPoints + k;
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
