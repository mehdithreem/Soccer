package soccer.model;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class Player {
    private String name;
    private String expertise;
    private Integer amadegi;
    private Integer ghodrat_badani;
    private Integer ghodrat_pass;
    private Integer toop_giri;
    private Integer ghodrat_golzani;
    private Integer ghodrat_shoot;
    private Integer sorat;
    private Integer darvazebani;

    private Integer shirtNumber;
    private String role;

    public Player(String name, String expertise, Integer amadegi, Integer ghodrat_badani, Integer ghodrat_pass, Integer toop_giri, Integer ghodrat_golzani, Integer ghodrat_shoot, Integer sorat, Integer darvazebani, Integer shirtNumber, String role) {
        this.name = name;
        this.expertise = expertise;
        this.amadegi = amadegi;
        this.ghodrat_badani = ghodrat_badani;
        this.ghodrat_pass = ghodrat_pass;
        this.toop_giri = toop_giri;
        this.ghodrat_golzani = ghodrat_golzani;
        this.ghodrat_shoot = ghodrat_shoot;
        this.sorat = sorat;
        this.darvazebani = darvazebani;
        this.shirtNumber = shirtNumber;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Integer getAmadegi() {
        return amadegi;
    }

    public void setAmadegi(Integer amadegi) {
        this.amadegi = amadegi;
    }

    public Integer getGhodrat_badani() {
        return ghodrat_badani;
    }

    public void setGhodrat_badani(Integer ghodrat_badani) {
        this.ghodrat_badani = ghodrat_badani;
    }

    public Integer getGhodrat_pass() {
        return ghodrat_pass;
    }

    public void setGhodrat_pass(Integer ghodrat_pass) {
        this.ghodrat_pass = ghodrat_pass;
    }

    public Integer getToop_giri() {
        return toop_giri;
    }

    public void setToop_giri(Integer toop_giri) {
        this.toop_giri = toop_giri;
    }

    public Integer getGhodrat_golzani() {
        return ghodrat_golzani;
    }

    public void setGhodrat_golzani(Integer ghodrat_golzani) {
        this.ghodrat_golzani = ghodrat_golzani;
    }

    public Integer getGhodrat_shoot() {
        return ghodrat_shoot;
    }

    public void setGhodrat_shoot(Integer ghodrat_shoot) {
        this.ghodrat_shoot = ghodrat_shoot;
    }

    public Integer getSorat() {
        return sorat;
    }

    public void setSorat(Integer sorat) {
        this.sorat = sorat;
    }

    public Integer getDarvazebani() {
        return darvazebani;
    }

    public void setDarvazebani(Integer darvazebani) {
        this.darvazebani = darvazebani;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
