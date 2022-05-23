package com.woyo.cms.model.request;

public class CreateCompanyRequestModel {
    private String companyCode;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private int companyPostalCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public int getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(int companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }
}
