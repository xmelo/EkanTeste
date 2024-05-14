package repository;

import java.util.List;

import model.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{

    @Query(value = "SELECT d FROM Documento d WHERE d.beneficiario = :idBeneficiario")
    List<Documento> getAllByBeneficiarioId(@Param("idBeneficiario") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Documento d where d.id = :id")
    void deleteAllDocumentsByBeneficiarioId(@Param("id") Long id);
}