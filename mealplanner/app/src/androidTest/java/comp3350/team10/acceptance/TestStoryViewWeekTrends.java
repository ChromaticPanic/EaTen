package comp3350.team10.acceptance;

import org.junit.*;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;
import comp3350.team10.presentation.ActivityDailyProgress;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewWeekTrends {
//    @Rule
//    public ActivityScenarioRule<ActivityMealDiary> activityRuleMeal = new ActivityScenarioRule<>(ActivityMealDiary.class);
//
//    @Rule
//    public ActivityScenarioRule<ActivityTrends> activityRuleTrends = new ActivityScenarioRule<>(ActivityTrends.class);
    @Rule
    public ActivityTestRule<ActivityDailyProgress> activityRuleTrends = new ActivityTestRule<>(ActivityDailyProgress.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("ConsumedCalories")).check(matches(isDisplayed()));
    }
}