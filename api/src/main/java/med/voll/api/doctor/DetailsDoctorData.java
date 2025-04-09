package med.voll.api.doctor;

import med.voll.api.adress.Adress;

public record DetailsDoctorData(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Adress endereco) {

    public DetailsDoctorData (Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getTelefone(), doctor.getCrm(), doctor.getEspecialidade(), doctor.getAdress());
    }
}
