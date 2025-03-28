package POO.Labo5;

public class Substraction implements Operation{

    /**
     * Allows you to subtract two elements together and apply a modulo.
     *
     *  @param firstOperator First element for the subtraction
     *  @param secondOperator Second element for the subtraction
     *  @param modulo Modulo use for the subtraction
     * */
    @Override
    public int operator(int firstOperator, int secondOperator, int modulo) {
        int res = (firstOperator - secondOperator);
        res =  Math.floorMod(res,modulo);
        return res;
    }
}
