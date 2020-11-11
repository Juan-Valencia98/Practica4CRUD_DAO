package com.emergentes.dao;

import com.emergentes.modelo.Estudiante;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOimpl extends ConexionDB implements EstudianteDAO {

    @Override
    public void insert(Estudiante estudiante) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into estudiante (nombre ,apellidos ,correo) values (?,?,?)");
            ps.setString(1,estudiante.getNombre()); 
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3,estudiante.getCorreo());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Estudiante estudiante) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update estudiante set nombre=?, apellidos=?, correo=? where id=?");
            ps.setString(1,estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3,estudiante.getCorreo());
            ps.setInt(4, estudiante.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("delete from estudiante where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Estudiante getById(int id) throws Exception {
        Estudiante pr =new Estudiante();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from estudiante where id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pr.setId(rs.getInt("id"));
                pr.setNombre(rs.getString("nombre"));
                pr.setApellidos(rs.getString("apellidos"));
                pr.setCorreo(rs.getString("correo"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pr;
    }

    @Override
    public List<Estudiante> getAll() throws Exception {
        List<Estudiante> lista= null;
        try{
            this.conectar();
            String sql="select * from estudiante";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            lista=new ArrayList<Estudiante>();
            while(rs.next()){
                Estudiante pr = new Estudiante();
                pr.setId(rs.getInt("id"));
                pr.setNombre(rs.getString("nombre"));
                pr.setApellidos(rs.getString("apellidos"));
                pr.setCorreo(rs.getString("correo"));
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
