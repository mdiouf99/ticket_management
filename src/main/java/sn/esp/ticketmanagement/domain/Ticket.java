package sn.esp.ticketmanagement.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import sn.esp.ticketmanagement.domain.enumeration.Status;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "ticket")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne
    private User user;

    @ManyToOne
    private User assignea;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ticket id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Ticket libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Status getStatus() {
        return this.status;
    }

    public Ticket status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public Ticket dateCreation(LocalDate dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket user(User user) {
        this.setUser(user);
        return this;
    }

    public User getAssignea() {
        return this.assignea;
    }

    public void setAssignea(User user) {
        this.assignea = user;
    }

    public Ticket assignea(User user) {
        this.setAssignea(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            "}";
    }
}
