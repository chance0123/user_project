package twofoxfargmentbean;

/**
 * Created by chengling on 2016/10/24.
 */
public class ActivityBean {
    private String end_time;
    private String logo;
    private String title;
    private String supports;
    private String comments;
    private String sn;

    public ActivityBean(String end_time, String logo, String title, String supports, String comments, String sn) {
        this.end_time = end_time;
        this.logo = logo;
        this.title = title;
        this.supports = supports;
        this.comments = comments;
        this.sn = sn;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSupports() {
        return supports;
    }

    public void setSupports(String supports) {
        this.supports = supports;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
