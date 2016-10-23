/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetGrGerencia;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_GR_GERENCIA_BL extends BaseBL implements IBaseBL<PaetGrGerencia, BigDecimal>{

    public PAET_GR_GERENCIA_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetGrGerencia o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetGrGerencia merge(PaetGrGerencia o) {
        return (PaetGrGerencia) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetGrGerencia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetGrGerencia findById(BigDecimal o) {
        return (PaetGrGerencia) this.getDao(PaetGrGerencia.class.getName()).findById(o);
    }

    @Override
    public List<PaetGrGerencia> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetGrGerencia> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
