package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetDiSlDiaSolicitud generated by hbm2java
 */
public class PaetDiSlDiaSolicitud  implements java.io.Serializable {


     private BigDecimal diSlCodigo;
     private char diDia;
     private BigDecimal slSolicitud;

    public PaetDiSlDiaSolicitud() {
    }

    public PaetDiSlDiaSolicitud(BigDecimal diSlCodigo, char diDia, BigDecimal slSolicitud) {
       this.diSlCodigo = diSlCodigo;
       this.diDia = diDia;
       this.slSolicitud = slSolicitud;
    }
   
    public BigDecimal getDiSlCodigo() {
        return this.diSlCodigo;
    }
    
    public void setDiSlCodigo(BigDecimal diSlCodigo) {
        this.diSlCodigo = diSlCodigo;
    }
    
    public char getDiDia() {
        return this.diDia;
    }
    
    public void setDiDia(char diDia) {
        this.diDia = diDia;
    }
    
    public BigDecimal getSlSolicitud() {
        return this.slSolicitud;
    }
    
    public void setSlSolicitud(BigDecimal slSolicitud) {
        this.slSolicitud = slSolicitud;
    }
}


