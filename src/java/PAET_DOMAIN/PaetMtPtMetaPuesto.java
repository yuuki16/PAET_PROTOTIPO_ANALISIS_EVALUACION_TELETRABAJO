package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PaetMtPtMetaPuesto generated by hbm2java
 */
public class PaetMtPtMetaPuesto  implements java.io.Serializable {


     private BigDecimal mtPtCodigo;
     private PaetMtMeta paetMtMeta;
     private PaetPtPuesto paetPtPuesto;
     private char mtPtEstado;

    public PaetMtPtMetaPuesto() {
    }

    public PaetMtPtMetaPuesto(BigDecimal mtPtCodigo, PaetMtMeta paetMtMeta, PaetPtPuesto paetPtPuesto, char mtPtEstado) {
       this.mtPtCodigo = mtPtCodigo;
       this.paetMtMeta = paetMtMeta;
       this.paetPtPuesto = paetPtPuesto;
       this.mtPtEstado = mtPtEstado;
    }
   
    public BigDecimal getMtPtCodigo() {
        return this.mtPtCodigo;
    }
    
    public void setMtPtCodigo(BigDecimal mtPtCodigo) {
        this.mtPtCodigo = mtPtCodigo;
    }
    public PaetMtMeta getPaetMtMeta() {
        return this.paetMtMeta;
    }
    
    public void setPaetMtMeta(PaetMtMeta paetMtMeta) {
        this.paetMtMeta = paetMtMeta;
    }
    public PaetPtPuesto getPaetPtPuesto() {
        return this.paetPtPuesto;
    }
    
    public void setPaetPtPuesto(PaetPtPuesto paetPtPuesto) {
        this.paetPtPuesto = paetPtPuesto;
    }
    public char getMtPtEstado() {
        return this.mtPtEstado;
    }
    
    public void setMtPtEstado(char mtPtEstado) {
        this.mtPtEstado = mtPtEstado;
    }




}


