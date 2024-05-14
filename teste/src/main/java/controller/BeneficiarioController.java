package controller;

import exception.InternalServerErrorException;
import exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import model.dto.BeneficiarioAtualizacaoDTO;
import model.dto.BeneficiarioDTO;
import model.dto.BeneficiarioInclusaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BeneficiarioService;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "/beneficiario", produces = {"application/json"})
@Tag(name="ekan-api")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService service;

    @Operation(summary = "Busca", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros Invalidos"),
            @ApiResponse(responseCode = "401", description = "Usuario não Autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a atualização"),
    })


    @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<BeneficiarioDTO>> getAllBeneficiarios(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllBeneficiarios());
    }

    @PutMapping
    public ResponseEntity<?> insertBeneficiario(@Valid @RequestBody BeneficiarioInclusaoDTO beneficiarioInclusaoDTO) throws InternalServerErrorException {


        BeneficiarioDTO insertBeneficiario = service.insertBeneficiario(beneficiarioInclusaoDTO);

        if (insertBeneficiario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(insertBeneficiario);
        } else {
            throw new InternalServerErrorException("Erro na inclusão!");
        }
    }

    @Operation(summary = "Atualização", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros Invalidos"),
            @ApiResponse(responseCode = "401", description = "Usuario não Autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a atualização"),
    })


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBeneficiario(
            @RequestParam("id") Long id,
            @RequestBody BeneficiarioAtualizacaoDTO beneficiarioAtualizacaoDTO) throws Exception {

        service.atualizacaoBeneficiario(id,
                new BeneficiarioInclusaoDTO(
                        beneficiarioAtualizacaoDTO.getNome(),
                        beneficiarioAtualizacaoDTO.getTelefone(),
                        null,
                        beneficiarioAtualizacaoDTO.getDataNascimento()));

        return ResponseEntity.status(HttpStatus.OK).body("Campo Beneficiario atualizado!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBeneficiario(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.deleteBeneficiario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Documentos excluídos com sucesso");
    }
}