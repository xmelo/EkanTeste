package controller;

import exception.InternalServerErrorException;
import exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import model.dto.BeneficiarioAtualizacaoDTO;
import model.dto.BeneficiarioDTO;
import model.dto.BeneficiarioInclusaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BeneficiarioService;

import java.util.List;

@RestController
@RequestMapping(value = "/beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService service;

    @GetMapping
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

    @PostMapping
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