package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetEvEvaluacion generated by hbm2java
 */
public class PaetEvEvaluacion  implements java.io.Serializable {


     private BigDecimal evCodigo;
     private Date evFecha;
     private Date evFechaDesde;
     private Date evFechaHasta;
     private Character evLogro;
     private Short evResultado;
     private BigDecimal pgProcesoSeguimiento;

    public PaetEvEvaluacion() {
    }

	
    public PaetEvEvaluacion(BigDecimal evCodigo, Date evFecha, Date evFechaDesde, Date evFechaHasta, Character evLogro, Short evResultado, BigDecimal pgProcesoSeguimiento) {
        this.evCodigo = evCodigo;
        this.evFecha = evFecha;
        this.evFechaDesde = evFechaDesde;
        this.evFechaHasta = evFechaHasta;
        this.evLogro = evLogro;
        this.evResultado = evResultado;
        this.pgProcesoSeguimiento = pgProcesoSeguimiento;
    }
   
    public BigDecimal getEvCodigo() {
        return this.evCodigo;
    }
    
    public void setEvCodigo(BigDecimal evCodigo) {
        this.evCodigo = evCodigo;
    }
    
    public Date getEvFecha() {
        return this.evFecha;
    }
    
    public void setEvFecha(Date evFecha) {
        this.evFecha = evFecha;
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
    public Character getEvLogro() {
        return this.evLogro;
    }
    
    public void setEvLogro(Character evLogro) {
        this.evLogro = evLogro;
    }
    public Short getEvResultado() {
        return this.evResultado;
    }
    
    public void setEvResultado(Short evResultado) {
        this.evResultado = evResultado;
    }
    
    public BigDecimal getPgProcesoSeguimiento() {
        return this.pgProcesoSeguimiento;
    }
    
    public void setPgProcesoSeguimiento(BigDecimal pgProcesoSeguimiento) {
        this.pgProcesoSeguimiento = pgProcesoSeguimiento;
    }
}


