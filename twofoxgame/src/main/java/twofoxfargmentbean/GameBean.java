package twofoxfargmentbean;

/**
 * Created by chengling on 2016/10/25.
 */
public class GameBean {
    private String logo;
    private String game_name;
    private String type_name;

    private String size;

    private String game_visits;

    private String desc1;

    public GameBean(String desc1, String game_name, String game_visits, String logo, String size, String type_name) {
        this.desc1 = desc1;
        this.game_name = game_name;
        this.game_visits = game_visits;
        this.logo = logo;
        this.size = size;
        this.type_name = type_name;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_visits() {
        return game_visits;
    }

    public void setGame_visits(String game_visits) {
        this.game_visits = game_visits;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public GameBean() {
        super();
    }
}
