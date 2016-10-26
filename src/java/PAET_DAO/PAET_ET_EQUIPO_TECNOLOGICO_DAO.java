/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetEtEquipoTecnologico;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_ET_EQUIPO_TECNOLOGICO_DAO extends HibernateUtil implements IBaseDAO<PaetEtEquipoTecnologico, BigDecimal> {

    @Override
    public void save(PaetEtEquipoTecnologico o) {
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
    public PaetEtEquipoTecnologico merge(PaetEtEquipoTecnologico o) {
        try {
            iniciaOperacion();
            o = (PaetEtEquipoTecnologico) getSesion().merge(o);
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
    public void delete(PaetEtEquipoTecnologico o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEtEquipoTecnologico findById(BigDecimal o) {
        PaetEtEquipoTecnologico equipoTecnologico = null;

        try {
            iniciaOperacion();
            equipoTecnologico = (PaetEtEquipoTecnologico) getSesion().get(PaetEtEquipoTecnologico.class, o);
        } finally {
            getSesion().close();
        }
        return equipoTecnologico;
    }

    @Override
    public List<PaetEtEquipoTecnologico> findAll() {
        List<PaetEtEquipoTecnologico> listaEquipoTecnologico;
        
        try {
            iniciaOperacion();
            listaEquipoTecnologico = getSesion().createQuery("from PaetEtEquipoTecnologico").list();
        } finally {
            getSesion().close();
        }

        return listaEquipoTecnologico;
    }

    @Override
    public List<PaetEtEquipoTecnologico> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetEtEquipoTecnologico> listaEquipoTecnologico;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetEtEquipoTecnologico where "+filterBy+" = "+filter);
            }
            else
            {
                query = getSesion().createQuery("from PaetEtEquipoTecnologico where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaEquipoTecnologico = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaEquipoTecnologico;
    }
    
}
