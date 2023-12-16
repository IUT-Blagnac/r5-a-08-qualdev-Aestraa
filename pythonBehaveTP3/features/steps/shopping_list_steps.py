from behave import given, when, then, step
from assertpy import assert_that

shopping_list = set()
message = ""

@given('I am a user of the shopping list application')
def step_impl(context):
    pass

@given('"{item}" is on my shopping list')
def step_impl(context, item):
    shopping_list.add(item)

@given('"{item}" is already on my shopping list')
def step_impl(context, item):
    shopping_list.add(item)

@when('I try to add "{item}" again')
def step_impl(context, item):
    global message
    if item in shopping_list:
        message = f"{item} is already on the list"
    else:
        shopping_list.add(item)

@when('I add "{item}" to my list')
def step_impl(context, item):
    global message
    if item in shopping_list:
        message = f"{item} is already on the list"
    else:
        shopping_list.add(item)

@then('"{item}" should be displayed in my shopping list')
def step_impl(context, item):
    assert_that(shopping_list).contains(item)

@when('I remove "{item}" from the list')
def step_impl(context, item):
    shopping_list.discard(item)  # discard won't raise an error if the item is not present

@then('I should see a message indicating that "{item}" is already on the list')
def step_impl(context, item):
    assert_that(message).is_equal_to(f"{item} is already on the list")

@then('"{item}" should no longer be displayed in my shopping list')
def step_impl(context, item):
    assert_that(shopping_list).does_not_contain(item)

@when('I "{action}" "{item}" to/from my list')
def action_on_my_list(context, action, item):
    global message
    if action == "add":
        if item in shopping_list:
            message = f"{item} is already on the list"
        else:
            shopping_list.add(item)
    elif action == "remove":
        shopping_list.discard(item)

@then('I should "{expectation}" "{item}"')
def step_impl(context, expectation, item):
    if expectation == "see 'milk' should be displayed in my shopping list":
        assert_that(shopping_list).contains(item)
    elif expectation == "see a message indicating that 'milk' is already on the list":
        assert_that(message).is_equal_to(f"{item} is already on the list")
    elif expectation == "see 'milk' should no longer be displayed in my shopping list":
        assert_that(shopping_list).does_not_contain(item)