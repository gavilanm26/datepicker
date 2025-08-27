package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.datepickerUI;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Date implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Switch.toFrame(datepickerUI.IFRAME.resolveFor(actor)),
                WaitUntil.the(datepickerUI.INPUTDATE, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(datepickerUI.INPUTDATE)

        );

    }

    public static Date todatepicker() {return Tasks.instrumented(Date.class);
    }
}


