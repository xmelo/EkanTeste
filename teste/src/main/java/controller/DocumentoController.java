package controller;

import exception.ResourceNotFoundException;
import model.dto.DocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping(value = "/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> getAllDocumentsByBeneficiarioId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        List<DocumentoDTO> result = service.getAllByBeneficiarioId(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Documentos não encontrados!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
}
