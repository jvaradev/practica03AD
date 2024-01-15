package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_id")
    private Byte staffId;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "address_id", insertable = false, updatable = false)
    private Short addressId;
    @Basic
    @Column(name = "picture")
    private byte[] picture;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "store_id", insertable = false, updatable = false)
    private Short storeId;
    @Basic
    @Column(name = "active")
    private Byte active;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "staffByStaffId")
    private Collection<Rental> rentalsByStaffId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address addressByAddressId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store storeByStoreId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, insertable = false, updatable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private Store store;

    @OneToMany(mappedBy = "staffByStaffId")
    private Collection<Payment> paymentsByStaffId;

    @OneToMany(mappedBy = "staff")
    private Collection<Rental> rentals;
    public String getAddress() {
        if (addressByAddressId != null) {
            return addressByAddressId.getAddress();
        } else {
            return "N/A";
        }
    }

    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
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

    public Short getAddressId() {
        return addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = Short.valueOf(storeId);
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(getStaffId(), staff.getStaffId()) && Objects.equals(getFirstName(), staff.getFirstName()) && Objects.equals(getLastName(), staff.getLastName()) && Objects.equals(getAddressId(), staff.getAddressId()) && Arrays.equals(getPicture(), staff.getPicture()) && Objects.equals(getEmail(), staff.getEmail()) && Objects.equals(getStoreId(), staff.getStoreId()) && Objects.equals(getActive(), staff.getActive()) && Objects.equals(getUsername(), staff.getUsername()) && Objects.equals(getPassword(), staff.getPassword()) && Objects.equals(getLastUpdate(), staff.getLastUpdate()) && Objects.equals(getRentalsByStaffId(), staff.getRentalsByStaffId()) && Objects.equals(getAddressByAddressId(), staff.getAddressByAddressId()) && Objects.equals(storeByStoreId, staff.storeByStoreId) && Objects.equals(getAddress(), staff.getAddress()) && Objects.equals(store, staff.store) && Objects.equals(getPaymentsByStaffId(), staff.getPaymentsByStaffId()) && Objects.equals(rentals, staff.rentals);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getStaffId(), getFirstName(), getLastName(), getAddressId(), getEmail(), getStoreId(), getActive(), getUsername(), getPassword(), getLastUpdate(), getRentalsByStaffId(), getAddressByAddressId(), storeByStoreId, getAddress(), store, getPaymentsByStaffId(), rentals);
        result = 31 * result + Arrays.hashCode(getPicture());
        return result;
    }

    public Collection<Payment> getPaymentsByStaffId() {
        return paymentsByStaffId;
    }

    public void setPaymentsByStaffId(Collection<Payment> paymentsByStaffId) {
        this.paymentsByStaffId = paymentsByStaffId;
    }

    public Collection<Rental> getRentalsByStaffId() {
        return rentalsByStaffId;
    }

    public void setRentalsByStaffId(Collection<Rental> rentalsByStaffId) {
        this.rentalsByStaffId = rentalsByStaffId;
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
