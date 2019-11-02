@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var k = n
    var a = 0
    if (n == 0) return 1
    while (k != 0) {
        a ++
        k /= 10
    }
    return a
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1 || n == 2) return 1
    var k = 3
    var num2 = 1
    var num3 = 2
    while (k != n) {
        val num1 = num3
        num3 += num2
        num2 = num1
        k++
    }
    return num3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = 1
    if (tSCD(m, n)) return m * n
    val minOfNum = min(m, n)
    val maxOfNum = max(m, n)
    while ((maxOfNum * k) % minOfNum != 0) {
        k++
    }
    return maxOfNum * k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k = 2
    while ((n % k != 0) && (k < (n / 2))) {
        k++
    }
    return if (k >= (n / 2)) n else k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = tSCD(m, n)

fun tSCD(m: Int, n: Int): Boolean {
    var remainder: Int
    var minOfNum = min(m, n)
    var maxOfNum = max(m, n)
    while (maxOfNum % minOfNum != 0) {
        remainder = maxOfNum % minOfNum
        maxOfNum = minOfNum
        minOfNum = remainder
    }
    return minOfNum == 1

}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val longM = m.toLong()
    val longN = n.toLong()
    var midSquare = sqrt(m.toDouble()).toLong()
    while (midSquare * midSquare <= longN) {
        if (midSquare * midSquare >= longM) {
            return true
        } else midSquare++
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var y = x
    var count = 0
    while (y != 1) {
        if (y % 2 == 0) y /= 2
        else y = y * 3 + 1
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val y = x % (2 * PI)
    var factDiv = 1.0
    var rowSum = 0.0
    var rowMember = y
    while (abs(rowMember) > eps) {
        rowSum += rowMember
        rowMember *= y.pow(2)
        rowMember /= ((factDiv + 1) * (factDiv + 2))
        factDiv += 2
        rowMember *= -1.0
    }
    return rowSum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val y = x % (2 * PI)
    var factDiv = 0.0
    var rowSum = 0.0
    var rowMember = 1.0
    while (abs(rowMember) > eps) {
        rowSum += rowMember
        rowMember *= y.pow(2)
        rowMember /= ((factDiv + 1) * (factDiv + 2))
        factDiv += 2
        rowMember *= -1.0
    }
    return rowSum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num = n
    var rNum = 0L
    val digitCount = digitNumber(num)
    num = n
    for (i in 1..digitCount) {
        val digit = num % 10
        num /= 10
        rNum += digit
        rNum *= 10
    }
    rNum /= 10
    return rNum.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n / 10 == 0) return false
    var digit = n % 10
    var num = n / 10
    while (digit == (num % 10)) {
        digit = num % 10
        num /= 10
        if (num == 0) return false
    }
    return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 1_4_9_16_25_36_49_64_81_100_121_144_169_196_225_...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var sqrNum = 0
    var digitCounter = 0
    while (digitCounter < n) {
        sqrNum++
        digitCounter += digitNumber(sqrNum * sqrNum)
    }
    sqrNum *= sqrNum
    while (digitCounter != n) {
        sqrNum /= 10
        digitCounter--
    }
    return sqrNum % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var fibNum = 0
    var digitCounter = 0
    while (digitCounter < n) {
        fibNum++
        digitCounter += digitNumber(fib(fibNum))
    }
    var fib = fib(fibNum)
    while (digitCounter != n) {
        fib /= 10
        digitCounter--
    }
    return fib % 10
}