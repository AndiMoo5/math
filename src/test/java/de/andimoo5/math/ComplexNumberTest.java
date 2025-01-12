package de.andimoo5.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ComplexNumberTest {

    private ComplexNumber z;

    @BeforeEach
    void setUp() {
        z = new ComplexNumber();
    }

    @Test
    void set() {
        z.set(new ComplexNumber(12, 3));
        assertEquals(12, z.getRe());
        assertEquals(3, z.getIm());
        z.set(3.35, 4.5);
        assertEquals(3.35, z.getRe());
        assertEquals(4.5, z.getIm());
    }

    @Test
    void getRe() {
        z.setRe(8);
        assertEquals(8, z.getRe());
        z.set(new ComplexNumber(12, 3));
        assertEquals(12, z.getRe());
    }

    @Test
    void getIm() {
        z.setIm(8);
        assertEquals(8, z.getIm());
        z.set(new ComplexNumber(12, 3));
        assertEquals(3, z.getIm());
    }

    @Test
    void testToString() {
        assertEquals("0.0", z.toString());
        z.set(new ComplexNumber(5));
        assertEquals("5.0", z.toString());
        z.set(0, -5);
        assertEquals("-5.0i", z.toString());
        z.set(5, -3);
        assertEquals("5.0-3.0i", z.toString());
        z.set(-5, 3);
        assertEquals("-5.0+3.0i", z.toString());
    }

    @Test
    void testEquals() {
        z.set(5,4);
        assertTrue(z.equals(new ComplexNumber(5, 4)));
    }

    @Test
    void intValue() {
        z.set(5.6, 3.45);
        assertEquals(5, z.intValue());
        z.setRe(5.4);
        assertEquals(5, z.intValue());
    }

    @Test
    void longValue() {
        z.set(5.8, 3.45);
        assertEquals(5, z.longValue());
        z.setRe(5.4);
        assertEquals(5, z.longValue());
    }

    @Test
    void floatValue() {
        z.set(5.6, 3.45);
        assertEquals(5.6f, z.floatValue());
    }

    @Test
    void doubleValue() {
        z.set(5.6, 3.45);
        assertEquals(5.6, z.doubleValue());
    }

    @Test
    void compareTo() {
        assertEquals(0, z.compareTo(new ComplexNumber()));
        z.set(5,5);
        assertEquals(-1, z.compareTo(new ComplexNumber(8,8)));
        assertEquals(1, z.compareTo(new ComplexNumber(4,4)));
    }

    @Test
    void sum() {
        z.set(5,2);
        ComplexNumber z2 = new ComplexNumber(5,2);
        assertEquals(new ComplexNumber(10,4), ComplexNumber.sum(z, z2));
        z2.set(-5,-2);
        assertEquals(new ComplexNumber(), ComplexNumber.sum(z, z2));

    }

    @Test
    void diff() {
        z.set(5,2);
        ComplexNumber z2 = new ComplexNumber(5,2);
        assertEquals(new ComplexNumber(), ComplexNumber.diff(z, z2));
        z2.set(-5,-2);
        assertEquals(new ComplexNumber(10,4), ComplexNumber.diff(z, z2));
    }

    @Test
    void prod() {
        z.set(5,2);
        ComplexNumber z2 = new ComplexNumber(5,2);
        assertEquals(new ComplexNumber(21,20), ComplexNumber.prod(z, z2));
    }

    @Test
    void division() {
        z.set(4,3);
        ComplexNumber z2 = new ComplexNumber(2,2);
        assertEquals(new ComplexNumber(1.75,-0.25), ComplexNumber.division(z, z2));
    }

    @Test
    void conjugate() {
        z.set(3,4);
        assertEquals(new ComplexNumber(3,-4), ComplexNumber.conjugate(z));
    }

    @Test
    void modulus() {
        z.set(3,4);
        assertEquals(5.0, ComplexNumber.modulus(z));
    }

    @Test
    void argument() {
        z.set(1, 1);
        assertEquals(Math.PI / 4, ComplexNumber.argument(z));
    }

    @Test
    void exp() {
        z.set(1, 1);
        assertEquals(new ComplexNumber(1.4686939399158851, 2.2873552871788423), ComplexNumber.exp(z));
    }

    @Test
    void sin() {
        z.set(1, 2);
        assertEquals(new ComplexNumber(3.165778513216168, 2.0327230070196656), ComplexNumber.sin(z));
    }

    @Test
    void cos() {
        z.set(1, 2);
        assertEquals(new ComplexNumber(2.0327230070196656, -3.0518977991518), ComplexNumber.cos(z));
    }
}