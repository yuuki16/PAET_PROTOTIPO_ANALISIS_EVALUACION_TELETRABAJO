/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetCnCanton;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_CN_CANTON_BL extends BaseBL implements IBaseBL<PaetCnCanton, java.lang.Short>{

    @Override
    public void save(PaetCnCanton o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCnCanton merge(PaetCnCanton o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetCnCanton o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCnCanton findById(Short o) {
        return (PaetCnCanton) this.getDao(PaetCnCanton.class.getName()).findById(o);
    }

    @Override
    public List<PaetCnCanton> findAll(String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetCnCanton> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public Short saveWithReturn(PaetCnCanton o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
