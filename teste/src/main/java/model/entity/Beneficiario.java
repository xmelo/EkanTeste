package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "dataInclusao")
    private LocalDateTime dataInclusao;

    @Column(name = "dataAtualizacao")
    private LocalDateTime dataAtualizacao;

    @Override
    public String toString() {
        return "Beneficiario{ "
                + "Id: " + id + '\'' + ","
                + "Nome: " + nome + '\'' + ","
                + "Telefone: " + telefone + '\'' + ","
                + "Data Nascimento: " + dataNascimento + '\'' + ","
                + "Data Inclusao: " + dataInclusao + '\'' + ","
                + "Data Atualizacao: " + dataAtualizacao + '\'' + "}";
    }

    public Object getLogin() {
        return null;
    }

    public Object getEmail() {
        return null;
    }

    public void setSenha(String encode) {
    }

    public CharSequence getSenha() {
        return null;
    }
}