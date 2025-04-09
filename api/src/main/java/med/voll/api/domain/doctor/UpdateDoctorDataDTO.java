package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.AdressData;

public record UpdateDoctorDataDTO(

        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        AdressData endereco
) {}
