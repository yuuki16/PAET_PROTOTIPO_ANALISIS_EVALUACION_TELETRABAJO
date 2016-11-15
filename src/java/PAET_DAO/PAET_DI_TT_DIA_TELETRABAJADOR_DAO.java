/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDiTtDiaTeletrabajador;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_DI_TT_DIA_TELETRABAJADOR_DAO extends HibernateUtil implements IBaseDAO<PaetDiTtDiaTeletrabajador, BigDecimal>{

    @Override
    public void save(PaetDiTtDiaTeletrabajador o) {
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
    public PaetDiTtDiaTeletrabajador merge(PaetDiTtDiaTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetDiTtDiaTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDiTtDiaTeletrabajador findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDiTtDiaTeletrabajador> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDiTtDiaTeletrabajador> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetDiTtDiaTeletrabajador> listaDiasTeletrabajador;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetDiTtDiaTeletrabajador where "+filterBy+" = '"+filter+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetDiTtDiaTeletrabajador where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaDiasTeletrabajador = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaDiasTeletrabajador;
    }

    @Override
    public BigDecimal saveWithReturn(PaetDiTtDiaTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
