package POO.Labo5;

public class Multiplication implements Operation{
    /**
     * Allows you to multiply two elements together and apply a modulo.
     *
     *  @param firstOperator First element for the multiplication
     *  @param secondOperator Second element for the multiplication
     *  @param modulo Modulo use for the multiplication
     *  @throws RuntimeException The result exceeds the maximum value of an int
     *  */
    @Override
    public int operator(int firstOperator, int secondOperator,int modulo) {
        try {
            Math.multiplyExact(firstOperator, secondOperator);
        } catch (ArithmeticException e) {
            throw new RuntimeException("The numbers in your matrices are too large to be " +
                    "multiplied and cause your capacity to be exceeded.\n" + e);
        }
        return (firstOperator * secondOperator) % modulo;
    }
}
