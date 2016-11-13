/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetTlTelefono;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_TL_TELEFONO_DAO extends HibernateUtil implements IBaseDAO<PaetTlTelefono, BigDecimal>{

    @Override
    public void save(PaetTlTelefono o) {
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
    public PaetTlTelefono merge(PaetTlTelefono o) {
        try {
            iniciaOperacion();
            o = (PaetTlTelefono) getSesion().merge(o);
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
    public void delete(PaetTlTelefono o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTlTelefono findById(BigDecimal o) {
        PaetTlTelefono telefono = null;

        try {
            iniciaOperacion();
            telefono = (PaetTlTelefono) getSesion().get(PaetTlTelefono.class, o);
        } finally {
            getSesion().close();
        }
        return telefono;
    }

    @Override
    public List<PaetTlTelefono> findAll() {
        List<PaetTlTelefono> listaTelefonos;
        
        try {
            iniciaOperacion();
            listaTelefonos = getSesion().createQuery("from PaetTlTelefono").list();
        } finally {
            getSesion().close();
        }

        return listaTelefonos;
    }

    @Override
    public List<PaetTlTelefono> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetTlTelefono> listaTelefonos;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetTlTelefono where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else   
            { 
                query = getSesion().createQuery("from PaetTlTelefono where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaTelefonos = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaTelefonos;
    }

    @Override
    public BigDecimal saveWithReturn(PaetTlTelefono o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
