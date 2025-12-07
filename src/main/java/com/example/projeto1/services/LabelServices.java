
package com.example.projeto1.services;

import lombok.RequiredArgsConstructor;
import com.example.projeto1.models.Entity.Label;
import com.example.projeto1.models.Entity.Task;
import com.example.projeto1.models.EntityDTO.LabelDTO;
import com.example.projeto1.repositories.LabelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabelServices {

    private final LabelRepository labelRepository;

    public List<Label> searchAllLabels(List<Long> labelIds){
        return labelRepository.findAllById(labelIds);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public LabelDTO createLabelDTO(Label label){
        return new LabelDTO(label.getId(), label.getName(), label.getTasks());
    }

    public ResponseEntity<?> findLabelByID(Long idLabel){
        Optional<Label> label = labelRepository.findById(idLabel);

        if(label.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LabelDTO labelDTO = createLabelDTO(label.get());
        return ResponseEntity.ok().body(labelDTO);
    }

    public ResponseEntity<List<LabelDTO>> getAllLabels(){
        List<Label> labels = labelRepository.findAll();

        if(labels.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<LabelDTO> labelDTOs = new ArrayList<>();

        for(Label label : labels){
            LabelDTO labelDTO = createLabelDTO(label);
            labelDTOs.add(labelDTO);
        }

        return ResponseEntity.ok().body(labelDTOs);
    }

    public void createLabel(LabelDTO labelDTO){
        Label label = new Label(labelDTO.name());
        labelRepository.save(label);
    }

    public ResponseEntity<?> deleteLabel(Long idLabel){
        Optional<Label> foundLabel = labelRepository.findById(idLabel);

        if(foundLabel.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Label label = foundLabel.get();
        labelRepository.delete(label);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateLabel(Long idLabel, LabelDTO labelDTO){
        Optional<Label> foundLabel = labelRepository.findById(idLabel);

        if(foundLabel.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Label label = foundLabel.get();

        label.setName(labelDTO.name());

        labelRepository.save(label);
        return ResponseEntity.ok().build();

    }

}

