package app;

import controller.MoviesController;
import controller.PersonController;
import entity.Movies;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MovieManager {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviePU");
    static final PersonController persons = new PersonController(emf);
    static final MoviesController movies = new MoviesController(emf);

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command:");
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "create-person":
                    createPerson(params[1]); //the person name
                    break;
                case "create-movie":
                    createMovies(params[1], params[2]); //the album name and the director
                    break;
//                case "list-movies":
//                    listMovies(params[1]); //the director name
//                    break;

            }
        }
    }
    private void createPerson(String personName) {
        Person person = new Person(300,personName,"Actor");
        persons.create(person);
    }
    private void createMovies(String movieName, String directorName) {
		Movies movie = new Movies(300, movieName, 5);
		movies.create(movie);
    }
    private void listMovies(String directorName) {
		//Implement this method
    }
    public static void main(String args[]) {
        new MovieManager().run();
        emf.close();
    }
}