package com.karan.mapstedtestcase.model;

public class SessionInfoItemModel {
    private int building_id;
    private PurchasesModel[] purchases;

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public PurchasesModel[] getPurchases() {
        return purchases;
    }

    public void setPurchases(PurchasesModel[] purchases) {
        this.purchases = purchases;
    }
}
