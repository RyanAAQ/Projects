package turtle;

public enum Directions {
    EAST("North", "South"),
    NORTH("West", "East"),
    SOUTH("East", "West"),
    WEST("South", "North");

    private String left;
    private String right;

    Directions(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public Directions getLeft() {
        return Directions.valueOf(this.left.toUpperCase());
    }

    public Directions getRight() {
        return Directions.valueOf(this.right.toUpperCase());
    }
}
