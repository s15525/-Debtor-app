package com.example.prm1;

import androidx.annotation.NonNull;

public class Debtor {
   private String name;
   private double debt;

    public Debtor(String name, double debt) {
        this.name = name;
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + debt;
    }
}
