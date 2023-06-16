package com.hus.ceremony.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hus.ceremony.Entity.Letter;

public interface LetterRepository extends JpaRepository<Letter, Integer> {  

    Letter findByRole(String role);
    
    List<Letter> findAll();

}
