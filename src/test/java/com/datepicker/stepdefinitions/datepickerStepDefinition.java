package com.datepicker.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.datepicker.questions.ManualEditDisabled;
import org.datepicker.questions.Result;
import org.datepicker.tasks.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

public class datepickerStepDefinition {

    @Given("I open the JQuery Datepicker page")
    public void iOpenTheJQueryDatepickerPage() {
        theActorInTheSpotlight().wasAbleTo(
                Access.todatepicker()
        );
    }

    @When("I switch to the iframe containing the calendar")
    public void iSwitchToTheIframeContainingTheCalendar() {
        theActorInTheSpotlight().wasAbleTo(
                Date.todatepicker()
        );
    }

    @When("I click on the date input field")
    public void iClickOnTheDateInputField() {
        theActorInTheSpotlight().wasAbleTo(
                Captur.todatepicker()
        );
    }

    @When("I select the {string} day of the current month")
    public void iSelectThe15thDayOfTheCurrentMonth(String day) {
        theActorInTheSpotlight().wasAbleTo(
                Day.todatepicker(day)
        );
    }

    @When("I select next month")
    public void iSelectNextMonth() {
        theActorInTheSpotlight().wasAbleTo(
                Mouthnext.todatepicker()
        );
    }

    @When("I enter the date {string}")
    public void iEnterTheDate(String date) throws InterruptedException {
        theActorInTheSpotlight().wasAbleTo(
                Enterdate.todatepicker(date)
        );

        Thread.sleep(3000);
    }

    @Then("the selected date should be displayed in the input field {string}")
    public void theSelectedDateShouldBeDisplayedInTheInputField(String date) {
        theActorInTheSpotlight().should(
            seeThat("Validar que la fecha seleccionada aparece en el campo de texto: " + date,
                Result.withName(date),
                is(true))
        );
    }

    @Then("Validate that manual editing is not allowed and that only one date can be selected from the calendar")
    public void validateThatManualEditingIsNotAllowedAndThatOnlyOneDateCanBeSelectedFromTheCalendar() {
        theActorInTheSpotlight().should(
                seeThat("Validar que el campo de fecha es de solo lectura (sin edición manual)",
                        ManualEditDisabled.isEnforced(),
                        is(true))
        );
    }
}
