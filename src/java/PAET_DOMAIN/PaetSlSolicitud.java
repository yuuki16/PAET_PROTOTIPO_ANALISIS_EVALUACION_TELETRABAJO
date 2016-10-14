package PAET_DOMAIN;
// Generated 13-oct-2016 20:16:04 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetSlSolicitud generated by hbm2java
 */
public class PaetSlSolicitud  implements java.io.Serializable {


     private BigDecimal slCodigo;
     private PaetTrTrabajador paetTrTrabajador;
     private Date slFecha;
     private String slJustificacion;
     private Short slResultado;
     private char slEstado;
     private Set paetDiSlDiaSolicituds = new HashSet(0);

    public PaetSlSolicitud() {
    }

	
    public PaetSlSolicitud(BigDecimal slCodigo, PaetTrTrabajador paetTrTrabajador, Date slFecha, String slJustificacion, char slEstado) {
        this.slCodigo = slCodigo;
        this.paetTrTrabajador = paetTrTrabajador;
        this.slFecha = slFecha;
        this.slJustificacion = slJustificacion;
        this.slEstado = slEstado;
    }
    public PaetSlSolicitud(BigDecimal slCodigo, PaetTrTrabajador paetTrTrabajador, Date slFecha, String slJustificacion, Short slResultado, char slEstado, Set paetDiSlDiaSolicituds) {
       this.slCodigo = slCodigo;
       this.paetTrTrabajador = paetTrTrabajador;
       this.slFecha = slFecha;
       this.slJustificacion = slJustificacion;
       this.slResultado = slResultado;
       this.slEstado = slEstado;
       this.paetDiSlDiaSolicituds = paetDiSlDiaSolicituds;
    }
   
    public BigDecimal getSlCodigo() {
        return this.slCodigo;
    }
    
    public void setSlCodigo(BigDecimal slCodigo) {
        this.slCodigo = slCodigo;
    }
    public PaetTrTrabajador getPaetTrTrabajador() {
        return this.paetTrTrabajador;
    }
    
    public void setPaetTrTrabajador(PaetTrTrabajador paetTrTrabajador) {
        this.paetTrTrabajador = paetTrTrabajador;
    }
    public Date getSlFecha() {
        return this.slFecha;
    }
    
    public void setSlFecha(Date slFecha) {
        this.slFecha = slFecha;
    }
    public String getSlJustificacion() {
        return this.slJustificacion;
    }
    
    public void setSlJustificacion(String slJustificacion) {
        this.slJustificacion = slJustificacion;
    }
    public Short getSlResultado() {
        return this.slResultado;
    }
    
    public void setSlResultado(Short slResultado) {
        this.slResultado = slResultado;
    }
    public char getSlEstado() {
        return this.slEstado;
    }
    
    public void setSlEstado(char slEstado) {
        this.slEstado = slEstado;
    }
    public Set getPaetDiSlDiaSolicituds() {
        return this.paetDiSlDiaSolicituds;
    }
    
    public void setPaetDiSlDiaSolicituds(Set paetDiSlDiaSolicituds) {
        this.paetDiSlDiaSolicituds = paetDiSlDiaSolicituds;
    }




}


