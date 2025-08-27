package org.datepicker.UI;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class datepickerUI {

    public static final Target IFRAME =
            Target.the("iframe de la demo del datepicker")
                    .located(By.cssSelector("iframe.demo-frame"));

    public static final Target INPUTDATE =
            Target.the("input date")
                    .located(By.id("datepicker"));

    public static final Target CAPTURMOTH =
            Target.the("capture el mes actual")
                    .locatedBy("//div[@class='ui-datepicker-title']//span[1]");

    public static final Target DAY =
            Target.the("capture el dia")
                    .locatedBy("//a[@data-date='{0}']");

    public static final Target MOUTH =
            Target.the("mouthnext")
                    .locatedBy("//a[@title='Next']");

}


