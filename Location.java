public class Location {
    private String name;
    private String story;
    private boolean isVisited;

    public Location(String name, String story) {
        this.name = name;
        this.story = story;
        this.isVisited = false;
    }

    public void visit() {
        this.isVisited = true;
        System.out.println("【發現景點】: " + name);
        System.out.println("【獲得碎片】: " + story);
    }

    public String getName() { 
        return name; 
    }
}