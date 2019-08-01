package io.github.eggloop.spider;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ParameterSupplier implements Supplier<double[]> {

    List<double[]> time;
    List<double[]> space;
    int nTime;
    int nSpace;
    int[] state;
    int[] maxState;
    private List<List<double[]>> values = new ArrayList<>();


    public ParameterSupplier(List<double[]> time, List<double[]> space) {
        this.time = time;
        this.space = space;
    }

    public void setConfiguration(int nTime, int nSpace) {
        this.nTime = nTime;
        this.nSpace = nSpace;
        this.state = new int[nTime + nSpace];
        int[] maxStateTime = IntStream.range(0, nTime).map(s -> this.time.size()).toArray();
        int[] maxStateSpace = IntStream.range(0, nSpace).map(s -> this.space.size()).toArray();
        this.maxState = ArrayUtils.addAll(maxStateTime, maxStateSpace);
        for (int i = 0; i < nTime; i++) {
            values.add(time);
        }
        for (int i = 0; i < nSpace; i++) {
            values.add(space);
        }
    }

    private synchronized int[] incrementAndGetState() {
        recursiveIncrement(state.length - 1);
        return state;

    }

    private synchronized void recursiveIncrement(int l) {
        if (l == -1) {
            state = null;
        }
        if ((state[l] + 1) % maxState[l] == 0) {
            state[l] = 0;
            recursiveIncrement(l - 1);
        } else {
            state[l]++;
        }
    }

    public synchronized long length() {
        System.out.println();
        return Arrays.stream(maxState).mapToLong(s->s).reduce(1, (a, b) -> a * b)-1;
    }


    @Override
    public synchronized double[] get() {
        List<double[]> elements = new ArrayList<>();
        IntStream.range(0, state.length).peek(i -> elements.add(values.get(i).get(state[i]))).forEach(s -> {
        });
        incrementAndGetState();
        double[] result = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            result = ArrayUtils.addAll(result, elements.get(i));
        }
        return result;

    }
}
