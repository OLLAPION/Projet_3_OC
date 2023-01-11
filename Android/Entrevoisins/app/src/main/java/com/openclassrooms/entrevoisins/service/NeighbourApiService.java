package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {
    /*
    List<Neighbour> neighbour = generateNeighbours();
    List<Neighbour> neighbourFavourite = generateFavouriteNeighbours();
     */

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my NeighboursFavourite
     * @return {@link List}
     */
    List<Neighbour> getNeighboursFavourite();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);


    /**
     * Deletes a favorite neighbour
     * @param neighbour
     */
    void deleteFavouriteNeighbour(Neighbour neighbour);

    /**
     * Add a neighbour in Favorite
     * @param neighbour
     */
    void addFavouriteNeighbour(Neighbour neighbour);

    /**
     * Check if neighbour is in favorite
     * @param neighbour
     */
    boolean isInFavourite(Neighbour neighbour);
}
