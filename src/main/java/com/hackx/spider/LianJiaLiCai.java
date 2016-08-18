package com.hackx.spider;

import com.hackx.spider.dataobject.GouMaiJiLuDO;
import com.hackx.spider.dataobject.LiCaiChanPinDO;
import com.hackx.common.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackx on 8/2/16.
 */
public class LianJiaLiCai {

    static String licaiIndex = "https://licai.lianjia.com/licai";

    public static void main(String[] args) throws Exception {
        LianJiaLiCai lianJiaLiCai = new LianJiaLiCai();
        WebDriver driver = lianJiaLiCai.initDriver();
//        lianJiaLiCaiSpider.login(driver, loginIndex, userName, password);
        driver.get(licaiIndex);
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int itemIndex = 8000; itemIndex < 15000; itemIndex++) {
            LiCaiChanPinDO liCaiChanPinDO = lianJiaLiCai.getLiCaiChanPinInfo(driver, itemIndex);
            for (int i = 0; i < liCaiChanPinDO.getGouMaiJiLu().size(); i++) {
                GouMaiJiLuDO gouMaiJiLuDO = liCaiChanPinDO.getGouMaiJiLu().get(i);
                FileUtil.writeToFile("lianjialicai.txt",
                        itemIndex + "\t" +
                                liCaiChanPinDO.getTitle() + "\t" +
                                liCaiChanPinDO.getKaiShouShiJian() + "\t" +
                                liCaiChanPinDO.getYuQiNianHuaShouYiLv() + "\t" +
                                liCaiChanPinDO.getXiangMuQiXian() + "\t" +
                                liCaiChanPinDO.getXiangMuGuiMo() + "\t" +
                                liCaiChanPinDO.getXiangMuShouWanLiShi() + "\t" +
                                liCaiChanPinDO.getTouBiaoBiShu() + "\t" +
                                gouMaiJiLuDO.getTouZiYongHu() + "\t" +
                                gouMaiJiLuDO.getTouZiZiJin() + "\t" +
                                gouMaiJiLuDO.getTouziShiJian());
            }
        }
    }

    private WebDriver initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

