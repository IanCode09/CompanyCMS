package com.woyo.cms.controller;

import com.woyo.cms.DTO.CompanyDTO;
import com.woyo.cms.model.request.CreateCompanyRequestModel;
import com.woyo.cms.response.DataResponse;
import com.woyo.cms.response.HandlerResponse;
import com.woyo.cms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/company", produces = {"application/json"})
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public void companyRegister(HttpServletResponse response, @RequestBody CreateCompanyRequestModel createCompanyRequestModel) {
        CompanyDTO companyDTO = companyService.companyRegister(createCompanyRequestModel);

        DataResponse<CompanyDTO> data = new DataResponse<>();
        data.setData(companyDTO);
        HandlerResponse.responseSuccessCreatedWithData(response, data);
    }

    @GetMapping("/{companyId}")
    public void getCompanyById(HttpServletResponse response, @PathVariable int companyId) {
        CompanyDTO companyDTO = companyService.getCompanyById(companyId);

        if (companyDTO != null) {
            DataResponse<CompanyDTO> data = new DataResponse<>();
            data.setData(companyDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseNotFoundRequest(response, "404", "Company not found");
        }
    }

    @GetMapping("/all")
    public void getAllCompany(HttpServletResponse response) {
        List<CompanyDTO> companyDTOS = companyService.getAllCompany();

        DataResponse<List<CompanyDTO>> data = new DataResponse<>();
        data.setData(companyDTOS);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
