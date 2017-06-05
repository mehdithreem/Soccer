package soccer.model;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class Dealer {
    private String name;
    private Double leverage;
    private Integer price;

    public Dealer(String name, Double leverage, Integer price) {
        this.name = name;
        this.leverage = leverage;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLeverage() {
        return leverage;
    }

    public void setLeverage(Double leverage) {
        this.leverage = leverage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
