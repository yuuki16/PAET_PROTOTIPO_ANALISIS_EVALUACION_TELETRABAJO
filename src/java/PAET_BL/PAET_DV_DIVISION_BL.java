/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetDvDivision;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DV_DIVISION_BL extends BaseBL implements IBaseBL<PaetDvDivision, BigDecimal> {

    public PAET_DV_DIVISION_BL() {
        super();
    }

    @Override
    public void save(PaetDvDivision o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetDvDivision merge(PaetDvDivision o) {
        return (PaetDvDivision) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetDvDivision o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDvDivision findById(BigDecimal o) {
        return (PaetDvDivision) this.getDao(PaetDvDivision.class.getName()).findById(o);
    }

    @Override
    public List<PaetDvDivision> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetDvDivision> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

}
