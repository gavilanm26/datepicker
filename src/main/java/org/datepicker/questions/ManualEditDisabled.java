package org.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.datepickerUI;

public class ManualEditDisabled implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        WaitUntil.the(datepickerUI.INPUTDATE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(8000).milliseconds();


        String readonly = datepickerUI.INPUTDATE.resolveFor(actor).getAttribute("readonly");
        String ariaReadonly = datepickerUI.INPUTDATE.resolveFor(actor).getAttribute("aria-readonly");
        boolean hasReadonly = (readonly != null && !readonly.isEmpty());
        boolean ariaIsTrue = (ariaReadonly != null && ("true".equalsIgnoreCase(ariaReadonly) || "1".equals(ariaReadonly)));
        if (hasReadonly || ariaIsTrue) return true;

        String currentValue = datepickerUI.INPUTDATE.resolveFor(actor).getValue();
        return currentValue == null || currentValue.isEmpty();
    }

    public static ManualEditDisabled isEnforced() {
        return new ManualEditDisabled();
    }
}

