package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetAmAccionMejora generated by hbm2java
 */
public class PaetAmAccionMejora  implements java.io.Serializable {


     private BigDecimal amCodigo;
     private PaetEvEvaluacion paetEvEvaluacion;
     private String amDescripcion;

    public PaetAmAccionMejora() {
    }

    public PaetAmAccionMejora(BigDecimal amCodigo, PaetEvEvaluacion paetEvEvaluacion, String amDescripcion) {
       this.amCodigo = amCodigo;
       this.paetEvEvaluacion = paetEvEvaluacion;
       this.amDescripcion = amDescripcion;
    }
   
    public BigDecimal getAmCodigo() {
        return this.amCodigo;
    }
    
    public void setAmCodigo(BigDecimal amCodigo) {
        this.amCodigo = amCodigo;
    }
    public PaetEvEvaluacion getPaetEvEvaluacion() {
        return this.paetEvEvaluacion;
    }
    
    public void setPaetEvEvaluacion(PaetEvEvaluacion paetEvEvaluacion) {
        this.paetEvEvaluacion = paetEvEvaluacion;
    }
    public String getAmDescripcion() {
        return this.amDescripcion;
    }
    
    public void setAmDescripcion(String amDescripcion) {
        this.amDescripcion = amDescripcion;
    }




}


