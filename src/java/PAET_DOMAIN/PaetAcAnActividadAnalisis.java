package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetAcAnActividadAnalisis generated by hbm2java
 */
public class PaetAcAnActividadAnalisis  implements java.io.Serializable {


     private BigDecimal acAnCodigo;
     private BigDecimal acActividad;
     private BigDecimal anAnalisis;

    public PaetAcAnActividadAnalisis() {
    }

    public PaetAcAnActividadAnalisis(BigDecimal acAnCodigo, BigDecimal acActividad, BigDecimal anAnalisis) {
       this.acAnCodigo = acAnCodigo;
       this.acActividad = acActividad;
       this.anAnalisis = anAnalisis;       
    }
   
    public BigDecimal getAcAnCodigo() {
        return this.acAnCodigo;
    }
    
    public void setAcAnCodigo(BigDecimal acAnCodigo) {
        this.acAnCodigo = acAnCodigo;
    }
    
    public BigDecimal getAcActividad() {
        return this.acActividad;
    }
    
    public void setAcActividad(BigDecimal acActividad) {
        this.acActividad = acActividad;
    }
    
    public BigDecimal getAnAnalisis() {
        return this.anAnalisis;
    }
    
    public void setAnAnalisis(BigDecimal anAnalisis) {
        this.anAnalisis = anAnalisis;
    }
}


