/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetDiSlDiaSolicitud;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DI_SL_DIA_SOLICITUD_DAO extends HibernateUtil implements IBaseDAO<PaetDiSlDiaSolicitud, BigDecimal>{

    @Override
    public void save(PaetDiSlDiaSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDiSlDiaSolicitud merge(PaetDiSlDiaSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetDiSlDiaSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDiSlDiaSolicitud findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDiSlDiaSolicitud> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDiSlDiaSolicitud> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
