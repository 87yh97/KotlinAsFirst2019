package lesson8.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.Math.ulp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

class Tests {
    @Test
    @Tag("Example")
    fun pointDistance() {
        assertEquals(0.0, Point(0.0, 0.0).distance(Point(0.0, 0.0)), 1e-5)
        assertEquals(5.0, Point(3.0, 0.0).distance(Point(0.0, 4.0)), 1e-5)
        assertEquals(50.0, Point(0.0, -30.0).distance(Point(-40.0, 0.0)), 1e-5)
    }

    @Test
    @Tag("Example")
    fun halfPerimeter() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).halfPerimeter(), 1e-5)
        assertEquals(2.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).halfPerimeter(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleArea() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).area(), 1e-5)
        assertEquals(0.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).area(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleContains() {
        assertTrue(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(1.5, 1.5)))
        assertFalse(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(2.5, 2.5)))
    }

    @Test
    @Tag("Example")
    fun segmentEquals() {
        val first = Segment(Point(1.0, 2.0), Point(3.0, 4.0))
        val second = Segment(Point(1.0, 2.0), Point(3.0, 4.0))
        val third = Segment(Point(3.0, 4.0), Point(1.0, 2.0))
        assertEquals(first, second)
        assertEquals(second, third)
        assertEquals(third, first)
    }

    private fun approxEquals(expected: Line, actual: Line, delta: Double): Boolean =
        abs(expected.angle - actual.angle) <= delta && abs(expected.b - actual.b) <= delta

    private fun assertApproxEquals(expected: Line, actual: Line, delta: Double = ulp(10.0)) {
        assertTrue(approxEquals(expected, actual, delta))
    }

    private fun assertApproxNotEquals(expected: Line, actual: Line, delta: Double = ulp(10.0)) {
        assertFalse(approxEquals(expected, actual, delta))
    }

    @Test
    @Tag("Example")
    fun lineEquals() {
        run {
            val first = Line(Point(0.0, 0.0), 0.0)
            val second = Line(Point(3.0, 0.0), 0.0)
            val third = Line(Point(-5.0, 0.0), 0.0)
            val fourth = Line(Point(3.0, 1.0), 0.0)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
        run {
            val first = Line(Point(0.0, 0.0), PI / 2)
            val second = Line(Point(0.0, 3.0), PI / 2)
            val third = Line(Point(0.0, -5.0), PI / 2)
            val fourth = Line(Point(1.0, 3.0), PI / 2)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
        run {
            val first = Line(Point(0.0, 0.0), PI / 4)
            val second = Line(Point(3.0, 3.0), PI / 4)
            val third = Line(Point(-5.0, -5.0), PI / 4)
            val fourth = Line(Point(3.00001, 3.0), PI / 4)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
    }

    @Test
    @Tag("Example")
    fun triangleEquals() {
        val first = Triangle(Point(0.0, 0.0), Point(3.0, 0.0), Point(0.0, 4.0))
        val second = Triangle(Point(0.0, 0.0), Point(0.0, 4.0), Point(3.0, 0.0))
        val third = Triangle(Point(0.0, 4.0), Point(0.0, 0.0), Point(3.0, 0.0))
        val fourth = Triangle(Point(0.0, 4.0), Point(0.0, 3.0), Point(3.0, 0.0))
        assertEquals(first, second)
        assertEquals(second, third)
        assertEquals(third, first)
        assertNotEquals(fourth, first)
    }

    @Test
    @Tag("Easy")
    fun circleDistance() {
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(1.0, 0.0), 1.0)), 1e-5)
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(0.0, 2.0), 1.0)), 1e-5)
        assertEquals(1.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(-4.0, 0.0), 2.0)), 1e-5)
        assertEquals(2.0 * sqrt(2.0) - 2.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(2.0, 2.0), 1.0)), 1e-5)
    }

    @Test
    @Tag("Trivial")
    fun circleContains() {
        val center = Point(1.0, 2.0)
        assertTrue(Circle(center, 1.0).contains(center))
        assertFalse(Circle(center, 2.0).contains(Point(0.0, 0.0)))
        assertTrue(Circle(Point(0.0, 3.0), 5.01).contains(Point(-4.0, 0.0)))
    }

    /*
    @Test
    fun middlePoint() {
        assertEquals(Point(1.0, 1.0), Segment(Point(0.0, 0.0), Point(2.0, 2.0)).middlePoint())
        assertEquals(Point(-1.0, 1.0), Segment(Point(0.0, 0.0), Point(-2.0, 2.0)).middlePoint())
        assertEquals(Point(-1.0, -1.0), Segment(Point(0.0, 0.0), Point(-2.0, -2.0)).middlePoint())
        assertEquals(Point(1.0, -1.0), Segment(Point(0.0, 0.0), Point(2.0, -2.0)).middlePoint())
    }
    @Test
    fun halfLength() {
        assertEquals(sqrt(2.0),Segment(Point(0.0, 0.0), Point(2.0, 2.0)).halfLength())
        assertEquals(sqrt(2.0), Segment(Point(0.0, 0.0), Point(-2.0, 2.0)).halfLength())
        assertEquals(sqrt(2.0), Segment(Point(0.0, 0.0), Point(-2.0, -2.0)).halfLength())
        assertEquals(sqrt(2.0), Segment(Point(0.0, 0.0), Point(2.0, -2.0)).halfLength())
    }

     */

    @Test
    @Tag("Normal")
    fun diameter() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)

        /*assertEquals(Segment(p5, p6), diameter(
            Point(
                -5e-324,
                5e-324
            ),


            Point(
                0.22328471274451012,
                0.876818942683284
            ),


            Point(
                -632.0,
                0.0
            ),


            Point(
                0.054289899991735835,
                0.823220315151513
            ),

            Point(
                -5e-324,
                0.9395067626946407
            ),


            Point(
                2.220446049250313e-16,
                0.46705219564971545
            ),


            Point(
                0.9855948363135889,
                2.220446049250313e-16
            ),


            Point(
                -632.0,
                0.9404689237092594
            ),


            Point(
                0.0,
                -632.0
            ),


            Point(
                0.41460129676901014,
                0.0
            ),


            Point(
                -632.0,
                -632.0
            ),


            Point(
                -2.220446049250313e-16,
                0.4603144606645546
            ),


            Point(
                0.10899688318086542,
                0.8072323825071757
            ),


            Point(
                0.19621123278354047,
                0.7082641049194346
            ),


            Point(
                0.0,
                0.2578425078894211
            ),


            Point(
                2.220446049250313e-16,
                -632.0
            ),


            Point(
                2.220446049250313e-16,
                -5e-324
            ),


            Point(
                0.870960558432801,
                0.8295406475964209
            ),


            Point(
                0.5537641342351493,
                0.43666322564533466
            ),


            Point(
                0.7651112176580097,
                0.522353415303571
            ),


            Point(
                -632.0,
                0.7923764329312101
            ),


            Point(
                5e-324,
                -5e-324
            ),


            Point(
                -632.0,
                -2.220446049250313e-16
            ),


            Point(
                0.6610484948581298,
                0.42404982922718604
            ),


            Point(
                0.7668175380826024,
                0.8391648527559372
            ),


            Point(
                0.0,
                0.5533989073675755
            ),


            Point(
                2.220446049250313e-16,
                -2.220446049250313e-16
            ),


            Point(
                0.0,
                -2.220446049250313e-16
            ),


            Point(
                2.220446049250313e-16,
                0.5250143790271942
            ),


            Point(
                0.9950270046114894,
                0.434066747523281
            ),


            Point(
                0.13887369659314075,
                0.7429681451833257
            ),


            Point(
                -632.0,
                0.6498970547416798
            ),


            Point(
                0.9563679592415417,
                0.8460534073386871
            ),


            Point(
                0.9726231063304224,
                -632.0
            ),


            Point(
                -5e-324,
                -632.0
            ),


            Point(
                0.3126274116561599,
                0.3751596356639587
            ),


            Point(
                -632.0,
                -632.0
            ),


            Point(
                0.4992931649191372,
                0.42539092581824967
            ),


            Point(
                -632.0,
                -632.0
            ),


            Point(
                0.0,
                -632.0
            ),


            Point(
                2.220446049250313e-16,
                -632.0
            ),


            Point(
                0.49199336401553484,
                5e-324
            ),


            Point(
                0.5553512894854455,
                0.6877976401712046
            ),


            Point(
                0.0,
                0.7403212106623527
            ),


            Point(
                0.29160915551501254,
                0.515022765368762
            ),


            Point(
                0.0,
                -632.0
            ),


            Point(
                -632.0,
                0.0
            ),


            Point(
                0.9807380672256207,
                0.07133092104091299
            ),


            Point(
                0.9072159786402122,
                0.9751401860739777
            ),


            Point(
                5e-324,
                -632.0
            ),


            Point(
                0.8373701290743879,
                -5e-324
            ),


            Point(
                0.02587322747190679,
                0.0
            ),


            Point(
                5e-324,
                -632.0
            ),


            Point(
                0.0,
                5e-324
            ),


            Point(
                -2.220446049250313e-16,
                0.48937514139238336
            ),


            Point(
                -632.0,
                0.0
            ),


            Point(
                0.5765079418736104,
                0.23594485680194777
            ),


            Point(
                -632.0,
                0.09287849646648771
            ),


            Point(
                0.0,
                5e-324
            ),


            Point(
                -5e-324,
                0.8376386758453163
            ),


            Point(
                0.34479168989741216,
                0.0
            ),


            Point(
                0.8429983399545946,
                -5e-324
            ),


            Point(
                0.17506720220916538,
                -632.0
            ),


            Point(
                0.33476811650343075,
                2.220446049250313e-16
            ),


            Point(
                0.2021217839778151,
                -632.0
            ),


            Point(
                0.37255383521654695,
                0.6430371807331484
            ),


            Point(
                -632.0,
                -632.0
            ),


            Point(
                -632.0,
                -2.220446049250313e-16
            ),


            Point(
                5e-324,
                0.636951147174752
            ),


            Point(
                -2.220446049250313e-16,
                5e-324
            ),


            Point(
                -632.0,
                -632.0
            ),


            Point(
                0.02074718457680791,
                0.7382247328951504
            ),


                    Point (0.47984613346018135,
            0.8610953716862746
        ),


        Point(
            -632.0,
            0.591136962582167
        ),


        Point(
            5e-324,
            -632.0
        ),


        Point(
            -632.0,
            0.0
        ),


        Point(
            0.8679724932064943,
            0.5625829155517172
        ),


        Point(
            0.9240391729394966,
            0.22781980367461907
        ),


        Point(
            0.25451144965834993,
            0.24257633737441886
        ),


        Point(
            -2.220446049250313e-16,
            -2.220446049250313e-16
        ),


        Point(
            0.9084924206522688,
            -5e-324
        ),


        Point(
            0.15898231462041423,
            5e-324
        )))*/
        /*
        val myDiameter = diameter(
            Point(
                0.6989402250698986,
                -632.0
            ),
            Point(
                -632.0,
                0.10018222401772148
            ),
            Point(
                -2.220446049250313e-16,
                0.9157384116511886
            ),
            Point(
                0.4092747525141712,
                -632.0
            ),
            Point(
                0.20236354441288296,
                0.21644379721526297
            ),
            Point(
                0.0,
                0.018825683675376848
            ),
            Point(
                0.35774123124394663,
                -632.0
            ),
            Point(
                0.7200669359988648,
                -632.0
            ),
            Point(
                0.06759609554387591,
                0.0
            ),
            Point(
                2.220446049250313e-16,
                -2.220446049250313e-16
            ),
            Point(
                -5e-324,
                -632.0
            ),
            Point(
                0.6823777932902575,
                0.85113338440918
            ),
            Point(
                -632.0,
                -2.220446049250313e-16
            )
        )
        */
        //assertEquals(p1, myDiameter)


        assertEquals(Segment(p5, p6), diameter(p1, p2, p3, p4, p5, p6))
        assertEquals(Segment(p4, p6), diameter(p1, p2, p3, p4, p6))
        assertEquals(Segment(p3, p4), diameter(p1, p2, p3, p4))
        assertEquals(Segment(p2, p4), diameter(p1, p2, p4))
        assertEquals(Segment(p1, p4), diameter(p1, p4))
    }

    @Test
    @Tag("Easy")
    fun circleByDiameter() {
        assertEquals(Circle(Point(0.0, 1.0), 1.0), circleByDiameter(Segment(Point(0.0, 0.0), Point(0.0, 2.0))))
        assertEquals(Circle(Point(2.0, 1.5), 2.5), circleByDiameter(Segment(Point(4.0, 0.0), Point(0.0, 3.0))))
    }

    @Test
    @Tag("Normal")
    fun crossPoint() {
        assertTrue(
            Point(2.0, 3.0).distance(
                Line(Point(2.0, 0.0), PI / 2).crossPoint(
                    Line(Point(0.0, 3.0), 0.0)
                )
            ) < 1e-5
        )

        assertTrue(
            Point(2.0, 2.0).distance(
                Line(Point(0.0, 0.0), PI / 4).crossPoint(
                    Line(Point(0.0, 4.0), 3 * PI / 4)
                )
            ) < 1e-5
        )
        val p = Point(1.0, 3.0)
        assertTrue(p.distance(Line(p, 1.0).crossPoint(Line(p, 2.0))) < 1e-5)


    }

    @Test
    @Tag("Normal")
    fun lineBySegment() {
        assertApproxEquals(Line(Point(0.0, 0.0), 0.0), lineBySegment(Segment(Point(0.0, 0.0), Point(7.0, 0.0))))
        assertApproxEquals(Line(Point(0.0, 0.0), PI / 2), lineBySegment(Segment(Point(0.0, 0.0), Point(0.0, 8.0))))
        assertApproxEquals(Line(Point(1.0, 1.0), PI / 4), lineBySegment(Segment(Point(1.0, 1.0), Point(3.0, 3.0))))
        assertApproxEquals(Line(Point(0.0, 0.0), 3 * PI / 4), lineBySegment(Segment(Point(0.0, 0.0), Point(-3.0, 3.0))))
        assertApproxEquals(
            Line(Point(-632.0, 5e-324), 0.0),
            lineBySegment(Segment(Point(-632.0, 5e-324), Point(0.7641960674013129, -2.220446049250313e-16)))
        )
    }

    @Test
    @Tag("Normal")
    fun lineByPoint() {
        assertApproxEquals(Line(Point(0.0, 0.0), PI / 2), lineByPoints(Point(0.0, 0.0), Point(0.0, 2.0)))
        assertApproxEquals(Line(Point(1.0, 1.0), PI / 4), lineByPoints(Point(1.0, 1.0), Point(3.0, 3.0)))
    }

    @Test
    @Tag("Hard")
    fun bisectorByPoints() {
        assertApproxEquals(Line(Point(2.0, 0.0), PI / 2), bisectorByPoints(Point(0.0, 0.0), Point(4.0, 0.0)))
        assertApproxEquals(Line(Point(1.0, 2.0), 0.0), bisectorByPoints(Point(1.0, 5.0), Point(1.0, -1.0)))
        assertApproxEquals(
            Line(Point(-632.0, 0.3043831306560855), 0.0),
            bisectorByPoints(Point(-632.0, 0.21493967593362562), Point(-632.0, 0.3938265853785454))
        )
    }

    @Test
    @Tag("Normal")
    fun findNearestCirclePair() {
        val c1 = Circle(Point(0.0, 0.0), 1.0)
        val c2 = Circle(Point(3.0, 0.0), 5.0)
        val c3 = Circle(Point(-5.0, 0.0), 2.0)
        val c4 = Circle(Point(0.0, 7.0), 3.0)
        val c5 = Circle(Point(0.0, -6.0), 4.0)
        assertEquals(Pair(c1, c5), findNearestCirclePair(c1, c3, c4, c5))
        assertEquals(Pair(c2, c4), findNearestCirclePair(c2, c4, c5))
        assertEquals(Pair(c1, c2), findNearestCirclePair(c1, c2, c4, c5))
    }

    @Test
    @Tag("Hard")
    fun circleByThreePoints() {

        val result = circleByThreePoints(Point(5.0, 0.0), Point(3.0, 4.0), Point(0.0, -5.0))
        assertTrue(result.center.distance(Point(0.0, 0.0)) < 1e-5)
        assertEquals(5.0, result.radius, 1e-5)

        val result2 = circleByThreePoints(

            Point(-2.220446049250313e-16, -632.0), Point(-632.0, 5e-324), Point(0.5823739647171357, 5e-324)
        )
        assertTrue(result2.center.distance(Point(-315.70881301764143, -315.70881301764143)) < 1e-5)
        assertEquals(446.89167544240223, result2.radius, 1e-5)


        val result3 = circleByThreePoints(
            Point(-632.0, -2.220446049250313e-16),
            Point(0.9952755402712069, -632.0),
            Point(0.37191669765902613, 5e-324)
        )
        assertTrue(result3.center.distance(Point(-315.8140416511705, -316.31217025501564)) < 1e-5)
        assertEquals(447.24372472781073, result3.radius, 1e-5)

    }


    @Test
    @Tag("Impossible")
    fun minContainingCircle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)
        val result = minContainingCircle(p1, p2, p3, p4, p5, p6)
        assertEquals(4.0, result.radius, 0.02)
        for (p in listOf(p1, p2, p3, p4, p5, p6)) {
            assertTrue(result.contains(p))
        }
    }
}