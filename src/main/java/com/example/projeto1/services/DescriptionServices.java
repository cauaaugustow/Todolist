package com.example.projeto1.services;

import com.example.projeto1.models.EntityDTO.DescriptionDTO;
import com.example.projeto1.repositories.DescriptionRepository;
import lombok.RequiredArgsConstructor;
import com.example.projeto1.models.Entity.Description;
import com.example.projeto1.models.Entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DescriptionServices {

    private final DescriptionRepository descriptionRepository;

    public DescriptionDTO createDescriptionDTO(Description description){
        return new DescriptionDTO(description.getText(),  description.getTask().getId());
    }

    public ResponseEntity<DescriptionDTO> findDescriptionById(Long idDescription){
        Optional<Description> foundDescription = descriptionRepository.findById(idDescription);

        if(foundDescription.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        DescriptionDTO descriptionDTO = createDescriptionDTO(foundDescription.get());
        return ResponseEntity.ok().body(descriptionDTO);
    }

    public ResponseEntity<List<DescriptionDTO>> getAllDescriptions(){
        List<Description> descriptions = descriptionRepository.findAll();

        if(descriptions.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();

        for(Description description: descriptions){
            DescriptionDTO descriptiondto = createDescriptionDTO(description);
            descriptionDTOS.add(descriptiondto);
        }
        return ResponseEntity.ok(descriptionDTOS);

    }

    public void createDescription(DescriptionDTO descriptionDTO, Task task){

        Description description = new Description(descriptionDTO.text(), task);

        descriptionRepository.save(description);
    }

    public void deleteDescription(Long idDescription){
        Optional<Description> foundDescription = descriptionRepository.findById(idDescription);

        if(foundDescription.isEmpty()){
            throw new RuntimeException("Description not found");
        }
        descriptionRepository.deleteById(idDescription);
    }

    public void updateDescription(Long idDescription, DescriptionDTO descriptionDTO){
        Optional<Description> foundDescription = descriptionRepository.findById(idDescription);

        if(foundDescription.isEmpty()){
            throw new RuntimeException("Description not found");
        }

        Description description = foundDescription.get();

        description.setText(descriptionDTO.text());

        descriptionRepository.save(description);
    }

}