package model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioInclusaoDTO {

    @NotNull(message = "O campo nome precisa ser preenchido!")
    private String nome;

    @NotNull(message = "O campo telefone precisa ser preenchido!")
    private String telefone;

    @Valid
    @NotNull(message = "O campo documento precisa ser preenchido!")
    private List<DocumentoInputDTO> documentos;

    @NotNull(message = "A data de nascimento precisa ser preenchida!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
}