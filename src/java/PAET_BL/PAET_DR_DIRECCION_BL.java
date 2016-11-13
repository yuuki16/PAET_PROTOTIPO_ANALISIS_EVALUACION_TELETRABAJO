/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetDrDireccion;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DR_DIRECCION_BL extends BaseBL implements IBaseBL<PaetDrDireccion, BigDecimal>  {

    public PAET_DR_DIRECCION_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetDrDireccion o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetDrDireccion merge(PaetDrDireccion o) {
        return (PaetDrDireccion) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetDrDireccion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDrDireccion findById(BigDecimal o) {
        return (PaetDrDireccion) this.getDao(PaetDrDireccion.class.getName()).findById(o);
    }

    @Override
    public List<PaetDrDireccion> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetDrDireccion> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetDrDireccion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
