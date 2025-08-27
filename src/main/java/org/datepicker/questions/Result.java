package org.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.datepicker.UI.datepickerUI;

public class Result implements Question<Boolean> {

  private final String expectDate;

  public Result(String expectedProductName) {this.expectDate = expectedProductName;
  }

  @Override
  public Boolean answeredBy(Actor actor) {
    System.out.println("fecha del excel " + expectDate);
    WaitUntil.the(datepickerUI.INPUTDATE, WebElementStateMatchers.isVisible())
            .forNoMoreThan(8000).milliseconds();

    String inputValue = Value.of(datepickerUI.INPUTDATE).answeredBy(actor);
    return expectDate.equals(inputValue);
  }

  public static Result withName(String productName) {
    return new Result(productName);
  }
}
