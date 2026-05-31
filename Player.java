import java.util.ArrayList;

public class Player {
    private int x, y;
    private ArrayList<String> inventory = new ArrayList<>();

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        System.out.println("玩家現在位置: (" + x + ", " + y + ")");
    }

    public void interact(Location loc) {
        inventory.add(loc.getName());
        loc.visit();
    }
}