/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetFcAnFactorAnalisis;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_FC_AN_FACTOR_ANALISIS_DAO extends HibernateUtil implements IBaseDAO<PaetFcAnFactorAnalisis, BigDecimal>{

    @Override
    public void save(PaetFcAnFactorAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetFcAnFactorAnalisis merge(PaetFcAnFactorAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetFcAnFactorAnalisis o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetFcAnFactorAnalisis findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetFcAnFactorAnalisis> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetFcAnFactorAnalisis> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
