package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsDoctorData> register(@RequestBody @Valid RegisterDoctorDataDTO data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);
        //devolver o cabeçalho location na resposta 201;
        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsDoctorData(doctor));
    }

    // http://localhost:8080/doctor?size=1&page=2 -> size: controlar o numero de registos que quero por página (por default é 20 registos)
    // page: controlar qual a página que quero apresentar (isto é consumido pelo client das nossas API (frontend)).
    // Para ordenação pode usar-se também um parametro na URL p. ex: http://localhost:8080/doctor?sort=nome (por default é ASC)
    // Para ser Desc: http://localhost:8080/doctor?sort=crm,desc
    // Se houver parametros na URL ele respeita esse, se não usa o default.
    @GetMapping
    public ResponseEntity<Page<ListDoctorDataDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) { //alterar o default do size; e ordenar pelo nome
        var page = repository.findAllByActiveTrue(pagination).map(ListDoctorDataDTO::new); //converter a entidade jpa num record com apenas os campos que queremos listar (DTO)
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional //como está dentro de uma transação não preciso de ter mais nada, ele vai detetar quando um campo é alterado.
    public ResponseEntity<DetailsDoctorData> update(@RequestBody @Valid UpdateDoctorDataDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        return ResponseEntity.ok(new DetailsDoctorData(doctor));
    }

    /* *
        *   Excluisão fsica - Forma de apagar realmente o registo
        *   @DeleteMapping("/{id}")
        *    public void delete(@PathVariable Long id) {
        *       repository.deleteById(id);
        *   }
        *
     */

    /**
     * Exclusão lógico - marcar o medico como inativo
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsDoctorData> details(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsDoctorData(doctor));
    }
}
