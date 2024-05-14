package service;

import exception.InternalServerErrorException;
import exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import model.dto.*;
import model.entity.Beneficiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.BeneficiarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DocumentoService documentoService;

    public List<BeneficiarioDTO> getAllBeneficiarios() {

        return repository.findAll()
                .stream()
                .map(beneficiario -> new BeneficiarioDTO(
                        beneficiario.getId(),
                        beneficiario.getNome(),
                        beneficiario.getTelefone(),
                        beneficiario.getDataNascimento(),
                        beneficiario.getDataInclusao(),
                        beneficiario.getDataAtualizacao(),
                        documentoService.getAllByBeneficiarioId(beneficiario.getId())))
                .collect(Collectors.toList());
    }

    public BeneficiarioDTO insertBeneficiario(@Valid BeneficiarioInclusaoDTO dto) throws InternalServerErrorException {

        Beneficiario newBeneficiario = new Beneficiario(null, dto.getNome(), dto.getTelefone(), dto.getDataNascimento(), null, null);
        newBeneficiario.setSenha(passwordEncoder.encode(newBeneficiario.getSenha()));
        newBeneficiario.setDataInclusao(LocalDateTime.now());

        try {
            Beneficiario result = repository.save(newBeneficiario);
            List<DocumentoDTO> newDocumentosDTO = new ArrayList<DocumentoDTO>();


            for (DocumentoInputDTO documentoInput : dto.getDocumentos()) {
                DocumentoDTO documento = new DocumentoDTO(
                        null,
                        documentoInput.getTipoDocumento(),
                        documentoInput.getDescricao(),
                        LocalDateTime.now(),
                        null,
                        result.getId());

                newDocumentosDTO.add(documentoService.saveDocumento(documento));

                return new BeneficiarioDTO(
                        result.getId(),
                        result.getNome(),
                        result.getTelefone(),
                        result.getDataNascimento(),
                        LocalDateTime.now(),
                        null,
                        newDocumentosDTO);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Erro na inclusão!");
        }
        return null;
    }

    public void atualizacaoBeneficiario(Long id, BeneficiarioInclusaoDTO dto) throws Exception {

        Optional<Beneficiario> validacaoBeneficiario = repository.findById(id);

        if (validacaoBeneficiario.isEmpty()) {
            throw new ResourceNotFoundException("Beneficiario não encontrado!");
        } else {
            repository.save(excecucaoBeneficiarioAtualizacao(validacaoBeneficiario.get(), dto));
        }
    }

    private Beneficiario excecucaoBeneficiarioAtualizacao(Beneficiario beneficiario, BeneficiarioInclusaoDTO dto) {
        beneficiario.setNome(dto.getNome() != null ? dto.getNome() : beneficiario.getNome());

        beneficiario.setTelefone(dto.getTelefone() != null ? dto.getTelefone() : beneficiario.getTelefone());

        beneficiario.setDataNascimento(dto.getDataNascimento() != null ? dto.getDataNascimento() : beneficiario.getDataNascimento());

        beneficiario.setDataAtualizacao(LocalDateTime.now());

        return beneficiario;
    }

    public Boolean deleteBeneficiario(Long id) throws ResourceNotFoundException {

        Optional<Beneficiario> checkBeneficiario = repository.findById(id);

        if (checkBeneficiario.isEmpty()) {
            throw new ResourceNotFoundException("Beneficiario não encontrado");
        }

        documentoService.deleteAllDocumentsByBeneficiarioId(id);
        repository.deleteById(id);

        return true;
    }
}