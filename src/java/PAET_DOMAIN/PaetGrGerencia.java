package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetGrGerencia generated by hbm2java
 */
public class PaetGrGerencia  implements java.io.Serializable {


     private BigDecimal grCodigo;
     private String grDescripcion;
     private char grEstado;
     private Set<PaetPtPuesto> paetPtPuestos = new HashSet<PaetPtPuesto>(0);
     private PaetDvDivision paetDvDivision;

    public PaetGrGerencia() {
    }

	
    public PaetGrGerencia(BigDecimal grCodigo, String grDescripcion, char grEstado) {
        this.grCodigo = grCodigo;
        this.grDescripcion = grDescripcion;
        this.grEstado = grEstado;
    }
    public PaetGrGerencia(BigDecimal grCodigo, String grDescripcion, char grEstado, Set<PaetPtPuesto> paetPtPuestos, PaetDvDivision paetDvDivision) {
       this.grCodigo = grCodigo;
       this.grDescripcion = grDescripcion;
       this.grEstado = grEstado;
       this.paetPtPuestos = paetPtPuestos;
       this.paetDvDivision = paetDvDivision;
    }
   
    public BigDecimal getGrCodigo() {
        return this.grCodigo;
    }
    
    public void setGrCodigo(BigDecimal grCodigo) {
        this.grCodigo = grCodigo;
    }
    public String getGrDescripcion() {
        return this.grDescripcion;
    }
    
    public void setGrDescripcion(String grDescripcion) {
        this.grDescripcion = grDescripcion;
    }
    public char getGrEstado() {
        return this.grEstado;
    }
    
    public void setGrEstado(char grEstado) {
        this.grEstado = grEstado;
    }
    public Set<PaetPtPuesto> getPaetPtPuestos() {
        return this.paetPtPuestos;
    }
    
    public void setPaetPtPuestos(Set<PaetPtPuesto> paetPtPuestos) {
        this.paetPtPuestos = paetPtPuestos;
    }
    public PaetDvDivision getPaetDvDivision() {
        return this.paetDvDivision;
    }
    
    public void setPaetDvDivision(PaetDvDivision paetDvDivision) {
        this.paetDvDivision = paetDvDivision;
    }




}

