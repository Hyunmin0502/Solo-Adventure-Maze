import java.io.IOException;

public class Room {
    private String[][] grid;
    private int rows;
    private int cols;

    public Room(String filename) {
        try {
            this.grid = RoomLoader.loadRoom(filename);
            this.rows = grid.length;
            this.cols = grid[0].length;
        } catch (IOException e) {
            System.out.println("Failed to load room: " + e.getMessage());
        }
    }

    public void displayRoom() {
        System.out.println("+" + "-".repeat(cols*3) + "+");
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("+" + "-".repeat(cols*3) + "+");
    }

    public void displayRoomWithStatus(Hero hero) {
        // 1. 상태 표시줄
        System.out.printf("HP: %d/%d | Weapon: %s | Key: %s\n",
                hero.getHp(), hero.getMaxHp(),
                hero.getWeapon() == null ? "None" : hero.getWeapon().getName(),
                hero.hasKey() ? "Yes" : "No");

        // 2. 방 테두리 상단
        System.out.println("+" + "-".repeat(cols*3) + "+");

        // 3. 셀 그리기
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-3s", renderCell(grid[i][j]));
            }
            System.out.println("|");
        }

        // 4. 방 테두리 하단
        System.out.println("+" + "-".repeat(cols*3) + "+");
    }

    private String renderCell(String cell) {
        cell = cell.trim();

        if (cell.isEmpty()) return " ";

        // 몬스터 식별
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


}
