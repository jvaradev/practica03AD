package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

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
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (cityId != null ? !cityId.equals(city1.cityId) : city1.cityId != null) return false;
        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;
        if (countryId != null ? !countryId.equals(city1.countryId) : city1.countryId != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(city1.lastUpdate) : city1.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Collection<Address> getAddressesByCityId() {
        return addressesByCityId;
    }

    public void setAddressesByCityId(Collection<Address> addressesByCityId) {
        this.addressesByCityId = addressesByCityId;
    }

    @Override
    public String toString() {
        return city;
    }
}
