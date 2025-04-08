package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.adress.Adress;

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

    public Patient(RegisterPatientData patientData) {
        this.nome = patientData.nome();
        this.email = patientData.email();
        this.telefone = patientData.telefone();
        this.cpf = patientData.cpf();
        this.adress = new Adress(patientData.endereco());
    }
}
