package com.mxllo.springionic.services;

import com.mxllo.springionic.model.Categoria;
import com.mxllo.springionic.repositories.CategoriaRepository;
import com.mxllo.springionic.services.exceptions.DataIntegrityException;
import com.mxllo.springionic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;


    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
        DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a categoria, a mesma possui produtos.");
        }
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }
}
