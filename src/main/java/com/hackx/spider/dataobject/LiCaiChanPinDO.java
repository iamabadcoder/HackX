package com.hackx.spider.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackx on 8/2/16.
 */
public class LiCaiChanPinDO implements Serializable {

    private String title;
    private String kaiShouShiJian;
    private String yuQiNianHuaShouYiLv;
    private String xiangMuQiXian;
    private String xiangMuGuiMo;
    private String xiangMuShouWanLiShi;
    private String touBiaoBiShu;
    private List<GouMaiJiLuDO> gouMaiJiLu = new ArrayList<GouMaiJiLuDO>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKaiShouShiJian() {
        return kaiShouShiJian;
    }

    public void setKaiShouShiJian(String kaiShouShiJian) {
        this.kaiShouShiJian = kaiShouShiJian;
    }

    public String getYuQiNianHuaShouYiLv() {
        return yuQiNianHuaShouYiLv;
    }

    public void setYuQiNianHuaShouYiLv(String yuQiNianHuaShouYiLv) {
        this.yuQiNianHuaShouYiLv = yuQiNianHuaShouYiLv;
    }

    public String getXiangMuQiXian() {
        return xiangMuQiXian;
    }

    public void setXiangMuQiXian(String xiangMuQiXian) {
        this.xiangMuQiXian = xiangMuQiXian;
    }

    public String getXiangMuGuiMo() {
        return xiangMuGuiMo;
    }

    public void setXiangMuGuiMo(String xiangMuGuiMo) {
        this.xiangMuGuiMo = xiangMuGuiMo;
    }

    public String getXiangMuShouWanLiShi() {
        return xiangMuShouWanLiShi;
    }

    public void setXiangMuShouWanLiShi(String xiangMuShouWanLiShi) {
        this.xiangMuShouWanLiShi = xiangMuShouWanLiShi;
    }

    public String getTouBiaoBiShu() {
        return touBiaoBiShu;
    }

    public void setTouBiaoBiShu(String touBiaoBiShu) {
        this.touBiaoBiShu = touBiaoBiShu;
    }

    public List<GouMaiJiLuDO> getGouMaiJiLu() {
        return gouMaiJiLu;
    }

    public void setGouMaiJiLu(List<GouMaiJiLuDO> gouMaiJiLu) {
        this.gouMaiJiLu = gouMaiJiLu;
    }

    @Override
    public String toString() {
        return "org.hack.com.hackx.spider.dataobject.LianJiaLiCai.LiCaiChanPinDO{" +
                "title='" + title + '\'' +
                ", kaiShouShiJian='" + kaiShouShiJian + '\'' +
                ", yuQiNianHuaShouYiLv='" + yuQiNianHuaShouYiLv + '\'' +
                ", xiangMuQiXian='" + xiangMuQiXian + '\'' +
                ", xiangMuGuiMo='" + xiangMuGuiMo + '\'' +
                ", xiangMuShouWanLiShi='" + xiangMuShouWanLiShi + '\'' +
                ", touBiaoBiShu='" + touBiaoBiShu + '\'' +
                ", gouMaiJiLu=" + gouMaiJiLu +
                '}';
    }
}