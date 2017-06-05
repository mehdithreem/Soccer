package soccer.model;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class Coach {
    private String name;
    private String duty;
    private Integer experience;
    private Integer price;

    public Coach(String name, String duty, Integer experience, Integer price) {
        this.name = name;
        this.duty = duty;
        this.experience = experience;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
