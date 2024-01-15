package org.example.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    private Short filmId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "release_year")
    private Object releaseYear;
    @Basic
    @Column(name = "language_id", insertable = false, updatable = false)
    private Byte languageId;
    @Basic
    @Column(name = "original_language_id", insertable = false, updatable = false)
    private Byte originalLanguageId;
    @Basic
    @Column(name = "rental_duration")
    private Byte rentalDuration;
    @Basic
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Basic
    @Column(name = "length")
    private Short length;
    @Basic
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Basic
    @Column(name = "rating")
    private Object rating;
    @Basic
    @Column(name = "special_features")
    private Object specialFeatures;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false, insertable = false, updatable = false)
    private Language languageByLanguageId;

    @ManyToOne
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id", insertable = false, updatable = false)
    private Language languageByOriginalLanguageId;
    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    @ManyToOne
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id", insertable = false, updatable = false)
    private Language originalLanguage;
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;
    @OneToMany(mappedBy = "film")
    private Set<Inventory> inventories;

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public Short getFilmId() {
        return filmId;
    }

    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }

    public Byte getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Byte originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Byte getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Byte rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Object getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(Object specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getInventoryId() {
        // Este método asume que hay una relación One-to-One entre Film e Inventory
        // Ajusta esto según tu modelo de datos real
        if (inventories != null && !inventories.isEmpty()) {
            // Devuelve el inventoryId de la primera relación, puedes ajustar esto según tus necesidades
            return (Integer) inventories.iterator().next().getInventoryId();
        } else {
            return null; // O lanza una excepción si no hay inventarios asociados al film
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return Objects.equals(getFilmId(), film.getFilmId()) && Objects.equals(getTitle(), film.getTitle()) && Objects.equals(getDescription(), film.getDescription()) && Objects.equals(getReleaseYear(), film.getReleaseYear()) && Objects.equals(getLanguageId(), film.getLanguageId()) && Objects.equals(getOriginalLanguageId(), film.getOriginalLanguageId()) && Objects.equals(getRentalDuration(), film.getRentalDuration()) && Objects.equals(getRentalRate(), film.getRentalRate()) && Objects.equals(getLength(), film.getLength()) && Objects.equals(getReplacementCost(), film.getReplacementCost()) && Objects.equals(getRating(), film.getRating()) && Objects.equals(getSpecialFeatures(), film.getSpecialFeatures()) && Objects.equals(getLastUpdate(), film.getLastUpdate()) && Objects.equals(language, film.language) && Objects.equals(languageByLanguageId, film.languageByLanguageId) && Objects.equals(languageByOriginalLanguageId, film.languageByOriginalLanguageId) && Objects.equals(getCategories(), film.getCategories()) && Objects.equals(getOriginalLanguage(), film.getOriginalLanguage()) && Objects.equals(getActors(), film.getActors()) && Objects.equals(getInventories(), film.getInventories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmId(), getTitle(), getDescription(), getReleaseYear(), getLanguageId(), getOriginalLanguageId(), getRentalDuration(), getRentalRate(), getLength(), getReplacementCost(), getRating(), getSpecialFeatures(), getLastUpdate(), language, languageByLanguageId, languageByOriginalLanguageId, getCategories(), getOriginalLanguage(), getActors(), getInventories());
    }

    @Override
    public String toString() {
        return title;
    }
}
