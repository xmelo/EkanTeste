package repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import model.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{

    @Modifying
    @Transactional
    @Query("  UPDATE Beneficiario b "
            + 	"SET "
            + 	" nome = :nome, "
            + 	" telefone = :telefone, "
            + 	" dataNascimento = :dataNascimento, "
            + 	" dataInclusao = :dataInclusao, "
            + 	" dataAtualizacao = :dataAtualizacao "
            + 	"WHERE b.id = :id")
    void updateBeneficiario(
            String nome,
            String telefone,
            LocalDate dataNascimento,
            LocalDateTime dataInclusao,
            LocalDateTime dataAtualizacao,
            Long id);

    Optional<Beneficiario> findByLogin(String login);

}