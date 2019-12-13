package com.karan.mapstedtestcase.model;

public class PurchasesModel {
    private int item_id;
    private int item_category_id;
    private double cost;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_category_id() {
        return item_category_id;
    }

    public void setItem_category_id(int item_category_id) {
        this.item_category_id = item_category_id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
