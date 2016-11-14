/*
 * Copyright (C) 2016 Michelle
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package PAET_DAO;

import PAET_DOMAIN.PaetTtTeletrabajador;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_TT_TELETRABAJADOR_DAO extends HibernateUtil implements IBaseDAO<PaetTtTeletrabajador, BigDecimal>{

    @Override
    public void save(PaetTtTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTtTeletrabajador merge(PaetTtTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetTtTeletrabajador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetTtTeletrabajador findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetTtTeletrabajador> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetTtTeletrabajador> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetTtTeletrabajador> listaTeletrabajadores;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetTtTeletrabajador where "+filterBy+" = '"+filter+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetTtTeletrabajador where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaTeletrabajadores = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaTeletrabajadores;
    }

    @Override
    public BigDecimal saveWithReturn(PaetTtTeletrabajador o) {
        BigDecimal ttCodigo;
        
        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransac().commit();
            ttCodigo = o.getTtCodigo();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        
        return ttCodigo;
    }
    
}
