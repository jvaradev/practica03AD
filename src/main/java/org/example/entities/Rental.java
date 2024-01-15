package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rental")
public class Rental {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id")
    private Integer rentalId;
    @Basic
    @Column(name = "rental_date")
    private Timestamp rentalDate;
    @Basic
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Basic
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Short customerId;
    @Basic
    @Column(name = "return_date")
    private Timestamp returnDate;
    @Basic
    @Column(name = "staff_id", insertable = false, updatable = false)
    private Integer staffId;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "rentalByRentalId")
    private Collection<Payment> paymentsByRentalId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staffByStaffId;
    @OneToMany(mappedBy = "rental")
    private Set<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false, insertable = false, updatable = false)
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private Staff employee;
    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", nullable = false, insertable = false, updatable = false)
    private Inventory inventoryByInventoryId;

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Object getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    public void setStaff(Staff staff) {
        this.staff = staff;
        if (staff != null && !staff.getRentalsByStaffId().contains(this)) {
            staff.getRentalsByStaffId().add(this);
        }
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
        if (!(o instanceof Rental rental)) return false;
        return Objects.equals(getRentalId(), rental.getRentalId()) && Objects.equals(getRentalDate(), rental.getRentalDate()) && Objects.equals(getInventoryId(), rental.getInventoryId()) && Objects.equals(getCustomerId(), rental.getCustomerId()) && Objects.equals(getReturnDate(), rental.getReturnDate()) && Objects.equals(getStaffId(), rental.getStaffId()) && Objects.equals(getLastUpdate(), rental.getLastUpdate()) && Objects.equals(getPaymentsByRentalId(), rental.getPaymentsByRentalId()) && Objects.equals(customer, rental.customer) && Objects.equals(staffByStaffId, rental.staffByStaffId) && Objects.equals(payments, rental.payments) && Objects.equals(customerByCustomerId, rental.customerByCustomerId) && Objects.equals(staff, rental.staff) && Objects.equals(employee, rental.employee) && Objects.equals(getInventoryByInventoryId(), rental.getInventoryByInventoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRentalId(), getRentalDate(), getInventoryId(), getCustomerId(), getReturnDate(), getStaffId(), getLastUpdate(), getPaymentsByRentalId(), customer, staffByStaffId, payments, customerByCustomerId, staff, employee, getInventoryByInventoryId());
    }

    public Collection<Payment> getPaymentsByRentalId() {
        return paymentsByRentalId;
    }

    public void setPaymentsByRentalId(Collection<Payment> paymentsByRentalId) {
        this.paymentsByRentalId = paymentsByRentalId;
    }

    public Inventory getInventoryByInventoryId() {
        return inventoryByInventoryId;
    }

    public void setInventoryByInventoryId(Inventory inventoryByInventoryId) {
        this.inventoryByInventoryId = inventoryByInventoryId;
    }

    public Customer getCustomerByCustomerId(int customerId) {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}