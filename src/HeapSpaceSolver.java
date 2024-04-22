import java.util.Objects;

public class HeapSpaceSolver {
    public int x;
    public int y;
    public int g; // Cost from start node
    public int h; // Heuristic value
    public int f; // f = g + h
    public HeapSpaceSolver parent;

    public HeapSpaceSolver(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getF() {
        return f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeapSpaceSolver that = (HeapSpaceSolver) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
