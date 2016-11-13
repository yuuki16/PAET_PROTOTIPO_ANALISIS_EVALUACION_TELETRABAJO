/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetTlTelefono;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_TL_TELEFONO_BL extends BaseBL implements IBaseBL<PaetTlTelefono, BigDecimal> {

    public PAET_TL_TELEFONO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetTlTelefono o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetTlTelefono merge(PaetTlTelefono o) {
        return (PaetTlTelefono) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetTlTelefono o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTlTelefono findById(BigDecimal o) {
        return (PaetTlTelefono) this.getDao(PaetTlTelefono.class.getName()).findById(o);
    }

    @Override
    public List<PaetTlTelefono> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetTlTelefono> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetTlTelefono o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
