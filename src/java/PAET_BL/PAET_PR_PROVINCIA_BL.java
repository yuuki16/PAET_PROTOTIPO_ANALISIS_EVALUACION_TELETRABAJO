/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetPrProvincia;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_PR_PROVINCIA_BL extends BaseBL implements IBaseBL<PaetPrProvincia, java.lang.Short>{

    public PAET_PR_PROVINCIA_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPrProvincia merge(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPrProvincia findById(Short o) {
        return (PaetPrProvincia) this.getDao(PaetPrProvincia.class.getName()).findById(o);
    }

    @Override
    public List<PaetPrProvincia> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetPrProvincia> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
