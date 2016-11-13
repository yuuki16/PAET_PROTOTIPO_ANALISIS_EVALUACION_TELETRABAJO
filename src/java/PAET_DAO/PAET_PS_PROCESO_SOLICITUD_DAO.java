/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetPsProcesoSolicitud;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_PS_PROCESO_SOLICITUD_DAO extends HibernateUtil implements IBaseDAO<PaetPsProcesoSolicitud, BigDecimal>{

    @Override
    public void save(PaetPsProcesoSolicitud o) {
        
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
    public PaetPsProcesoSolicitud merge(PaetPsProcesoSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetPsProcesoSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPsProcesoSolicitud findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetPsProcesoSolicitud> findAll() {
        List<PaetPsProcesoSolicitud> listaProcesosSolicitud;
        
        try {
            iniciaOperacion();
            listaProcesosSolicitud = getSesion().createQuery("from PaetPsProcesoSolicitud").list();
        } finally {
            getSesion().close();
        }

        return listaProcesosSolicitud;
    }

    @Override
    public List<PaetPsProcesoSolicitud> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetPsProcesoSolicitud> listaProcesosSolicitud;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetPsProcesoSolicitud where "+filterBy+" = '"+filter+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetPsProcesoSolicitud where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaProcesosSolicitud = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaProcesosSolicitud;
    }

    @Override
    public BigDecimal saveWithReturn(PaetPsProcesoSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
