package com.alliance.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alliance.entity.type.SuperPower;

@Entity
public class UltraCustomer implements Serializable {

    private static final long serialVersionUID = -4929200848786203558L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, columnDefinition = "bigint")
    private BigInteger id;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String firstName;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "decimal", length = 1)
    private SuperPower superPower;

    public BigInteger getId() {
        return id;
    }

    public UltraCustomer setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UltraCustomer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UltraCustomer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SuperPower getSuperPower() {
        return superPower;
    }

    public UltraCustomer setSuperPower(SuperPower superPower) {
        this.superPower = superPower;
        return this;
    }

}
