package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetAcPtActividadPuesto generated by hbm2java
 */
public class PaetAcPtActividadPuesto  implements java.io.Serializable {


     private BigDecimal acPtCodigo;
     private BigDecimal acActividad;
     private String ptPuesto;

    public PaetAcPtActividadPuesto() {
    }

    public PaetAcPtActividadPuesto(BigDecimal acPtCodigo, BigDecimal acActividad, String ptPuesto) {
       this.acPtCodigo = acPtCodigo;
       this.acActividad = acActividad;
       this.ptPuesto = ptPuesto;       
    }
   
    public BigDecimal getAcPtCodigo() {
        return this.acPtCodigo;
    }
    
    public void setAcPtCodigo(BigDecimal acPtCodigo) {
        this.acPtCodigo = acPtCodigo;
    }
    
    public BigDecimal getAcActividad() {
        return this.acActividad;
    }
    
    public void setAcActividad(BigDecimal acActividad) {
        this.acActividad = acActividad;
    }
    
    public String getPtPuesto() {
        return this.ptPuesto;
    }
    
    public void setPtPuesto(String ptPuesto) {
        this.ptPuesto = ptPuesto;
    }
}


