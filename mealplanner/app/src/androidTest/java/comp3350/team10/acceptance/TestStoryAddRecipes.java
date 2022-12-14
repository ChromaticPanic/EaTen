package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static comp3350.team10.utils.Utils.clickChildViewWithId;
import static comp3350.team10.utils.Utils.selectTabAtPosition;
import static comp3350.team10.utils.Utils.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team10.R;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.DataAccess;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryAddRecipes {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.recipeBookNav)).perform(click());
    }

    @Test
    public void user_can_see_app_navigation() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_browse_recipes() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Grain of Rice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Steak"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Banana"))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Orange Juice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka OJ"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka Tonic"))));
    }

    @Test
    public void user_is_warned_invalid_add_food_input() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test bad food"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.dialogRecipeTitle)).perform(scrollTo());
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.dialogRecipeTitle)).perform(scrollTo());
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.dialogRecipeTitle)).perform(scrollTo());
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.dialogRecipeTitle)).perform(scrollTo());
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test bad meal"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo()).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.ingredientError)).perform(scrollTo()).check(matches(hasErrorText("input list cannot be null and requires length between 0 and 9999")));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnOk)).perform( click());

        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnOk)).perform( click());

        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999")));
        onView(withId(R.id.btnCancel)).perform( click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform( click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        //onView(withId(R.id.dialogRecipeNameInput)).perform(click());
        onView(withId(R.id.dialogRecipeNameInput)).perform(scrollTo(), clearText(), typeText("Test bad drink"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo()).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());

        onView(withId(R.id.isVegan)).perform(scrollTo(), click());
        onView(withId(R.id.isVegetarian)).perform(scrollTo(), click());
        onView(withId(R.id.isGluteenFree)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo()).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        onView(withId(R.id.btnOk)).perform(click());

        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnOk)).perform( click());

        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999")));
        onView(withId(R.id.btnCancel)).perform( click());
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform( click());

        cleanup();
    }

    private void cleanup() {
        DataAccess hsql = DBSelector.getSharedDB();
        hsql.removeTestData();
    }

    @Test
    public void user_can_add_food() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Celery"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("oz")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("oz"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isSpicy)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isVegetarian)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Celery"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Something else"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Celery"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Something else"))));

        cleanup();
    }

    @Test
    public void user_can_add_meal() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test MCRibs"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test MCRibs"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test modMeal"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("11"));
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("g")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.btnOk)).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(isRoot()).perform(waitId(R.id.recipeRecyclerView, 5000));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test modMeal"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test dupeMeal"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test dupeMeal"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test rmIngredientMeal"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test rmIngredientMeal"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiIngredient"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiDrinkIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mint"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Lime"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiDrinkIngredient"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiMealIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 2items"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiMealIngredient"))));
        cleanup();
    }

    @Test
    public void user_can_add_simple_drinks() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Drink1"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Drink2"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("oz")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("oz"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isSpicy)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isVegetarian)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink2"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Something else"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink2"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Something else"))));

        cleanup();
    }

    @Test
    public void user_can_add_complex_drinks() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test 2 sugar water"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test 2 sugar water"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test modDrink"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("11"));
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("g")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.btnOk)).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(scrollTo());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(isRoot()).perform(waitId(R.id.recipeRecyclerView, 5000));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test modDrink"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test dupeDrink"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test dupeDrink"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test rmIngredientDrink"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test rmIngredientDrink"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiIngredient"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiDrinkIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mint"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Lime"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiDrinkIngredient"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test multiMealIngredient"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 2items"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test multiMealIngredient"))));
        cleanup();

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test hasSubDrink"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Lime"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mint"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("11"));
        onView(withId(R.id.isSubstitute)).perform(click());
        onView(withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test hasSubDrink"))));

        cleanup();
    }
}