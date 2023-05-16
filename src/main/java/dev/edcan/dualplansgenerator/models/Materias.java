package dev.edcan.dualplansgenerator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


/*
public class Materias {

    private byte[] myJsonString;
    private ObjectMapper om = new ObjectMapper();
    public Root[] root;

    public Materias() throws IOException {

    }


}*/

class Actividade{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("ponderacion")
    public double getPonderacion() {
        return this.ponderacion; }
    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion; }
    double ponderacion;
    @JsonProperty("horas")
    public int getHoras() {
        return this.horas; }
    public void setHoras(int horas) {
        this.horas = horas; }
    int horas;
    @JsonProperty("evidencia")
    public String getEvidencia() {
        return this.evidencia; }
    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia; }
    String evidencia;
    @JsonProperty("entidad")
    public int getEntidad() {
        return this.entidad; }
    public void setEntidad(int entidad) {
        this.entidad = entidad; }
    int entidad;
}

class Carrera{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("nombre")
    public String getNombre() {
        return this.nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    String nombre;
    @JsonProperty("clave")
    public Object getClave() {
        return this.clave; }
    public void setClave(Object clave) {
        this.clave = clave; }
    Object clave;
}

class Competencia{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("tipo")
    public int getTipo() {
        return this.tipo; }
    public void setTipo(int tipo) {
        this.tipo = tipo; }
    int tipo;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("clave")
    public Object getClave() {
        return this.clave; }
    public void setClave(Object clave) {
        this.clave = clave; }
    Object clave;
}

class CompetenciasEspecifica{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("tipo")
    public int getTipo() {
        return this.tipo; }
    public void setTipo(int tipo) {
        this.tipo = tipo; }
    int tipo;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
}

class CompetenciasGenerica{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("tipo")
    public int getTipo() {
        return this.tipo; }
    public void setTipo(int tipo) {
        this.tipo = tipo; }
    int tipo;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
}

class CompetenciasPrevia{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("tipo")
    public int getTipo() {
        return this.tipo; }
    public void setTipo(int tipo) {
        this.tipo = tipo; }
    int tipo;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("clave")
    public Object getClave() {
        return this.clave; }
    public void setClave(Object clave) {
        this.clave = clave; }
    Object clave;
}

class PlanDeEstudio{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
    @JsonProperty("porcentajeUE")
    public double getPorcentajeUE() {
        return this.porcentajeUE; }
    public void setPorcentajeUE(double porcentajeUE) {
        this.porcentajeUE = porcentajeUE; }
    double porcentajeUE;
    @JsonProperty("porcentajeIE")
    public double getPorcentajeIE() {
        return this.porcentajeIE; }
    public void setPorcentajeIE(double porcentajeIE) {
        this.porcentajeIE = porcentajeIE; }
    double porcentajeIE;
    @JsonProperty("temas")
    public ArrayList<Tema> getTemas() {
        return this.temas; }
    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas; }
    ArrayList<Tema> temas;
    @JsonProperty("competencias")
    public ArrayList<Competencia> getCompetencias() {
        return this.competencias; }
    public void setCompetencias(ArrayList<Competencia> competencias) {
        this.competencias = competencias; }
    ArrayList<Competencia> competencias;
    @JsonProperty("competenciasPrevias")
    public ArrayList<CompetenciasPrevia> getCompetenciasPrevias() {
        return this.competenciasPrevias; }
    public void setCompetenciasPrevias(ArrayList<CompetenciasPrevia> competenciasPrevias) {
        this.competenciasPrevias = competenciasPrevias; }
    ArrayList<CompetenciasPrevia> competenciasPrevias;
}

public class Materias {
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("nombre")
    public String getNombre() {
        return this.nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    String nombre;
    @JsonProperty("creditos")
    public int getCreditos() {
        return this.creditos; }
    public void setCreditos(int creditos) {
        this.creditos = creditos; }
    int creditos;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
    @JsonProperty("semestre")
    public int getSemestre() {
        return this.semestre; }
    public void setSemestre(int semestre) {
        this.semestre = semestre; }
    int semestre;
    @JsonProperty("carrera")
    public Carrera getCarrera() {
        return this.carrera; }
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera; }
    Carrera carrera;
    @JsonProperty("planDeEstudio")
    public PlanDeEstudio getPlanDeEstudio() {
        return this.planDeEstudio; }
    public void setPlanDeEstudio(PlanDeEstudio planDeEstudio) {
        this.planDeEstudio = planDeEstudio; }
    PlanDeEstudio planDeEstudio;
}

class Subtema{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return this.descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    String descripcion;
    @JsonProperty("clave")
    public String getClave() {
        return this.clave; }
    public void setClave(String clave) {
        this.clave = clave; }
    String clave;
}

 class Tema{
    @JsonProperty("id")
    public int getId() {
        return this.id; }
    public void setId(int id) {
        this.id = id; }
    int id;
    @JsonProperty("nombre")
    public String getNombre() {
        return this.nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    String nombre;
    @JsonProperty("numero")
    public int getNumero() {
        return this.numero; }
    public void setNumero(int numero) {
        this.numero = numero; }
    int numero;
    @JsonProperty("periodo")
    public int getPeriodo() {
        return this.periodo; }
    public void setPeriodo(int periodo) {
        this.periodo = periodo; }
    int periodo;
    @JsonProperty("peso")
    public double getPeso() {
        return this.peso; }
    public void setPeso(double peso) {
        this.peso = peso; }
    double peso;
    @JsonProperty("competenciasEspecificas")
    public ArrayList<CompetenciasEspecifica> getCompetenciasEspecificas() {
        return this.competenciasEspecificas; }
    public void setCompetenciasEspecificas(ArrayList<CompetenciasEspecifica> competenciasEspecificas) {
        this.competenciasEspecificas = competenciasEspecificas; }
    ArrayList<CompetenciasEspecifica> competenciasEspecificas;
    @JsonProperty("competenciasGenericas")
    public ArrayList<CompetenciasGenerica> getCompetenciasGenericas() {
        return this.competenciasGenericas; }
    public void setCompetenciasGenericas(ArrayList<CompetenciasGenerica> competenciasGenericas) {
        this.competenciasGenericas = competenciasGenericas; }
    ArrayList<CompetenciasGenerica> competenciasGenericas;
    @JsonProperty("actividades")
    public ArrayList<Actividade> getActividades() {
        return this.actividades; }
    public void setActividades(ArrayList<Actividade> actividades) {
        this.actividades = actividades; }
    ArrayList<Actividade> actividades;
    @JsonProperty("subtemas")
    public ArrayList<Subtema> getSubtemas() {
        return this.subtemas; }
    public void setSubtemas(ArrayList<Subtema> subtemas) {
        this.subtemas = subtemas; }
    ArrayList<Subtema> subtemas;
}

