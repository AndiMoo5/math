package de.andimoo5.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * {@code ComplexNumber} is an implementation of complex numbers in Java.
 * It implements basic operations and some more. Arithmetic functions are implemented as
 * <i>static</i> and <i>non-static</i> versions. Most other functions are implemented as <i>static</i>.<br>
 * <br> Features included in this class are:<br>
 * <ul>
 *     <li>Arithmetic Operations ({@link #sum(ComplexNumber, ComplexNumber)  addition}, {@link #diff(ComplexNumber, ComplexNumber) subtraction},
 *          {@link #prod(ComplexNumber, ComplexNumber) multiplication}, {@link #diff(ComplexNumber, ComplexNumber) division})</li>
 *     <li>Complex Operations ({@link #conjugate(ComplexNumber) conjugate}, {@link #mod(ComplexNumber) modulus (absolute, magnitude)},
 *          {@link #arg(ComplexNumber) argument (angle)}, {@link #rcp(ComplexNumber) reciprocal (multiplicative inverse)})</li>
 *     <li>Exponential and Logarithmic Functions ({@link #exp(ComplexNumber) exp}, {@link #log(ComplexNumber) log},
 *          {@link  #log10(ComplexNumber) log10}, {@link  #pow(ComplexNumber, int) pow}, {@link #sqrt(ComplexNumber) sqrt})</li>
 *     <li>Trigonometric Operations ({@link #sin(ComplexNumber) sin}, {@link #cos(ComplexNumber) cos}, {@link #tan(ComplexNumber) tan},
 *          {@link #asin(ComplexNumber) asin}, {@link #acos(ComplexNumber) acos}, {@link #asin(ComplexNumber) atan})</li>
 * </ul>
 *
 * @author  Andras Martin Moosbauer
 * @version 2.0
 */
public class ComplexNumber extends Number implements Comparable<ComplexNumber> {

    //Values of a complex Number, a real part, Re(z), and an imaginary part, Im(z):
    private double real, imaginary;

    //Constructors of the "ComplexNumber" class:
    /**
     * Constructs a new {@code ComplexNumber} object with both real and imaginary parts equal to 0.
     */
    public ComplexNumber() {
        set(0.0, 0.0);
    }

    /**
     * Constructs a new {@code ComplexNumber} object with both real and imaginary having
     * different and randomly chosen values between <i>0</i> and <i>Double.MAX_VALUE</i>.
     * @param random    {@code true}: creates a new {@code ComplexNumber} with random parameters <br>
     *                  {@code false}: creates a new {@code ComplexNumber} using with {@code 0} as a value
     */
    public ComplexNumber(boolean random) {
        if(random) new ComplexNumber(Math.random() * Double.MAX_VALUE, Math.random() * Double.MAX_VALUE);
        else new ComplexNumber();
    }

    /**
     * Constructs a new {@code ComplexNumber} object with the imaginary part equal to 0.
     * @param real the real part, Re(z), of the complex number
     */
    public ComplexNumber(double real) {
        set(real, 0.0);
    }

    /**
     * Constructs a new {@code ComplexNumber} object.
     * @param real      the real part, Re(z), of the complex number
     * @param imaginary the imaginary part, Im(z), of the complex number
     */
    public ComplexNumber(double real, double imaginary) {
        set(real, imaginary);
    }

    /**
     * Constructs a new {@code ComplexNumber} object with the same properties as <i>z</i>.
     * @param z an instance {@code ComplexNumber}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public ComplexNumber(ComplexNumber z) throws NullPointerException{
        if(z == null) throw new NullPointerException();
        set(z);
    }

    //Basic functions (to set/get data from objects of this class):
    /**
     * Sets the value of current complex number to the passed {@code ComplexNumber}.
     * @param z an instance of {@code ComplexNumber}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public void set(ComplexNumber z) {
        if(z == null) throw new NullPointerException();
        setRe(z.real);
        setIm(z.imaginary);
    }

    /**
     * Sets <i>Re(z)</i> and <i>Im(z)</i> to the passed values.
     * @param real      the real part, Re(z), of the complex number
     * @param imaginary the imaginary part, Im(z), of the complex number
     */
    public void set(double real, double imaginary) {
        setRe(real);
        setIm(imaginary);
    }

    /**
     * Sets <i>Re(z)</i> to the passed value.
     * @param real  the real part, Re(z), of the complex number
     */
    public void setRe(double real) {
        this.real = real;
    }

    /**
     * Sets <i>Im(z)</i> to the passed value.
     * @param imaginary the real part, Im(z), of the complex number
     */
    public void setIm(double imaginary) {
        this.imaginary = imaginary;
    }

    /**
     * Return <i>Re(z)</i> of itself
     * @return  a {@code double} of the real part, <i>Re(z)</i>, of itself
     */
    public double getRe() {
        return real;
    }

    /**
     * Return <i>Im(z)</i> of itself
     * @return  a {@code double} of the imaginary part, <i>Im(z)</i>, of itself
     */
    public double getIm() {
        return imaginary;
    }

    /**
     * @return      a String representing the value of the {@code ComplexNumber} itself in the format of: <br>{@code 'Re(z)'+'Im(z)'i}
     */
    @Override
    public String toString() {
        if(imaginary == 0) return String.valueOf(real);
        if(real == 0) return imaginary + "i";
        if(imaginary < 0) return String.valueOf(real) + String.valueOf(imaginary) + "i";
        return real + "+" + imaginary + "i";
    }

    /**
     * Compares the values of {@code ComplexNUmber} object itself with the ones of {@code Object z}.
     * @param z     any {@code Object} (but if it isn't an instance of {@code ComplexNumber} it will always return {@code false})
     * @return      {@code true} if the <i>Re(z)</i> and <i>Im(z)</i> are equal to those of itself
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Override
    public final boolean equals(Object z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        if(!(z instanceof ComplexNumber)) return false;
        return (real == ((ComplexNumber)z).real) && (imaginary == ((ComplexNumber)z).imaginary);
    }

    //Methods needed due to extending "Number":
    /**
     * <i><b>Waring!</b></i> - by using this method the imaginary, <i>Im(z)</i>, as well as precession may be lost.
     * @return  the numeric value of <i>Re(z)</i> of the {@code ComplexNumber} object after conversion to type {@code int}.
     */
    public int intValue() {
        return (int)real;
    }

    /**
     * <i><b>Waring!</b></i> - by using this method the imaginary, <i>Im(z)</i>, as well as precession may be lost.
     * @return  the numeric value of <i>Re(z)</i> of the {@code ComplexNumber} object after conversion to type {@code long}.
     */
    public long longValue() {
        return (long)real;
    }

    /**
     * <i><b>Waring!</b></i> - by using this method the imaginary, <i>Im(z)</i>, as well as precession may be lost.
     * @return  the numeric value of <i>Re(z)</i> of the {@code ComplexNumber} object after conversion to type {@code float}.
     */
    public float floatValue() {
        return (float)real;
    }

    /**
     * <i><b>Waring!</b></i> - by using this method the imaginary, <i>Im(z)</i>.
     * @return  the numeric value of <i>Re(z)</i> of the {@code ComplexNumber} object.
     */
    public double doubleValue() {
        return real;
    }

    //Methods needed due to implementing "Comparable<ComplexNumber>":
    /**
     * Compares the {@code ComplexNumber}  with {@code z} and declares which one is bigger / smaller or if
     * they are equal to another. It uses {@link #mod(ComplexNumber) modulus (absolute value / magnitude)} to
     * compare both complex numbers as it's the most comparable value of a complex number due to its matrix-like nature.
     * @param z the {@code ComplexNumber} to be compared to
     * @return  <ul>
     *              <li><i>-1</i>: if {@code this} &lt; {@code z},</li>
     *              <li><i>0</i>: if {@code this} = {@code z},</li>
     *              <li><i>1</i>: if {@code this} > {@code z}.</li>
     *          </ul>
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public int compareTo(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        double t = mod(this) - mod(z);
        if(t == 0) return 0;
        if(t < 0) return -1;
        return 1;
    }

    //Methods for Arithmetic Operations:
    /**
     * Calculates the sum of the {@code ComplexNumbers} {@code z1} and {@code z2}.
     * @param z1    1st summand of the addition, an instance of {@code ComplexNumber}
     * @param z2    2nd summand of the addition, an instance of {@code ComplexNumber}
     * @return      the sum: {@code z1} + {@code z2}
     * @throws NullPointerException if either {@code z1} or {@code z2} is equal to {@code null}
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber sum(ComplexNumber z1, ComplexNumber z2) throws NullPointerException{
        if(z1 == null || z2 == null) throw new NullPointerException();
        return new ComplexNumber(z1.real + z2.real, z1.imaginary + z2.imaginary);
    }

    /**
     * Mathematically adds {@code z} to itself.
     * @param z     number to be added, an instance of {@code ComplexNumber}
     */
    public void add(ComplexNumber z) {
        set(sum(this, z));
    }

    /**
     * Calculates the difference of the {@code ComplexNumbers} {@code z1} and {@code z2}.
     * @param z1    minuend of the difference, an instance of {@code ComplexNumber}
     * @param z2    subtrahend of the difference, an instance of {@code ComplexNumber}
     * @return      the difference: {@code z1} - {@code z2}
     * @throws NullPointerException if either {@code z1} or {@code z2} is equal to {@code null}
     */
    public static ComplexNumber diff(ComplexNumber z1, ComplexNumber z2) {
        if(z1 == null | z2 == null) throw new NullPointerException();
        return new ComplexNumber(z1.real - z2.real, z1.imaginary - z2.imaginary);
    }

    /**
     * Mathematically subtracts {@code z} from itself.
     * @param z     number to be subtracted, an instance of {@code ComplexNumber}
     */
    public void sub(ComplexNumber z) {
        set(diff(this, z));
    }

    /**
     * Calculates the product of the {@code ComplexNumbers} {@code z1} and {@code z2}.
     * @param z1    1st multiplier of the product, an instance of {@code ComplexNumber}
     * @param z2    2nd multiplier of the product, an instance of {@code ComplexNumber}
     * @return      the product: {@code z1} * {@code z2}
     * @throws NullPointerException if either {@code z1} or {@code z2} is equal to {@code null}
     */
    public static ComplexNumber prod(ComplexNumber z1, ComplexNumber z2) throws NullPointerException {
        if(z1 == null || z2 == null) throw new NullPointerException();
        double u = z1.real * z2.real - z1.imaginary * z2.imaginary;
        double v = z1.real * z2.imaginary + z1.imaginary * z2.real;
        return new ComplexNumber(u, v);
    }

    /**
     * Mathematically multiplies {@code z} to itself.
     * @param z     number to be multiplied with, an instance of {@code ComplexNumber}
     */
    public void mul(ComplexNumber z) {
        set(prod(this, z));
    }

    /**
     * Calculates the division of the {@code ComplexNumbers} {@code z1} and {@code z2}.
     * @param z1    dividend of the division, an instance of {@code ComplexNumber}
     * @param z2    divisor of the division, an instance of {@code ComplexNumber}
     * @return      the division: {@code z1} / {@code z2}
     * @throws NullPointerException if either {@code z1} or {@code z2} is equal to {@code null}
     * @throws  ArithmeticException  if {@code z2} is equal to {@code 0}
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber division(ComplexNumber z1, ComplexNumber z2) throws ArithmeticException, NullPointerException {
        if(z1 == null || z2 == null) throw new NullPointerException();
        if(z2.real == 0 && z2.imaginary == 0) throw new ArithmeticException("Can not divide by 0");
        double u = (z1.real * z2.real + z1.imaginary * z2.imaginary) / (z2.real * z2.real + z2.imaginary * z2.imaginary);
        double v = (z1.imaginary * z2.real - z1.real * z2.imaginary) / (z2.real * z2.real + z2.imaginary * z2.imaginary);
        return new ComplexNumber(u, v);
    }

    /**
     * Mathematically divides {@code z} from itself.
     * @param z     number to be divided by, an instance of {@code ComplexNumber}
     */
    public void div(ComplexNumber z) {
        set(division(this, z));
    }

    //Complex Operations:
    /**
     * Returns the conjugate of a given complex number.
     * @param z an instance of {@code ComplexNumber} to calculate the conjugate from
     * @return  the conjugate of {@code z}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail; !null -> new")
    public static @NotNull ComplexNumber conjugate(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        return new ComplexNumber(z.real, -z.imaginary);
    }

    /**
     * Calculates the {@code modulus (absolute value / magnitude)} of the {@code ComplexNumber} {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the modulus of {@code z}, represented by a {@code double}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public static double mod(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        return Math.sqrt(z.real * z.real + z.imaginary * z.imaginary);
    }

    /**
     * Calculates the {@code argument (angle)} of the {@code ComplexNumber} {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the argument of {@code z} in radians, represented by a {@code double}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public static double arg(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        return Math.atan2(z.imaginary, z.real);
    }

    /**
     * Calculates the {@code reciprocal (multiplicative inverse)} of the {@code ComplexNumber} {@code z}
     * @param z an instance of {@code ComplexNumber}
     * @return  the reciprocal of {@code z}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull ComplexNumber rcp(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        if(z.real == 0 && z.imaginary == 0) return z;
        return division(new ComplexNumber(1), z);
    }

    //Exponential and Logarithmic Functions:
    /**
     * Calculates the exponential of the {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the exponential of {@code z}, which is e^{@code z}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public static ComplexNumber exp(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        double r = Math.exp(z.real);
        double u = r * Math.cos(z.imaginary);
        double v = r * Math.sin(z.imaginary);
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates the natural logarithm <i>(base e)</i> of a {@code ComplexNumber}.
     * @param z an instance of {@code ComplexNUmber}
     * @return  the log({@code z})
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail; !null -> new")
    public static @NotNull ComplexNumber log(ComplexNumber z) throws  NullPointerException {
        if(z == null) throw new NullPointerException();
        return new ComplexNumber(Math.log(mod(z)), arg(z));
    }

    /**
     * Calculates the logarithm with the base {@code b} of a {@code ComplexNumber}.
     * @param z an instance of {@code ComplexNUmber}
     * @param b the base of the logarithm, as a {@code double}
     * @return  the log({@code z})
     * @throws NullPointerException if {@code z} is equal to {@code null}
     * @throws ArithmeticException  if the base {@code b} of the logarithm is &lt;= 0
     */
    public static @NotNull ComplexNumber log(ComplexNumber z, double b) throws  NullPointerException, ArithmeticException {
        if(z == null) throw new NullPointerException();
        if(b <= 0) throw new ArithmeticException("base of the log should be > 0");
        return division(log(z), log(new ComplexNumber(b)));
    }

    /**
     * Calculates the logarithm with the base {@code b} of a {@code ComplexNumber}.
     * @param z an instance of {@code ComplexNUmber}
     * @return  the log({@code z})
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public static ComplexNumber log10(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        return log(z, 10);
    }


    /**
     * Calculates {@code z} to the power of {@code power}.
     * @param z     an instance of {@code ComplexNumber}
     * @param power the power, as an {@code int}
     * @return      a {@code ComplexNumber}, which is {@code z}^{@code power}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null, _ -> fail")
    public static @NotNull ComplexNumber pow(ComplexNumber z, int power) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        for(int i = 1; i < power; i++) {
            z.mul(z);
        }
        return z;
    }

    /**
     * Calculates the square root of {@code z}
     * @param z an instance of {@code ComplexNumber}
     * @return  the square root of {@code z}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    public static ComplexNumber sqrt(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        double u = Math.sqrt(mod(z)) * Math.cos(arg(z) / 2);
        double v = Math.sqrt(mod(z)) * Math.sin(arg(z) / 2);
        return new ComplexNumber(u, v);
    }

    //Trigonometric Operations:
    /**
     * Calculates the sine of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code sin(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail; !null -> new")
    public static @NotNull ComplexNumber sin(ComplexNumber z) {
        if(z == null) throw new NullPointerException();
        double x = Math.exp(z.imaginary);
        double u = Math.sin(z.real) * (x + 1/x) / 2;
        double v = Math.cos(z.real) * (x + 1/x) / 2;
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates the cosine of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code cos(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail; !null -> new")
    public static @NotNull ComplexNumber cos(ComplexNumber z) {
        if(z == null) throw new NullPointerException();
        double x = Math.exp(z.imaginary);
        double u = Math.cos(z.real) * (x + 1/x) / 2;
        double v = -Math.sin(z.real) * (x - 1/x) / 2;
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates the tangent of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code tan(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull ComplexNumber tan(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        return division(sin(z), cos(z));
    }

    /**
     * Calculates the arc sine of the given value {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {code ComplexNumber} which is {@code asin(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull ComplexNumber asin(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        z = new ComplexNumber(-z.real, -z.imaginary);
        ComplexNumber tmp = prod(z, new ComplexNumber(0, 1));
        z.mul(z);
        z = diff(new ComplexNumber(1), z);
        z = sqrt(z);
        z.add(tmp);
        z = log(z);
        z.mul(new ComplexNumber(0, 1));
        return z;
    }

    /**
     * Calculates the arc cosine of the given value {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {code ComplexNumber} which is {@code acos(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull ComplexNumber acos(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        z = atan(division(sqrt(diff(new ComplexNumber(1), prod(z, z))), z));
        return z;
    }

    /**
     * Calculates the arc tangent of the given value {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {code ComplexNumber} which is {@code atan(z)}
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull ComplexNumber atan(ComplexNumber z) throws NullPointerException {
        if(z == null) throw new NullPointerException();
        ComplexNumber tmp = prod(z, new ComplexNumber(0,1));
        z = division(sum(new ComplexNumber(1), tmp), diff(new ComplexNumber(1), tmp));
        z = prod(division(new ComplexNumber(1), new ComplexNumber(0, 2)), log(z));
        return z;
    }
}