package PAET_DOMAIN;
// Generated 13-oct-2016 20:16:04 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PaetTlTeletrabajador generated by hbm2java
 */
public class PaetTlTeletrabajador  implements java.io.Serializable {


     private String trTrabajador;
     private PaetTrTrabajador paetTrTrabajador;
     private Date tlFechaInicio;
     private char tlEstado;
     private Set paetEvEvaluacions = new HashSet(0);

    public PaetTlTeletrabajador() {
    }

	
    public PaetTlTeletrabajador(PaetTrTrabajador paetTrTrabajador, Date tlFechaInicio, char tlEstado) {
        this.paetTrTrabajador = paetTrTrabajador;
        this.tlFechaInicio = tlFechaInicio;
        this.tlEstado = tlEstado;
    }
    public PaetTlTeletrabajador(PaetTrTrabajador paetTrTrabajador, Date tlFechaInicio, char tlEstado, Set paetEvEvaluacions) {
       this.paetTrTrabajador = paetTrTrabajador;
       this.tlFechaInicio = tlFechaInicio;
       this.tlEstado = tlEstado;
       this.paetEvEvaluacions = paetEvEvaluacions;
    }
   
    public String getTrTrabajador() {
        return this.trTrabajador;
    }
    
    public void setTrTrabajador(String trTrabajador) {
        this.trTrabajador = trTrabajador;
    }
    public PaetTrTrabajador getPaetTrTrabajador() {
        return this.paetTrTrabajador;
    }
    
    public void setPaetTrTrabajador(PaetTrTrabajador paetTrTrabajador) {
        this.paetTrTrabajador = paetTrTrabajador;
    }
    public Date getTlFechaInicio() {
        return this.tlFechaInicio;
    }
    
    public void setTlFechaInicio(Date tlFechaInicio) {
        this.tlFechaInicio = tlFechaInicio;
    }
    public char getTlEstado() {
        return this.tlEstado;
    }
    
    public void setTlEstado(char tlEstado) {
        this.tlEstado = tlEstado;
    }
    public Set getPaetEvEvaluacions() {
        return this.paetEvEvaluacions;
    }
    
    public void setPaetEvEvaluacions(Set paetEvEvaluacions) {
        this.paetEvEvaluacions = paetEvEvaluacions;
    }




}


