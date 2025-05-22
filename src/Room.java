import java.io.IOException;

public class Room {
    private String[][] grid;
    private int rows;
    private int cols;
    private int heroRow;
    private int heroCol;

    public Room(String filename) {
        try {
            this.grid = RoomLoader.loadRoom(filename);
            this.rows = grid.length;
            this.cols = grid[0].length;

            // Find the initial position of the hero '@'
            boolean found = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j].equals("@")) {
                        heroRow = i;
                        heroCol = j;
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (!found) {
                System.out.println("Warning: No hero '@' found in the room.");
            }

        } catch (IOException e) {
            System.out.println("Failed to load room: " + e.getMessage());
        }
    }

    public void displayRoom() {
        System.out.println("+" + "-".repeat(cols * 3) + "+");
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("+" + "-".repeat(cols * 3) + "+");
    }

    public void displayRoomWithStatus(Hero hero) {
        // 1. Status bar
        System.out.printf("HP: %d/%d | Weapon: %s | Key: %s\n",
                hero.getHp(), hero.getMaxHp(),
                hero.getWeapon() == null ? "None" : hero.getWeapon().getName(),
                hero.hasKey() ? "Yes" : "No");

        // 2. Top border
        System.out.println("+" + "-".repeat(cols * 3) + "+");

        // 3. Grid content
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-3s", renderCell(grid[i][j]));
            }
            System.out.println("|");
        }

        // 4. Bottom border
        System.out.println("+" + "-".repeat(cols * 3) + "+");
    }

    private String renderCell(String cell) {
        cell = cell.trim();

        if (cell.isEmpty()) return " ";

        // Identify monster by prefix
        if (cell.startsWith("G:")) return "♙";
        if (cell.startsWith("O:")) return "♞";
        if (cell.startsWith("T:")) return "♖";

        return switch (cell) {
            case "@" -> "☻";
            case "m" -> "♥";
            case "B" -> "♛";
            case "S" -> "|";
            case "W" -> "†";
            case "X" -> "⚔";
            case "D" -> "☗";
            case "*" -> "⛁";
            case " " -> " ";
            default -> "?";
        };
    }

    public void moveHero(char dir) {
        int newRow = heroRow;
        int newCol = heroCol;

        switch (dir) {
            case 'u' -> newRow--;
            case 'd' -> newRow++;
            case 'l' -> newCol--;
            case 'r' -> newCol++;
            default -> {
                System.out.println("Invalid direction!");
                return;
            }
        }

        // Check boundaries
        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
            System.out.println("You hit the wall!");
            return;
        }

        String target = grid[newRow][newCol];

        // Block movement if cell has monster or door
        if (target.startsWith("G") || target.startsWith("O") || target.startsWith("T") || target.equals("D")) {
            System.out.println("You can't move there.");
            return;
        }

        // Move the hero
        grid[heroRow][heroCol] = " ";
        heroRow = newRow;
        heroCol = newCol;
        grid[heroRow][heroCol] = "@";


    }
}
