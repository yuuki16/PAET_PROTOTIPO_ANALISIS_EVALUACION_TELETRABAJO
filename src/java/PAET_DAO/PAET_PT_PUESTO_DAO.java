/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetPtPuesto;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_PT_PUESTO_DAO extends HibernateUtil implements IBaseDAO<PaetPtPuesto, String>{

    @Override
    public void save(PaetPtPuesto o) {
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
    public PaetPtPuesto merge(PaetPtPuesto o) {
        try {
            iniciaOperacion();
            o = (PaetPtPuesto) getSesion().merge(o);
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
    public void delete(PaetPtPuesto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPtPuesto findById(String o) {
        PaetPtPuesto puesto = null;

        try {
            iniciaOperacion();
            puesto = (PaetPtPuesto) getSesion().get(PaetPtPuesto.class, o);
        } finally {
            getSesion().close();
        }
        return puesto;
    }

    @Override
    public List<PaetPtPuesto> findAll() {
        List<PaetPtPuesto> listaPuestos;
        
        try {
            iniciaOperacion();
            listaPuestos = getSesion().createQuery("from PaetPtPuesto").list();
        } finally {
            getSesion().close();
        }

        return listaPuestos;
    }

    @Override
    public List<PaetPtPuesto> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetPtPuesto> listaPuestos;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetPtPuesto where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else   
            { 
                query = getSesion().createQuery("from PaetPtPuesto where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaPuestos = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaPuestos;
    }
    
}
