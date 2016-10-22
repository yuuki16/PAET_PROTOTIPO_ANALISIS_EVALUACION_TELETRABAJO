package PAET_DOMAIN;
// Generated 19-oct-2016 22:10:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PaetPrProvincia generated by hbm2java
 */
public class PaetPrProvincia  implements java.io.Serializable {


     private boolean prCodigo;
     private String prDescripcion;
     private Set<PaetCnCanton> paetCnCantons = new HashSet<PaetCnCanton>(0);

    public PaetPrProvincia() {
    }

	
    public PaetPrProvincia(boolean prCodigo, String prDescripcion) {
        this.prCodigo = prCodigo;
        this.prDescripcion = prDescripcion;
    }
    public PaetPrProvincia(boolean prCodigo, String prDescripcion, Set<PaetCnCanton> paetCnCantons) {
       this.prCodigo = prCodigo;
       this.prDescripcion = prDescripcion;
       this.paetCnCantons = paetCnCantons;
    }
   
    public boolean isPrCodigo() {
        return this.prCodigo;
    }
    
    public void setPrCodigo(boolean prCodigo) {
        this.prCodigo = prCodigo;
    }
    public String getPrDescripcion() {
        return this.prDescripcion;
    }
    
    public void setPrDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
    }
    public Set<PaetCnCanton> getPaetCnCantons() {
        return this.paetCnCantons;
    }
    
    public void setPaetCnCantons(Set<PaetCnCanton> paetCnCantons) {
        this.paetCnCantons = paetCnCantons;
    }




}


