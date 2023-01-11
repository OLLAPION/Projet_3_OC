package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class FavouriteNeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getFavouriteNeighboursWithSuccess() {
        List<Neighbour> favouriteNeighbours = service.getNeighboursFavourite();
        List<Neighbour> expectedFavouriteNeighbours = DummyNeighbourGenerator.DUMMY_Favourite_NEIGHBOURS;
        assertThat(favouriteNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavouriteNeighbours.toArray()));
    }

    @Test
    public void addFavouriteNeighbourWithSuccess() {
        Neighbour neighbourFavouriteToAdd = service.getNeighbours().get(0);
        service.addFavouriteNeighbour(neighbourFavouriteToAdd);
        assertTrue(service.getNeighboursFavourite().contains(neighbourFavouriteToAdd));
    }

    @Test
    public void deleteFavouriteNeighbourWithSuccess() {
        Neighbour neighbourFavouriteToAdd = service.getNeighbours().get(7);
        service.addFavouriteNeighbour(neighbourFavouriteToAdd);
        Neighbour neighbourFavouriteToDelete = service.getNeighboursFavourite().get(1);
        service.deleteFavouriteNeighbour(neighbourFavouriteToDelete);
        assertFalse(service.getNeighboursFavourite().contains(neighbourFavouriteToDelete));

    }
}
