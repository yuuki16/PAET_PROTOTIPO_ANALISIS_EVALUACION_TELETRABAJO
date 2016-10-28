/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetTrTrabajador;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_TR_TRABAJADOR_BL extends BaseBL implements IBaseBL<PaetTrTrabajador, String> {

    public PAET_TR_TRABAJADOR_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetTrTrabajador o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetTrTrabajador merge(PaetTrTrabajador o) {
        return (PaetTrTrabajador) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetTrTrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTrTrabajador findById(String o) {
        return (PaetTrTrabajador) this.getDao(PaetTrTrabajador.class.getName()).findById(o);
    }

    @Override
    public List<PaetTrTrabajador> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetTrTrabajador> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
