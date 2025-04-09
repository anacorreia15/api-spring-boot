package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorDataDTO data) {
        repository.save(new Doctor(data));
    }

    // http://localhost:8080/doctor?size=1&page=2 -> size: controlar o numero de registos que quero por página (por default é 20 registos)
    // page: controlar qual a página que quero apresentar (isto é consumido pelo client das nossas API (frontend)).
    // Para ordenação pode usar-se também um parametro na URL p. ex: http://localhost:8080/doctor?sort=nome (por default é ASC)
    // Para ser Desc: http://localhost:8080/doctor?sort=crm,desc
    // Se houver parametros na URL ele respeita esse, se não usa o default.
    @GetMapping
    public Page<ListDoctorDataDTO> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) { //alterar o default do size; e ordenar pelo nome
        return repository.findAllByActiveTrue(pagination).map(ListDoctorDataDTO::new); //converter a entidade jpa num record com apenas os campos que queremos listar (DTO)
    }


    @PutMapping
    @Transactional //como está dentro de uma transação não preciso de ter mais nada, ele vai detetar quando um campo é alterado.
    public void update(@RequestBody @Valid UpdateDoctorDataDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

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
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }

}
