package med.voll.api.patient;

public record ListPatientData(String nome, String email, String cpf) {

    public ListPatientData(Patient patient) {
        this(patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
