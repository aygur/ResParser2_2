package com.naraikin.models;

import java.io.*;
import java.net.URL;

/**
 *  Модель ресурса
 */
public class Resource {

    /**
     *  Переменная хранит строковый путь к файлу
     */
    private String resourceString;

    /**
     * Метод возвращает строковый путь к файлу
     * @return строка путь к файлу
     */
    public String getResourceString() {
        return resourceString;
    }

    /**
     * Конструктор класса ресурса
     * @param resString
     */
    public Resource(String resString) {
        resourceString = resString;
    }

    /**
     * Метод создает bufferedReader для полученной текстовой строки
     * в конструкторе
     *
     * @return BufferedReader Возвращает поток
     * @throws IOException
     */
    public BufferedReader getBufferedReader() throws IOException{
        if(resourceString.toLowerCase().indexOf("http://") != -1
                || resourceString.toLowerCase().indexOf("https://") !=-1) {
            return new BufferedReader(
                    new InputStreamReader(new URL(resourceString).openStream()));
        } else {
            return new BufferedReader(
                    new InputStreamReader(new FileInputStream(resourceString)));
        }
    }

    /**
     * Метод проверяет существования файла на диске
     *
     * @param filePath Путь к файлу на диске
     * @return
     */
    public static boolean validateFilePath(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }
}
