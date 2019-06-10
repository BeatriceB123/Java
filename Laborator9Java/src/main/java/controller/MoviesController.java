package controller;

import entity.Movies;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class MoviesController {
    private EntityManagerFactory emf;
    public MoviesController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MoviesController() {

    }

    public void create(Movies movie) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        em.close();
    }
    public Movies findByName(String movieName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from Movies t where t.name like :name");
        List movies = query.setParameter("name", movieName).getResultList();
        em.close();
        return movies.isEmpty() ? null : (Movies) movies.get(0);
    }
}