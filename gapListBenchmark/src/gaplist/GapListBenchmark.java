package gaplist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import org.magicwerk.brownies.collections.GapList;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(NANOSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1)
@Fork(5)
@State(Scope.Benchmark)

public class GapListBenchmark {

    private final List<Integer> aList = new ArrayList<>();
    private final List<Integer> lList = new LinkedList<>();
    private final List<Integer> gapList = new GapList<>();

    private static final Integer value = Integer.MIN_VALUE;
    private static final int TEST_LIST_SIZE = 100_000;
    private static final Random rand = new Random();

    @Setup
    public void setUp() {
        fillList(aList);
        fillList(lList);
        fillList(gapList);
    }

    private void fillList(List<Integer> list) {
        for (int i = 0; i < TEST_LIST_SIZE; i++) {
            list.add(rand.nextInt());
        }
    }

    @GenerateMicroBenchmark
    public int test_getRandom_ArrayList() {
        int index = (int) (Math.random() * TEST_LIST_SIZE);
        return aList.get(index);
    }

    @GenerateMicroBenchmark
    public int test_getRandom_LinkedList() {
        int index = (int) (Math.random() * TEST_LIST_SIZE);
        return lList.get(index);
    }

    @GenerateMicroBenchmark
    public int test_getRandom_GapList() {
        int index = (int) (Math.random() * TEST_LIST_SIZE);
        return gapList.get(index);
    }

}
