package com.example.jdk8;

import java.util.List;

/**
 * 公司类
 *
 * @author win10
 */
public class Company {
    private String name;
    private List<Employee> companies;

    public List<Employee> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Employee> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", companies=" + companies +
                '}';
    }
}
