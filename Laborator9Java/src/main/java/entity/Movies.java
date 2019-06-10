package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "movies")
public class Movies implements Serializable {
    @Id
    @Column(name = "id")
    private int movieId;
    @Column(name = "name")
    private String name;
    @Column(name = "director_id")
    private int directorId;



    public Movies(int movieId, String name, int directorId) {
        this.movieId = movieId;
        this.name = name;
        this.directorId = directorId;
    }

    public Movies() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }
}
