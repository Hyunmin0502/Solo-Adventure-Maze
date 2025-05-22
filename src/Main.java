import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Room room = new Room("room1.csv");
        Hero hero = new Hero();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            room.displayRoomWithStatus(hero);
            System.out.print("Enter command (u/d/l/r to move, q to quit): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("q")) {
                System.out.println("Game exited.");
                break;
            }

            if ("udlr".contains(input)) {
                room.moveHero(input.charAt(0));
            } else {
                System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }
}
