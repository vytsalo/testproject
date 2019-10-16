package entities;

public class GoodsItem{
    String name;
    float price;
    public GoodsItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String toString(){
        return name + " : " + price;
    }
}