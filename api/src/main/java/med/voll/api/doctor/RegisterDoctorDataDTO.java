package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.adress.AdressData;

public record RegisterDoctorDataDTO(

        @NotBlank //Verifica se não é nulo nem vazio
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //expressão regex: valida se é de 4 a 6 digitos
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid AdressData endereco
) {}
