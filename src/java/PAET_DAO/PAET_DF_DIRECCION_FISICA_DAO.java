/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDfDireccionFisica;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DF_DIRECCION_FISICA_DAO extends HibernateUtil implements IBaseDAO<PaetDfDireccionFisica, BigDecimal>{

    @Override
    public void save(PaetDfDireccionFisica o) {
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
    public PaetDfDireccionFisica merge(PaetDfDireccionFisica o) {
        try {
            iniciaOperacion();
            o = (PaetDfDireccionFisica) getSesion().merge(o);
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
    public void delete(PaetDfDireccionFisica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDfDireccionFisica findById(BigDecimal o) {
        PaetDfDireccionFisica direccionFisica = null;

        try {
            iniciaOperacion();
            direccionFisica = (PaetDfDireccionFisica) getSesion().get(PaetDfDireccionFisica.class, o);
        } finally {
            getSesion().close();
        }
        return direccionFisica;
    }

    @Override
    public List<PaetDfDireccionFisica> findAll() {
        List<PaetDfDireccionFisica> listaDireccionesFísicas;
        
        try {
            iniciaOperacion();
            listaDireccionesFísicas = getSesion().createQuery("from PaetDfDireccionFisica").list();
        } finally {
            getSesion().close();
        }

        return listaDireccionesFísicas;
    }

    @Override
    public List<PaetDfDireccionFisica> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetDfDireccionFisica> listaDireccionesFísicas;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetDfDireccionFisica where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else   
            { 
                query = getSesion().createQuery("from PaetDfDireccionFisica where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaDireccionesFísicas = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDireccionesFísicas;
    }

    @Override
    public BigDecimal saveWithReturn(PaetDfDireccionFisica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
