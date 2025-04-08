package med.voll.api.doctor;

public record ListDoctorData(String nome, String email, String crm, Especialidade especialidade) {

    public ListDoctorData (Doctor doctor) {
        this(doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }
}
