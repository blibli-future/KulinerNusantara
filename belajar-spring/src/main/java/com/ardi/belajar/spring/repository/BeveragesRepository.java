package com.ardi.belajar.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ardi.belajar.spring.model.Beverages;

public interface BeveragesRepository extends CrudRepository<Beverages, Long>{
	public List<Beverages> findByName(String name);
}
