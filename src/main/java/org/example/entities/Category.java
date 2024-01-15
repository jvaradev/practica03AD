package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private Byte categoryId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getCategoryId(), category.getCategoryId()) && Objects.equals(getName(), category.getName()) && Objects.equals(getLastUpdate(), category.getLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getName(), getLastUpdate());
    }
}
