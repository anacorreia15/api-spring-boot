package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.ListDoctorData;
import med.voll.api.doctor.RegisterDoctorData;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorData data) {
        repository.save(new Doctor(data));
    }

    // http://localhost:8080/doctor?size=1&page=2 -> size: controlar o numero de registos que quero por página (por default é 20 registos)
    // page: controlar qual a página que quero apresentar (isto é consumido pelo client das nossas API (frontend)).
    // Para ordenação pode usar-se também um parametro na URL p. ex: http://localhost:8080/doctor?sort=nome (por default é ASC)
    // Para ser Desc: http://localhost:8080/doctor?sort=crm,desc
    // Se houver parametros na URL ele respeita esse, se não usa o default.
    @GetMapping
    public Page<ListDoctorData> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) { //alterar o default do size; e ordenar pelo nome
        return repository.findAll(pagination).map(ListDoctorData::new); //converter a entidade jpa num record com apenas os campos que queremos listar (DTO)
    }



}
