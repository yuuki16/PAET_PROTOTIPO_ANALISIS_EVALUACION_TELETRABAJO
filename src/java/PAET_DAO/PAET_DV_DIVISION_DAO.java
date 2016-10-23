/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDvDivision;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DV_DIVISION_DAO extends HibernateUtil implements IBaseDAO<PaetDvDivision, BigDecimal> {

    @Override
    public void save(PaetDvDivision o) {
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
    public PaetDvDivision merge(PaetDvDivision o) {
        try {
            iniciaOperacion();
            o = (PaetDvDivision) getSesion().merge(o);
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
    public void delete(PaetDvDivision o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDvDivision findById(BigDecimal o) {
        PaetDvDivision gerencia = null;

        try {
            iniciaOperacion();
            gerencia = (PaetDvDivision) getSesion().get(PaetDvDivision.class, o);
        } finally {
            getSesion().close();
        }
        return gerencia;
    }

    @Override
    public List<PaetDvDivision> findAll() {
        List<PaetDvDivision> listaDivisiones;
        
        try {
            iniciaOperacion();
            listaDivisiones = getSesion().createQuery("from PaetDvDivision").list();
        } finally {
            getSesion().close();
        }

        return listaDivisiones;
    }

    @Override
    public List<PaetDvDivision> findDynamicFilter(String filterBy, String filter) {
        List<PaetDvDivision> listaDivisiones;
        Query query;
        
        try {
            iniciaOperacion();
            query = getSesion().createQuery("from PaetDvDivision where lower("+filterBy+") like ?");
            query.setString(0, "%"+filter.toLowerCase()+"%");
            listaDivisiones = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDivisiones;
    }
}
