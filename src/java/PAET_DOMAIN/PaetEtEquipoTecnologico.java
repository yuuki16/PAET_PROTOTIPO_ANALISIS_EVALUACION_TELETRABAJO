package PAET_DOMAIN;
// Generated 17-oct-2016 18:54:49 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetEtEquipoTecnologico generated by hbm2java
 */
public class PaetEtEquipoTecnologico  implements java.io.Serializable {


     private BigDecimal etCodigo;
     private String etDescripcion;
     private char etEstado;
     private Set paetEtSlEquipoSolicituds = new HashSet(0);

    public PaetEtEquipoTecnologico() {
    }

	
    public PaetEtEquipoTecnologico(BigDecimal etCodigo, String etDescripcion, char etEstado) {
        this.etCodigo = etCodigo;
        this.etDescripcion = etDescripcion;
        this.etEstado = etEstado;
    }
    public PaetEtEquipoTecnologico(BigDecimal etCodigo, String etDescripcion, char etEstado, Set paetEtSlEquipoSolicituds) {
       this.etCodigo = etCodigo;
       this.etDescripcion = etDescripcion;
       this.etEstado = etEstado;
       this.paetEtSlEquipoSolicituds = paetEtSlEquipoSolicituds;
    }
   
    public BigDecimal getEtCodigo() {
        return this.etCodigo;
    }
    
    public void setEtCodigo(BigDecimal etCodigo) {
        this.etCodigo = etCodigo;
    }
    public String getEtDescripcion() {
        return this.etDescripcion;
    }
    
    public void setEtDescripcion(String etDescripcion) {
        this.etDescripcion = etDescripcion;
    }
    public char getEtEstado() {
        return this.etEstado;
    }
    
    public void setEtEstado(char etEstado) {
        this.etEstado = etEstado;
    }
    public Set getPaetEtSlEquipoSolicituds() {
        return this.paetEtSlEquipoSolicituds;
    }
    
    public void setPaetEtSlEquipoSolicituds(Set paetEtSlEquipoSolicituds) {
        this.paetEtSlEquipoSolicituds = paetEtSlEquipoSolicituds;
    }




}


