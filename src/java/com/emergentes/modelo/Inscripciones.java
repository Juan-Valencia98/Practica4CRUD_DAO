package com.emergentes.modelo;
public class Inscripciones {
    private int id;
    private String curso;
    private String  estudiante;
    private int nota_final;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public int getNota_final() {
        return nota_final;
    }

    public void setNota_final(int nota_final) {
        this.nota_final = nota_final;
    }
    
    @Override
    public String toString(){
        return "Inscripciones{"+"id="+id+"curso="+curso+", estudiante="+estudiante+", nota_final="+nota_final+'}';
    }

}
