package PAET_DOMAIN;
// Generated 13-oct-2016 20:16:04 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * PaetEvEvaluacion generated by hbm2java
 */
public class PaetEvEvaluacion  implements java.io.Serializable {


     private BigDecimal evCodigo;
     private PaetTlTeletrabajador paetTlTeletrabajador;
     private Date evFechaDesde;
     private Date evFechaHasta;
     private char evResultado;

    public PaetEvEvaluacion() {
    }

    public PaetEvEvaluacion(BigDecimal evCodigo, PaetTlTeletrabajador paetTlTeletrabajador, Date evFechaDesde, Date evFechaHasta, char evResultado) {
       this.evCodigo = evCodigo;
       this.paetTlTeletrabajador = paetTlTeletrabajador;
       this.evFechaDesde = evFechaDesde;
       this.evFechaHasta = evFechaHasta;
       this.evResultado = evResultado;
    }
   
    public BigDecimal getEvCodigo() {
        return this.evCodigo;
    }
    
    public void setEvCodigo(BigDecimal evCodigo) {
        this.evCodigo = evCodigo;
    }
    public PaetTlTeletrabajador getPaetTlTeletrabajador() {
        return this.paetTlTeletrabajador;
    }
    
    public void setPaetTlTeletrabajador(PaetTlTeletrabajador paetTlTeletrabajador) {
        this.paetTlTeletrabajador = paetTlTeletrabajador;
    }
    public Date getEvFechaDesde() {
        return this.evFechaDesde;
    }
    
    public void setEvFechaDesde(Date evFechaDesde) {
        this.evFechaDesde = evFechaDesde;
    }
    public Date getEvFechaHasta() {
        return this.evFechaHasta;
    }
    
    public void setEvFechaHasta(Date evFechaHasta) {
        this.evFechaHasta = evFechaHasta;
    }
    public char getEvResultado() {
        return this.evResultado;
    }
    
    public void setEvResultado(char evResultado) {
        this.evResultado = evResultado;
    }




}


