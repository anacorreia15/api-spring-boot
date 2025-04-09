package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Adress;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Adress adress;

    private boolean active;

    public Patient(RegisterPatientDataDTO patientData) {
        this.nome = patientData.nome();
        this.email = patientData.email();
        this.telefone = patientData.telefone();
        this.cpf = patientData.cpf();
        this.adress = new Adress(patientData.endereco());
        this.active = true;
    }

    public void updateData(UpdatePatientDataDTO data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if (data.endereco() != null) {
            this.adress.updateAdress(data.endereco());
        }
    }

    public void delete() {
        this.active = false;
    }
}
