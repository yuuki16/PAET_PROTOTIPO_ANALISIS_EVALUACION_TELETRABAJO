
package PAET_DAO;

import PAET_DOMAIN.PaetCrCorreo;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_CR_CORREO_DAO extends HibernateUtil implements IBaseDAO<PaetCrCorreo, BigDecimal>{

    @Override
    public void save(PaetCrCorreo o) {
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
    public PaetCrCorreo merge(PaetCrCorreo o) {
        try {
            iniciaOperacion();
            o = (PaetCrCorreo) getSesion().merge(o);
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
    public void delete(PaetCrCorreo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCrCorreo findById(BigDecimal o) {
        PaetCrCorreo correo = null;

        try {
            iniciaOperacion();
            correo = (PaetCrCorreo) getSesion().get(PaetCrCorreo.class, o);
        } finally {
            getSesion().close();
        }
        return correo;
    }

    @Override
    public List<PaetCrCorreo> findAll() {
        List<PaetCrCorreo> listaCorreos;
        
        try {
            iniciaOperacion();
            listaCorreos = getSesion().createQuery("from PaetCrCorreo").list();
        } finally {
            getSesion().close();
        }

        return listaCorreos;
    }

    @Override
    public List<PaetCrCorreo> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetCrCorreo> listaCorreos;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetCrCorreo where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else   
            { 
                query = getSesion().createQuery("from PaetCrCorreo where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaCorreos = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaCorreos;
    }

    @Override
    public BigDecimal saveWithReturn(PaetCrCorreo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
