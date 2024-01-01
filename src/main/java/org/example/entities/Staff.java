package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

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
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (staffId != null ? !staffId.equals(staff.staffId) : staff.staffId != null) return false;
        if (firstName != null ? !firstName.equals(staff.firstName) : staff.firstName != null) return false;
        if (lastName != null ? !lastName.equals(staff.lastName) : staff.lastName != null) return false;
        if (addressId != null ? !addressId.equals(staff.addressId) : staff.addressId != null) return false;
        if (!Arrays.equals(picture, staff.picture)) return false;
        if (email != null ? !email.equals(staff.email) : staff.email != null) return false;
        if (storeId != null ? !storeId.equals(staff.storeId) : staff.storeId != null) return false;
        if (active != null ? !active.equals(staff.active) : staff.active != null) return false;
        if (username != null ? !username.equals(staff.username) : staff.username != null) return false;
        if (password != null ? !password.equals(staff.password) : staff.password != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(staff.lastUpdate) : staff.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId != null ? staffId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
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
