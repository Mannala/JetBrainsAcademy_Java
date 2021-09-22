import java.util.Scanner;
import java.util.regex.*;
import java.util.Arrays;


public class TicTacToe {
    static String line = "         ";
    static char[][] array = new char[3][3];
    static String status;
    static Scanner scanner;
    static int xoCounter = 0;
    static boolean finished = false;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            Arrays.fill(array[i], ' ');
        }
        printGame();
        while (!finished) {
            gameStart();
            gamePatterns();
            if (!finished) printGame();
        }
        printGame();
        System.out.println(status);
    }

    public static void gameStart() {
        System.out.println("Enter the coordinates: ");
        int n1 = scanner.nextInt() - 1;
        int n2 = scanner.nextInt() - 1;

        if (n1 < 0| n1 > 2 | n2 < 0 | n2 > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            gameStart();
            return;
        } else if (array[n1][n2] == 'X' | array[n1][n2] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            gameStart();
            return;
        }
        int num = numberConversion(n1, n2);
        if (xoCounter % 2 == 0) {
            array[n1][n2] = 'X';
            line = addChar(line, 'X', num);
        } else {
            array[n1][n2] = 'O';
            line = addChar(line, 'O', num);
        }
        xoCounter++;

    }

    private static int numberConversion(int n1, int n2) {
        int num;
        if (n1 == 0) {
            num = n2;
        } else if (n1 == 1) {
            num = 3 + n2;
        } else {
            num = 6 + n2;
        }
        return num;
    }

    private static String addChar(String str, char ch, int position) {
        return str.substring(0, position) + Character.toString(ch) + str.substring(position + 1, str.length());
    }


    public static void gamePatterns() {
        /* if (xWins() && oWins()) { // both players can't win
            System.out.println("Impossible");
            finished = true;
        } else */ if (Math.abs(stringCharCounter('X') - stringCharCounter('O')) > 1) {
            status = "Impossible";
            finished = true;
        } else if (xWins()) {
            status = "X wins";
            finished = true;
        } else if (oWins()) {
            status = "O wins";
            finished = true;
        } /* else if(Pattern.matches("(.*)( |_)(.*)", line)) { // game not finished patterns
            System.out.println("Game not finished");
            finished = true;
        } */ else if (stringCharCounter('X') + stringCharCounter('O') == 9) { // draw patterns
            status = "Draw";
            finished = true;
        }
    }

    public static void printGame() {
        System.out.printf("---------\n" +
                        "| %s %s %s |\n" +
                        "| %s %s %s |\n" +
                        "| %s %s %s |\n" +
                        "---------\n",  array[0][0], array[0][1], array[0][2],
                array[1][0], array[1][1], array[1][2],
                array[2][0], array[2][1], array[2][2]);
    }

    public static int stringCharCounter(char letter) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == letter) counter++;
        }
        return counter;
    }

    public static boolean xWins() {
        if (Pattern.matches("(XXX)(.*)", line) | // X Wins patterns
                Pattern.matches("(...)(XXX)(...)", line) |
                Pattern.matches("(.*)(XXX)", line) |
                Pattern.matches("(.*)(X)(..)(X)(..)(X)(.*)", line) |
                Pattern.matches("(.*)(X)(..)(X)(..)(X)(.*)", line) |
                Pattern.matches("(.*)(X)(...)(X)(...)(X)(.*)", line) |
                Pattern.matches("(..)(X)(.)(X)(.)(X)(..)", line)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean oWins() {
        if (Pattern.matches("(OOO)(.*)", line) | // o wins patterns
                Pattern.matches("(...)(OOO)(...)", line) |
                Pattern.matches("(.*)(OOO)", line) |
                Pattern.matches("(.*)(O)(..)(O)(..)(O)(.*)", line) |
                Pattern.matches("(.*)(O)(..)(O)(..)(O)(.*)", line) |
                Pattern.matches("(.*)(O)(...)(O)(...)(O)(.*)", line) |
                Pattern.matches("(..)(O)(.)(O)(.)(O)(..)", line)) {
            return true;
        } else {
            return false;
        }
    }
}