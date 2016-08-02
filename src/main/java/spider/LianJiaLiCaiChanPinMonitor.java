package spider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hackx on 8/2/16.
 */
public class LianJiaLiCaiChanPinMonitor {

    public static void main(String[] args) {
        LianJiaLiCaiChanPinMonitor lianJiaLiCaiChanPinMonitor = new LianJiaLiCaiChanPinMonitor();
        WebDriver driver = lianJiaLiCaiChanPinMonitor.initDriver();
        lianJiaLiCaiChanPinMonitor.monitor(driver, "https://licai.lianjia.com/licai");
    }

    private WebDriver initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private void monitor(WebDriver driver, String target) {
        while (true) {
            driver.get(target);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            java.util.List<WebElement> tbodyElements = new ArrayList<WebElement>();
            java.util.List<WebElement> trElements = new ArrayList<WebElement>();
            try {
                tbodyElements = driver.findElements(By.tagName("tbody"));
                if (null != tbodyElements && tbodyElements.size() > 0) {
                    trElements = tbodyElements.get(0).findElements(By.tagName("tr"));
                    if (null != trElements && trElements.size() == 10) {
                        for (WebElement trElement : trElements) {
                            WebElement gmElement = trElement.findElement(By.className("gm"));
                            WebElement bidElement = trElement.findElement(By.className("bid"));
                            if (null != gmElement && null != bidElement) {
                                if (!bidElement.getText().contains("已售") && bidElement.isEnabled() && gmElement.getText().length() > 7) {
                                    beep();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    System.out.printf("error occur when sleep");
                }
            }
        }
    }

    private void beep() {
        for (int k = 0; k < 10; k++) {
            Frame f = new Frame();
            Toolkit kit = f.getToolkit();
            kit.beep();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

