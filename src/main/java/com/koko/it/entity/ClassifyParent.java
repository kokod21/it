package com.koko.it.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classify_parent")
public class ClassifyParent implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parentName;

    @OneToMany
    private Set<Classify> classifies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Set<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(Set<Classify> classifies) {
        this.classifies = classifies;
    }
}

