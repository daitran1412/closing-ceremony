package com.hus.ceremony.Service.Implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.hus.ceremony.Dto.LetterDto;
import com.hus.ceremony.Entity.Letter;
import com.hus.ceremony.Repository.LetterRepository;
import com.hus.ceremony.Service.LetterService;

public class LetterServiceImp implements LetterService {

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Letter createLetter(LetterDto letterDto) {
        
        Letter letter = modelMapper.map(letterDto, Letter.class);
        return letterRepository.save(letter);

    }

    @Override
    public Letter getLetterByRole(String role) {
        
        return letterRepository.findByRole(role);

    }

    @Override
    public List<Letter> getAllLetter() {
        
        return letterRepository.findAll();

    }
    
    @Override
    public boolean updateLetter(String role, LetterDto letterDto) {
        
        Letter letter = letterRepository.findByRole(role);
        if (letter == null) {
            return false;
        } else {
            letter.setContent(letterDto.getContent());
            letterRepository.save(letter);
            return true;
        }

    }
  
}
