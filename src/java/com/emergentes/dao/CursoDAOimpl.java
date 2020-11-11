package com.emergentes.dao;

import com.emergentes.modelo.Curso;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOimpl extends ConexionDB implements CursoDAO {
    @Override
    public void insert(Curso curso) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into curso (descripcion) values (?)");
            ps.setString(1,curso.getDescripcion()); 
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Curso curso) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update curso set descripcion=? where id=?");
            ps.setString(1,curso.getDescripcion());
            ps.setInt(2, curso.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from curso where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Curso getById(int id) throws Exception {
        Curso pr =new Curso();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from curso where id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pr.setId(rs.getInt("id"));
                pr.setDescripcion(rs.getString("descripcion"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pr;
    }

    @Override
    public List<Curso> getAll() throws Exception {
        List<Curso> lista= null;
        try{
            this.conectar();
            String sql="select * from curso";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            lista=new ArrayList<Curso>();
            while(rs.next()){
                Curso pr = new Curso();
                pr.setId(rs.getInt("id"));
                pr.setDescripcion(rs.getString("descripcion"));
                lista.add(pr);
            }

        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;
    }
}
