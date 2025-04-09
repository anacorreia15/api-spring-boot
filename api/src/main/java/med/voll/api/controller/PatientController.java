package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    public void register(@RequestBody @Valid RegisterPatientDataDTO data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<ListPatientDataDTO> list(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListPatientDataDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientDataDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }
}
