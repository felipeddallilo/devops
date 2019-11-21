package com.financeapp.financeapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.financeapp.financeapp.models.Company;

public interface IFinanceRepository extends CrudRepository<Company, String>{
	
	Company findByCompanyId(long companyId);

}
