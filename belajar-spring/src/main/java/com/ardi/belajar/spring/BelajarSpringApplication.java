package com.ardi.belajar.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ardi.belajar.spring.model.Beverages;
import com.ardi.belajar.spring.model.Catering;
import com.ardi.belajar.spring.repository.BeveragesRepository;
import com.ardi.belajar.spring.repository.CateringRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class BelajarSpringApplication implements CommandLineRunner{

	@Autowired
	CateringRepository cateringRepository;
	
	@Autowired
	BeveragesRepository beveragesRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Beverages nasi = new Beverages("nasi", "Makanan nasi");
		Beverages jagung = new Beverages("jagung", "Makanan jagung");
		Beverages sagu = new Beverages("sagu", "Makanan sagu");
		Beverages bubur = new Beverages("bubur", "Makanan bubur");
		
		beveragesRepository.save(nasi);
		beveragesRepository.save(jagung);
		beveragesRepository.save(sagu);
		beveragesRepository.save(bubur);
		
		List<Catering> catering = new LinkedList<Catering>();
		catering.add(new Catering("John", "Jateng", 
				Arrays.asList(new Beverages[] { nasi, jagung })));
		catering.add(new Catering("Juan", "Jatim", 
				Arrays.asList(new Beverages[] { nasi, bubur })));
		catering.add(new Catering("Jack", "Jabar", 
				Arrays.asList(new Beverages[] { sagu, jagung })));
		catering.add(new Catering("Jim", "Jakarta", 
				Arrays.asList(new Beverages[] { bubur, sagu })));
		
		cateringRepository.save(catering);
	}
}
