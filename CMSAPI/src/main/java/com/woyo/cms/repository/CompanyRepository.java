package com.woyo.cms.repository;

import com.woyo.cms.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Integer> {
}
