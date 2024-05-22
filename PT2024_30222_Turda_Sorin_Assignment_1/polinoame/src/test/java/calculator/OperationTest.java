package calculator;

import calculator.logic.Operation;
import calculator.model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {
    Operation op = new Operation();

    @Test
    public void addTest1() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> p2 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        p1.addCoefficient(3, 1);
        p1.addCoefficient(2, 3);
        p1.addCoefficient(1, -8);
        p1.addCoefficient(0, -10);
        p2.addCoefficient(2, -5);
        p2.addCoefficient(0, 2);
        res.addCoefficient(3, 1);
        res.addCoefficient(2, -2);
        res.addCoefficient(1, -8);
        res.addCoefficient(0, -8);
        assertEquals(op.add(p1, p2).toString(), res.toString());
    }

    @Test
    public void addTest2() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> p2 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        p1.addCoefficient(1, 1);
        p1.addCoefficient(1, 1);
        p1.addCoefficient(1, 1);
        p2.addCoefficient(1, 1);
        p2.addCoefficient(1, 1);
        p2.addCoefficient(1, 1);
        res.addCoefficient(1, 6);
        assertEquals(op.add(p1, p2).toString(), res.toString());
    }

    @Test
    public void substractTest1() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> p2 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        //(1263 x^123 - 128 x^23 - 12 x^2 + 1) - (29 x^123 + 10 x^23 + x^12 + x^11)
        p1.addCoefficient(123, 1263);
        p1.addCoefficient(23, -128);
        p1.addCoefficient(2, -12);
        p1.addCoefficient(0, 1);
        p2.addCoefficient(123, 29);
        p2.addCoefficient(23, 10);
        p2.addCoefficient(12, 1);
        p2.addCoefficient(11, 1);
        //1234 x^123 - 138 x^23 - x^12 - x^11 - 12 x^2 + 1
        res.addCoefficient(123, 1234);
        res.addCoefficient(23, -138);
        res.addCoefficient(12, -1);
        res.addCoefficient(11, -1);
        res.addCoefficient(2, -12);
        res.addCoefficient(0, 1);
        assertEquals(op.substract(p1, p2).toString(), res.toString());
    }

    @Test
    public void derivateTest1() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        //-78 x^99 + 32 x^87 + 11 x^75 - 92 x^63 + 54 x^51 - 6 x^39 + 84 x^27 - 23 x^15 + 98 x^3 + 41
        p1.addCoefficient(99, -78);
        p1.addCoefficient(87, 32);
        p1.addCoefficient(75, 11);
        p1.addCoefficient(63, -92);
        p1.addCoefficient(51, 54);
        p1.addCoefficient(39, -6);
        p1.addCoefficient(27, 84);
        p1.addCoefficient(15, -23);
        p1.addCoefficient(3, 98);
        p1.addCoefficient(0, 41);
        //-7722 x^98 + 2784 x^86 + 825 x^74 - 5796 x^62 + 2754 x^50 - 234 x^38 + 2268 x^26 - 345 x^14 + 294 x^2
        res.addCoefficient(98, -7722);
        res.addCoefficient(86, 2784);
        res.addCoefficient(74, 825);
        res.addCoefficient(62, -5796);
        res.addCoefficient(50, 2754);
        res.addCoefficient(38, -234);
        res.addCoefficient(26, 2268);
        res.addCoefficient(14, -345);
        res.addCoefficient(2, 294);
        assertEquals(op.derivate(p1).toString(), res.toString());
    }

    @Test
    public void multiplyTest1() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> p2 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        //x^5+10x^12+x^5-12x^3-99x^2-5-15
        p1.addCoefficient(5, 1);
        p1.addCoefficient(12, 10);
        p1.addCoefficient(5, 1);
        p1.addCoefficient(3, -12);
        p1.addCoefficient(2, -99);
        p1.addCoefficient(0, -5);
        p1.addCoefficient(0, -15);
        //x^5-2x^5-2x^12-16x^8+86x^3-53+17x
        p2.addCoefficient(5, 1);
        p2.addCoefficient(5, -2);
        p2.addCoefficient(12, -2);
        p2.addCoefficient(8, -16);
        p2.addCoefficient(3, 86);
        p2.addCoefficient(0, -53);
        p2.addCoefficient(1, 17);
        //-20 x^24 - 160 x^20 - 14 x^17 + 884 x^15 + 198 x^14 + 138 x^13 - 490 x^12 + 192 x^11 + 1582 x^10 + 504 x^8 + 99 x^7 - 998 x^6 - 8600 x^5 - 204 x^4 - 2767 x^3 + 5247 x^2 - 340 x + 1060
        res.addCoefficient(20, -160);
        res.addCoefficient(24, -20);
        res.addCoefficient(17, -14);
        res.addCoefficient(14, 198);
        res.addCoefficient(15, 884);
        res.addCoefficient(13, 138);
        res.addCoefficient(12, -490);
        res.addCoefficient(11, 192);
        res.addCoefficient(10, 1582);
        res.addCoefficient(8, 504);
        res.addCoefficient(7, 99);
        res.addCoefficient(6, -998);
        res.addCoefficient(5, -8600);
        res.addCoefficient(4, -204);
        res.addCoefficient(3, -2767);
        res.addCoefficient(2, 5247);
        res.addCoefficient(1, -340);
        res.addCoefficient(0, 1060);
        assertEquals(op.multiply(p1, p2).toString(), res.toString());
    }

    @Test
    public void multilpyTest2() {
        Polynomial<Integer> p1 = new Polynomial<>();
        Polynomial<Integer> p2 = new Polynomial<>();
        Polynomial<Integer> res = new Polynomial<>();
        p1.addCoefficient(2,1);
        p1.addCoefficient(1,2);
        p1.addCoefficient(0,1);
        p2.addCoefficient(1,1);
        p2.addCoefficient(0,1);
        res.addCoefficient(3,1);
        res.addCoefficient(0,1);
        res.addCoefficient(2,3);
        res.addCoefficient(1,3);
        assertEquals(op.multiply(p1, p2).toString(), res.toString());
    }

    @Test
    public void integrateTest() {
        Polynomial<Integer> p = new Polynomial<>();
        Polynomial<Double> res = new Polynomial<>();
        //7x^9 - 2x^7 + 5x^5 - 8x^4 + 1x^3 + 4x^2 - 3x + 6
        p.addCoefficient(9, 7);
        p.addCoefficient(7, -2);
        p.addCoefficient(5, 5);
        p.addCoefficient(4, -8);
        p.addCoefficient(3, 1);
        p.addCoefficient(2, 4);
        p.addCoefficient(1, -3);
        p.addCoefficient(0, 6);
        res.addCoefficient(10, 0.7);
        res.addCoefficient(8, -0.25);
        res.addCoefficient(6, 0.833333333);
        res.addCoefficient(5, -1.6);
        res.addCoefficient(4, 0.25);
        res.addCoefficient(3, 1.333333333);
        res.addCoefficient(2, -1.5);
        res.addCoefficient(1, 6.0);
        assertEquals(res.toString(), op.integrate(p).toString());
    }
}
