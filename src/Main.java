import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cells = "         ";
        String coordinates;
        boolean condition1 = false;
        boolean condition2 = false;
        boolean playing = true;
        int xAsciiValue;
        int yAsciiValue;
        int count = 1;

        printEmptyGrid();

        while (playing) {
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

            if(verifyCellOccupied(cells, xAsciiValue, yAsciiValue)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            cells = insertValueIntoCell(cells, xAsciiValue, yAsciiValue, count);
            printGrid(cells);
            playing = analyzeGame(cells, xAsciiValue, yAsciiValue, count);
            count += 1;
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

    public static boolean isWinner(String cells, char ch, int cellNumber) {
        return switch (cellNumber) {
            case 0 -> cells.charAt(1) == ch && cells.charAt(2) == ch ||
                    cells.charAt(3) == ch && cells.charAt(6) == ch ||
                    cells.charAt(4) == ch && cells.charAt(8) == ch;
            case 2 -> cells.charAt(0) == ch && cells.charAt(1) == ch ||
                    cells.charAt(5) == ch && cells.charAt(8) == ch ||
                    cells.charAt(4) == ch && cells.charAt(6) == ch;
            case 6 -> cells.charAt(0) == ch && cells.charAt(3) == ch ||
                    cells.charAt(7) == ch && cells.charAt(8) == ch ||
                    cells.charAt(4) == ch && cells.charAt(2) == ch;
            case 8 -> cells.charAt(2) == ch && cells.charAt(5) == ch ||
                    cells.charAt(6) == ch && cells.charAt(7) == ch ||
                    cells.charAt(0) == ch && cells.charAt(4) == ch;
            case 1 -> cells.charAt(0) == ch && cells.charAt(2) == ch ||
                    cells.charAt(4) == ch && cells.charAt(7) == ch;
            case 3 -> cells.charAt(4) == ch && cells.charAt(5) == ch ||
                    cells.charAt(0) == ch && cells.charAt(6) == ch;
            case 5 -> cells.charAt(3) == ch && cells.charAt(4) == ch ||
                    cells.charAt(2) == ch && cells.charAt(8) == ch;
            case 7 -> cells.charAt(1) == ch && cells.charAt(4) == ch ||
                    cells.charAt(6) == ch && cells.charAt(8) == ch;
            case 4 -> cells.charAt(1) == ch && cells.charAt(7) == ch ||
                    cells.charAt(3) == ch && cells.charAt(5) == ch ||
                    cells.charAt(2) == ch && cells.charAt(6) == ch ||
                    cells.charAt(0) == ch && cells.charAt(8) == ch;
            default -> false;
        };
    }

    public static boolean analyzeGame(String cells, int x, int y, int count) {
        x -= 49;
        y -= 49;
        int cellNumber = 3 * x + y;

        if (isWinner(cells, 'X', cellNumber) && count % 2 == 1) {
            System.out.println("X wins");
            return false;
        } else if (isWinner(cells, 'O', cellNumber) && count % 2 == 0) {
            System.out.println("O wins");
            return false;
        } else {
            for (int i = 0; i < cells.length(); i++) {
                if (cells.charAt(i) == ' ') {
                    return true;
                }
            }
            System.out.println("Draw");
            return false;
        }
    }

    public static void printGrid(String cells) {
        System.out.println("---------");
        System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " +  cells.charAt(2) + " |");
        System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        System.out.println("---------");
    }

    public static void printEmptyGrid() {
        System.out.println("---------");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("|       |");;
        System.out.println("---------");
    }

    public static boolean verifyCellOccupied(String cells, int x, int y) {
        x -= 49;
        y -= 49;
        int cellNumber = 3 * x + y;
        int cellContent = cells.charAt(cellNumber);

        return cellContent != 32;
    }

    public static String insertValueIntoCell(String cells, int x, int y, int count) {
        x -= 49;
        y -= 49;
        int cellNumber = 3 * x + y;
        if (count % 2 == 1) {
            cells = cells.substring(0, cellNumber) + "X" + cells.substring(cellNumber + 1);
        } else {
            cells = cells.substring(0, cellNumber) + "O" + cells.substring(cellNumber + 1);
        }
        return cells;
    }
}
