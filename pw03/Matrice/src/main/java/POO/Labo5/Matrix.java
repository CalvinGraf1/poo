package POO.Labo5;

import java.lang.Math;

public class Matrix {

    private final int NB_LINE, NB_COLUMN, MODULO;
    private final int[][] VALUES;

    /**
     * Builds a matrix from a 2-dimensional table.
     * The modulo is calculated by taking the largest element of the matrix + 1.
     *
     * @param values 2-dimensional array of integers representing the values of the matrix.
     * @throws RuntimeException If the array is uninitialised, empty or contains
     *                          negative elements.
     */
    public Matrix(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            throw new RuntimeException("The matrix must be at least 1x1 in size.");

        this.NB_LINE = values.length;
        this.NB_COLUMN = values[0].length;
        this.VALUES = values;

        int moduloMax = 0;
        for (int i = 0; i < NB_LINE; ++i) {
            for (int j = 0; j < NB_COLUMN; ++j) {
                if(this.VALUES[i][j] < 0)
                    throw new RuntimeException("Matrix elements must be equal or greater than 0.");
                moduloMax = Math.max(moduloMax, this.VALUES[i][j]);
            }
        }
        this.MODULO = moduloMax + 1;
    }

    /**
     * Builds a matrix of randomly chosen elements.
     *
     *  @param nbLine Number of lines in the matrix.
     *  @param nbColumn Number of columns in the matrix.
     *  @param modulo The modulo limits the value of the elements of the matrix.
     *  @throws RuntimeException If the number of lines or columns is less than 1,
     *                           or if the modulo is less than 1.
     *  */
    public Matrix(int nbLine, int nbColumn, int modulo) {
        if(nbLine < 1 || nbColumn < 1) throw new RuntimeException("The matrix must be at least 1x1 in size.");
        else if (modulo < 1) throw new RuntimeException("The modulo must be equal or greater than 1.");

        this.NB_LINE = nbLine;
        this.NB_COLUMN = nbColumn;
        this.MODULO = modulo;

        VALUES = new int[NB_LINE][NB_COLUMN];
        for(int i = 0; i < NB_LINE; ++i) {
            for(int j = 0; j < NB_COLUMN; ++j) {
                double randomDouble = Math.random();
                VALUES[i][j] = (int)(randomDouble * Integer.MAX_VALUE) % this.MODULO;
            }
        }
    }

    private Matrix calculateMatrix(Matrix m, Operation op) {
        if(MODULO != m.MODULO) throw new RuntimeException("The two modulos must be equal.");

        Matrix res = new Matrix(Math.max(NB_LINE, m.NB_LINE), Math.max(NB_COLUMN, m.NB_COLUMN), MODULO);
        for(int i = 0; i < res.NB_LINE; ++i) {
            for(int j = 0; j < res.NB_COLUMN; ++j) {
                int ope1 = 0;
                int ope2 = 0;
                if(i < NB_LINE && j < NB_COLUMN) ope1 = VALUES[i][j];
                if (i < m.NB_LINE && j < m.NB_COLUMN) ope2 = m.VALUES[i][j];
                res.VALUES[i][j] = op.operator(ope1, ope2, MODULO);
            }
        }
        return res;
    }


    /**
     * Adds the elements of 2 matrices component by component.
     *
     *  @param otherMatrix Second matrix to add.
     *  */
    public Matrix add(Matrix otherMatrix) {
        return calculateMatrix(otherMatrix, new Addition());
    }

    /**
     * Subtracts the elements of 2 matrices component by component.
     *
     *  @param otherMatrix Second matrix to subtract.
     *  */
    public Matrix sub(Matrix otherMatrix) {
        return calculateMatrix(otherMatrix, new Substraction());
    }

    /**
     * Multiplies  the elements of 2 matrices component by component.
     *
     *  @param otherMatrix Second matrix to multriply.
     *  */
    public Matrix multiply(Matrix otherMatrix) {
        return calculateMatrix(otherMatrix, new Multiplication());
    }

    /**
     * Displays the elements of a matrix
     *  */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < NB_LINE; i++) {
            for (int j = 0; j < NB_COLUMN; j++) {
                res += VALUES[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }
}


