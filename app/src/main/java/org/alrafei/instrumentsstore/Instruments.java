package org.alrafei.instrumentsstore;

public class Instruments {
    private String name;
    private String description;
    private int imageResource;
    private String type;
    private int stock;
    private float price;
    private int sold;
    private int outOfStock;
    private int id;


    public int getOutOfStock() {
        return outOfStock;
    }

    public int getId() {
        return id;
    }

    public Instruments(int id, String name, String description, int imageResource, String type, int stock, float price, int sold, int outOfStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.sold = sold;
        this.outOfStock = outOfStock;
    }

    public int getSold() {
        return sold;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
