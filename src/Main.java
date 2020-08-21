import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    static char player = 'X';

    public static void main(String[] args) {

        ticTacToe();

    }

    static void ticTacToe() {
        char[][] matrix = {{' ',' ',' '},
                {' ',' ',' '},
                {' ',' ',' '}};

        display(matrix);

        while (true) {
            playerMove(matrix);
            display(matrix);

            if (gameOver(matrix) == true) {
                break;
            }
            player = player == 'X' ? 'O' : 'X';
        }
    }

    static boolean gameOver(char[][] matrix) {

        if (hasEmptyCell(matrix) == false) {

            if (wins(player,matrix) == true) {
                System.out.print(player + " wins");
                return true;
            }

            System.out.print("Draw");

            return true;
        }

        if (wins(player,matrix) == true) {
            System.out.print(player + " wins");
            return true;
        }

        return false;
    }

    static boolean wins(char player, char[][] matrix) {

        //diagonally
        if (matrix[1][1] == player) {
            if (matrix[0][0] == player && matrix[2][2] == player)
                return true;
            if (matrix[0][2] == player && matrix[2][0] == player)
                return true;
        }
        // three a row
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (checkPlayer(i,j,player,matrix) == true)
                    return true;
            }
        }

        return false;
    }

    static boolean checkPlayer(int i, int j, char player, char[][] matrix) {

        int count = 0;

        for (int k = 0; k < 3; ++k) {
            if (matrix[k][j] == player)
                ++count;
        }
        if (count > 2)
            return true;
        count = 0;

        for (int k = 0; k < 3; ++k) {
            if (matrix[i][k] == player)
                ++count;
        }
        if (count > 2)
            return true;

        return false;
    }

    static boolean hasEmptyCell(char[][] matrix) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (matrix[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }

    static void playerMove(char[][] matrix) {

        while (true) {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();

            if (checkCoordinates(matrix, coordinates) == true) {
                break;
            }
        }
    }

    static boolean checkCoordinates(char[][] matrix, String coordinates) {

        if (hasSymbol(coordinates) == true) {
            System.out.println("You should enter numbers!");
            return false;
        }

        String[] numbers = coordinates.split(" ");

        if (numbers.length > 2 || numbers.length <= 0)
            return false;

        int[] _coordinates = new int[2];

        for (int i = 0; i < 2; ++i) {
            _coordinates[i] = Integer.valueOf(numbers[i]);
            _coordinates[i] -= 1;

            if (_coordinates[i] < 0 || _coordinates[i] > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }

        if (matrix[_coordinates[0]][_coordinates[1]] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        if (matrix[_coordinates[0]][_coordinates[1]] == ' ') {
            matrix[_coordinates[0]][_coordinates[1]] = player;
            return true;
        }

        return false;
    }

    static boolean hasSymbol(String coordinates) {
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        for (int i = 0; i < coordinates.length(); ++i) {
            for (int j = 0; j < alphabet.length; ++j) {
                if (coordinates.charAt(i) == alphabet[j])
                    return true;
            }
        }

        return false;
    }

    static void display(char[][] matrix) {
        System.out.println("---------");

        for (int i = 0; i < 3; ++i) {
            System.out.print("| ");
            for (int j = 0; j < 3; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
