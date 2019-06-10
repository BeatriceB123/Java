import controller.ActorController;
import controller.MoviesController;
import controller.PersonController;
import database.Database;
import entity.Actor;
import entity.Movies;
import entity.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class RandomData {
    private PersonController persons = new PersonController();
    private MoviesController movies = new MoviesController();
    private ActorController actors = new ActorController();

    public void generateData() throws SQLException {
        ArrayList<String> personFirstNames = new ArrayList<>();
        personFirstNames.add("Smith");
        personFirstNames.add("Wick");
        personFirstNames.add("Jones");
        personFirstNames.add("Banner");
        personFirstNames.add("Stark");
        personFirstNames.add("Iago");
        personFirstNames.add("Putin");
        personFirstNames.add("Gab");
        personFirstNames.add("Smith");
        personFirstNames.add("Glass");
        personFirstNames.add("Archer");

        ArrayList<String> personLastNames = new ArrayList<>();
        personLastNames.add("Andrew");
        personLastNames.add("Tony");
        personLastNames.add("Bruce");
        personLastNames.add("John");
        personLastNames.add("Joe");
        personLastNames.add("Joshua");
        personLastNames.add("Josh");
        personLastNames.add("Alexander");
        personLastNames.add("Alex");
        personLastNames.add("Andrea");
        personLastNames.add("Tris");
        personLastNames.add("Jonathan");
        personLastNames.add("Iris");

        ArrayList<String> movieStart = new ArrayList<>();
        movieStart.add("The curse of ");
        movieStart.add("The revenge of ");
        movieStart.add("The crimes of ");
        movieStart.add("The secrets of ");
        movieStart.add("The awakening of ");
        movieStart.add("The teenage years of ");
        movieStart.add("The end of ");
        movieStart.add("The electric boogaloo of ");

        ArrayList<String> movieEnd = new ArrayList<>();
        movieEnd.add("Osiris");
        movieEnd.add("Dio");
        movieEnd.add("Pucci");
        movieEnd.add("the pillar men");
        movieEnd.add("dawn");
        movieEnd.add("stardust crusaders");
        movieEnd.add("Alcatraz");
        movieEnd.add("Joseph");
        movieEnd.add("JoJo");

        ArrayList<String> personTypes = new ArrayList<>();
        personTypes.add("Director");
        personTypes.add("Actor");

        for(int i = 1; i <= 200; i++){
            Random random = new Random();
            String name = personLastNames.get(random.nextInt(personLastNames.size())) + " " + personFirstNames.get(random.nextInt(personFirstNames.size()));
            String type = personTypes.get(random.nextInt(personTypes.size()));
            persons.create(new Person(i, name, type));
        }
        Database.commit();
        for(int i = 1; i <= 100; i++){
            Random random = new Random();
            String name = movieStart.get(random.nextInt(movieStart.size())) + movieEnd.get(random.nextInt(movieEnd.size()));
            int directorId = persons.getRandomDirector();
            movies.create(new Movies(i, name, directorId));
        }
        Database.commit();
        for(int i = 1; i <= 800; i++){
            int movieId = new Random().nextInt(100) + 1;
            int actorId = persons.getRandomActor();
            Actor auxActor = new Actor(movieId, actorId);
            if (!actors.findActor(auxActor)) actors.create(auxActor);
            else
                System.out.println("Movie id - Actor id pair " + movieId + " - " + actorId + " already exists.");
        }
        Database.commit();
    }
}
