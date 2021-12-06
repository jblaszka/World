package simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Genome {
    private static final int GENOME_LENGTH = 32;
    private List<MapDirection> genome = new LinkedList<>();
    private final Random random = new Random();

    public Genome() {
        random.ints(GENOME_LENGTH, 0, MapDirection.values().length)
                .forEach(i -> genome.add(MapDirection.values()[i]));
    }

    public Genome(Genome mother, Genome father) {
        int split = random.nextInt(GENOME_LENGTH - 2) + 1;
        IntStream.range(0, GENOME_LENGTH)
                .forEach(i -> genome.add((i <= split ? mother : father).genome.get(i)));
    }

    public MapDirection getRandomMove() {
        return genome.get(random.nextInt(GENOME_LENGTH));
    }
}