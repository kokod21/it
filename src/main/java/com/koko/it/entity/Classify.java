package com.koko.it.entity;

import javax.persistence.*;

@Entity
@Table(name = "classify")
public class Classify implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
//    private Long parent_id;
    private String classifyName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ClassifyParent classifyParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getParent_id() {
//        return parent_id;
//    }
//
//    public void setParent_id(Long parent_id) {
//        this.parent_id = parent_id;
//    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public ClassifyParent getClassifyParent() {
        return classifyParent;
    }

    public void setClassifyParent(ClassifyParent classifyParent) {
        this.classifyParent = classifyParent;
    }
}
