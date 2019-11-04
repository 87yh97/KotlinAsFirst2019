@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    return if (v.isEmpty()) 0.0
    else {
        var sqrt = 0.0
        for (i in v.indices) sqrt += v[i].pow(2)
        sqrt(sqrt)
    }
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isEmpty()) 0.0
    else {
        var average = 0.0
        for (i in list.indices) average += list[i]
        average / list.size
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    return if (list.isEmpty()) list
    else {
        val average = mean(list)
        for (i in list.indices) {
            list[i] -= average
        }
        list
    }
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    return if (a.isEmpty() || b.isEmpty()) 0
    else {
        var multiply = 0
        for (i in a.indices) {
            multiply += a[i] * b[i]
        }
        multiply
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    return if (p.isEmpty()) 0
    else {
        var value = 0
        var tempX = 1
        for (i in p) {
            value += tempX * i
            tempX *= x
        }
        value
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    return if (list.isEmpty()) list
    else {
        var sum = list[0]
        for (i in 1 until list.size) {
            list[i] += sum
            sum = list[i]
        }
        list
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    var number = n
    var minDivisorN: Int
    return if (isPrime(n)) listOf(n)
    else {
        while (!isPrime(number)) {
            minDivisorN = minDivisor(number)
            list.add(minDivisorN)
            number /= minDivisorN
        }
        list.add(number)
        list
    }
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String =
    factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    if (number == 0) return listOf(0)
    while (number > 0) {
        list.add(number % base)
        number /= base
    }
    val listSize = list.size
    var temp: Int
    for (i in 0 until listSize / 2) {
        temp = list[i]
        list[i] = list[(listSize - 1) - i]
        list[(listSize - 1) - i] = temp
    }
    return list

}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base) as MutableList<Int>
    var str = ""
    val anyCharYouLike = "abcdefghijklmnopqrstuvwxyz"
    for (i in 0 until list.size) {
        str += if (list[i] > 9) anyCharYouLike[list[i] - 10]
        else list[i].toString()
    }
    return str
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    var baseInPow = 1
    for (i in 0 until digits.size) {
        number += digits[digits.size - 1 - i] * baseInPow
        baseInPow *= base
    }
    return number
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var number =
        0L // Long нужно, так как при 10-ти значном аргументе str number переполняется при последнем домножении number на base в цикле for
    for (i in 0 until str.length) {
        number += if (str[i] > '9') {
            str[i] - 'W'
        } else {
            (str[i] - '0')
        }
        number *= base
    }
    number /= base
    return number.toInt()
}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var numberStr = ""
    var number = n
    val list = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val listOfDecEquivalentOfRanks = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val listOfStringEquivalentOfRanks = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    for (i in 0..12) {
        list[i] = anyRankYouLike(i, number, listOfDecEquivalentOfRanks)
        number -= list[i] * listOfDecEquivalentOfRanks[i]
        for (j in 0 until list[i]) numberStr += listOfStringEquivalentOfRanks[i]
    }
    return numberStr
}

fun anyRankYouLike(rank: Int, initialNumber: Int, listOfDecEquivalentOfRanks: List<Int>): Int {
    var numberOfDigitsOfRank = 0
    var number = initialNumber
    while (number > (listOfDecEquivalentOfRanks[rank] - 1)) {
        numberOfDigitsOfRank++
        number -= listOfDecEquivalentOfRanks[rank]
    }
    return numberOfDigitsOfRank
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var number = n
    var wholeNumberString = ""
    val numberOfDigits = mutableListOf(0, 0, 0, 0, 0, 0)
    for (i in 0 until digitNumber(n)) {
        numberOfDigits[5 - i] = number % 10
        number /= 10
    }

    val wordsForHundredsOfAnything =
        listOf(
            "",
            " сто",
            " двести",
            " триста",
            " четыреста",
            " пятьсот",
            " шестьсот",
            " семьсот",
            " восемьсот",
            " девятьсот"
        )
    val wordsForTensOfAnything =
        listOf(
            "",
            " десять",
            " двадцать",
            " тридцать",
            " сорок",
            " пятьдесят",
            " шестьдесят",
            " семьдесят",
            " восемьдесят",
            " девяносто"
        )
    val wordsForTeensOfAnything =
        listOf(
            "",
            " одиннадцать",
            " двенадцать",
            " тринадцать",
            " четырнадцать",
            " пятнадцать",
            " шестнадцать",
            " семнадцать",
            " восемнадцать",
            " девятнадцать"
        )
    val wordsForUnitsOfThousands =
        listOf("", " одна", " две", " три", " четыре", " пять", " шесть", " семь", " восемь", " девять")
    val wordsForUnits =
        listOf("", " один", " два", " три", " четыре", " пять", " шесть", " семь", " восемь", " девять")

    wholeNumberString += wordsForHundredsOfAnything[numberOfDigits[0]]

    wholeNumberString += if (numberOfDigits[1] == 1) {
        if (numberOfDigits[2] == 0) " десять"
        else wordsForTeensOfAnything[numberOfDigits[2]]
    } else {
        wordsForTensOfAnything[numberOfDigits[1]]
    }

    if (numberOfDigits[1] != 1) wholeNumberString += wordsForUnitsOfThousands[numberOfDigits[2]]

    if (numberOfDigits[1] == 1 ||
        (numberOfDigits[2] == 0 && (numberOfDigits[0] != 0 || numberOfDigits[1] != 0)) ||
        numberOfDigits[2] in 5..9
    ) {
        wholeNumberString += " тысяч"
    } else {
        if (numberOfDigits[2] == 1) wholeNumberString += " тысяча"
        if (numberOfDigits[2] in 2..4) wholeNumberString += " тысячи"
    }

    wholeNumberString += wordsForHundredsOfAnything[numberOfDigits[3]]

    wholeNumberString += if (numberOfDigits[4] == 1) {
        if (numberOfDigits[5] == 0) " десять"
        else wordsForTeensOfAnything[numberOfDigits[5]]
    } else {
        wordsForTensOfAnything[numberOfDigits[4]]
    }

    if (numberOfDigits[4] != 1) wholeNumberString += wordsForUnits[numberOfDigits[5]]

    return wholeNumberString.substring(1, wholeNumberString.length)
}