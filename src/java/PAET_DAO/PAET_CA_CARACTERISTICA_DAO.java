/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetCaCaracteristica;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_CA_CARACTERISTICA_DAO extends HibernateUtil implements IBaseDAO<PaetCaCaracteristica, BigDecimal>{

    @Override
    public void save(PaetCaCaracteristica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCaCaracteristica merge(PaetCaCaracteristica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetCaCaracteristica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetCaCaracteristica findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetCaCaracteristica> findAll() {
        List<PaetCaCaracteristica> listaCaracteristicas;
        
        try {
            iniciaOperacion();
            listaCaracteristicas = getSesion().createQuery("from PaetCaCaracteristica").list();
        } finally {
            getSesion().close();
        }

        return listaCaracteristicas;
    }

    @Override
    public List<PaetCaCaracteristica> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
