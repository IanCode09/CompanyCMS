package com.woyo.cms.service;

import com.woyo.cms.DTO.CompanyDTO;
import com.woyo.cms.model.request.CreateCompanyRequestModel;

import java.util.List;

public interface CompanyService {
    CompanyDTO companyRegister(CreateCompanyRequestModel companyRequestModel);
    CompanyDTO getCompanyById(int companyId);
    List<CompanyDTO> getAllCompany();
}
