package edu.txstate.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.txstate.cs.model.dto.Meal;

public interface MealRepo extends JpaRepository<Meal, Long>{

}
