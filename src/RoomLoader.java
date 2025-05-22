import java.io.*;
import java.util.*;

public class RoomLoader {
    public static String[][] loadRoom(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));

        // 1. First line: number of rows and columns
        String[] dims = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dims[0].trim());
        int cols = Integer.parseInt(dims[1].trim());

        String[][] grid = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (!scanner.hasNextLine()) {
                throw new IOException("Insufficient number of rows: " + i + "/" + rows);
            }

            String[] line = scanner.nextLine().split(",");

            for (int j = 0; j < cols; j++) {
                if (j < line.length) {
                    grid[i][j] = line[j].trim();
                } else {
                    // If columns are missing, fill with blank
                    grid[i][j] = " ";
                }
            }
        }

        scanner.close();
        return grid;
    }
}
