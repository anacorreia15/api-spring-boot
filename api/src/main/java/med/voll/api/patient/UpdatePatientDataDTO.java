package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.AdressData;

public record UpdatePatientDataDTO(

        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid
        AdressData endereco
) {}
