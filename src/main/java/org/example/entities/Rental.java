package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
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
    private Object inventoryId;
    @Basic
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Short customerId;
    @Basic
    @Column(name = "return_date")
    private Timestamp returnDate;
    @Basic
    @Column(name = "staff_id", insertable = false, updatable = false)
    private Byte staffId;
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
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", nullable = false, insertable = false, updatable = false)
    private Inventory inventoryByInventoryId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false, insertable = false, updatable = false)
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private Staff employee;

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

    public void setInventoryId(Object inventoryId) {
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

    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
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

        Rental rental = (Rental) o;

        if (rentalId != null ? !rentalId.equals(rental.rentalId) : rental.rentalId != null) return false;
        if (rentalDate != null ? !rentalDate.equals(rental.rentalDate) : rental.rentalDate != null) return false;
        if (inventoryId != null ? !inventoryId.equals(rental.inventoryId) : rental.inventoryId != null) return false;
        if (customerId != null ? !customerId.equals(rental.customerId) : rental.customerId != null) return false;
        if (returnDate != null ? !returnDate.equals(rental.returnDate) : rental.returnDate != null) return false;
        if (staffId != null ? !staffId.equals(rental.staffId) : rental.staffId != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(rental.lastUpdate) : rental.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rentalId != null ? rentalId.hashCode() : 0;
        result = 31 * result + (rentalDate != null ? rentalDate.hashCode() : 0);
        result = 31 * result + (inventoryId != null ? inventoryId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
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
