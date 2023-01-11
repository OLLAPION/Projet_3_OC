package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> neighboursFavourite = DummyNeighbourGenerator.generateFavouriteNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getNeighboursFavourite() {
        return neighboursFavourite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public void deleteFavouriteNeighbour(Neighbour neighbour) {
        neighboursFavourite.remove(neighbour);
    }

    @Override
    public void addFavouriteNeighbour(Neighbour neighbour) {
        if (!isInFavourite(neighbour)) {
            neighboursFavourite.add(neighbour);
        }

    }

    @Override
    public boolean isInFavourite(Neighbour neighbour) {
        if(neighboursFavourite.contains(neighbour)) {
            return true;
        };
        return false;
    }



}
