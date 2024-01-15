package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "city")
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id")
    private Short cityId;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "country_id", insertable = false, updatable = false)
    private Short countryId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country countryByCountryId;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    @OneToMany(mappedBy = "cityByCityId")
    private Collection<Address> addressesByCityId;


    public Short getCityId() {
        return cityId;
    }

    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
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
        if (!(o instanceof City city1)) return false;
        return Objects.equals(getCityId(), city1.getCityId()) && Objects.equals(getCity(), city1.getCity()) && Objects.equals(getCountryId(), city1.getCountryId()) && Objects.equals(getLastUpdate(), city1.getLastUpdate()) && Objects.equals(countryByCountryId, city1.countryByCountryId) && Objects.equals(country, city1.country) && Objects.equals(getAddressesByCityId(), city1.getAddressesByCityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityId(), getCity(), getCountryId(), getLastUpdate(), countryByCountryId, country, getAddressesByCityId());
    }


    public Collection<Address> getAddressesByCityId() {
        return addressesByCityId;
    }


    @Override
    public String toString() {
        return city;
    }
}
