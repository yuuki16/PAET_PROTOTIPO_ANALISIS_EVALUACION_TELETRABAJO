package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PaetDsDistrito generated by hbm2java
 */
public class PaetDsDistrito  implements java.io.Serializable {


     private int dsCodigo;
     private PaetCnCanton paetCnCanton;
     private String dsDescripcion;
     private Set<PaetDfDireccionFisica> paetDfDireccionFisicas = new HashSet<PaetDfDireccionFisica>(0);

    public PaetDsDistrito() {
    }

	
    public PaetDsDistrito(int dsCodigo, PaetCnCanton paetCnCanton, String dsDescripcion) {
        this.dsCodigo = dsCodigo;
        this.paetCnCanton = paetCnCanton;
        this.dsDescripcion = dsDescripcion;
    }
    public PaetDsDistrito(int dsCodigo, PaetCnCanton paetCnCanton, String dsDescripcion, Set<PaetDfDireccionFisica> paetDfDireccionFisicas) {
       this.dsCodigo = dsCodigo;
       this.paetCnCanton = paetCnCanton;
       this.dsDescripcion = dsDescripcion;
       this.paetDfDireccionFisicas = paetDfDireccionFisicas;
    }
   
    public int getDsCodigo() {
        return this.dsCodigo;
    }
    
    public void setDsCodigo(int dsCodigo) {
        this.dsCodigo = dsCodigo;
    }
    public PaetCnCanton getPaetCnCanton() {
        return this.paetCnCanton;
    }
    
    public void setPaetCnCanton(PaetCnCanton paetCnCanton) {
        this.paetCnCanton = paetCnCanton;
    }
    public String getDsDescripcion() {
        return this.dsDescripcion;
    }
    
    public void setDsDescripcion(String dsDescripcion) {
        this.dsDescripcion = dsDescripcion;
    }
    public Set<PaetDfDireccionFisica> getPaetDfDireccionFisicas() {
        return this.paetDfDireccionFisicas;
    }
    
    public void setPaetDfDireccionFisicas(Set<PaetDfDireccionFisica> paetDfDireccionFisicas) {
        this.paetDfDireccionFisicas = paetDfDireccionFisicas;
    }




}

