package com.sismanut.sismanut.services;

import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.MaterialDTOIn;
import com.sismanut.sismanut.repositories.ControleDeEstoqueRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ControleDeEstoqueService {
    @Autowired
    ControleDeEstoqueRepository repository;
    public String cadastrarMaterial(@Valid MaterialDTOIn material)
            throws DataIntegrityViolationException {
        /*
        verificar unicidade de cor, marca e descricao
         */

        return material.getUnidadesDeMedida().get(0).getNome();
//        return repository.buscarCorPorNome(material.getCor().getCor()).toString();
    }
}
