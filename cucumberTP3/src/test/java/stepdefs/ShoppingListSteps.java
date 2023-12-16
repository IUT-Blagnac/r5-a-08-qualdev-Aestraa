package stepdefs;

import static org.junit.Assert.*;

import java.util.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingListSteps {

    private Set<String> shoppingList = new HashSet<>();
    private String message = "";

    @Given("^I am a user of the shopping list application$")
    public void i_am_a_user_of_the_shopping_list_application() {
    }

    @When("^I add '(.+)' to my list$")
    public void i_add_item_to_my_list(String item) {
        if (!shoppingList.add(item)) {
            message = item + " is already on the list";
        }
    }

    @Then("^'(.+)' should be displayed in my shopping list$")
    public void item_should_be_displayed_in_my_shopping_list(String item) {
        assertTrue(shoppingList.contains(item));
    }

    @Given("{string} is on my shopping list")
    public void is_on_my_shopping_list(String item) {
        shoppingList.add(item);
    }

    @Given("^'(.+)' is already on my shopping list$")
    public void item_is_already_on_my_shopping_list(String item) {
        shoppingList.add(item);
    }

    @When("^I try to add '(.+)' again$")
    public void i_try_to_add_item_again(String item) {
        i_add_item_to_my_list(item);
    }

    @Then("^I should see a message indicating that '(.+)' is already on the list$")
    public void i_should_see_a_message_indicating_that_item_is_already_on_the_list(String item) {
        assertEquals(item + " is already on the list", message);
    }

    @When("^I remove '(.+)' from the list$")
    public void i_remove_item_from_the_list(String item) {
        shoppingList.remove(item);
    }

    @Then("^'(.+)' should no longer be displayed in my shopping list$")
    public void item_should_no_longer_be_displayed_in_my_shopping_list(String item) {
        assertFalse(shoppingList.contains(item));
    }

    @When("I {string} {string} to\\/from my list")
    public void i_perform_action_on_my_list(String action, String item) {
        if ("add".equals(action)) {
            if (!shoppingList.add(item)) {
                message = item + " is already on the list";
            }
        } else if ("remove".equals(action)) {
            shoppingList.remove(item);
        }
    }

    @Then("I should {string} {string}")
    public void i_should_see_the_expectation(String expectation, String item) {
        switch (expectation) {
            case "see 'milk' should be displayed in my shopping list":
                assertTrue(shoppingList.contains(item));
                break;
            case "see a message indicating that 'milk' is already on the list":
                assertEquals(item + " is already on the list", message);
                break;
            case "see 'milk' should no longer be displayed in my shopping list":
                assertFalse(shoppingList.contains(item));
                break;
            default:
                fail("Unexpected expectation: " + expectation);
        }
    }
}
