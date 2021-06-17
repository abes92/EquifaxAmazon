$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/home.feature");
formatter.feature({
  "name": "end to end testing",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.beforestep({
  "status": "passed"
});
formatter.scenario({
  "name": "search for an item",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user on amazon home page",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_on_amazon_home_page()"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "user enter your book name on search box option",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_enter_your_book_name_on_search_box_option()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\n\tat com.sun.proxy.$Proxy24.sendKeys(Unknown Source)\n\tat homePage.searchPage.setSearchBox(searchPage.java:33)\n\tat stepDefinitions.homePageStepDefinition.user_enter_your_book_name_on_search_box_option(homePageStepDefinition.java:68)\n\tat ✽.user enter your book name on search box option(file:///Users/abestaieb/InterviewQuestions/Amazon/src/test/java/features/home.feature:7)\n",
  "status": "failed"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "user validate your book is displaying properly",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_validate_your_book_is_displaying_properly()"
});
formatter.result({
  "status": "skipped"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "user click on add to cart",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_click_on_add_to_cart()"
});
formatter.result({
  "status": "skipped"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "user click on cart option",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_click_on_cart_option()"
});
formatter.result({
  "status": "skipped"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "user proceed to check out",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.homePageStepDefinition.user_proceed_to_check_out()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "error_message": "java.lang.NullPointerException\n\tat common.WebAPI.cleanUp(WebAPI.java:247)\n\tat stepDefinitions.homePageStepDefinition.closeBrowser(homePageStepDefinition.java:39)\n",
  "status": "failed"
});
formatter.after({
  "error_message": "java.lang.NullPointerException\n\tat stepDefinitions.homePageStepDefinition.tearDown(homePageStepDefinition.java:32)\n",
  "status": "failed"
});
});