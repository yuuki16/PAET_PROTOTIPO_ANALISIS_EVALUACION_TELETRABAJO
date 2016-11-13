/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetFcFactorComplementario;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_FC_FACTOR_COMPLEMENTARIO_DAO extends HibernateUtil implements IBaseDAO<PaetFcFactorComplementario, BigDecimal>{

    @Override
    public void save(PaetFcFactorComplementario o) {
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
    public PaetFcFactorComplementario merge(PaetFcFactorComplementario o) {
        try {
            iniciaOperacion();
            o = (PaetFcFactorComplementario) getSesion().merge(o);
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
    public void delete(PaetFcFactorComplementario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetFcFactorComplementario findById(BigDecimal o) {
        PaetFcFactorComplementario factorComplementario = null;

        try {
            iniciaOperacion();
            factorComplementario = (PaetFcFactorComplementario) getSesion().get(PaetFcFactorComplementario.class, o);
        } finally {
            getSesion().close();
        }
        return factorComplementario;
    }

    @Override
    public List<PaetFcFactorComplementario> findAll() {
        List<PaetFcFactorComplementario> listaFactores;
        
        try {
            iniciaOperacion();
            listaFactores = getSesion().createQuery("from PaetFcFactorComplementario").list();
        } finally {
            getSesion().close();
        }

        return listaFactores;
    }

    @Override
    public List<PaetFcFactorComplementario> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetFcFactorComplementario> listaFactores;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetFcFactorComplementario where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetFcFactorComplementario where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaFactores = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaFactores;
    }

    @Override
    public BigDecimal saveWithReturn(PaetFcFactorComplementario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
