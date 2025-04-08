package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.ListPatientData;
import med.voll.api.patient.Patient;
import med.voll.api.patient.RegisterPatientData;
import med.voll.api.patient.PatientRepository;
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
    public void register(@RequestBody @Valid RegisterPatientData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<ListPatientData> list(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pagination) {
        return repository.findAll(pagination).map(ListPatientData::new);
    }
}
