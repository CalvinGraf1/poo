package POO.Labo5;

public class Main {
    public static void main(String[] args) {
        int m1Lines = 0;
        int m1Columns = 0;
        int m2Lines = 0;
        int m2Columns = 0;
        int mod = 0;

        try {
            m1Lines = Integer.parseInt(args[0]);
            m1Columns = Integer.parseInt(args[1]);
            m2Lines = Integer.parseInt(args[2]);
            m2Columns = Integer.parseInt(args[3]);
            mod = Integer.parseInt(args[4]);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Please enter valid numbers in the parameters.\n" + e);
        }

        if(m1Lines > 500 || m1Columns > 500 || m2Lines > 500 || m2Columns > 500)
            throw new RuntimeException("Please enter a matrix smaller than 500x500.");

        Matrix m1 = new Matrix(m1Lines, m1Columns, mod);
        Matrix m2 = new Matrix(m2Lines, m2Columns, mod);

        System.out.println("Modulo is : " + mod + "\n");
        System.out.println("Matrix 1 : \n" + m1);
        System.out.println("Matrix 2 : \n" + m2);

        System.out.println("Addition : \n" + m1.add(m2));
        System.out.println("Substraction : \n" + m1.sub(m2));
        System.out.println("Multiplication : \n" + m1.multiply(m2));

        // Test avec les mêmes matrices que la donnée
        /*
        int[][] tbl = new int[][] {
                {1, 3, 1, 1},
                {3, 2, 4, 2},
                {1, 0, 1, 0},
        };
        int[][] tbl2 = new int[][] {
                {1, 4, 2, 3, 2},
                {0, 1, 0, 4, 2},
                {0, 0, 2, 0, 2}
        };

        Matrix m3 = new Matrix(tbl);
        Matrix m4 = new Matrix(tbl2);
        Matrix res;

        System.out.println("The modulos is : 5");
        System.out.println("Matrix 3 : \n" + m3);
        System.out.println("Matrix 4 : \n" + m4);

        System.out.println("Addition : \n" + m3.add(m4));
        System.out.println("Substraction : \n" + m3.sub(m4));
        System.out.println("Multiplication : \n" + m3.multiply(m4));
         */
    }
}