package soccer.model;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class Stadium {
    private String name;
    private Integer capacity;
    private Integer price;

    public Stadium(String name, Integer capacity, Integer price) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
