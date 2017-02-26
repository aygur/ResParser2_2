package com.naraikin.models;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmitrii on 09.02.17.
 *
 *  Класс хранящий сумму чисел
 *  с блоками synchronized
 */
public class Counter {
    private AtomicLong value = new AtomicLong(0);
    private AtomicBoolean error = new AtomicBoolean(false);
    private ReentrantLock reentrantLock = new ReentrantLock();


    /**
     * Возвращает флаг ошибки потоков
     * @return  Если флаг установлен в true, в неком потоке произошла
     */
    public boolean isError() {

        return error.get();
    }

    /**
     * Устанавливает флаг ошибки
     */
    public void setError() {
        try {
            reentrantLock.lock();
            error.set(true);
        } finally {
            reentrantLock.unlock();
        }

    }

    /**
     * Метод принимает значение для сложения с текущим результатом
     * и возвращает получившуюся сумму
     *
     * @param val
     * @return  Возвращает результат сложения val с value
     */
    public long addValue(long val) {
        try {
            reentrantLock.lock();
            return value.addAndGet(val);
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * Метод принимает значение для сложения с текущим результатом
     * и возвращает получившуюся сумму
     *
     * @param val
     * @return  Возвращает результат сложения val с value
     */
    public void addValueAndPrint(long val) {
        try {
            reentrantLock.lock();
            value.addAndGet(val);
            this.printer();
        } finally {
            reentrantLock.unlock();
        }

    }

    /**
     * Возвращает сумму
     * @return
     */
    public  long getValue() {
        try {
            reentrantLock.lock();
            return value.get();
        } finally {
            reentrantLock.unlock();
        }

    }

    /**
     * Вывод на консоль текущей суммы
     */
    public void printer() {
        System.out.println(value.get());
    }

}
