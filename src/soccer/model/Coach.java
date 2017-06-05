package soccer.model;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class Coach {
    private String name;
    private String duty;
    private Integer experience;

    public Coach(String name, String duty, Integer experience) {
        this.name = name;
        this.duty = duty;
        this.experience = experience;
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
}
