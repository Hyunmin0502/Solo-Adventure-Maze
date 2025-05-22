public class Main {
    public static void main(String[] args) {
        Room room = new Room("room1.csv");
        Hero hero = new Hero();

        room.displayRoomWithStatus(hero);

        // 이후 입력 처리 등으로 확장
    }
}
