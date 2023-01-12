
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion;
import com.openclassrooms.entrevoisins.utils.ToolBarSelect;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.JMock1Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsNull.notNullValue;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import java.util.List;



@RunWith(AndroidJUnit4.class)
public class FavouriteNeighbourInstrumentalTest {

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Test
    public void whenClickOnNeighbourList() {

        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(ViewMatchers.withId(R.id.neighbourProfilDetail))
                .check(matches(isDisplayed()));
    }

    @Test
    public void nameNeighboursIsNameOnNeighboursDetail() {

        // Given : position fixé à 0, la liste des voisins et nom du voisin à tester
        final int position = 0;
        List<Neighbour> NeighbourList = DI.getNewInstanceApiService().getNeighbours();
        String nameNeighbourTest = NeighbourList.get(position).getName();

        // Je clic sur le voisin en position 0
        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        // Le nom de la liste est le même que le nom du détail
        onView(ViewMatchers.withId(R.id.prenom1))
                .check(matches(withText(containsString(nameNeighbourTest))));
    }


    @Test
    public void checkInFavouriteListTheNeighbour() {

        final int positionStart = 5;
        final int positionExpected = 1;
        List<Neighbour> NeighbourList = DI.getNewInstanceApiService().getNeighbours();
        String nameNeighbourTest = NeighbourList.get(positionStart).getName();

        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionStart, click()));

        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.addFavourite), isDisplayed()))
                .perform(click());

        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.buttonBack), isDisplayed()))
                .perform(click());

        onView(withId(R.id.tabs)).perform(new ToolBarSelect(1));

        onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.list_favourite_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionExpected, click()));

        onView(ViewMatchers.withId(R.id.prenom1))
                .check(matches(withText(containsString(nameNeighbourTest))));
    }
}