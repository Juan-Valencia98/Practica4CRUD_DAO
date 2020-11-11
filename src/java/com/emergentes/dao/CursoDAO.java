package com.emergentes.dao;

import com.emergentes.modelo.Curso;
import java.util.List;

public interface CursoDAO {
    public void insert(Curso curso) throws Exception; 
    public void update(Curso curso) throws Exception; 
    public void delete(int id) throws Exception; 
    public Curso getById(int id) throws Exception; 
    public List<Curso> getAll() throws Exception; 
}
