package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "customer")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private Short customerId;
    @Basic
    @Column(name = "store_id", insertable = false, updatable = false)
    private Byte storeId;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "address_id", insertable = false, updatable = false)
    private Short addressId;
    @Basic
    @Column(name = "active")
    private Byte active;
    @Basic
    @Column(name = "create_date")
    private Timestamp createDate;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, insertable = false, updatable = false)
    private Address addressByAddressId;

    @OneToMany(mappedBy = "customerByCustomerId")
    private Collection<Rental> rentals;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private Store storeByStoreId;
    @OneToMany(mappedBy = "customerByCustomerId")
    private Collection<Payment> payments;
    public Collection<Payment> getPayments() {
        return payments;
    }
    public Collection<Rental> getRentals() {
        return rentals;
    }
    public Store getStoreByStoreId() {
        return storeByStoreId;
    }
    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getAddressId() {
        return addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(getCustomerId(), customer.getCustomerId()) && Objects.equals(getStoreId(), customer.getStoreId()) && Objects.equals(getFirstName(), customer.getFirstName()) && Objects.equals(getLastName(), customer.getLastName()) && Objects.equals(getEmail(), customer.getEmail()) && Objects.equals(getAddressId(), customer.getAddressId()) && Objects.equals(getActive(), customer.getActive()) && Objects.equals(getCreateDate(), customer.getCreateDate()) && Objects.equals(getLastUpdate(), customer.getLastUpdate()) && Objects.equals(store, customer.store) && Objects.equals(getAddressByAddressId(), customer.getAddressByAddressId()) && Objects.equals(getRentals(), customer.getRentals()) && Objects.equals(getStoreByStoreId(), customer.getStoreByStoreId()) && Objects.equals(getPayments(), customer.getPayments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getStoreId(), getFirstName(), getLastName(), getEmail(), getAddressId(), getActive(), getCreateDate(), getLastUpdate(), store, getAddressByAddressId(), getRentals(), getStoreByStoreId(), getPayments());
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

}
