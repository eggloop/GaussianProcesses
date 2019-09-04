package io.github.eggloop.spider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class prova {
    public static void main(String[] args) {

        List<double[]> a = Arrays.asList(new double[] { 1, 2 },
                new double[] { 3, 4 });
        List<double[]> b = Arrays.asList(new double[] { 1, 2 },
                new double[] { 3, 4 });


        Bean bean = new Bean(a,b);


        try {
            FileOutputStream fos = new FileOutputStream("test.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bean);

            FileInputStream fis = new FileInputStream("test.dat");
            ObjectInputStream iis = new ObjectInputStream(fis);
            Bean newTwoD = (Bean) iis.readObject();
            System.out.println();

        } catch (Exception e) {

        }

    }
}
