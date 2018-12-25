package restaurant.business.menu;

import java.math.BigDecimal;

public class Dish {
    private final int id;
    private final String name;
    private final int calories;
    private final Type type;
    private boolean isVegeterian;
    private BigDecimal price;

    public Dish() {
        this(0, "default", 0, Type.NONE, false, BigDecimal.ZERO);
    }

    public Dish(int id, String name, int calories, Type type, boolean isVegeterian, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.isVegeterian = isVegeterian;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public boolean isVegeterian() {
        return isVegeterian;
    }

    public String isVegeterianString() {
        return isVegeterian ? "vegeterian" : "not vegeterian";
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Dish: %s, cal: %s, type: %s, isVegeterian: %s", name, calories, type, isVegeterianString());
    }

    public enum Type {
        MEET, FISH, NONE;
    }
}
