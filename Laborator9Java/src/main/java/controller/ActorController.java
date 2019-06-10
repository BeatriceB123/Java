package controller;

import database.Database;
import entity.Actor;

import java.sql.*;
import java.util.ArrayList;

public class ActorController {
    /**
     * Inserts into the database an actor entry.
     *
     * @param actor the object that stores the following fields
     *              id   the id of the entry
     *              name the name of the entry
     * @throws SQLException
     */
    public void create(Actor actor) throws SQLException {
        Connection con = Database.getConnection();
        if (findActor(actor)) System.out.println("Actor already exists.");
        else
            try (PreparedStatement pstmt = con.prepareStatement("insert into MOVIE_ACTORS values (?,?)")) {
                pstmt.setInt(1, actor.getMovieId());
                pstmt.setInt(2, actor.getActorId());
                pstmt.executeUpdate();
            }
    }

    /**
     * Finds and returns whether or not an actor is part of a given movie.
     *
     * @param actor object that stores the following fields
     *              idMovie the id of the movie
     *              idActor the id of the actor
     * @return true if yes, false otherwise
     * @throws SQLException
     */
    public boolean findActor(Actor actor) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from movie_actors where movie_id = " + actor.getMovieId() + " AND actor_id = " + actor.getActorId() + "")) {
            if (rs.next()) return true;
            return false;
        }
    }

    /**
     * Finds and returns all actors that starred in given movie
     *
     * @param id id of the movie
     * @return the ArrayList
     * @throws SQLException
     */
    public ArrayList<String> findActorByMovie(int id) throws SQLException {
        ArrayList<String> auxList = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from movie_actors join persons on persons.id = movie_actors.actor_id where movie_id = " + id + "")) {
            while (rs.next()) {
                String name = rs.getString(1);
                auxList.add(name);
            }
        }
        return auxList;
    }
}
