package com.naraikin;

import com.naraikin.models.Counter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmitrii on 09.02.17.
 *
 * Управляет созданием потоков для чтения файлов
 */
public class Controller {

    public static final Logger logger = Logger.getLogger(Controller.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    private String[] resourcesArray;
    private List<Thread> threads = new ArrayList<>();
    private List<Future> futures = new ArrayList<>();
    private Counter counter = new Counter();
    private ExecutorService executor;

    /**
     * Class constructor
     * @param resourceStrings
     */
    public Controller(String[] resourceStrings) {
        this.resourcesArray = resourceStrings;
        logger.trace("Контроллер создан с " + resourceStrings.length + " ресурсами");
        executor = Executors.newFixedThreadPool(this.resourcesArray.length);
    }

    /**
     * Возвращение объекта Counter
     * @return Counter
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * Запускает массив потоки
     */
    private void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
        logger.trace("Запуск потоков в массиве");
    }

    /**
     * Присоединяет массив потоков к главному потоку
     */
    private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error("InterruptedException");
            }
        }
    }

    /**
     * Создает массив содержащий потоки
     */
   /* private void createThreads() {
        for (String res : resourcesArray) {
            Thread t = new Thread(new ResourceHandler(counter, res));
            threads.add(t);
        }
        logger.trace("Создан массив потоков");
    }*/

    /**
     * Запуск поток old
     */
    /*public void run(){
       createThreads();
       startThreads();
       joinThreads();
       if(!counter.isError()){
           logger.trace("Выполнение завершено успешно");
       }

    }*/

    public void run(){
        for (String res : resourcesArray) {
           futures.add(executor.submit(new ResourceHandler(counter, res)));
        }

        for (Future f :
                futures) {
            while (!f.isDone()){
                logger.trace("Поток выполняется " + f);
            }
        }
        executor.shutdown();
    }

}
