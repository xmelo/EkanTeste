package controller;

import exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.dto.DocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping(value = "/documento", produces = {"application/json"})
@Tag(name="ekan-api")
public class DocumentoController {

    @Autowired
    private DocumentoService service;


    @Operation(summary = "Busca", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros Invalidos"),
            @ApiResponse(responseCode = "401", description = "Usuario não Autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a atualização"),
    })

    @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<DocumentoDTO>> getAllDocumentsByBeneficiarioId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        List<DocumentoDTO> result = service.getAllByBeneficiarioId(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Documentos não encontrados!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
}
