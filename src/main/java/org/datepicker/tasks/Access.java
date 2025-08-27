package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.AccessUI;

public class Access implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(AccessUI.class),
                WaitUntil.the(AccessUI.VALIDATEPICKER, WebElementStateMatchers
                                .isVisible())
                        .forNoMoreThan(20)
                        .seconds()
        );


    }

    public static Performable todatepicker() {
        return Tasks.instrumented(Access.class);
    }
}

