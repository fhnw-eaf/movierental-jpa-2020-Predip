package ch.fhnw.eaf.rental.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "MOVIES")
@AttributeOverride(name = "id", column = @Column(name = "MOVIE_ID"))
@NamedQuery(name = Movie.FIND_BY_TITLE, query = "SELECT m FROM Movie m WHERE m.title=:title")
public class Movie extends BasicModel {
    @Column(name = "MOVIE_TITLE")
    private String title;
    @Column(name = "MOVIE_RELEASEDATE")
    private LocalDate releaseDate;
    @Column(name = "MOVIE_RENTED")
    private boolean rented;

    @ManyToOne
    @JoinColumn(name = "PRICECATEGORY_FK")
    private PriceCategory priceCategory;

    public static final String FIND_BY_TITLE = "Movie.byTitle";

    protected Movie() {
    }

    public Movie(Long id, String title, LocalDate releaseDate, boolean rented, PriceCategory priceCategory) {
        this(title, releaseDate, priceCategory);
        setId(id);
        setRented(rented);
    }

    public Movie(String title, LocalDate releaseDate, PriceCategory priceCategory) {
        if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
            throw new NullPointerException("not all input parameters are set!");
        }
        this.title = title;
        this.releaseDate = releaseDate;
        this.priceCategory = priceCategory;
        this.rented = false;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

}
