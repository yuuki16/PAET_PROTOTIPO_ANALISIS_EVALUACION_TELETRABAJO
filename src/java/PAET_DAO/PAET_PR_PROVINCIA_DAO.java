package PAET_DAO;

import PAET_DOMAIN.PaetPrProvincia;
import PAET_UTILS.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_PR_PROVINCIA_DAO  extends HibernateUtil implements IBaseDAO<PaetPrProvincia, java.lang.Short> {

    @Override
    public void save(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPrProvincia merge(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetPrProvincia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPrProvincia findById(java.lang.Short o) {
        PaetPrProvincia provincia = null;

        try {
            iniciaOperacion();
            provincia = (PaetPrProvincia) getSesion().get(PaetPrProvincia.class, o);
        } finally {
            getSesion().close();
        }
        return provincia;
    }

    @Override
    public List<PaetPrProvincia> findAll() {
        List<PaetPrProvincia> listaProvincias;
        
        try {
            iniciaOperacion();
            listaProvincias = getSesion().createQuery("from PaetPrProvincia").list();
        } finally {
            getSesion().close();
        }

        return listaProvincias;
    }

    @Override
    public List<PaetPrProvincia> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
