package com.woyo.cms.service.impl;

import com.woyo.cms.DTO.CompanyDTO;
import com.woyo.cms.model.CompanyModel;
import com.woyo.cms.model.request.CreateCompanyRequestModel;
import com.woyo.cms.repository.CompanyRepository;
import com.woyo.cms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDTO companyRegister(CreateCompanyRequestModel companyRequestModel) {
        CompanyModel company = new CompanyModel();
        company.setCompanyCode(companyRequestModel.getCompanyCode());
        company.setCompanyName(companyRequestModel.getCompanyName());
        company.setCompanyAddress(companyRequestModel.getCompanyAddress());
        company.setCompanyPhone(companyRequestModel.getCompanyPhone());
        company.setCompanyPostalCode(companyRequestModel.getCompanyPostalCode());
        company.setCreatedAt(LocalDateTime.now());

        return convertCompanyDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDTO getCompanyById(int companyId) {
        Optional<CompanyModel> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            return convertCompanyDTO(company.get());
        } else {
            return null;
        }
    }

    @Override
    public List<CompanyDTO> getAllCompany() {
        return companyRepository.findAll().stream().map(this::convertCompanyDTO).collect(Collectors.toList());
    }

    public CompanyDTO convertCompanyDTO(CompanyModel item) {
        return new CompanyDTO(item.getCompanyId(), item.getCompanyCode(), item.getCompanyName(),
                item.getCompanyAddress(), item.getCompanyPhone(), item.getCompanyPostalCode());
    }
}
