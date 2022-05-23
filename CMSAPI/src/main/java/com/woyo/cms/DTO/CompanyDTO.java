package com.woyo.cms.DTO;

public class CompanyDTO {
    private int companyId;
    private String companyCode;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private int companyPostalCode;

    public CompanyDTO() {
    }

    public CompanyDTO(int companyId, String companyCode, String companyName, String companyAddress, String companyPhone, int companyPostalCode) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhone = companyPhone;
        this.companyPostalCode = companyPostalCode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

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
