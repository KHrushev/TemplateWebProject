package com.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String job;
    private int manager;
    private Date hireDate;
    private float salary;
    private int commissions;
    private int deptNo;

    public Employee(int id, String name, String job, int manager, Date hireDate, float salary, int commissions, int deptNo) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissions = commissions;
        this.deptNo = deptNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getCommissions() {
        return commissions;
    }

    public void setCommissions(int commissions) {
        this.commissions = commissions;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", manager=" + manager +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commissions=" + commissions +
                ", deptNo=" + deptNo +
                '}';
    }
}
