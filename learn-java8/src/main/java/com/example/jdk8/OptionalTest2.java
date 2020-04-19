package com.example.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author win10
 */
public class OptionalTest2 {
    public static void main(String[] args) {
        Employee employee0 = new Employee();
        Employee employee1 = new Employee();
        employee0.setName("张三");
        employee1.setName("李四");
        Company company = new Company();
        company.setName("baidu");
        List<Employee> ls = Arrays.asList(employee0, employee1);
        company.setCompanies(ls);
        System.out.println(company.toString());

        // Optional的用法：判断是否为空,若为空则返回一个空List
        // optionalCompany.map(company1 -> company1.getCompanies()),
        // 这一步是把Company中的List<Employee>对象转成Optional对象，
        // 然后才能执行Optional对象的orElse()方法
        // 若是没有optionalCompany.map(),就没有办法操作Optional对象的方法
        Optional<Company> optionalCompany = Optional.ofNullable(company);
        System.out.println(optionalCompany.map(company1 -> company1.getCompanies())
                .orElse(Collections.emptyList()));

        // 把Company中的List<Employee>对象转成Optional对象，
        Optional<List<Employee>> employees = optionalCompany.map(company1 -> company1.getCompanies());

    }
}
