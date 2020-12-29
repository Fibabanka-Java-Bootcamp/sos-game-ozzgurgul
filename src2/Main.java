
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static boolean start;
    public static String playerLetter, computerLetter;
    public static Random random = new Random();
    public static boolean beginning = true;
    public static int value;
    public static Scanner scanner = new Scanner(System.in);
    private static int row, column;
    static String[][] gameBoard;

    public static void main(String[] args) {

        defineLetter();
        createBoard();
        defineStart();
        while (!(gameover(value))) {
            if (start) {
                if (beginning = gameover(value)) {
                    break;
                } else {
                    playerTurn(playerLetter, value);
                    if (winner()) {
                        System.out.println("Kullanıcı Kazandı...");
                        break; 
                    }
                }
                if (beginning = gameover(value)) {
                    break;
                } else {
                    computerTurn(value, computerLetter);
                    if (winner()) {
                        System.out.println("Bilgisayar Kazandı...");
                        break;
                    }
                }
            } else {
                if (beginning = gameover(value)) {
                    break;
                } else {
                    computerTurn(value, computerLetter);
                    if (winner()) {
                        System.out.println("Bilgisayar Kazandı...");
                      break;
                    }
                }
                if (beginning = gameover(value)) {
                    break;
                } else {
                    playerTurn(playerLetter, value);
                    if (winner()) {
                        System.out.println("Kullanıcı Kazandı...");
                       break;
                    }
                }
            }          
        }
        if(!winner()){
                System.out.println("Berabere");
            }
        System.out.println("Oyun Bitti");
        
    }

    public static void defineStart() {
        int i = random.nextInt(2);
        if (i == 0) {
            start = true;   
            System.out.println();
              System.out.println("Kullanıcı başlıyor");
        } else {
            start = false;
            System.out.println();
              System.out.println("Bilgisayar başlıyor");
        }
    }

    public static void defineLetter() {
        int l = random.nextInt(2);
        if (l == 0) {
            playerLetter = "S";
            computerLetter = "O";
        } else {
            playerLetter = "O";
            computerLetter = "S";
        }
    }

    public static void createBoard() {
        System.out.println("Matrisin değerini giriniz : (3-7)");
        while (beginning) {
            value = scanner.nextInt();
            if (3 <= value && value <= 7) {
                gameBoard = new String[value][value];
                for (int i = 0; i < gameBoard.length; i++) {
                    for (int j = 0; j < gameBoard.length; j++) {
                        gameBoard[i][j] = "-";
                    }
                }
                beginning = false;
                printBoard(gameBoard);
                break;
            } else {

                System.out.println("Hatalı Giriş");
            }
        }
    }

    public static void printBoard(String[][] gameBoard) {

        for (String[] row : gameBoard) {
            for (String b : row) {
                System.out.print(b + "|");
            }
            System.out.println();
        }
    }

    public static void playerTurn(String playerLetter, int value) {
        System.out.println("Satırı giriniz :");
        row = scanner.nextInt();
        System.out.println("Sütunu giriniz :");
        column = scanner.nextInt();
        while (value <= row || value <= column) {  //Satır ve sütun sayısından fazla girdi mi ?
            System.out.println("Hatalı giriş.Tekrar deneyin :");
            System.out.println("Satırı giriniz :");
            row = scanner.nextInt();
            System.out.println("Sütunu giriniz :");
            column = scanner.nextInt();
        }
        while (!(gameBoard[row][column]).equals("-")) {  // Girilen yer dolu mu ?
            System.out.println("Hatalı giriş.Tekrar deneyin :");
            row = scanner.nextInt();
            column = scanner.nextInt();
        }
        gameBoard[row][column] = playerLetter;
        printBoard(gameBoard);
    }

    public static void computerTurn(int value, String computerLetter) {

        row = random.nextInt(value);
        column = random.nextInt(value);
        while (!(gameBoard[row][column]).equals("-")) {
            row = random.nextInt(value);
            column = random.nextInt(value);
        }
        gameBoard[row][column] = computerLetter;
        System.out.println("");
        printBoard(gameBoard);

    }

    public static boolean checkHorizantal(int value) {
        for (int i = 0; i < value; i++) {
            for (int j = 0; j < (value - 2); j++) {
                if ((gameBoard[i][j].equals("S")) && (gameBoard[i][j + 1].equals("O")) && (gameBoard[i][j + 2].equals("S"))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkVertical(int value) {
        for (int i = 0; i < (value - 2); i++) {
            for (int j = 0; j < value; j++) {
                if ((gameBoard[i][j].equals("S")) && (gameBoard[i + 1][j].equals("O")) && (gameBoard[i + 2][j].equals("S"))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonal(int value) {
        for (int i = 0; i < (value - 2); i++) {
            for (int j = 0; j < (value - 2); j++) {
                if ((gameBoard[i][j].equals("S")) && (gameBoard[i + 1][j + 1].equals("O")) && (gameBoard[i + 2][j + 2].equals("S"))) {
                    return true;
                }
                if ((gameBoard[i][j + 2].equals("S")) && (gameBoard[i + 1][j + 1].equals("O")) && (gameBoard[i + 2][j].equals("S"))) {
                    return true;
                }

            }
        }
        return false;
    }

    public static boolean winner() {

        return (checkHorizantal(value) || checkVertical(value) || checkDiagonal(value));

    }

    public static boolean gameover(int value) {
        for (int i = 0; i < value; i++) {
            for (int j = 0; j < value; j++) {
                if ((gameBoard[i][j]).equals("-")) {
                    return false;

                }
            }
        }
        return true;
    }

}
