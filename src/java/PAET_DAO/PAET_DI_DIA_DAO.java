/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDiDia;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Michelle
 */
public class PAET_DI_DIA_DAO extends HibernateUtil implements IBaseDAO<PaetDiDia, String> {

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
        try {
            iniciaOperacion();
            getSesion().delete(o);
            getTransac().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    @Override
    public PaetDiDia findById(String o) {
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
}
