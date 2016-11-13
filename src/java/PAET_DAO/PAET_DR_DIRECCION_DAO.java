/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDrDireccion;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DR_DIRECCION_DAO  extends HibernateUtil implements IBaseDAO<PaetDrDireccion, BigDecimal> {

    @Override
    public void save(PaetDrDireccion o) {
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
    public PaetDrDireccion merge(PaetDrDireccion o) {
        try {
            iniciaOperacion();
            o = (PaetDrDireccion) getSesion().merge(o);
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
    public void delete(PaetDrDireccion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDrDireccion findById(BigDecimal o) {
        PaetDrDireccion gerencia = null;

        try {
            iniciaOperacion();
            gerencia = (PaetDrDireccion) getSesion().get(PaetDrDireccion.class, o);
        } finally {
            getSesion().close();
        }
        return gerencia;
    }

    @Override
    public List<PaetDrDireccion> findAll() {
        List<PaetDrDireccion> listaDirecciones;
        
        try {
            iniciaOperacion();
            listaDirecciones = getSesion().createQuery("from PaetDrDireccion").list();
        } finally {
            getSesion().close();
        }

        return listaDirecciones;
    }

    @Override
    public List<PaetDrDireccion> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetDrDireccion> listaDirecciones;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetDrDireccion where "+filterBy+" = "+filter);
            }
            else   
            { 
                query = getSesion().createQuery("from PaetDrDireccion where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaDirecciones = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDirecciones;
    }

    @Override
    public BigDecimal saveWithReturn(PaetDrDireccion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
