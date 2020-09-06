package com.smoothstack.lms.orchestratorservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table
public class LibraryBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column
    @Size(min = 2, max = 100)
    private String name;

    @Column
    @Size(min = 10, max = 100)
    private String address;

    @OneToMany(mappedBy = "id.branch", cascade = CascadeType.ALL)
    private Set<BookCopy> bookCopies = new HashSet<>();

    @OneToMany(mappedBy = "id.branch", cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LibraryBranch [id=" + this.id + ", name=" + this.name + ", address=" + this.address + "]";
    }

}