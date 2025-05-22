import java.io.*;
import java.util.*;

public class RoomLoader {
    public static String[][] loadRoom(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));

        // 첫 줄: 방 크기
        String[] dims = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dims[0].trim());
        int cols = Integer.parseInt(dims[1].trim());

        String[][] grid = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] line = scanner.nextLine().split(",");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line[j].trim();
            }
        }

        scanner.close();
        return grid;
    }
}
