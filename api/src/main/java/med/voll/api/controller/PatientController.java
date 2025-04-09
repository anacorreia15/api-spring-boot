package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.ListPatientDataDTO;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    public ResponseEntity<DetailsPatientData> register(@RequestBody @Valid RegisterPatientDataDTO data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsPatientData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientDataDTO>> list(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(ListPatientDataDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailsPatientData> update(@RequestBody @Valid UpdatePatientDataDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
        return ResponseEntity.ok(new DetailsPatientData(patient));
    }

    /**
     * Exclusão lógico - marcar o paciente como inativo
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsPatientData> details(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsPatientData(patient));
    }
}
