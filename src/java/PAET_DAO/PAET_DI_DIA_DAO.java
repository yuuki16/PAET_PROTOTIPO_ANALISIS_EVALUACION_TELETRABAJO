/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDiDia;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DI_DIA_DAO extends HibernateUtil implements IBaseDAO<PaetDiDia, java.lang.Character> {

    @Override
    public void save(PaetDiDia o) {
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
    public PaetDiDia merge(PaetDiDia o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (PaetDiDia) getSesion().merge(o);
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
    public void delete(PaetDiDia o) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDiDia findById(java.lang.Character o) {
        PaetDiDia dia = null;

        try {
            iniciaOperacion();
            dia = (PaetDiDia) getSesion().get(PaetDiDia.class, o);
        } finally {
            getSesion().close();
        }
        return dia;
    }

    @Override
    public List<PaetDiDia> findAll() {
        List<PaetDiDia> listaDias;
        
        try {
            iniciaOperacion();
            listaDias = getSesion().createQuery("from PaetDiDia").list();
        } finally {
            getSesion().close();
        }

        return listaDias;
    }
    
    @Override
    public List<PaetDiDia> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetDiDia> listaDias;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetDiDia where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetDiDia where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaDias = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDias;
    }

    @Override
    public Character saveWithReturn(PaetDiDia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
