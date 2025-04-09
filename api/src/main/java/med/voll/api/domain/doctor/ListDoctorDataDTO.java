package med.voll.api.domain.doctor;

public record ListDoctorDataDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ListDoctorDataDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }
}
