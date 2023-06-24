package com.hus.ceremony.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hus.ceremony.Dto.LetterDto;
import com.hus.ceremony.Entity.Letter;
import com.hus.ceremony.Entity.Status;
import com.hus.ceremony.Service.LetterService;

@RestController
@RequestMapping("api/v1/letter")
@CrossOrigin
public class LetterController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterService letterService;

    public LetterController(LetterService letterService) {
        // TODO Auto-generated constructor stub
        this.letterService = letterService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLetter(@RequestBody LetterDto letterDto) {

        Letter creLetter = letterService.createLetter(letterDto);
        LetterDto creLetterDto = modelMapper.map(creLetter, LetterDto.class);
        return ResponseEntity.ok(creLetterDto);

    }

    @GetMapping("/get/role/{role}")
    public ResponseEntity<?> getLetterByRole(@PathVariable String role) {

        Letter letter = letterService.getLetterByRole(role);
        if (letter == null) {
            return ResponseEntity.ok(new Status("Letter not found"));
        } else {
            LetterDto letterDto = modelMapper.map(letter, LetterDto.class);
            return ResponseEntity.ok(letterDto);
        }

    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllLetter() {

        return ResponseEntity.ok(letterService.getAllLetter());

    }

    @PostMapping("/update/{role}")
    public ResponseEntity<?> updateLetter(@PathVariable String role, @RequestBody LetterDto letterDto) {

        boolean updateLetter = letterService.updateLetter(role, letterDto);
        if (updateLetter) {
            return ResponseEntity.ok(new Status("Update letter successfully"));
        } else {
            return ResponseEntity.ok(new Status("Update letter failed"));
        }

    }

    @PostMapping("/delete/role/{role}")
    public ResponseEntity<?> deleteLetterByRole(@PathVariable String role) {

        if(letterService.getLetterByRole(role) == null) {
            return ResponseEntity.ok(new Status("Role not found"));
        } else {
            letterService.deleteLetterByRole(role);
            return ResponseEntity.ok(new Status("Delete letter successfully"));
        }

    }

    @PostMapping("/delete/all")
    public ResponseEntity<?> deleteAllLetter() {

        if(letterService.getAllLetter().size() != 0) {
            letterService.deleteAllLetter();
        } else {
            return ResponseEntity.ok(new Status("Not letter"));
        }
        if(letterService.getAllLetter().size() == 0) {
            return ResponseEntity.ok(new Status("Delete all letter successfully"));
        } else {
            return ResponseEntity.ok(new Status("Delete all letter failed"));
        }

    }

}
