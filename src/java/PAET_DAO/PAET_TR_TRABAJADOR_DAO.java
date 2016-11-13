package PAET_DAO;

import PAET_DOMAIN.PaetTrTrabajador;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_TR_TRABAJADOR_DAO extends HibernateUtil implements IBaseDAO<PaetTrTrabajador, String>{

    @Override
    public void save(PaetTrTrabajador o) {
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
    public PaetTrTrabajador merge(PaetTrTrabajador o) {
        try {
            iniciaOperacion();
            o = (PaetTrTrabajador) getSesion().merge(o);
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
    public void delete(PaetTrTrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTrTrabajador findById(String o) {
        PaetTrTrabajador trabajador = null;

        try {
            iniciaOperacion();
            trabajador = (PaetTrTrabajador) getSesion().get(PaetTrTrabajador.class, o);
        } finally {
            getSesion().close();
        }
        return trabajador;
    }

    @Override
    public List<PaetTrTrabajador> findAll() {
        List<PaetTrTrabajador> listaTrabajadores;
        
        try {
            iniciaOperacion();
            listaTrabajadores = getSesion().createQuery("from PaetTrTrabajador").list();
        } finally {
            getSesion().close();
        }

        return listaTrabajadores;
    }

    @Override
    public List<PaetTrTrabajador> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetTrTrabajador> listaTrabajadores;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetTrTrabajador where lower("+filterBy+") = '"+filter.toLowerCase()+"'");
            }
            else   
            { 
                query = getSesion().createQuery("from PaetTrTrabajador where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaTrabajadores = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaTrabajadores;
    }

    @Override
    public String saveWithReturn(PaetTrTrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
