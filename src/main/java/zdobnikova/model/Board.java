package zdobnikova.model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private int width;
    private int height;

    public Map<Point, Stone> cells = new HashMap<>();


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean inBoard(Point point) {
        return point.getX() >= 0 && point.getX() <= width && point.getY() >= 0 && point.getY() <= height;
    }

    private boolean isEmpty(Point point) {
        return cells.get(point) == null;
    }


    public boolean put(Point point, Stone stone) {
        if (inBoard(point) && isEmpty(point)) {
            cells.put(point, stone);
            return true;
        }
        return false;
    }




    public Stone get(Point point) {
        return cells.get(point);
    }


}