//    private void login(WebDriver driver, String indexUrl, String userName, String password) throws Exception {
//        driver.get(indexUrl);
//        WebElement nameElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
//        nameElement.sendKeys(userName);
//        WebElement passElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
//        passElement.sendKeys(password);
//        try {
//            Thread.sleep(8000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private LiCaiChanPinDO getLiCaiChanPinInfo(WebDriver driver, int itemIndex) {
        System.out.println("itemIndex:" + itemIndex);
        LiCaiChanPinDO liCaiChanPinDO = new LiCaiChanPinDO();
        driver.get(licaiIndex + "_" + String.valueOf(itemIndex) + ".html");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pageSource = driver.getPageSource();
        if (null == pageSource || pageSource.contains("������")) {
            return liCaiChanPinDO;
        }
        parseHtml(pageSource, liCaiChanPinDO);
        getShouWanLiShiAndBiShu(driver, liCaiChanPinDO);
        int touBiaoBiShu = 0;
        int totalPageSize = 1;
        if (null != liCaiChanPinDO.getTouBiaoBiShu()) {
            touBiaoBiShu = Integer.parseInt(liCaiChanPinDO.getTouBiaoBiShu().trim());
            totalPageSize = touBiaoBiShu / 10 + (touBiaoBiShu % 10 != 0 ? 1 : 0);
        }
        getGouMaiJiLuInfo(driver, liCaiChanPinDO);
        for (int currPageNum = 1; currPageNum < totalPageSize; currPageNum++) {
            WebElement nextPageElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("nextPage")));
            if (null != nextPageElement) {
                try {
                    nextPageElement.click();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getGouMaiJiLuInfo(driver, liCaiChanPinDO);
            }
        }
        return liCaiChanPinDO;
    }

    private void parseHtml(String pageSource, LiCaiChanPinDO liCaiChanPinDO) {
        Document document = Jsoup.parse(pageSource);

        Elements finContentElements = document.getElementsByClass("fin_content");
        if (null != finContentElements && finContentElements.size() > 0) {
            Element finContentElement = finContentElements.get(0);

            // title
            Elements bTagElements = finContentElement.getElementsByTag("b");
            if (null != bTagElements && bTagElements.size() > 0) {
                Element bTagElement = bTagElements.get(0);
                liCaiChanPinDO.setTitle(bTagElement.text());
            }

            // kaiShouShiJian
            Elements upTimeElements = finContentElement.getElementsByClass("up_time");
            if (null != upTimeElements && upTimeElements.size() > 0) {
                Element upTimeElement = upTimeElements.get(0);
                liCaiChanPinDO.setKaiShouShiJian(upTimeElement.text().replace("����ʱ�䣺", ""));
            }

            // yuQiNianHuaShouYiLv    xiangMuQiXian    xiangMuGuiMo
            Elements pb20Elements = finContentElement.getElementsByClass("pb20");
            if (null != pb20Elements && pb20Elements.size() > 0) {
                Element pb20TagElement = pb20Elements.get(0);
                Elements liElements = pb20TagElement.getElementsByTag("li");
                for (Element liElement : liElements) {
                    String liText = liElement.text();
                    if (liText.contains("����")) {
                        liCaiChanPinDO.setYuQiNianHuaShouYiLv(liElement.text());
                    } else if (liText.contains("����")) {
                        liCaiChanPinDO.setXiangMuQiXian(liElement.text());
                    } else if (liText.contains("��ģ")) {
                        liCaiChanPinDO.setXiangMuGuiMo(liElement.text());
                    }
                }
            }
        }
    }

    private static void getShouWanLiShiAndBiShu(WebDriver driver, LiCaiChanPinDO liCaiChanPinDO) {
        WebElement fullTimeElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("full_time")));
        if (null != fullTimeElement) {
            List<WebElement> ddTagElements = fullTimeElement.findElements(By.tagName("dd"));
            if (null != ddTagElements && ddTagElements.size() > 0) {
                for (WebElement ddTagElement : ddTagElements) {
                    if (ddTagElement.getText().contains("��ʱ")) {
                        liCaiChanPinDO.setXiangMuShouWanLiShi(ddTagElement.getText().trim().replace("\n", ""));
                    } else if (ddTagElement.getText().contains("Ͷ��")) {
                        liCaiChanPinDO.setTouBiaoBiShu(ddTagElement.getText().replace("Ͷ�������", "").replace("��", "").replace("\n", ""));
                    }
                }
            }
        }
    }

    private static void getGouMaiJiLuInfo(WebDriver driver, LiCaiChanPinDO liCaiChanPinDO) {
        try {
            WebElement fullListElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("full_list")));
            if (null != fullListElement) {
                List<WebElement> tbodyTagElements = fullListElement.findElements(By.tagName("tbody"));
                if (null != tbodyTagElements && tbodyTagElements.size() > 0) {
                    WebElement tbodyTagElement = tbodyTagElements.get(0);
                    List<WebElement> trTagElements = tbodyTagElement.findElements(By.tagName("tr"));
                    if (null != trTagElements && trTagElements.size() > 0) {
                        for (WebElement trTagElement : trTagElements) {
                            List<WebElement> tdTagElements = findTagElements(trTagElement, "td");
                            if (null != tdTagElements && tdTagElements.size() > 0) {
                                GouMaiJiLuDO gouMaiJiLuDO = new GouMaiJiLuDO();
                                for (WebElement tdTagElement : tdTagElements) {
                                    String tdTagText = tdTagElement.getText(); // exeception
                                    if (tdTagText.contains("*")) {
                                        gouMaiJiLuDO.setTouZiYongHu(tdTagText);
                                    } else if (tdTagText.contains("Ԫ")) {
                                        gouMaiJiLuDO.setTouZiZiJin(tdTagText);
                                    } else if (tdTagText.contains(":")) {
                                        gouMaiJiLuDO.setTouziShiJian(tdTagText);
                                    }
                                }
                                liCaiChanPinDO.getGouMaiJiLu().add(gouMaiJiLuDO);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exeception occur when getGouMaiJiLuInfo");
            FileUtil.writeToFile("error.txt", liCaiChanPinDO.toString() + e);
        }
    }

    private static List<WebElement> findTagElements(WebElement parentElement, String tagName) {
        List<WebElement> tagElements = new ArrayList<WebElement>();
        for (int curr_retry_time = 1; curr_retry_time <= 5/*MAX_RETRY_TIMES*/; curr_retry_time++) {
            boolean breakIt = true;
            try {
                tagElements = parentElement.findElements(By.tagName(tagName));
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    System.out.println("Exeception occur when findTagElements");
                    FileUtil.writeToFile("error.txt", e.getMessage());
                    breakIt = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        System.out.printf("error occur when sleep");
                    }
                }
            }
            if (breakIt) {
                break;
            }
        }
        return tagElements;
    }
}
