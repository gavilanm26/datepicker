package org.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.annotations.Step;
import org.datepicker.UI.datepickerUI;

import java.time.Duration;

public class ManualEditDisabled implements Question<Boolean> {

    @Step("{0} verifica si la edición manual está deshabilitada")
    @Override
    public Boolean answeredBy(Actor actor) {
        // Esperar a que el elemento sea visible
        WaitUntil.the(datepickerUI.INPUTDATE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(Duration.ofSeconds(8));

        // Usar Questions para obtener atributos de manera más limpia
        String readonly = Attribute.of(datepickerUI.INPUTDATE).named("readonly").answeredBy(actor);
        String ariaReadonly = Attribute.of(datepickerUI.INPUTDATE).named("aria-readonly").answeredBy(actor);
        
        boolean hasReadonly = (readonly != null && !readonly.isEmpty());
        boolean ariaIsTrue = (ariaReadonly != null && 
                            ("true".equalsIgnoreCase(ariaReadonly) || "1".equals(ariaReadonly)));
        
        if (hasReadonly || ariaIsTrue) return true;

        // Usar Value para obtener el valor del campo
        String currentValue = Value.of(datepickerUI.INPUTDATE).answeredBy(actor);
        return currentValue == null || currentValue.isEmpty();
    }

    public static ManualEditDisabled isEnforced() {
        return new ManualEditDisabled();
    }
}

