package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "inventory")
public class Inventory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Basic
    @Column(name = "film_id", insertable = false, updatable = false)
    private Short filmId;

    @Basic
    @Column(name = "store_id", insertable = false, updatable = false)
    private Short storeId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    private Film filmByFilmId;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store storeByStoreId;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false, insertable = false, updatable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private Store store;

    @OneToMany(mappedBy = "inventoryByInventoryId")
    private Collection<Rental> rentals;

    public Collection<Rental> getRentals() {
        return rentals;
    }

    public Film getFilm() {
        return film;
    }
    public Object getInventoryId() {
        return this.inventoryId;
    }

    public void setInventoryId(Object inventoryId) {
        this.inventoryId = (Integer) inventoryId;
    }

    public Short getFilmId() {
        return filmId;
    }

    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    public Short getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = Short.valueOf(storeId);
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.inventoryId) && Objects.equals(filmId, inventory.filmId) && Objects.equals(storeId, inventory.storeId) && Objects.equals(lastUpdate, inventory.lastUpdate) && Objects.equals(filmByFilmId, inventory.filmByFilmId) && Objects.equals(storeByStoreId, inventory.storeByStoreId) && Objects.equals(film, inventory.film) && Objects.equals(store, inventory.store) && Objects.equals(rentals, inventory.rentals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, filmId, storeId, lastUpdate, filmByFilmId, storeByStoreId, film, store, rentals);
    }

    public Film getFilmByFilmId() {
        return filmByFilmId;
    }

    public void setFilmByFilmId(Film filmByFilmId) {
        this.filmByFilmId = filmByFilmId;
    }
}
