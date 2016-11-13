/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetArArea;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_AR_AREA_DAO extends HibernateUtil implements IBaseDAO<PaetArArea, BigDecimal> {

    @Override
    public void save(PaetArArea o) {
        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransac().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    @Override
    public PaetArArea merge(PaetArArea o) {
        try {
            iniciaOperacion();
            o = (PaetArArea) getSesion().merge(o);
            getTransac().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return o;
    }

    @Override
    public void delete(PaetArArea o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetArArea findById(BigDecimal o) {
        PaetArArea area = null;

        try {
            iniciaOperacion();
            area = (PaetArArea) getSesion().get(PaetArArea.class, o);
        } finally {
            getSesion().close();
        }
        return area;
    }

    @Override
    public List<PaetArArea> findAll() {
        List<PaetArArea> listaAreas;
        
        try {
            iniciaOperacion();
            listaAreas = getSesion().createQuery("from PaetArArea").list();
        } finally {
            getSesion().close();
        }

        return listaAreas;
    }

    @Override
    public List<PaetArArea> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetArArea> listaAreas;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetArArea where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetArArea where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaAreas = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaAreas;
    }

    @Override
    public BigDecimal saveWithReturn(PaetArArea o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
