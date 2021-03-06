package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetPgProcesoSeguimiento generated by hbm2java
 */
public class PaetPgProcesoSeguimiento  implements java.io.Serializable {


     private BigDecimal pgCodigo;
     private Date pgFecha;
     private BigDecimal pgNumero;
     private String pgObservacion;
     private BigDecimal ttTeletrabajador;
     private BigDecimal esEstado;
     private Date pgFechaAtendido;
     private char pgEstado;

    public PaetPgProcesoSeguimiento() {
    }

	
    public PaetPgProcesoSeguimiento(BigDecimal pgCodigo, Date pgFecha, BigDecimal pgNumero, String pgObservacion, BigDecimal ttTeletrabajador, BigDecimal esEstado, Date pgFechaAtendido, char pgEstado) {
        this.pgCodigo = pgCodigo;
        this.pgFecha = pgFecha;
        this.pgNumero = pgNumero;
        this.pgObservacion = pgObservacion;
        this.ttTeletrabajador = ttTeletrabajador;
        this.esEstado = esEstado;
        this.pgFechaAtendido = pgFechaAtendido;
        this.pgEstado = pgEstado;
    }
   
    public BigDecimal getPgCodigo() {
        return this.pgCodigo;
    }
    
    public void setPgCodigo(BigDecimal pgCodigo) {
        this.pgCodigo = pgCodigo;
    }
    
    public Date getPgFecha() {
        return this.pgFecha;
    }
    
    public void setPgFecha(Date pgFecha) {
        this.pgFecha = pgFecha;
    }
    public BigDecimal getPgNumero() {
        return this.pgNumero;
    }
    
    public void setPgNumero(BigDecimal pgNumero) {
        this.pgNumero = pgNumero;
    }
    public String getPgObservacion() {
        return this.pgObservacion;
    }
    
    public void setPgObservacion(String pgObservacion) {
        this.pgObservacion = pgObservacion;
    }
    
    public BigDecimal getTtTeletrabajador() {
        return this.ttTeletrabajador;
    }
    
    public void setTtTeletrabajador(BigDecimal ttTeletrabajador) {
        this.ttTeletrabajador = ttTeletrabajador;
    }
    
    public BigDecimal getEsEstado() {
        return this.esEstado;
    }
    
    public void setEsEstado(BigDecimal esEstado) {
        this.esEstado = esEstado;
    }
    
    public Date getPgFechaAtendido() {
        return this.pgFechaAtendido;
    }
    
    public void setPgFechaAtendido(Date pgFechaAtendido) {
        this.pgFechaAtendido = pgFechaAtendido;
    }
    public char getPgEstado() {
        return this.pgEstado;
    }
    
    public void setPgEstado(char pgEstado) {
        this.pgEstado = pgEstado;
    }
}


