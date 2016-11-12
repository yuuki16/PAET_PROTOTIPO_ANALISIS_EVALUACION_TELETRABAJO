/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetAcAnActividadAnalisis;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_AC_AN_ACTIVIDAD_ANALISIS_DAO extends HibernateUtil implements IBaseDAO<PaetAcAnActividadAnalisis, BigDecimal>{

    @Override
    public void save(PaetAcAnActividadAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetAcAnActividadAnalisis merge(PaetAcAnActividadAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetAcAnActividadAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetAcAnActividadAnalisis findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetAcAnActividadAnalisis> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetAcAnActividadAnalisis> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
