package myjpetstore.domain;

/**
 * Created by zuo on 2015/6/6.
 */
public class Profile {
    private String userid;
    private String langpref;
    private String favcategory;
    private int mylistopt;
    private int banneropt;
    public Profile(){}
    public Profile(String userid, String langpref, String favcategory, int mylistopt, int banneropt) {
        this.userid = userid;
        this.langpref = langpref;
        this.favcategory = favcategory;
        this.mylistopt = mylistopt;
        this.banneropt = banneropt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLangpref() {
        return langpref;
    }

    public void setLangpref(String langpref) {
        this.langpref = langpref;
    }

    public String getFavcategory() {
        return favcategory;
    }

    public void setFavcategory(String favcategory) {
        this.favcategory = favcategory;
    }

    public int getMylistopt() {
        return mylistopt;
    }

    public void setMylistopt(int mylistopt) {
        this.mylistopt = mylistopt;
    }

    public int getBanneropt() {
        return banneropt;
    }

    public void setBanneropt(int banneropt) {
        this.banneropt = banneropt;
    }
}
