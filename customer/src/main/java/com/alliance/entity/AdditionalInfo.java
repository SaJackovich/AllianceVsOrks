package com.alliance.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdditionalInfo implements Serializable {

    private List<Integer> anotherCustomers;

    public AdditionalInfo() {
    }

    public AdditionalInfo(List<Integer> anotherCustomers) {
        this.anotherCustomers = anotherCustomers;
    }

    public List<Integer> getAnotherCustomers() {
        return anotherCustomers;
    }

    public void setAnotherCustomers(List<Integer> anotherCustomers) {
        this.anotherCustomers = anotherCustomers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdditionalInfo that = (AdditionalInfo) o;
        return Objects.equals(anotherCustomers, that.anotherCustomers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anotherCustomers);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("anotherCustomer", anotherCustomers)
                .toString();
    }
}
