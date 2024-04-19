import java.util.Objects;

public class HeapSpaceSolver {
    int x;
    int y;

    public HeapSpaceSolver(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HeapSpaceSolver other = (HeapSpaceSolver) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}