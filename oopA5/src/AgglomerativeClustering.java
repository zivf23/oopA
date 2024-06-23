import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.stream.Collectors;

public class AgglomerativeClustering<T extends Clusterable<T>> implements Clustering<T> {
    double threshold;

    public AgglomerativeClustering(double threshold) {
        this.threshold = threshold;
    }

    public Set<Set<T>> clusterSet(Set<T> elements) {
        Set<Set<T>> clusters = elements.stream()
                .map(item -> {
                    Set<T> singleton = new HashSet<>();
                    singleton.add(item);
                    return singleton;
                })
                .collect(Collectors.toSet());

        while (clusters.size() > 1) {
            SimpleEntry<Set<T>, Set<T>> closestPair = findClosestClusters(clusters);
            if (closestPair == null || distance(closestPair.getKey(), closestPair.getValue()) > threshold) {
                break;
            }
            clusters.remove(closestPair.getKey());
            clusters.remove(closestPair.getValue());
            Set<T> mergedCluster = new HashSet<>();
            mergedCluster.addAll(closestPair.getKey());
            mergedCluster.addAll(closestPair.getValue());
            clusters.add(mergedCluster);
        }

        return clusters;
    }

    private SimpleEntry<Set<T>, Set<T>> findClosestClusters(Set<Set<T>> clusters) {
        return clusters.stream()
                .flatMap(c1 -> clusters.stream()
                        .filter(c2 -> !c1.equals(c2))
                        .map(c2 -> new SimpleEntry<>(c1, c2)))
                .min((e1, e2) -> Double.compare(distance(e1.getKey(), e2.getValue()), distance(e1.getKey(), e2.getValue())))
                .orElse(null);
    }

    private double distance(Set<T> cluster1, Set<T> cluster2) {
        return cluster1.stream()
                .flatMap(c1 -> cluster2.stream()
                        .map(c2 -> c1.distance(c2)))
                .min(Double::compare)
                .orElse(Double.MAX_VALUE);
    }
}
