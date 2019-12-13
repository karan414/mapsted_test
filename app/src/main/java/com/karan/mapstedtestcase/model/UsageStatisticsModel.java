package com.karan.mapstedtestcase.model;

public class UsageStatisticsModel {
    private String manufacturer;
    private String market_name;
    private String codename;
    private String model;
    private SessionInfosModel usage_statistics;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public SessionInfosModel getUsage_statistics() {
        return usage_statistics;
    }

    public void setUsage_statistics(SessionInfosModel usage_statistics) {
        this.usage_statistics = usage_statistics;
    }
}
