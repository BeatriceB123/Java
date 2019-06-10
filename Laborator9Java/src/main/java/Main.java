import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main ( String[] args ) {
        /*try{
            PersonController persons = new PersonController();
            MoviesController movies = new MoviesController();
            ActorController actors = new ActorController();
            ArrayList <Person> personList = new ArrayList <>();
            ArrayList <Movie> movieList = new ArrayList <>();
            ArrayList <String> actorNameList = new ArrayList <>();
            ArrayList <String> movieNameList = new ArrayList <>();
            ArrayList <String> directorNameList = new ArrayList <>();

//            persons.create(new Director(1,"Francis Ford Coppola"));
//            persons.create(new Director(2,"Marlon Brando"));
//            persons.create(new Director(3,"Al Pacino"));
//            persons.create(new Person(4,"John Doe", "Actor"));
//            persons.create(new Person(5,"Random Smith", "Actor"));
//            persons.create(new Person(6,"Jane Doe", "Actor"));
//            persons.create(new Person(7,"Im Not Original", "Actor"));
//            persons.create(new Person(8,"Will Jones", "Actor"));
//            Database.commit();
//
//            movies.create(new Movie(1,"The Godfather", persons.findByName("Coppola")));
//            movies.create(new Movie(2,"Endgame", persons.findByName("Brando")));
//            movies.create(new Movie(3,"Thor", persons.findByName("Pacino")));
//            movies.create(new Movie(4,"The", persons.findByName("Coppola")));
//            movies.create(new Movie(5,"Shrek", persons.findByName("Al")));
//            movies.create(new Movie(6,"JoJo", persons.findByName("Coppola")));
//            //Add other movies to the database
//            Database.commit();

//            movieList = movies.findAll();
//            for(Movie movie : movieList)
//                System.out.println("Movie id: " + movie.getMovieId() + "; movie name: " + movie.getName() + "; director id: " + movie.getDirectorId() + ".");

//            personList = persons.findAll();
//            for(Person person : personList)
//                System.out.println("Person id: " + person.getId() + "; person name: " + person.getName() + "; person type: " + person.getType() + ".");

//            actors.create(new Actor(1,4));
//            actors.create(new Actor(1, 7));
//            actors.create(new Actor(5, 8));
//            actors.create(new Actor(4, 5));
//            actors.create(new Actor(6, 6));
//            actors.create(new Actor(2, 7));
//            actors.create(new Actor(3, 8));
//            Database.commit();
//            System.out.println(actors.findActor(new Actor(1,1)));

//            actorNameList = actors.findActorByMovie(1);
//            for(String string : actorNameList)
//                System.out.println("Actor name: " + string + ".");

//            movieNameList = movies.findMovieByActor(8);
//            for(String string : movieNameList)
//                System.out.println("Movie name: " + string + ".");

//            directorNameList = movies.findMovieByDirector(1);
//            for (String string : directorNameList)
//                System.out.println("Movie name: " + string + ".");

            RandomData randomData = new RandomData();
            randomData.generateData();

            Database.closeConnection();
        }
        catch(SQLException e){
            System.err.println(e);
            Database.rollback();
        }
        */
    }
}