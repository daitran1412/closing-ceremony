package com.hus.ceremony.Service;

import java.util.List;

import com.hus.ceremony.Dto.LetterDto;
import com.hus.ceremony.Entity.Letter;

public interface LetterService {

    Letter createLetter(LetterDto letterDto);

    Letter getLetterByRole(String role);

    List <Letter> getAllLetter();

    boolean updateLetter(String role, LetterDto letterDto);

    void deleteLetterByRole(String role);

    void deleteAllLetter();
    
}
