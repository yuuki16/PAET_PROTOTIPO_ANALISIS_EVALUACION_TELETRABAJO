/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetDsDistrito;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DS_DISTRITO_BL extends BaseBL implements IBaseBL<PaetDsDistrito, Integer>{

    @Override
    public void save(PaetDsDistrito o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDsDistrito merge(PaetDsDistrito o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetDsDistrito o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDsDistrito findById(Integer o) {
        return (PaetDsDistrito) this.getDao(PaetDsDistrito.class.getName()).findById(o);
    }

    @Override
    public List<PaetDsDistrito> findAll(String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDsDistrito> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }
    
}
