package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetFcAnFactorAnalisis generated by hbm2java
 */
public class PaetFcAnFactorAnalisis  implements java.io.Serializable {


     private BigDecimal fcAnCodigo;
     private Short fcAnGrado;
     private BigDecimal fcFactor;
     private BigDecimal anAnalisis;

    public PaetFcAnFactorAnalisis() {
    }

    public PaetFcAnFactorAnalisis(BigDecimal fcAnCodigo, Short fcAnGrado, BigDecimal fcFactor, BigDecimal anAnalisis) {
       this.fcAnCodigo = fcAnCodigo;
       this.fcAnGrado = fcAnGrado;
       this.fcFactor = fcFactor;
       this.anAnalisis = anAnalisis;
    }
   
    public BigDecimal getFcAnCodigo() {
        return this.fcAnCodigo;
    }
    
    public void setFcAnCodigo(BigDecimal fcAnCodigo) {
        this.fcAnCodigo = fcAnCodigo;
    }
    
    public Short isFcAnGrado() {
        return this.fcAnGrado;
    }
    
    public void setFcAnGrado(Short fcAnGrado) {
        this.fcAnGrado = fcAnGrado;
    }

    public BigDecimal getFcFactor() {
        return this.fcFactor;
    }
    
    public void setFcFactor(BigDecimal fcFactor) {
        this.fcFactor = fcFactor;
    }

    public BigDecimal getAnAnalisis() {
        return this.anAnalisis;
    }
    
    public void setAnAnalisis(BigDecimal anAnalisis) {
        this.anAnalisis = anAnalisis;
    }
}


