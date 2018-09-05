package zdobnikova.model;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point sum(Point other) {
        return new Point(this.getX() + other.getX(), this.getY() + other.getY());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof Point) {
            Point other = (Point) object;
            return (x == other.x)
                    && (y == other.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    @Override
    public String toString() {
        return "{" + x + ";" + y + '}';
    }


}

