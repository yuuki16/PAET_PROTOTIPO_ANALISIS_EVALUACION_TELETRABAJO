package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetDrDireccion generated by hbm2java
 */
public class PaetDrDireccion  implements java.io.Serializable {

     private BigDecimal drCodigo;
     private String drDescripcion;
     private char drEstado;
     private BigDecimal dvDivision;
     private String trDirector;

    public PaetDrDireccion() {
    }

    public PaetDrDireccion(BigDecimal drCodigo, String drDescripcion, char drEstado, BigDecimal dvDivision, String trDirector) {
        this.drCodigo = drCodigo;
        this.drDescripcion = drDescripcion;
        this.drEstado = drEstado;
        this.dvDivision = dvDivision;
        this.trDirector = trDirector;
    }
   
    public BigDecimal getDrCodigo() {
        return this.drCodigo;
    }
    
    public void setDrCodigo(BigDecimal drCodigo) {
        this.drCodigo = drCodigo;
    }
    
    public String getDrDescripcion() {
        return this.drDescripcion;
    }
    
    public void setDrDescripcion(String drDescripcion) {
        this.drDescripcion = drDescripcion;
    }
    public char getDrEstado() {
        return this.drEstado;
    }
    
    public void setDrEstado(char drEstado) {
        this.drEstado = drEstado;
    }
    public BigDecimal getDvDivision() {
        return this.dvDivision;
    }
    
    public void setDvDivision(BigDecimal dvDivision) {
        this.dvDivision = dvDivision;
    }
    
    public String getTrDirector() {
        return this.trDirector;
    }
    
    public void setTrDirector(String trDirector) {
        this.trDirector = trDirector;
    }
}


