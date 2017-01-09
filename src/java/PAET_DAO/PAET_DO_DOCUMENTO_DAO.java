/*
 * Copyright (C) 2017 Michelle
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

import PAET_DOMAIN.PaetDoDocumento;
import PAET_UTILS.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

/**
 *
 * @author Michelle
 */
public class PAET_DO_DOCUMENTO_DAO extends HibernateUtil implements IBaseDAO<PaetDoDocumento, BigDecimal>{

    @Override
    public void save(PaetDoDocumento o) {
        try {
            iniciaOperacion();
            
            FileInputStream fileInputStream = new FileInputStream("c:\\temp\\sample.txt");

            File clobFile = new File("c:\\temp\\sample.txt");
            FileReader clobFileReader = new FileReader(new File("c:\\temp\\sample.txt"));

            o.setDoDocumento(Hibernate.getLobCreator(getSesion()).createClob(clobFileReader, (int) clobFile.length()));
            
            getSesion().save(o);
            getTransac().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PAET_DO_DOCUMENTO_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            getSesion().close();
        }
    }

    @Override
    public BigDecimal saveWithReturn(PaetDoDocumento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDoDocumento merge(PaetDoDocumento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetDoDocumento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetDoDocumento findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDoDocumento> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetDoDocumento> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
