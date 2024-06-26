package com.github.dcysteine.nesql.sql.thaumcraft;

import com.github.dcysteine.nesql.sql.Identifiable;
import com.github.dcysteine.nesql.sql.base.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/** Holds basic information about Thaumcraft aspects. */
@Entity
@EqualsAndHashCode
@Getter
@ToString
public class Aspect implements Identifiable<String> {
    @Id
    @Column(nullable = false)
    private String id;

    @OneToOne
    private Item icon;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private boolean primal;

    /** Aspects that this aspect is built from. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Aspect> components;

    /** Aspects that this aspect builds into. */
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "components")
    private Set<Aspect> componentOf;

    /** Needed by Hibernate. */
    protected Aspect() {}

    /**
     * We'll set {@link #components} in a second pass,
     * after all aspects have been committed to the DB.
     */
    public Aspect(String id, Item icon, String name, String description, boolean primal) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.primal = primal;

        components = new HashSet<>();
    }

    public void addComponent(Aspect component) {
        components.add(component);
    }

    @Override
    public int compareTo(Identifiable<String> other) {
        if (other instanceof Aspect) {
            return Comparator.comparing(Aspect::isPrimal)
                    .thenComparing(Aspect::getName)
                    .thenComparing(Aspect::getId)
                    .compare(this, (Aspect) other);
        } else {
            return Identifiable.super.compareTo(other);
        }
    }
}
