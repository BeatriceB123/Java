package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="movie_actors")
public class Actor implements Serializable {
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Id
    @Column(name = "actor_id")
    private int actorId;

    public Actor(int movieId, int actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public Actor() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
}
