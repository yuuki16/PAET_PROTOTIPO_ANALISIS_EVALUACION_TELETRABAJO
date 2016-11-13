/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetGrGerencia;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_GR_GERENCIA_DAO extends HibernateUtil implements IBaseDAO<PaetGrGerencia, BigDecimal> {

    @Override
    public void save(PaetGrGerencia o) {
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
    public PaetGrGerencia merge(PaetGrGerencia o) {
        try {
            iniciaOperacion();
            o = (PaetGrGerencia) getSesion().merge(o);
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
    public void delete(PaetGrGerencia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetGrGerencia findById(BigDecimal o) {
        PaetGrGerencia gerencia = null;

        try {
            iniciaOperacion();
            gerencia = (PaetGrGerencia) getSesion().get(PaetGrGerencia.class, o);
        } finally {
            getSesion().close();
        }
        return gerencia;
    }

    @Override
    public List<PaetGrGerencia> findAll() {
        List<PaetGrGerencia> listaDias;
        
        try {
            iniciaOperacion();
            listaDias = getSesion().createQuery("from PaetGrGerencia").list();
        } finally {
            getSesion().close();
        }

        return listaDias;
    }

    @Override
    public List<PaetGrGerencia> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetGrGerencia> listaGerencias;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetGrGerencia where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetGrGerencia where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaGerencias = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaGerencias;
    }

    @Override
    public BigDecimal saveWithReturn(PaetGrGerencia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
