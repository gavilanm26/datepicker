package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import org.datepicker.UI.datepickerUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class Day implements Task {

    private final String day;

    public Day(String day) {
        this.day = day;
    }

    @Step("{0} selecciona el día {day}")
    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println("el dia es " + day);
        actor.attemptsTo(
                WaitUntil.the(datepickerUI.DAY.of(day), isClickable()).forNoMoreThan(10).seconds(),
                Click.on(datepickerUI.DAY.of(day))
        );

    }

    public static Day todatepicker(String day) { return Tasks.instrumented(Day.class, day);
    }
}
