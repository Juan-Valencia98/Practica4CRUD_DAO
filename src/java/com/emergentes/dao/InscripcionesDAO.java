package com.emergentes.dao;

import com.emergentes.modelo.Inscripciones;
import java.util.List;

public interface InscripcionesDAO {
    public void insert(Inscripciones inscripciones) throws Exception; 
    public void update(Inscripciones inscripciones) throws Exception; 
    public void delete(int id) throws Exception; 
    public Inscripciones getById(int id) throws Exception; 
    public List<Inscripciones> getAll() throws Exception; 
}
