package PAET_DOMAIN;
// Generated 13-oct-2016 20:16:04 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetAcPtActividadPuesto generated by hbm2java
 */
public class PaetAcPtActividadPuesto  implements java.io.Serializable {


     private BigDecimal acPtCodigo;
     private PaetAcActividad paetAcActividad;
     private PaetPtPuesto paetPtPuesto;
     private char acPtEstado;

    public PaetAcPtActividadPuesto() {
    }

    public PaetAcPtActividadPuesto(BigDecimal acPtCodigo, PaetAcActividad paetAcActividad, PaetPtPuesto paetPtPuesto, char acPtEstado) {
       this.acPtCodigo = acPtCodigo;
       this.paetAcActividad = paetAcActividad;
       this.paetPtPuesto = paetPtPuesto;
       this.acPtEstado = acPtEstado;
    }
   
    public BigDecimal getAcPtCodigo() {
        return this.acPtCodigo;
    }
    
    public void setAcPtCodigo(BigDecimal acPtCodigo) {
        this.acPtCodigo = acPtCodigo;
    }
    public PaetAcActividad getPaetAcActividad() {
        return this.paetAcActividad;
    }
    
    public void setPaetAcActividad(PaetAcActividad paetAcActividad) {
        this.paetAcActividad = paetAcActividad;
    }
    public PaetPtPuesto getPaetPtPuesto() {
        return this.paetPtPuesto;
    }
    
    public void setPaetPtPuesto(PaetPtPuesto paetPtPuesto) {
        this.paetPtPuesto = paetPtPuesto;
    }
    public char getAcPtEstado() {
        return this.acPtEstado;
    }
    
    public void setAcPtEstado(char acPtEstado) {
        this.acPtEstado = acPtEstado;
    }




}


