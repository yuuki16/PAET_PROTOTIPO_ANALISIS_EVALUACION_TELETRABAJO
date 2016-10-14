package PAET_DOMAIN;
// Generated 13-oct-2016 20:16:04 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetPtPuesto generated by hbm2java
 */
public class PaetPtPuesto  implements java.io.Serializable {


     private String ptCodigo;
     private PaetGrGerencia paetGrGerencia;
     private PaetDvDivision paetDvDivision;
     private PaetDrDireccion paetDrDireccion;
     private PaetArArea paetArArea;
     private String ptNombre;
     private String ptDescripcion;
     private char ptEstado;
     private BigDecimal drDireccion;
     private BigDecimal dvDivision;
     private BigDecimal grGerencia;
     private Set paetFcPtFactorPuestos = new HashSet(0);
     private Set paetTrTrabajadors = new HashSet(0);
     private Set paetMtPtMetaPuestos = new HashSet(0);
     private Set paetAcPtActividadPuestos = new HashSet(0);

    public PaetPtPuesto() {
    }

	
    public PaetPtPuesto(String ptCodigo, String ptNombre, char ptEstado) {
        this.ptCodigo = ptCodigo;
        this.ptNombre = ptNombre;
        this.ptEstado = ptEstado;
    }
    public PaetPtPuesto(String ptCodigo, PaetGrGerencia paetGrGerencia, PaetDvDivision paetDvDivision, PaetDrDireccion paetDrDireccion, PaetArArea paetArArea, String ptNombre, String ptDescripcion, char ptEstado, BigDecimal drDireccion, BigDecimal dvDivision, BigDecimal grGerencia, Set paetFcPtFactorPuestos, Set paetTrTrabajadors, Set paetMtPtMetaPuestos, Set paetAcPtActividadPuestos) {
       this.ptCodigo = ptCodigo;
       this.paetGrGerencia = paetGrGerencia;
       this.paetDvDivision = paetDvDivision;
       this.paetDrDireccion = paetDrDireccion;
       this.paetArArea = paetArArea;
       this.ptNombre = ptNombre;
       this.ptDescripcion = ptDescripcion;
       this.ptEstado = ptEstado;
       this.drDireccion = drDireccion;
       this.dvDivision = dvDivision;
       this.grGerencia = grGerencia;
       this.paetFcPtFactorPuestos = paetFcPtFactorPuestos;
       this.paetTrTrabajadors = paetTrTrabajadors;
       this.paetMtPtMetaPuestos = paetMtPtMetaPuestos;
       this.paetAcPtActividadPuestos = paetAcPtActividadPuestos;
    }
   
    public String getPtCodigo() {
        return this.ptCodigo;
    }
    
    public void setPtCodigo(String ptCodigo) {
        this.ptCodigo = ptCodigo;
    }
    public PaetGrGerencia getPaetGrGerencia() {
        return this.paetGrGerencia;
    }
    
    public void setPaetGrGerencia(PaetGrGerencia paetGrGerencia) {
        this.paetGrGerencia = paetGrGerencia;
    }
    public PaetDvDivision getPaetDvDivision() {
        return this.paetDvDivision;
    }
    
    public void setPaetDvDivision(PaetDvDivision paetDvDivision) {
        this.paetDvDivision = paetDvDivision;
    }
    public PaetDrDireccion getPaetDrDireccion() {
        return this.paetDrDireccion;
    }
    
    public void setPaetDrDireccion(PaetDrDireccion paetDrDireccion) {
        this.paetDrDireccion = paetDrDireccion;
    }
    public PaetArArea getPaetArArea() {
        return this.paetArArea;
    }
    
    public void setPaetArArea(PaetArArea paetArArea) {
        this.paetArArea = paetArArea;
    }
    public String getPtNombre() {
        return this.ptNombre;
    }
    
    public void setPtNombre(String ptNombre) {
        this.ptNombre = ptNombre;
    }
    public String getPtDescripcion() {
        return this.ptDescripcion;
    }
    
    public void setPtDescripcion(String ptDescripcion) {
        this.ptDescripcion = ptDescripcion;
    }
    public char getPtEstado() {
        return this.ptEstado;
    }
    
    public void setPtEstado(char ptEstado) {
        this.ptEstado = ptEstado;
    }
    public BigDecimal getDrDireccion() {
        return this.drDireccion;
    }
    
    public void setDrDireccion(BigDecimal drDireccion) {
        this.drDireccion = drDireccion;
    }
    public BigDecimal getDvDivision() {
        return this.dvDivision;
    }
    
    public void setDvDivision(BigDecimal dvDivision) {
        this.dvDivision = dvDivision;
    }
    public BigDecimal getGrGerencia() {
        return this.grGerencia;
    }
    
    public void setGrGerencia(BigDecimal grGerencia) {
        this.grGerencia = grGerencia;
    }
    public Set getPaetFcPtFactorPuestos() {
        return this.paetFcPtFactorPuestos;
    }
    
    public void setPaetFcPtFactorPuestos(Set paetFcPtFactorPuestos) {
        this.paetFcPtFactorPuestos = paetFcPtFactorPuestos;
    }
    public Set getPaetTrTrabajadors() {
        return this.paetTrTrabajadors;
    }
    
    public void setPaetTrTrabajadors(Set paetTrTrabajadors) {
        this.paetTrTrabajadors = paetTrTrabajadors;
    }
    public Set getPaetMtPtMetaPuestos() {
        return this.paetMtPtMetaPuestos;
    }
    
    public void setPaetMtPtMetaPuestos(Set paetMtPtMetaPuestos) {
        this.paetMtPtMetaPuestos = paetMtPtMetaPuestos;
    }
    public Set getPaetAcPtActividadPuestos() {
        return this.paetAcPtActividadPuestos;
    }
    
    public void setPaetAcPtActividadPuestos(Set paetAcPtActividadPuestos) {
        this.paetAcPtActividadPuestos = paetAcPtActividadPuestos;
    }




}


