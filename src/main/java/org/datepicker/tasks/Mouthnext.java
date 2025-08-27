package org.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.datepickerUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class Mouthnext implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            WaitUntil.the(datepickerUI.MOUTH, isClickable()).forNoMoreThan(10).seconds(),
            Click.on(datepickerUI.MOUTH)
    );

  }

  public static Mouthnext todatepicker() { return Tasks.instrumented(Mouthnext.class);
  }
}
