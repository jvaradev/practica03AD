package org.example.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "language")
public class Language {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "language_id")
    private Byte languageId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "languageByLanguageId")
    private Collection<Film> filmsByLanguageId;
    @OneToMany(mappedBy = "languageByOriginalLanguageId")
    private Collection<Film> filmsByLanguageId_0;

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
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
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (languageId != null ? !languageId.equals(language.languageId) : language.languageId != null) return false;
        if (name != null ? !name.equals(language.name) : language.name != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(language.lastUpdate) : language.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = languageId != null ? languageId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Collection<Film> getFilmsByLanguageId() {
        return filmsByLanguageId;
    }

    public void setFilmsByLanguageId(Collection<Film> filmsByLanguageId) {
        this.filmsByLanguageId = filmsByLanguageId;
    }

    public Collection<Film> getFilmsByLanguageId_0() {
        return filmsByLanguageId_0;
    }

    public void setFilmsByLanguageId_0(Collection<Film> filmsByLanguageId_0) {
        this.filmsByLanguageId_0 = filmsByLanguageId_0;
    }
}
