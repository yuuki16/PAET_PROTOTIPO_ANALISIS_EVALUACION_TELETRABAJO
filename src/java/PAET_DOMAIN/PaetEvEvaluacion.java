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
     private PaetPgProcesoSeguimiento paetPgProcesoSeguimiento;
     private Date evFecha;
     private Date evFechaDesde;
     private Date evFechaHasta;
     private Character evLogro;
     private Short evResultado;
     private Set<PaetCuCausa> paetCuCausas = new HashSet<PaetCuCausa>(0);
     private Set<PaetMtMeta> paetMtMetas = new HashSet<PaetMtMeta>(0);
     private Set<PaetAmAccionMejora> paetAmAccionMejoras = new HashSet<PaetAmAccionMejora>(0);

    public PaetEvEvaluacion() {
    }

	
    public PaetEvEvaluacion(BigDecimal evCodigo, PaetPgProcesoSeguimiento paetPgProcesoSeguimiento, Date evFecha, Date evFechaDesde, Date evFechaHasta) {
        this.evCodigo = evCodigo;
        this.paetPgProcesoSeguimiento = paetPgProcesoSeguimiento;
        this.evFecha = evFecha;
        this.evFechaDesde = evFechaDesde;
        this.evFechaHasta = evFechaHasta;
    }
    public PaetEvEvaluacion(BigDecimal evCodigo, PaetPgProcesoSeguimiento paetPgProcesoSeguimiento, Date evFecha, Date evFechaDesde, Date evFechaHasta, Character evLogro, Short evResultado, Set<PaetCuCausa> paetCuCausas, Set<PaetMtMeta> paetMtMetas, Set<PaetAmAccionMejora> paetAmAccionMejoras) {
       this.evCodigo = evCodigo;
       this.paetPgProcesoSeguimiento = paetPgProcesoSeguimiento;
       this.evFecha = evFecha;
       this.evFechaDesde = evFechaDesde;
       this.evFechaHasta = evFechaHasta;
       this.evLogro = evLogro;
       this.evResultado = evResultado;
       this.paetCuCausas = paetCuCausas;
       this.paetMtMetas = paetMtMetas;
       this.paetAmAccionMejoras = paetAmAccionMejoras;
    }
   
    public BigDecimal getEvCodigo() {
        return this.evCodigo;
    }
    
    public void setEvCodigo(BigDecimal evCodigo) {
        this.evCodigo = evCodigo;
    }
    public PaetPgProcesoSeguimiento getPaetPgProcesoSeguimiento() {
        return this.paetPgProcesoSeguimiento;
    }
    
    public void setPaetPgProcesoSeguimiento(PaetPgProcesoSeguimiento paetPgProcesoSeguimiento) {
        this.paetPgProcesoSeguimiento = paetPgProcesoSeguimiento;
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
    public Set<PaetCuCausa> getPaetCuCausas() {
        return this.paetCuCausas;
    }
    
    public void setPaetCuCausas(Set<PaetCuCausa> paetCuCausas) {
        this.paetCuCausas = paetCuCausas;
    }
    public Set<PaetMtMeta> getPaetMtMetas() {
        return this.paetMtMetas;
    }
    
    public void setPaetMtMetas(Set<PaetMtMeta> paetMtMetas) {
        this.paetMtMetas = paetMtMetas;
    }
    public Set<PaetAmAccionMejora> getPaetAmAccionMejoras() {
        return this.paetAmAccionMejoras;
    }
    
    public void setPaetAmAccionMejoras(Set<PaetAmAccionMejora> paetAmAccionMejoras) {
        this.paetAmAccionMejoras = paetAmAccionMejoras;
    }




}

