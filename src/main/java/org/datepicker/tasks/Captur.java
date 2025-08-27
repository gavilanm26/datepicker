package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import org.datepicker.UI.datepickerUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Captur implements Task {

    @Step("{0} captura el mes actual")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(datepickerUI.CAPTURMOTH, isVisible()).forNoMoreThan(10).seconds()
        );

        String monthText = actor.asksFor(Text.of(datepickerUI.CAPTURMOTH));
        actor.remember("month", monthText);
        actor.attemptsTo(
                Ensure.that(monthText).matches("El mes capturado debe ser August", m -> m.equals("August"))
        );
    }

    public static Captur todatepicker() { return Tasks.instrumented(Captur.class);}

}
