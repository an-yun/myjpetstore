package myjpetstore.domain;

/**
 * Created by zuo on 2015/6/13.
 */
public class Bannerdata {
    private String favcategory;
    private String bannername;
    public Bannerdata(){}
    public Bannerdata(String favcategory, String bannername) {
        this.favcategory = favcategory;
        this.bannername = bannername;
    }

    public String getFavcategory() {
        return favcategory;
    }

    public void setFavcategory(String favcategory) {
        this.favcategory = favcategory;
    }

    public String getBannername() {
        return bannername;
    }

    public void setBannername(String bannername) {
        this.bannername = bannername;
    }
}
