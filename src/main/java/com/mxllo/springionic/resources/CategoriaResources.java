package com.mxllo.springionic.resources;

import com.mxllo.springionic.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> getlistar(){
        Categoria cat1 = new Categoria(1, "informatica");
        Categoria cat2 = new Categoria(2, "escritorio");
        List<Categoria> list = new ArrayList<Categoria>();
        list.add(cat1);
        list.add(cat2);
        return list;
    }

}
