package model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipoDocumento")
    private String tipoDocumento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataInclusao")
    private LocalDateTime dataInclusao;

    @Column(name = "dataAtualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "idBeneficiario")
    private Long beneficiario;

    @Override
    public String toString() {
        return "Documento { "
                + "Id: " + id + '\'' + ","
                + "Tipo Documento: " + tipoDocumento + '\'' + ","
                + "Data Inclusao: " + dataInclusao + '\'' + ","
                + "Data Atualizacao: " + dataAtualizacao + '\'' + "}";
    }
}