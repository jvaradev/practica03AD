package org.example.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (paymentId != null ? !paymentId.equals(payment.paymentId) : payment.paymentId != null) return false;
        if (customerId != null ? !customerId.equals(payment.customerId) : payment.customerId != null) return false;
        if (staffId != null ? !staffId.equals(payment.staffId) : payment.staffId != null) return false;
        if (rentalId != null ? !rentalId.equals(payment.rentalId) : payment.rentalId != null) return false;
        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
        if (paymentDate != null ? !paymentDate.equals(payment.paymentDate) : payment.paymentDate != null) return false;
        return lastUpdate != null ? lastUpdate.equals(payment.lastUpdate) : payment.lastUpdate == null;
    }

    @Override
    public int hashCode() {
        int result = paymentId != null ? paymentId.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (rentalId != null ? rentalId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
