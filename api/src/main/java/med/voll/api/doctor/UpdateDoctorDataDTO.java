package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.AdressData;

public record UpdateDoctorDataDTO(

        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        AdressData endereco
) {}
