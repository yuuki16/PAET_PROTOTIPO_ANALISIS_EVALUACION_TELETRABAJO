/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetFcFactorComplementario;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_FC_FACTOR_COMPLEMENTARIO_BL extends BaseBL implements IBaseBL<PaetFcFactorComplementario, BigDecimal>{

    public PAET_FC_FACTOR_COMPLEMENTARIO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetFcFactorComplementario o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetFcFactorComplementario merge(PaetFcFactorComplementario o) {
        return (PaetFcFactorComplementario) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetFcFactorComplementario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetFcFactorComplementario findById(BigDecimal o) {
        return (PaetFcFactorComplementario) this.getDao(PaetFcFactorComplementario.class.getName()).findById(o);
    }

    @Override
    public List<PaetFcFactorComplementario> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetFcFactorComplementario> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
