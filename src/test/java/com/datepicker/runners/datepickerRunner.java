package com.datepicker.runners;

import org.datepicker.utils.customRunner.CustomRunner;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CustomRunner.class)
@CucumberOptions(
        features = {"src/test/resources/features/reserva.feature"},
        glue = {"com.datepicker.stepdefinitions", "com.datepicker.hook"},
        monochrome = true, 
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = ""
)
public class datepickerRunner {
}
