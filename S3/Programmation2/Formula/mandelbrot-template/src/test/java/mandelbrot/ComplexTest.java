package mandelbrot;
import static net.obvj.junit.utils.matchers.AdvancedMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ComplexTest {
    private Complex onePlusI;
    private Complex minusI;
    private Complex minusOne;
    private Complex oneMinusI;
    private Complex twoI;
    private Complex two;
    private Complex one;
    private Complex i;
    private Complex zero;

    @BeforeEach
    void initializeTestValues(){
        onePlusI = new Complex(1,1);
        minusI = new Complex(0,-1);
        minusOne = new Complex(-1,0);
        oneMinusI = new Complex(1, -1);
        twoI = new Complex(0,2);
        two = new Complex(2,0);
        one = new Complex(1,0);
        i = new Complex(0,1);
        zero = new Complex(0,0);
    }

    @Test
    void testEquals(){
        assertThat(onePlusI, is(equalTo(onePlusI)));
        assertThat(onePlusI, is(equalTo(new Complex(1, 1))));
        assertThat(two, is(not(equalTo(twoI))));
    }

    @Test
    void testGetReal(){
        assertThat(two.getReal(), is(closeTo(2., Helpers.EPSILON)));
        assertThat(onePlusI.getReal(), is(closeTo(1., Helpers.EPSILON)));
        assertThat(oneMinusI.getReal(), is(closeTo(1., Helpers.EPSILON)));
    }

    @Test
    void testGetImaginary(){
        assertThat(two.getImaginary(), is(closeTo(0., Helpers.EPSILON)));
        assertThat(onePlusI.getImaginary(), is(closeTo(1., Helpers.EPSILON)));
        assertThat(oneMinusI.getImaginary(), is(closeTo(-1., Helpers.EPSILON)));
    }
    @Test
    void testOne(){
        assertThat(Complex.ONE.getReal(), is(closeTo(1., Helpers.EPSILON)));
        assertThat(Complex.ONE.getImaginary(), is(closeTo(0., Helpers.EPSILON)));
    }
    @Test
    void testI(){
        assertThat(Complex.I.getReal(), is(closeTo(0., Helpers.EPSILON)));
        assertThat(Complex.I.getImaginary(), is(closeTo(1., Helpers.EPSILON)));
    }
    @Test
    void testZero(){
        assertThat(Complex.ZERO.getReal(), is(closeTo(0., Helpers.EPSILON)));
        assertThat(Complex.ZERO.getImaginary(), is(closeTo(0., Helpers.EPSILON)));
    }
    @Test
    void testNegate(){
        assertThat(two.negate(), is(equalTo(new Complex(-2,0))));
        assertThat(minusI.negate(), is(equalTo(i)));
        assertThat(oneMinusI.negate(), is(equalTo(new Complex(-1, 1))));
    }

    @Test
    void testReciprocal(){
        assertThat(one.reciprocal(), is(equalTo(one)));
        assertThat(minusI.reciprocal(), is(equalTo(i)));
        assertThat(two.reciprocal(), is(equalTo(new Complex(0.5,0))));
        assertThat(oneMinusI.reciprocal(), is(equalTo(new Complex(0.5,0.5))));
    }

    @Test
    void testReciprocalOfZero(){
        assertThat(()->zero.reciprocal(), throwsException(ArithmeticException.class));
    }

    @Test
    void testDivide(){
        assertThat(onePlusI.divide(Complex.ONE), equalTo(onePlusI));
        assertThat(Complex.ONE.divide(two), equalTo(new Complex(0.5, 0)));
        assertThat(oneMinusI.divide(onePlusI), equalTo(minusI));
    }

    @Test
    void testDivideByZero(){
        assertThat(()->one.divide(zero), throwsException(ArithmeticException.class));
    }

    @Test
    void testConjugate(){
        assertThat(two.conjugate(), equalTo(two));
        assertThat(oneMinusI.conjugate(), equalTo(onePlusI));
    }

    @Test
    void testRotation(){
        assertThat(Complex.rotation(Math.PI/2), equalTo(i));
        assertThat(Complex.rotation(-Math.PI/2), equalTo(minusI));
        assertThat(Complex.rotation(0), equalTo(one));
        assertThat(Complex.rotation(Math.PI/4), equalTo(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.)));
        assertThat(Complex.rotation(Math.PI/3), equalTo(new Complex(1./2., Math.sqrt(3)/2.)));
    }

    @Test
    void testBasicToString(){
        assertThat(two.toString(), containsString("2.0"));
        assertThat(i.toString(), containsString("i"));
    }

    @Test
    void testToStringFormat(){
        assertThat(oneMinusI.toString(), is(equalTo("1.0 - 1.0i")));
        assertThat(onePlusI.toString(), is(equalTo("1.0 + 1.0i")));
        assertThat(minusI.toString(), is(equalTo("-1.0i")));
        assertThat(twoI.toString(), is(equalTo("2.0i")));
        assertThat(two.toString(), is(equalTo("2.0")));
    }
    @Test
    void testReal(){
        assertThat(Complex.real(-2), is(equalTo(new Complex(-2,0))));

    }
    @Test
    void testAdd(){
        assertThat(two.add(one), is(equalTo(new Complex(3,0))));
    }

}