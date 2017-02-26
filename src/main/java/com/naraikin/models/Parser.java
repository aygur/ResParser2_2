package com.naraikin.models;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by dmitrii on 09.02.17.
 *  Объект парсера строки
 */
public class Parser {

    /**
     * Возвращает сумму положительных и четных числе в строке.
     * @param inputString получаемая строка
     * @return  Возвращает сумму
     * @throws NumberFormatException
     */

    public static int getPositiveEvenSum(String inputString) throws NumberFormatException {

        int out = 0;
        if(inputString == null){
            return out;
        } else if(inputString.isEmpty()){
            return out;
        }

        for (String str: inputString.split("\\s+")) {
            int tmp = Integer.parseInt(str);
            if( (tmp < 0) || (tmp % 2 != 0)){
                continue;
            }
            out += tmp;
        }

        return out;
    }

}