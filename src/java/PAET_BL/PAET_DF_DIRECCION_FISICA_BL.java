/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetDfDireccionFisica;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DF_DIRECCION_FISICA_BL extends BaseBL implements IBaseBL<PaetDfDireccionFisica, BigDecimal> {

    public PAET_DF_DIRECCION_FISICA_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetDfDireccionFisica o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetDfDireccionFisica merge(PaetDfDireccionFisica o) {
        return (PaetDfDireccionFisica) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetDfDireccionFisica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDfDireccionFisica findById(BigDecimal o) {
        return (PaetDfDireccionFisica) this.getDao(PaetDfDireccionFisica.class.getName()).findById(o);
    }

    @Override
    public List<PaetDfDireccionFisica> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetDfDireccionFisica> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
