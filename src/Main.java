import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();

        System.out.println("---------");
        System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " +  cells.charAt(2) + " |");
        System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        System.out.println("---------");

        analyzeGame(cells);
    }

    public static boolean countElements(String cells) {
        int count = 0;

        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == 'X') {
                count++;
            }
            if (cells.charAt(i) == 'O') {
                count--;
            }
        }

        return count == 0 || Math.abs(count) == 1;
    }

    public static boolean isWinner(String cells, char ch) {
        if (cells.charAt(4) == ch) {
            return  cells.charAt(0) == ch && cells.charAt(8) == ch ||
                    cells.charAt(1) == ch && cells.charAt(7) == ch ||
                    cells.charAt(2) == ch && cells.charAt(6) == ch ||
                    cells.charAt(3) == ch && cells.charAt(5) == ch;
        } else if (cells.charAt(0) == ch) {
            return  cells.charAt(1) == ch && cells.charAt(2) == ch ||
                    cells.charAt(3) == ch && cells.charAt(6) == ch;
        } else if (cells.charAt(2) == ch) {
            return  cells.charAt(5) == ch && cells.charAt(8) == ch;
        } else if (cells.charAt(6) == ch) {
            return  cells.charAt(7) == ch && cells.charAt(8) == ch;
        } else {
            return  false;
        }
    }

    public static void analyzeGame(String cells) {
        if (!countElements(cells) || (isWinner(cells, 'X') && isWinner(cells, 'O'))) {
            System.out.println("Impossible");
        } else if (isWinner(cells, 'X')) {
            System.out.println("X wins");
        } else if (isWinner(cells, 'O')) {
            System.out.println("O wins");
        } else {
            for (int i = 0; i < cells.length(); i++) {
                if (cells.charAt(i) == '_') {
                    System.out.println("Game not finished");
                    return;
                }
            }
            System.out.println("Draw");
        }
    }
}
