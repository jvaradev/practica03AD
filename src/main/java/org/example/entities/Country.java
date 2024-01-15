package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "country_id")
    private Short countryId;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "countryByCountryId")
    private Collection<City> citiesByCountryId;

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(o instanceof Country country1)) return false;
        return Objects.equals(getCountryId(), country1.getCountryId()) && Objects.equals(getCountry(), country1.getCountry()) && Objects.equals(getLastUpdate(), country1.getLastUpdate()) && Objects.equals(getCitiesByCountryId(), country1.getCitiesByCountryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryId(), getCountry(), getLastUpdate(), getCitiesByCountryId());
    }

    public Collection<City> getCitiesByCountryId() {
        return citiesByCountryId;
    }

    public void setCitiesByCountryId(Collection<City> citiesByCountryId) {
        this.citiesByCountryId = citiesByCountryId;
    }
}
