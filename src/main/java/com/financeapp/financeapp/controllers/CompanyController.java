package com.financeapp.financeapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.financeapp.financeapp.models.Company;
import com.financeapp.financeapp.models.Debtor;
import com.financeapp.financeapp.repository.IDebtorRepository;
import com.financeapp.financeapp.repository.IFinanceRepository;

@Controller
public class CompanyController {
	
	//injeção de dependência desta instância
	@Autowired
	private IFinanceRepository iFR;
	
	@Autowired
	private IDebtorRepository iDR;
	
	@RequestMapping(method=RequestMethod.GET, path="/")
	public String inicio() {
		return "redirect:/listaCompany";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/entrar")
	public String entrar() {
		return "Index";
	}
	
	
	@RequestMapping(value="/cadastrarCompany", method=RequestMethod.GET )
	public String form() {
		return "company/formCompany";
	}
	
	@RequestMapping(value="/cadastrarCompany", method=RequestMethod.POST )
	public String form(@Valid Company company, BindingResult result, RedirectAttributes attributes ) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarCompany";
		}
		iFR.save(company);
		attributes.addFlashAttribute("mensagem", "Compania adicionado com sucesso !");
		return "redirect:/listaCompany";
	}
	
	@RequestMapping(value="/listaCompany", method=RequestMethod.GET )
	public ModelAndView listaCompanys() {
		ModelAndView mv = new ModelAndView("company/listCompany");
		Iterable<Company> companys = iFR.findAll();
		mv.addObject("companys", companys);
		return mv;
	}

	@RequestMapping(value="/{companyId}", method=RequestMethod.GET)
	public ModelAndView detalhesCompany(@PathVariable("companyId") long companyId ) {
		Company company = iFR.findByCompanyId(companyId);
		ModelAndView mv = new ModelAndView("company/detalhesCompany");
		mv.addObject("company",company);
		Iterable<Debtor> debtors = iDR.findByCompany(company);
		mv.addObject("debtors",debtors);
		return mv;
	}
	
	@RequestMapping("/deletarCompany")
	public String deletarCompany(long companyId) {
		Company company = iFR.findByCompanyId(companyId);
		iFR.delete(company);
		return "redirect:/listaCompany";
		
	}
	
	@RequestMapping(value="/{companyId}", method=RequestMethod.POST)
	public String detalhesCompanyPost(@PathVariable("companyId") long companyId, @Valid Debtor debtor, BindingResult result, RedirectAttributes attributes ) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{companyId}";
		}
		Company company = iFR.findByCompanyId(companyId);
		debtor.setCompany(company);
		iDR.save(debtor);
		attributes.addFlashAttribute("mensagem", "Devedor adicionado com sucesso !");
		return "redirect:/{companyId}";
	}
	
	@RequestMapping("/deletarDebtor")
	public String deletarDebtor(String cpf) {
		Debtor debtor = iDR.findByCpf(cpf);
		iDR.delete(debtor);
		Company company = debtor.getCompany();
		long companyId = company.getCompanyId();
		String codigo = "" + companyId;
		return "redirect:/" + codigo;
		
	}
	
	
}
