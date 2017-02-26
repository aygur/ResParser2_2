package com.naraikin;

import com.naraikin.models.Counter;
import com.naraikin.models.Parser;
import com.naraikin.models.Resource;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmitrii on 09.02.17.
 *
 * Обработчик ресурса в потоках
 */
public class ResourceHandler implements Callable {

    public static final Logger logger = Logger.getLogger(ResourceHandler.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    private Counter counter;
    private Resource resource;

    /**
     * Конструктор обработчика потоков
     * Создает объект ресурса
     *
     * @param ctr   Сумматор
     * @param resourceString    Путь к файлу
     */
    public ResourceHandler(Counter ctr, String resourceString) {
        counter = ctr;
        resource = new Resource(resourceString);
        logger.trace("Создан поток для " + resourceString );
    }

    /**
     * В методе происходит чтение построчно из BufferedReader
     * и проверка поднятия флага в Counter
     *
     * В случае появление невалидных данных выбрасывается исключение
     * и поднятие флаги ошибки в Counter
     *
     */
    @Override
    public Integer call() {
            logger.trace("Поток запущен " + Thread.currentThread().getName());
            try (BufferedReader bufferedReader = resource.getBufferedReader()){
                String line;
                while (((line = bufferedReader.readLine()) != null)) {
                    int sumInString = Parser.getPositiveEvenSum(line);
                    if(!counter.isError()) {
                        counter.addValueAndPrint(sumInString);
                        logger.trace(counter.getValue() +
                                " -- " + Thread.currentThread().getName() +
                                " -- " + resource.getResourceString());
                    }
                }
            } catch (NumberFormatException ex) {
                counter.setError();
                String error = "Not valid symbol "
                        + ex.getMessage() + " in resource " + resource.getResourceString();
                logger.error(error);
                System.out.println(error);
            } catch (IOException ex) {
                counter.setError();
                String error ="IO Error: " + ex.getMessage() +" in "  + resource.getResourceString();
                logger.error(error);
                System.out.println(error);
            }
            return 1;
    }
}
