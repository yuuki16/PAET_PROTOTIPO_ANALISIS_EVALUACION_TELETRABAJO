/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetArArea;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_AR_AREA_BL extends BaseBL implements IBaseBL<PaetArArea, BigDecimal>{

    public PAET_AR_AREA_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetArArea o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetArArea merge(PaetArArea o) {
        return (PaetArArea) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetArArea o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetArArea findById(BigDecimal o) {
        return (PaetArArea) this.getDao(PaetArArea.class.getName()).findById(o);
    }

    @Override
    public List<PaetArArea> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetArArea> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
