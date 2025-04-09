package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.AdressData;

public record UpdatePatientDataDTO(

        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        AdressData endereco
) {}
