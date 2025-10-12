package main.java.com.right.github.core;

public class SelectableItem {
    private final String name;
    private final int id;
    public SelectableItem(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
}
