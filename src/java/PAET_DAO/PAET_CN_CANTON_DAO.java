/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetCnCanton;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_CN_CANTON_DAO extends HibernateUtil implements IBaseDAO<PaetCnCanton, java.lang.Short> {

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
        PaetCnCanton canton = null;

        try {
            iniciaOperacion();
            canton = (PaetCnCanton) getSesion().get(PaetCnCanton.class, o);
        } finally {
            getSesion().close();
        }
        return canton;
    }

    @Override
    public List<PaetCnCanton> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetCnCanton> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetCnCanton> listaCantones;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetCnCanton where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetCnCanton where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaCantones = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaCantones;
    }

    @Override
    public Short saveWithReturn(PaetCnCanton o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
