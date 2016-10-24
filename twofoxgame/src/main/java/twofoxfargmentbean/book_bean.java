package twofoxfargmentbean;

/**
 * Created by chengling on 2016/10/24.
 */
public class Book_bean {
    private String game_id;
    private String game_name;
    private String img;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Book_bean(String game_id, String game_name, String img) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.img = img;
    }

    public Book_bean() {
        super();
    }
}
