@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import java.lang.IllegalArgumentException
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt((x - other.x)*(x - other.x) + (y - other.y)*(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val distance = center.distance(other.center)
        val radiusSum = radius + other.radius
        if (distance <= radiusSum) return 0.0
        return distance - radiusSum
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean {
        return center.distance(p) <= radius
    }
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    /*
    if (points.size < 2) throw(IllegalArgumentException())
    val thePeakPoints = mutableListOf(
        points[0], // Leftmost point
        points[0], // Rightmost point
        points[0], // Highest point
        points[0] // Lowest point
    )
    val thePeakCoords = mutableListOf(points[0].x, points[0].x, points[0].y, points[0].y)
    //                                                       Leftmost crd Rightmost crd Highest crd  Lowest crd
    for (point in points) {

        if (point.x <= thePeakCoords[0]) {
            thePeakCoords[0] = point.x
            thePeakPoints[0] = point
        }
        if (point.x >= thePeakCoords[1]) {
            thePeakCoords[1] = point.x
            thePeakPoints[1] = point
        }
        if (point.y >= thePeakCoords[2]) {
            thePeakCoords[2] = point.y
            thePeakPoints[2] = point
        }
        if (point.y <= thePeakCoords[3]) {
            thePeakCoords[3] = point.y
            thePeakPoints[3] = point
        }
    }
    var maxDistance = -1.0
    var pairWithMaxDistance = Pair(points[0], points[0])
    for (i in 0 .. 3) {
        for (j in 0 .. 3) {
            val currentDistance = thePeakPoints[i].distance(thePeakPoints[j])
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance
                pairWithMaxDistance = Pair(thePeakPoints[i], thePeakPoints[j])
            }
        }
    }
     */
    /*
    var maxDistance = -1.0
    var pairWithMaxDistance = Pair(points[0], points[0])
    for (point in points) {
        for (point2 in points) {
            val currentDistance = point.distance(point2)
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance
                pairWithMaxDistance = Pair(point, point2)
            }
        }
    }

     */
    /*
    if (points.size < 2) throw(IllegalArgumentException())
    val thePeakPoints = mutableListOf(
        points[0], // Левая нижняя 0
        points[0], // Левая верхняя 1
        points[0], // Правая верхняя 2
        points[0] // Правая нижняя 3
    )
    //val thePeakCoords = mutableListOf(points[0].x, points[0].x, points[0].y, points[0].y)
    //                                                       Leftmost crd Rightmost crd Highest crd  Lowest crd
    for (point in points) {

        if (point.x <= thePeakPoints[0].x || point.x <= thePeakPoints[1].x) { //Если левее или равен любой из левых
            if (point.y < thePeakPoints[0].y) thePeakPoints[0] = point // Если ниже левой нижней
            if (point.y > thePeakPoints[1].y) thePeakPoints[1] = point // Если выше левой верхней
        }

        if (point.x >= thePeakPoints[2].x || point.x >= thePeakPoints[3].x) { //Если правее или равен правой
            if (point.y < thePeakPoints[3].y) thePeakPoints[3] = point // Если ниже правой нижней
            if (point.y > thePeakPoints[2].y) thePeakPoints[2] = point // Если выше правой нижней
        }

        if (point.y >= thePeakPoints[1].y || point.y >= thePeakPoints[2].y) {
            if (point.x < thePeakPoints[1].x) thePeakPoints[1] = point
            if (point.x > thePeakPoints[2].x) thePeakPoints[2] = point
        }

        if (point.y <= thePeakPoints[0].y || point.y <= thePeakPoints[3].y) {
            if (point.x < thePeakPoints[0].x) thePeakPoints[0] = point
            if (point.x > thePeakPoints[2].x) thePeakPoints[2] = point
        }

    }*/
    var maxDistance = -1.0
    var pairWithMaxDistance = Pair(points[0], points[0])
    for (i in points.indices) {
        for (j in i until points.size) {
            val currentDistance = points[i].distance(points[j])
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance
                pairWithMaxDistance = Pair(points[i], points[j])
            }
        }
    }
    return Segment(pairWithMaxDistance.first, pairWithMaxDistance.second)
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle = TODO()

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point = TODO()

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = TODO()

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = TODO()

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line = TODO()

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> = TODO()

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle = TODO()

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle = TODO()

