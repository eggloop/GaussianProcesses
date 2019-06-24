package io.github.eggloop.trajectories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrajectoryTest {

    @Test
    void tesToJSON() {
        double[][] values = new double[][]{{1,2},{2,3}};
        double[] times = new double[]{1,2};
        String[] names = new String[]{"X","Y"};
        String jsonExpected = "{\"variables\":[\"X\",\"Y\"],\"values\":[[1.0,2.0],[2.0,3.0]],\"time\":[1.0,2.0]}";

        Trajectory trajectory = new Trajectory(names,times,values);

       assertEquals(trajectory.toJSON(),jsonExpected);
    }
}