package common.sizzle;

import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-27
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class ByExtended extends By {
    public static By cssSelector(final String selector) {
        if (selector == null)
            throw new IllegalArgumentException(
                    "Cannot find elements when the selector is null");
        return new ByCssSelectorExtended(selector);
    }
}
