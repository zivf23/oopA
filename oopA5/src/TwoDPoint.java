import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class TwoDPoint implements Clusterable<TwoDPoint> {
    double x;
    double y;

    public TwoDPoint(String str) {

        String[] coords = str.split(",");
        this.x = Double.parseDouble(coords[0]);
        this.y = Double.parseDouble(coords[1]);
    }

    public TwoDPoint(double x, double y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public double distance(TwoDPoint other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // Method to read a set of TwoDPoint from a file
    public static Set<TwoDPoint> readClusterableSet(String path) throws IOException {
        Set<TwoDPoint> points = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                points.add(new TwoDPoint(line));
            }
        }
        return points;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    @Override
    public boolean equals(Object other) {
        TwoDPoint otherP = (TwoDPoint) other;
        return (otherP.x == x && otherP.y == y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
