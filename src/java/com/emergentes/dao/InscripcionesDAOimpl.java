package com.emergentes.dao;

import com.emergentes.modelo.Inscripciones;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesDAOimpl extends ConexionDB implements InscripcionesDAO  {
    @Override
    public void insert(Inscripciones inscripciones) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into inscripciones (id_curso,id_estudiante,nota_final) values ("
                    + "(select id from curso where descripcion=?),"
                    + "(select id from (select id, concat(nombre,' ',apellidos) as completo from estudiante) a where completo=?),"
                    + " ?)");
            ps.setString(1,inscripciones.getCurso()); 
            ps.setString(2,inscripciones.getEstudiante()); 
            ps.setInt(3,inscripciones.getNota_final()); 
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Inscripciones inscripciones) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update inscripciones set id_curso="
                    + "(select id from curso where descripcion=?), id_estudiante="
                    + "(select id from (select id, concat(nombre,' ',apellidos) as estudiante from estudiante) a where estudiante=?), nota_final=? where id=?");
            ps.setString(1,inscripciones.getCurso()); 
            ps.setString(2,inscripciones.getEstudiante()); 
            ps.setInt(3,inscripciones.getNota_final()); 
            ps.setInt(4,inscripciones.getId()); 
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
            PreparedStatement ps = this.conn.prepareStatement("delete from inscripciones where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Inscripciones getById(int id) throws Exception {
        Inscripciones pr =new Inscripciones();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select i.id as id,c.descripcion as curso,concat(e.nombre , ' ',e.apellidos) as estudiante, i.nota_final as nota_final"
                    + " from inscripciones i left join curso c on i.id_curso=c.id left join estudiante e on i.id_estudiante=e.id where i.id=? limit 1");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pr.setId(rs.getInt("id"));
                pr.setCurso(rs.getString("curso"));
                pr.setEstudiante(rs.getString("estudiante"));
                pr.setNota_final(rs.getInt("nota_final"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pr;
    }

    @Override
    public List<Inscripciones> getAll() throws Exception {
        List<Inscripciones> lista= null;
        try{
            this.conectar();
            String sql="select i.id as id,c.descripcion as curso ,concat(e.nombre , ' ',e.apellidos) as estudiante, i.nota_final as nota_final "
                    + "from inscripciones i left join curso c on i.id_curso=c.id left join estudiante e on i.id_estudiante=e.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            lista=new ArrayList<Inscripciones>();
            while(rs.next()){
                Inscripciones pr = new Inscripciones();
                pr.setId(rs.getInt("id"));
                pr.setCurso(rs.getString("curso"));
                pr.setEstudiante(rs.getString("estudiante"));
                pr.setNota_final(rs.getInt("nota_final"));
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
