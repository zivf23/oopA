import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitArray implements Clusterable<BitArray> {
    private final ArrayList<Boolean> bits;

    public BitArray(String str) {
        this.bits = Stream.of(str.split(","))
                .map(Boolean::parseBoolean)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public BitArray(boolean[] bits) {
        this.bits = IntStream.range(0, bits.length)
                .mapToObj(i -> bits[i])
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public double distance(BitArray other) {
        //Hamming distance
        if (this.bits.size() != other.bits.size()) {
            throw new IllegalArgumentException("BitArrays must be of the same length to calculate distance.");
        }
        return IntStream.range(0, bits.size())
                .filter(i -> !this.bits.get(i).equals(other.bits.get(i)))
                .count();

    }

    public static Set<BitArray> readClusterableSet(String path) throws IOException {
        // TODO: Complete. If the file contains bitarrays of different lengths,
        //  retain only those of maximal length
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines()
                    .map(BitArray::new)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public String toString() {
        return bits.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitArray bitArray = (BitArray) o;
        return bits.equals(bitArray.bits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bits);
    }
}
