package model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Valid
@Getter
@Setter
@AllArgsConstructor
public class DocumentoInputDTO {

    @NotNull(message = "O campo documento precisa ser preenchido!")
    private String tipoDocumento;

    @NotNull(message = "O campo descrição precisa ser preenchido!")
    private String descricao;
}