import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cells;
        String coordinates;
        boolean condition1 = false;
        boolean condition2 = false;
        int xAsciiValue;
        int yAsciiValue;
        int x;
        int y;

        System.out.print("Enter cells: ");
        cells = scanner.nextLine();

        printCells(cells);
//        analyzeGame(cells);

        while (true) {
            System.out.println("Enter the coordinates: ");
            coordinates = scanner.nextLine();

            xAsciiValue = coordinates.charAt(0);
            yAsciiValue = coordinates.charAt(2);

            condition1 = xAsciiValue < 49 || xAsciiValue > 57;
            condition2 = yAsciiValue < 49 || yAsciiValue > 57;

            if (condition1 || condition2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            condition1 = xAsciiValue <= 51 && yAsciiValue <= 51;

            if (!condition1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if(!verifyCellOccupied(cells, xAsciiValue, yAsciiValue)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            break;
        }
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

    public static void printCells(String cells) {
        System.out.println("---------");
        System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " +  cells.charAt(2) + " |");
        System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        System.out.println("---------");
    }

    public static boolean verifyCellOccupied(String cells, int x, int y) {
        x -= 49;
        y -= 49;
        int cellNumber = 3 * x + y;
        int cellContent = cells.charAt(cellNumber);

        return cellContent == 95;
    }
}
