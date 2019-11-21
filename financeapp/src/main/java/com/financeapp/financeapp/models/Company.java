package com.financeapp.financeapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;

@Entity
public class Company implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long companyId;
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;
	@NotEmpty
	private String data;
	private String horario;
	
	@OneToMany
	private List<Debtor> listDebtors;
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public List<Debtor> getListDebtors() {
		return listDebtors;
	}
	public void setListDebtors(List<Debtor> listDebtors) {
		this.listDebtors = listDebtors;
	}
	
	
	
}
