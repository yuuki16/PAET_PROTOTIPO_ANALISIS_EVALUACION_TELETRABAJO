package PAET_DOMAIN;
// Generated 17-oct-2016 18:54:49 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetDiTtDiaTeletrabajador generated by hbm2java
 */
public class PaetDiTtDiaTeletrabajador  implements java.io.Serializable {


     private BigDecimal diTtCodigo;
     private PaetDiDia paetDiDia;
     private PaetTtTeletrabajador paetTtTeletrabajador;
     private char diTtEstado;

    public PaetDiTtDiaTeletrabajador() {
    }

    public PaetDiTtDiaTeletrabajador(BigDecimal diTtCodigo, PaetDiDia paetDiDia, PaetTtTeletrabajador paetTtTeletrabajador, char diTtEstado) {
       this.diTtCodigo = diTtCodigo;
       this.paetDiDia = paetDiDia;
       this.paetTtTeletrabajador = paetTtTeletrabajador;
       this.diTtEstado = diTtEstado;
    }
   
    public BigDecimal getDiTtCodigo() {
        return this.diTtCodigo;
    }
    
    public void setDiTtCodigo(BigDecimal diTtCodigo) {
        this.diTtCodigo = diTtCodigo;
    }
    public PaetDiDia getPaetDiDia() {
        return this.paetDiDia;
    }
    
    public void setPaetDiDia(PaetDiDia paetDiDia) {
        this.paetDiDia = paetDiDia;
    }
    public PaetTtTeletrabajador getPaetTtTeletrabajador() {
        return this.paetTtTeletrabajador;
    }
    
    public void setPaetTtTeletrabajador(PaetTtTeletrabajador paetTtTeletrabajador) {
        this.paetTtTeletrabajador = paetTtTeletrabajador;
    }
    public char getDiTtEstado() {
        return this.diTtEstado;
    }
    
    public void setDiTtEstado(char diTtEstado) {
        this.diTtEstado = diTtEstado;
    }




}


