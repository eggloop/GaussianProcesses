package io.github.eggloop.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class loop {
    public static void main(String[] args) throws IOException {
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            String data = stdIn.readLine();
            if (data != null) {
                System.out.println(data);
                if (data.equals("EXIT")) {
                    return;
                }
            }
        }
    }
}
