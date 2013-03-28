package common.sizzle;

import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.FindsByCssSelector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-27
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */

public class ByCssSelectorExtended extends ByCssSelector {
    private String ownSelector;
    public ByCssSelectorExtended(String selector) {
        super(selector);
        ownSelector = selector;
    }

    @Override
    public WebElement findElement(SearchContext context) {
        try {
            if (context instanceof FindsByCssSelector) {
                return ((FindsByCssSelector) context)
                        .findElementByCssSelector(ownSelector);
            }
        } catch (InvalidElementStateException e) {
            return findElementBySizzleCss(ownSelector);
        }
        throw new WebDriverException(
                "Driver does not support finding an element by selector: "
                        + ownSelector);
    }

    public WebElement findElementBySizzleCss(String cssLocator) {
        injectSizzleIfNeeded();
        String javascriptExpression = "return Sizzle(\"" +cssLocator + "\")";
        List elements = (List)
                ((JavascriptExecutor) getDriver())
                        .executeScript(javascriptExpression);
        if (elements.size() > 0)
            return (WebElement) elements.get(0);
        return null;
    }

    private void injectSizzleIfNeeded() {
        if (!sizzleLoaded())
            injectSizzle();
    }

    public Boolean sizzleLoaded() {
        Boolean loaded;
        try {
            loaded = (Boolean) driver.executeScript("return Sizzle()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public void injectSizzle() {
        driver.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';"
                + "newScript.src = 'https://raw.github.com/jquery/sizzle/master/sizzle.js';"
                + "headID.appendChild(newScript);");
    }
}
