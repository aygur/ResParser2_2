package utils;

import java.io.*;

/**
 * Created by dmitrii on 08.02.17.
 */
public class FileGenerator {

    public static void main(String[] args) {

        for (int i = 1; i<= 10; i++){
            Thread t = new FillFile("files/file"+i+".txt");
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
