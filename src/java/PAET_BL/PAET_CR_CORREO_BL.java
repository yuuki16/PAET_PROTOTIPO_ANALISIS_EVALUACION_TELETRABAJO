/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetCrCorreo;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_CR_CORREO_BL extends BaseBL implements IBaseBL<PaetCrCorreo, BigDecimal> {

    public PAET_CR_CORREO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetCrCorreo o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetCrCorreo merge(PaetCrCorreo o) {
        return (PaetCrCorreo) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetCrCorreo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCrCorreo findById(BigDecimal o) {
        return (PaetCrCorreo) this.getDao(PaetCrCorreo.class.getName()).findById(o);
    }

    @Override
    public List<PaetCrCorreo> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetCrCorreo> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
