package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "address")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "address_id")
    private Short addressId;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "address2")
    private String address2;
    @Basic
    @Column(name = "district")
    private String district;
    @Basic
    @Column(name = "city_id", insertable = false, updatable = false)
    private Short cityId;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
    private City cityByCityId;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false, insertable = false, updatable = false)
    private City city;

    @OneToMany(mappedBy = "addressByAddressId")
    private Collection<Customer> customersByAddressId;

    @OneToMany(mappedBy = "addressByAddressId")
    private Collection<Staff> staffByAddressId;

    public Short getAddressId() {
        return addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Short getCityId() {
        return cityId;
    }

    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

        Address address1 = (Address) o;

        if (addressId != null ? !addressId.equals(address1.addressId) : address1.addressId != null) return false;
        if (address != null ? !address.equals(address1.address) : address1.address != null) return false;
        if (address2 != null ? !address2.equals(address1.address2) : address1.address2 != null) return false;
        if (district != null ? !district.equals(address1.district) : address1.district != null) return false;
        if (cityId != null ? !cityId.equals(address1.cityId) : address1.cityId != null) return false;
        if (postalCode != null ? !postalCode.equals(address1.postalCode) : address1.postalCode != null) return false;
        if (phone != null ? !phone.equals(address1.phone) : address1.phone != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(address1.lastUpdate) : address1.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", district='" + district + '\'' +
                ", city=" + city +
                '}';
    }
}
