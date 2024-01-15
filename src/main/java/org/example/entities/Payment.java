package org.example.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private Short paymentId;

    @Basic
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Short customerId;

    @Basic
    @Column(name = "staff_id", insertable = false, updatable = false)
    private Byte staffId;

    @Basic
    @Column(name = "rental_id", insertable = false, updatable = false)
    private Integer rentalId;

    @Basic
    @Column(name = "amount")
    private BigDecimal amount;

    @Basic
    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customerByCustomerId;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "rental_id", nullable = false, insertable = false, updatable = false)
    private Rental rentalByRentalId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false, insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rental_id", insertable = false, updatable = false)
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff staffByStaffId;

    public Short getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Short paymentId) {
        this.paymentId = paymentId;
    }

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
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
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(getPaymentId(), payment.getPaymentId()) && Objects.equals(getCustomerId(), payment.getCustomerId()) && Objects.equals(getStaffId(), payment.getStaffId()) && Objects.equals(getRentalId(), payment.getRentalId()) && Objects.equals(getAmount(), payment.getAmount()) && Objects.equals(getPaymentDate(), payment.getPaymentDate()) && Objects.equals(getLastUpdate(), payment.getLastUpdate()) && Objects.equals(customerByCustomerId, payment.customerByCustomerId) && Objects.equals(rentalByRentalId, payment.rentalByRentalId) && Objects.equals(customer, payment.customer) && Objects.equals(rental, payment.rental) && Objects.equals(staffByStaffId, payment.staffByStaffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentId(), getCustomerId(), getStaffId(), getRentalId(), getAmount(), getPaymentDate(), getLastUpdate(), customerByCustomerId, rentalByRentalId, customer, rental, staffByStaffId);
    }
}
