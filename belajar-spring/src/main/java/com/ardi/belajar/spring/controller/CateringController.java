package com.ardi.belajar.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ardi.belajar.spring.model.Beverages;
import com.ardi.belajar.spring.model.Catering;
import com.ardi.belajar.spring.repository.BeveragesRepository;
import com.ardi.belajar.spring.repository.CateringRepository;

@Controller
public class CateringController {
	
	@Autowired
	CateringRepository cateringRepository;
	
	@Autowired
	BeveragesRepository beveragesRepository;
	
	@RequestMapping("/catering/{id}")
	public String catering(@PathVariable Long id, Model model){
		model.addAttribute("catering", cateringRepository.findOne(id));
		model.addAttribute("beverages", beveragesRepository.findAll());
		return "cateringDetails";
	}
	
	@RequestMapping(value="/catering",method=RequestMethod.GET)
	public String cateringList(Model model){
		model.addAttribute("catering", cateringRepository.findAll());
		return "cateringList";
	}
	
	@RequestMapping(value="/catering",method=RequestMethod.POST)
	public String cateringAdd(@RequestParam String cateringName, @RequestParam String alamat, Model model){
		Catering newCatering = new Catering();
		newCatering.setCateringName(cateringName);
		newCatering.setAlamat(alamat);
		cateringRepository.save(newCatering);	
		
		model.addAttribute("catering", newCatering);
		model.addAttribute("beverages", beveragesRepository.findAll());
		return "redirect:/catering/" + newCatering.getId();
	}
	
	@RequestMapping(value="/catering/{cateringId}/beverages", method=RequestMethod.POST)
	public String cateringAddBeverages(@PathVariable Long cateringId, @RequestParam Long beveragesId, Model model){
		Beverages beverages = beveragesRepository.findOne(beveragesId);
		Catering catering = cateringRepository.findOne(cateringId);
		
		if(catering != null){
			if(!catering.hasBeverages(beverages)){
				catering.getBeverages().add(beverages);
			}
			cateringRepository.save(catering);
			model.addAttribute("catering", cateringRepository.findOne(cateringId));
			model.addAttribute("beverages", beveragesRepository.findAll());
			return "redirect:/catering/" + catering.getId();
		}
		model.addAttribute("catering", cateringRepository.findAll());
		return "redirect:/catering";
	}
}
