package io.github.eggloop.trajectories;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static io.github.eggloop.testUtils.AssertionsCustom.assertMatrixEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrajectoryFactoryTest {

    @Test
    void testToJSON() {
        double[][] values = new double[][]{{1, 2}, {2, 3}};
        double[] times = new double[]{1, 2};
        String[] names = new String[]{"X", "Y"};
        String jsonExpected = "{\"variables\":[\"X\",\"Y\"],\"values\":[[1.0,2.0],[2.0,3.0]],\"time\":[1.0,2.0]}";

        Trajectory trajectory = new Trajectory(names, times, values);

        assertEquals(TrajectoryFactory.toJSON(trajectory), jsonExpected);
    }

    @Test
    void testFromJSON() throws ParseException {
        double[][] values = new double[][]{{1, 2}, {2, 3}};
        double[] times = new double[]{1, 2};
        String[] names = new String[]{"X", "Y"};
        String actualJSON = "{\"variables\":[\"X\",\"Y\"],\"values\":[[1.0,2.0],[2.0,3.0]],\"time\":[1.0,2.0]}";

        Trajectory actualTrajectory = TrajectoryFactory.fromJSON(actualJSON);

        assertArrayEquals(names, actualTrajectory.getVariables());
        assertArrayEquals(times, actualTrajectory.getTimes());
        assertMatrixEquals(values, actualTrajectory.getValues());
    }
}