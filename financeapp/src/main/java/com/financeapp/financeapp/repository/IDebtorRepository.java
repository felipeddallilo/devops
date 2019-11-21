package com.financeapp.financeapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.financeapp.financeapp.models.Company;
import com.financeapp.financeapp.models.Debtor;

public interface IDebtorRepository extends CrudRepository<Debtor,String>{
  
	 Iterable<Debtor> findByCompany(Company company);
	 Debtor findByCpf(String cpf);
	
}
