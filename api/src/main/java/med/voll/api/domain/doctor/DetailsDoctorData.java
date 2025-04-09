package med.voll.api.domain.doctor;

import med.voll.api.domain.adress.Adress;

public record DetailsDoctorData(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Adress endereco) {

    public DetailsDoctorData (Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getTelefone(), doctor.getCrm(), doctor.getEspecialidade(), doctor.getAdress());
    }
}
