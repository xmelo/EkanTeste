package service;

import java.util.List;
import java.util.stream.Collectors;

import model.dto.DocumentoDTO;
import model.entity.Documento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DocumentoRepository;


@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    public DocumentoDTO saveDocumento(DocumentoDTO dto) throws Exception{

        Documento newDocumento = new Documento();
        BeanUtils.copyProperties(dto, newDocumento);

        newDocumento.setBeneficiario(dto.getIdBeneficiario());
        newDocumento = repository.save(newDocumento);

        DocumentoDTO result = new DocumentoDTO(
                newDocumento.getId(),
                newDocumento.getTipoDocumento(),
                newDocumento.getDescricao(),
                newDocumento.getDataInclusao(),
                newDocumento.getDataAtualizacao(),
                newDocumento.getBeneficiario());

        return result;
    }

    public List<DocumentoDTO> getAllByBeneficiarioId(Long idBeneficiario){
        return repository.getAllByBeneficiarioId(idBeneficiario)
                .stream()
                .map(documento -> new DocumentoDTO(
                        documento.getId(),
                        documento.getTipoDocumento(),
                        documento.getDescricao(),
                        documento.getDataInclusao(),
                        documento.getDataAtualizacao(),
                        idBeneficiario))
                .collect(Collectors.toList());
    }


    public void deleteAllDocumentsByBeneficiarioId(Long id) {
        repository.deleteAllDocumentsByBeneficiarioId(id);
    }
}