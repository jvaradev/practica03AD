package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private Byte storeId;
    @Basic
    @Column(name = "manager_staff_id", insertable = false, updatable = false)
    private Byte managerStaffId;
    @Basic
    @Column(name = "address_id", insertable = false, updatable = false)
    private Short addressId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<Inventory> inventoriesByStoreId;
    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff staffByManagerStaffId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address addressByAddressId;

    @OneToMany(mappedBy = "store")
    private Set<Staff> employees;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, insertable = false, updatable = false)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private Staff managerStaff;

    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<Customer> customersByStoreId;

    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<Staff> staffByStoreId;

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Byte getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(Byte managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public Short getAddressId() {
        return addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
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
        Store store = (Store) o;
        return Objects.equals(storeId, store.storeId) && Objects.equals(managerStaffId, store.managerStaffId) && Objects.equals(addressId, store.addressId) && Objects.equals(lastUpdate, store.lastUpdate) && Objects.equals(inventoriesByStoreId, store.inventoriesByStoreId) && Objects.equals(staffByManagerStaffId, store.staffByManagerStaffId) && Objects.equals(addressByAddressId, store.addressByAddressId) && Objects.equals(employees, store.employees) && Objects.equals(address, store.address) && Objects.equals(managerStaff, store.managerStaff) && Objects.equals(customersByStoreId, store.customersByStoreId) && Objects.equals(staffByStoreId, store.staffByStoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, managerStaffId, addressId, lastUpdate, inventoriesByStoreId, staffByManagerStaffId, addressByAddressId, employees, address, managerStaff, customersByStoreId, staffByStoreId);
    }

    public Collection<Customer> getCustomersByStoreId() {
        return customersByStoreId;
    }

    public void setCustomersByStoreId(Collection<Customer> customersByStoreId) {
        this.customersByStoreId = customersByStoreId;
    }

    public Collection<Inventory> getInventoriesByStoreId() {
        return inventoriesByStoreId;
    }

    public void setInventoriesByStoreId(Collection<Inventory> inventoriesByStoreId) {
        this.inventoriesByStoreId = inventoriesByStoreId;
    }

    public Collection<Staff> getStaffByStoreId() {
        return staffByStoreId;
    }

    public void setStaffByStoreId(Collection<Staff> staffByStoreId) {
        this.staffByStoreId = staffByStoreId;
    }

    public Staff getStaffByManagerStaffId() {
        return staffByManagerStaffId;
    }

    public void setStaffByManagerStaffId(Staff staffByManagerStaffId) {
        this.staffByManagerStaffId = staffByManagerStaffId;
    }

    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
