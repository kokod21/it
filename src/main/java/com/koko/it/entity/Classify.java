package com.koko.it.entity;

import javax.persistence.*;

@Entity
@Table(name = "classify")
public class Classify implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String classifyName;

//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private ClassifyParent classifyParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

//    public ClassifyParent getClassifyParent() {
//        return classifyParent;
//    }
//
//    public void setClassifyParent(ClassifyParent classifyParent) {
//        this.classifyParent = classifyParent;
//    }
}
