package POO.Labo5;

public interface Operation {
    /**
     * Enables an operation to be performed between two elements and a modulo to be applied.
     *
     *  @param firstOperator First element for the operation
     *  @param secondOperator Second element for the operation
     *  @param modulo Modulo use for the operation
     *  */
    public int operator(int firstOperator, int secondOperator, int modulo);
}
