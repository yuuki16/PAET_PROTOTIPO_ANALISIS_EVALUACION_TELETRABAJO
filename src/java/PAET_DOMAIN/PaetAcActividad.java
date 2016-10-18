package PAET_DOMAIN;
// Generated 17-oct-2016 18:54:49 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetAcActividad generated by hbm2java
 */
public class PaetAcActividad  implements java.io.Serializable {


     private BigDecimal acCodigo;
     private String acDescripcion;
     private char acAlineamiento;
     private Set paetAcPtActividadPuestos = new HashSet(0);
     private Set paetAcCaActCaracts = new HashSet(0);

    public PaetAcActividad() {
    }

	
    public PaetAcActividad(BigDecimal acCodigo, String acDescripcion, char acAlineamiento) {
        this.acCodigo = acCodigo;
        this.acDescripcion = acDescripcion;
        this.acAlineamiento = acAlineamiento;
    }
    public PaetAcActividad(BigDecimal acCodigo, String acDescripcion, char acAlineamiento, Set paetAcPtActividadPuestos, Set paetAcCaActCaracts) {
       this.acCodigo = acCodigo;
       this.acDescripcion = acDescripcion;
       this.acAlineamiento = acAlineamiento;
       this.paetAcPtActividadPuestos = paetAcPtActividadPuestos;
       this.paetAcCaActCaracts = paetAcCaActCaracts;
    }
   
    public BigDecimal getAcCodigo() {
        return this.acCodigo;
    }
    
    public void setAcCodigo(BigDecimal acCodigo) {
        this.acCodigo = acCodigo;
    }
    public String getAcDescripcion() {
        return this.acDescripcion;
    }
    
    public void setAcDescripcion(String acDescripcion) {
        this.acDescripcion = acDescripcion;
    }
    public char getAcAlineamiento() {
        return this.acAlineamiento;
    }
    
    public void setAcAlineamiento(char acAlineamiento) {
        this.acAlineamiento = acAlineamiento;
    }
    public Set getPaetAcPtActividadPuestos() {
        return this.paetAcPtActividadPuestos;
    }
    
    public void setPaetAcPtActividadPuestos(Set paetAcPtActividadPuestos) {
        this.paetAcPtActividadPuestos = paetAcPtActividadPuestos;
    }
    public Set getPaetAcCaActCaracts() {
        return this.paetAcCaActCaracts;
    }
    
    public void setPaetAcCaActCaracts(Set paetAcCaActCaracts) {
        this.paetAcCaActCaracts = paetAcCaActCaracts;
    }




}


