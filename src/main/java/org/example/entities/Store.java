package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
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

        if (storeId != null ? !storeId.equals(store.storeId) : store.storeId != null) return false;
        if (managerStaffId != null ? !managerStaffId.equals(store.managerStaffId) : store.managerStaffId != null)
            return false;
        if (addressId != null ? !addressId.equals(store.addressId) : store.addressId != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(store.lastUpdate) : store.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId != null ? storeId.hashCode() : 0;
        result = 31 * result + (managerStaffId != null ? managerStaffId.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
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
