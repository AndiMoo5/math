package de.andimoo5.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * {@code ComplexNumber} is a class which implements complex numbers in Java.
 * It implements basic operations and some more. Arithmetic functions are implemented as
 * <i>static</i> and <i>non-static</i> versions. Most other functions are implemented as <i>static</i>.<br>
 * <br> Features included in this class are:<br>
 * <ul>
 *     <li>Arithmetic Operations ({@link #sum(ComplexNumber, ComplexNumber)  addition}, {@link #diff(ComplexNumber, ComplexNumber) subtraction},
 *          {@link #prod(ComplexNumber, ComplexNumber) multiplication}, {@link #diff(ComplexNumber, ComplexNumber) division})</li>
 *     <li>Complex Operations ({@link #conjugate(ComplexNumber) conjugate}, {@link #modulus(ComplexNumber) modulus (absolute, magnitude)},
 *          {@link #phase(ComplexNumber) phase (argument, angle)}, {@link #reciprocal(ComplexNumber) reciprocal (multiplicative inverse)})</li>
 *     <li>Mathematical Functions ({@link #exp(ComplexNumber) exp}, {@link  #pow(ComplexNumber, int) pow})</li>
 *     <li>Trigonometric Operations ({@link #sin(ComplexNumber) sin}, {@link #cos(ComplexNumber) cos}, {@link #tan(ComplexNumber) tan})</li>
 * </ul>
 *
 * @author Andras Martin Moosbauer
 * @version 1.0
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
     */
    public ComplexNumber(ComplexNumber z) {
        set(z);
    }

    //Basic functions (to set/get data from objects of this class):
    /**
     * Sets the value of current complex number to the passed {@code ComplexNumber}.
     * @param z an instance of {@code ComplexNumber}
     */
    public void set(@NotNull ComplexNumber z) {
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
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code double} equal to <i>Re({@code z})</i>
     */
    public static double getRe(@NotNull ComplexNumber z) {
        return z.real;
    }

    /**
     * @return  a {@code double} of the real part, <i>Re(z)</i>, of itself
     */
    public double getRe() {
        return real;
    }

    /**
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code double} equal to <i>Im({@code z})</i>
     */
    public static double getIm(@NotNull ComplexNumber z) {
        return z.imaginary;
    }

    /**
     * @return  a {@code double} of the imaginary part, <i>Im(z)</i>, of itself
     */
    public double getIm() {
        return imaginary;
    }

    /**
     *
     * @param z an instance of {@code ComplexNumber}
     * @return  a String representing the value of {@code z} in the format of <br> {@code 'Re(z)'+'Im(z)'i}
     */
    public static @NotNull String toString(@NotNull ComplexNumber z) {
        if(z.imaginary == 0) return String.valueOf(z.real);
        if(z.real == 0) return z.imaginary + "i";
        if(z.imaginary < 0) return String.valueOf(z.real) + z.imaginary + "i";
        return z.real + "-" + z.imaginary + "i";
    }

    /**
     * @return      a String representing the value of the {@code ComplexNumber} itself in the format of: <br>{@code 'Re(z)'+'Im(z)'i}
     */
    public String toString() {
        return toString(this);
    }

    /**
     * Compares the values of {@code ComplexNUmber} object itself with the ones of {@code Object z}.
     * @param z     any {@code Object} (but if it isn't an instance of {@code ComplexNumber} it will always return {@code false})
     * @return      {@code true} if the <i>Re(z)</i> and <i>Im(z)</i> are equal to those of itself
     * @throws NullPointerException if {@code z} is equal to {@code null}
     */
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
     * Compares the {@code ComplexNumbers} {@code z1} and {@code z2} and declares which one is bigger / smaller or if
     * they are equal to another. It uses {@link #modulus(ComplexNumber) modulus (absolute value / magnitude)} to
     * compare both complex numbers as it's the most comparable value of a complex number due to its matrix-like nature.
     * @param z1    an instance of {@code ComplexNumber}
     * @param z2    an instance of {@code ComplexNumber}
     * @return  <ul>
     *            <li><i>-1</i>: if {@code z1} < {@code z2},</li>
     *            <li><i>0</i>: if {@code z1} = {@code z2},</li>
     *            <li><i>1</i>: if {@code z1} > {@code z2}.</li>
     *          </ul>
     * @throws NullPointerException if either {@code z1} or {@code z2} are equal to {@code null}
     */
    public static int compare(ComplexNumber z1, ComplexNumber z2) throws NullPointerException {
        if(z1 == null || z2 == null) throw new NullPointerException();
        double t = modulus(z1) - modulus(z2);
        if(t == 0) return 0;
        if(t < 0) return -1;
        return 1;
    }

    /**
     * Compares the {@code ComplexNumber}  with {@code z} and declares which one is bigger / smaller or if
     * they are equal to another. It uses {@link #modulus(ComplexNumber) modulus (absolute value / magnitude)} to
     * compare both complex numbers as it's the most comparable value of a complex number due to its matrix-like nature.
     * @param z the {@code ComplexNumber} to be compared to
     * @return  <ul>
     *              <li><i>-1</i>: if {@code z1} < {@code z2},</li>
     *              <li><i>0</i>: if {@code z1} = {@code z2},</li>
     *              <li><i>1</i>: if {@code z1} > {@code z2}.</li>
     *          </ul>
     */
    public int compareTo(@NotNull ComplexNumber z){
        return compare(this, z);
    }

    //Methods for Arithmetic Operations:
    /**
     * Calculates the sum of the {@code ComplexNumbers} {@code z1} and {@code z2}.
     * @param z1    1st summand of the addition, an instance of {@code ComplexNumber}
     * @param z2    2nd summand of the addition, an instance of {@code ComplexNumber}
     * @return      the sum: {@code z1} + {@code z2}
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber sum(@NotNull ComplexNumber z1, @NotNull ComplexNumber z2) {
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
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber diff(@NotNull ComplexNumber z1, @NotNull ComplexNumber z2) {
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
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber prod(@NotNull ComplexNumber z1, @NotNull ComplexNumber z2) {
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
     */
    @Contract("_, _ -> new")
    public static @NotNull ComplexNumber division(@NotNull ComplexNumber z1, @NotNull ComplexNumber z2) {
        double u = (z1.real * z2.real + z1.imaginary * z2.imaginary) / (z2.real * z2.real + z1.imaginary * z1.imaginary);
        double v = (z1.imaginary * z2.real - z1.real * z2.imaginary) / (z2.real * z2.real + z1.imaginary * z1.imaginary);
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
     * @param z an instance of {@code ComplexNumber} to calculate the conjugate from
     * @return  the conjugate of {@code z}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber conjugate(@NotNull ComplexNumber z) {
        return new ComplexNumber(z.real, -z.imaginary);
    }

    /**
     * Calculates the {@code modulus (absolute value / magnitude)} of the {@code ComplexNumber} {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the modulus of {@code z}, represented by a {@code double}
     */
    public static double modulus(@NotNull ComplexNumber z) {
        double u = z.real;
        double v = z.imaginary * z.imaginary;
        return Math.sqrt(u + v);
    }

    /**
     * Calculates the {@code phase (argument / angle)} of the {@code ComplexNumber} {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the phase of {@code z} in radians, represented by a {@code double}
     */
    public static double phase(@NotNull ComplexNumber z) {
        return Math.atan2(z.real, z.imaginary);
    }

    /**
     * Calculates the {@code reciprocal (multiplicative inverse)} of the {@code ComplexNumber} {@code z}
     * @param z an instance of {@code ComplexNumber}
     * @return  the reciprocal of {@code z}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber reciprocal(ComplexNumber z) {
        return division(new ComplexNumber(1), z);
    }

    //Mathematical Functions:
    /**
     * Calculates the exponential of the {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  the exponential of {@code z}, which is e^{@code z}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber exp(@NotNull ComplexNumber z) {
        double r = Math.exp(z.real);
        double u = r * Math.cos(z.imaginary);
        double v = r * Math.sin(z.imaginary);
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates {@code z} to the power of {@code power}.
     * @param z     an instance of {@code ComplexNumber}
     * @param power the power, as an {@code int}
     * @return      a {@code ComplexNumber}, which is {@code z}^{@code power}
     */
    public static ComplexNumber pow(ComplexNumber z, int power) {
        for(int i = 1; i < power; i++) {
            z.mul(z);
        }
        return z;
    }

    //Trigonometric Operations:
    /**
     * Calculates the sine of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code sin(z)}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber sin(@NotNull ComplexNumber z) {
        double x = Math.exp(z.imaginary);
        double u = Math.sin(z.real) * (x + 1/x) / 2;
        double v = Math.cos(z.real) * (x + 1/x) / 2;
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates the cosine of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code cos(z)}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber cos(@NotNull ComplexNumber z) {
        double x = Math.exp(z.imaginary);
        double u = Math.cos(z.real) * (x + 1/x) / 2;
        double v = -Math.sin(z.real) * (x - 1/x) / 2;
        return new ComplexNumber(u, v);
    }

    /**
     * Calculates the tangent of {@code z}.
     * @param z an instance of {@code ComplexNumber}
     * @return  a {@code ComplexNumber} which is {@code tan(z)}
     */
    @Contract("_ -> new")
    public static @NotNull ComplexNumber tan(ComplexNumber z) {
        return division(sin(z), cos(z));
    }
}