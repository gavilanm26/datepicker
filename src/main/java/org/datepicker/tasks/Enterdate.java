package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.datepickerUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Enterdate implements Task {

  private final String date;

  public Enterdate(String date) {
    this.date = date;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            WaitUntil.the(datepickerUI.INPUTDATE, isVisible()).forNoMoreThan(10).seconds(),
            Enter.theValue(date).into(datepickerUI.INPUTDATE)
    );

  }

  public static Enterdate todatepicker(String date) { return Tasks.instrumented(Enterdate.class, date);
  }
}
