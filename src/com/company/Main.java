package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Задача 1. Основы процедурного программирования
 * Вариант 1
 *
 * Дано: String s - список всех камней, которые являются драгоценными (не содержит повторений,
 * буквы в разном регистре считаются различными) и String j - камни, которые накопал рабочий
 * (может содержать повторения, а также не драгоценные камни). Найти количество драгоценных
 * камней, которые нашел рабочий. Пример: s=aA , j=aAAbbbbb , ответ: 3.
 *
 * Данильченко Роман, 9 гр.
 */

public class Main {

    /**
     * Проверяет, есть ли в передаваемой строке повторяющиеся символы
     *
     * @param   strToCheck  строка, которая проверяется на наличие повторяющихся символов
     * @return              true, если в строке есть повторяющиеся символы, false в противном случае
     */
    public static boolean ContainsDuplicateSymbols(String strToCheck) {
        for (int i = 0, strLength = strToCheck.length(); i < strLength - 1; ++i) // Берем символы по порядку...
            for (int j = i + 1; j < strLength; ++j) // ...и сравниваем со всеми остальными
                if (strToCheck.charAt(i) == strToCheck.charAt(j))
                    return true;

        return false;
    }

    /**
     * Подсчитывает количество драгоценных камней в dugUpStones,
     *      сравнивая все камни со списком драгоценных камней preciousStones
     *
     * @param   preciousStones  строка, содержащая список драгоценных камней в виде символов без повторений (1 символ = 1 камень)
     * @param   dugUpStones     строка, содержащая все камни, выкопанные рабочим, в виде символов (1 символ = 1 камень)
     * @return                  целое число драгоценных камней, выкопанных рабочим
     */
    public static int CountPreciousStones(String preciousStones, String dugUpStones) {
        int count = 0;

        for (int i = 0, dugUpStonesCount = dugUpStones.length(); i < dugUpStonesCount; ++i) // Берём накопанные камни по порядку...
            for (int j = 0, preciousStonesCount = preciousStones.length(); j < preciousStonesCount; ++j) // ...и сравниваем со всеми драгоценными
                if (dugUpStones.charAt(i) == preciousStones.charAt(j))
                    ++count;

        return count;
    }

    /**
     * Реализует ввод строки с выводом перед этим, если требуется, сообщения msg,
     *      а также проверку введенной строки на валидность
     *      (строка должна быть непустая, и в зависимости от allowRepeatSymbols может содержать повторяющиеся символы)
     *
     * @param   msg                 сообщение, которое выводится пользователю перед считыванием строки
     * @param   allowRepeatSymbols  флаг, указывающий на то, может ли вводимая строка содержать повторяющиеся символы
     *                                  (true, если может, false в противном случае)
     * @return                      введённая пользователем непустая строка, удовлетворяющая критерию allowRepeatSymbols
     * @throws  java.io.IOException обработка исключения ввода-вывода
     */
    public static String InputString(String msg, boolean allowRepeatSymbols)
            throws java.io.IOException {
        BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
        String resString;

        if (msg != null && !msg.isEmpty())
            System.out.println(msg);

        resString = bR.readLine();
        while (resString.isEmpty() || (!allowRepeatSymbols && ContainsDuplicateSymbols(resString))) {
            System.out.println("Введена недопустимая строка, повторите ввод");
            resString = bR.readLine();
        }

        return resString;
    }

    public static void main(String[] args)
        throws java.io.IOException {
        String preciousStones, dugUpStones;

        preciousStones = InputString("Введите полный список драгоценных камней без повторений",
                                     false);
        dugUpStones = InputString("Введите список драгоценных камней, выкопанных рабочим",
                                  true);

        System.out.println("Число драгоценных камней, найденных рабочим: " + CountPreciousStones(preciousStones, dugUpStones));
    }
}
