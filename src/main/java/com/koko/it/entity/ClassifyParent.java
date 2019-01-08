package com.koko.it.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classify_parent")
public class ClassifyParent implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parent_name;

    @OneToMany
    private Set<Classify> classifies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public Set<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(Set<Classify> classifies) {
        this.classifies = classifies;
    }
}

