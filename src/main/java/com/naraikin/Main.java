package com.naraikin;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    public static void main(String[] args) {

        File folder = new File("files");
        File[] listOfFiles = folder.listFiles();
        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile())
                files[i] = "files/" + listOfFiles[i].getName();
        }

        String[] files2 = {
                "files/file1.txt",
                "files/file2.txt",
        };


        logger.trace("Создание Controller");
        Controller controller = new Controller(files2);
        controller.run();
        System.out.println(controller.getCounter().isError()
                ? "<<<Error>>>"
                : "Sum: " + controller.getCounter().getValue() );
    }
}
