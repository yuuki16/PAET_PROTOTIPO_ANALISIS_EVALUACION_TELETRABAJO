/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDsDistrito;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DS_DISTRITO_DAO extends HibernateUtil implements IBaseDAO<PaetDsDistrito, java.lang.Integer> {

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
        PaetDsDistrito distrito = null;

        try {
            iniciaOperacion();
            distrito = (PaetDsDistrito) getSesion().get(PaetDsDistrito.class, o);
        } finally {
            getSesion().close();
        }
        return distrito;
    }

    @Override
    public List<PaetDsDistrito> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDsDistrito> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetDsDistrito> listaDistritos;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetDsDistrito where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetDsDistrito where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaDistritos = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDistritos;
    }

    @Override
    public Integer saveWithReturn(PaetDsDistrito o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
