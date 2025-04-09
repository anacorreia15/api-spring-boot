package med.voll.api.domain.patient;

public record ListPatientDataDTO(Long id, String nome, String email, String cpf) {

    public ListPatientDataDTO(Patient patient) {
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getCpf());
    }

}
