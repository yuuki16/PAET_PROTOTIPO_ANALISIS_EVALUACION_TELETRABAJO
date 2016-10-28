/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetPtPuesto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_PT_PUESTO_BL extends BaseBL implements IBaseBL<PaetPtPuesto, String> {

    public PAET_PT_PUESTO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetPtPuesto o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetPtPuesto merge(PaetPtPuesto o) {
        return (PaetPtPuesto) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetPtPuesto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPtPuesto findById(String o) {
        return (PaetPtPuesto) this.getDao(PaetPtPuesto.class.getName()).findById(o);
    }

    @Override
    public List<PaetPtPuesto> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetPtPuesto> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
