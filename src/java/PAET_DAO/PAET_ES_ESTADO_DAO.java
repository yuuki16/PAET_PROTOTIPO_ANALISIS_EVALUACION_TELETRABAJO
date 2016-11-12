/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetEsEstado;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_ES_ESTADO_DAO extends HibernateUtil implements IBaseDAO<PaetEsEstado, BigDecimal>{

    @Override
    public void save(PaetEsEstado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEsEstado merge(PaetEsEstado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetEsEstado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEsEstado findById(BigDecimal o) {
        PaetEsEstado estado = null;

        try {
            iniciaOperacion();
            estado = (PaetEsEstado) getSesion().get(PaetEsEstado.class, o);
        } finally {
            getSesion().close();
        }
        return estado;
    }

    @Override
    public List<PaetEsEstado> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetEsEstado> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetEsEstado> listaEstados;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetEsEstado where "+filterBy+" = '"+filter+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetEsEstado where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaEstados = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaEstados;
    }
    
}
