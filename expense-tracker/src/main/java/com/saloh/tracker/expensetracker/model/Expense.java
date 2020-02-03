package com.saloh.tracker.expensetracker.model;

import javax.persistence.*;

@Entity
@Table(name="expenses")
public class Expense {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
@Column(name="expense_name", nullable = false)
    public String expense_name;
    @Column(name="expense_type", nullable = false)
    public String expense_type;
    @Column(name="expense_amount", nullable = false)
    public double expense_amount;


    public Expense(){

    }

    public Expense(String expense_name, String expense_type, double expense_amount) {
        this.expense_name = expense_name;
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public double getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(double expense_amount) {
        this.expense_amount = expense_amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expense_name='" + expense_name + '\'' +
                ", expense_type='" + expense_type + '\'' +
                ", expense_amount=" + expense_amount +
                '}';
    }
}
