package spider.dataobject;

import java.io.Serializable;

/**
 * Created by hackx on 8/2/16.
 */
public class GouMaiJiLuDO implements Serializable {

    private String touZiYongHu;
    private String touZiZiJin;
    private String touziShiJian;

    public String getTouZiYongHu() {
        return touZiYongHu;
    }

    public void setTouZiYongHu(String touZiYongHu) {
        this.touZiYongHu = touZiYongHu;
    }

    public String getTouZiZiJin() {
        return touZiZiJin;
    }

    public void setTouZiZiJin(String touZiZiJin) {
        this.touZiZiJin = touZiZiJin;
    }

    public String getTouziShiJian() {
        return touziShiJian;
    }

    public void setTouziShiJian(String touziShiJian) {
        this.touziShiJian = touziShiJian;
    }

    @Override
    public String toString() {
        return "org.hack.spider.LianJiaLiCai.GouMaiJiLuDO{" +
                "touZiYongHu='" + touZiYongHu + '\'' +
                ", touZiZiJin='" + touZiZiJin + '\'' +
                ", touziShiJian='" + touziShiJian + '\'' +
                '}';
    }
}
