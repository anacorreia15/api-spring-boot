package med.voll.api.patient;

import med.voll.api.adress.Adress;
import med.voll.api.doctor.Doctor;

public record DetailsPatientData(Long id, String nome, String email, String telefone, String cpf, Adress endereco) {

    public DetailsPatientData (Patient patient) {
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getTelefone(), patient.getCpf(), patient.getAdress());
    }
}
