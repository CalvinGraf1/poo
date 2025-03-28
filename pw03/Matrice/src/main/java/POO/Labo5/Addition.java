package POO.Labo5;

public class Addition implements Operation{

    /**
     * Allows you to add two elements together and apply a modulo.
     *
     *  @param firstOperator First element for the addition
     *  @param secondOperator Second element for the addition
     *  @param modulo Modulo use for the addition
     *  @throws RuntimeException The result exceeds the maximum value of an int
     *  */
    @Override
    public int operator(int firstOperator, int secondOperator, int modulo) {
        try {
            Math.addExact(firstOperator, secondOperator);
        } catch (ArithmeticException e) {
            throw new RuntimeException("The numbers in your matrices are too " +
                    "large to be added together and cause capacity overflow.\n" + e);
        }
        return (firstOperator + secondOperator) % modulo;
    }
}
