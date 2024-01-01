package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;


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
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;
        if (storeId != null ? !storeId.equals(customer.storeId) : customer.storeId != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (addressId != null ? !addressId.equals(customer.addressId) : customer.addressId != null) return false;
        if (active != null ? !active.equals(customer.active) : customer.active != null) return false;
        if (createDate != null ? !createDate.equals(customer.createDate) : customer.createDate != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(customer.lastUpdate) : customer.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

}
