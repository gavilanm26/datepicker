package org.datepicker.UI;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.targets.Target;

@DefaultUrl("page:webdriver.base.url")
public class AccessUI extends UIInteractions {
    public static final Target VALIDATEPICKER =
            Target.the("validar titulo del datepicker")
                    .locatedBy("//h1[normalize-space(text())='Datepicker']");

}
