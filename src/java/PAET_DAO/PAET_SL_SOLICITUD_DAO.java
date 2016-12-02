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

import PAET_BL.PAET_PS_PROCESO_SOLICITUD_BL;
import PAET_DOMAIN.PaetPsProcesoSolicitud;
import PAET_DOMAIN.PaetSlSolicitud;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_SL_SOLICITUD_DAO extends HibernateUtil implements IBaseDAO<PaetSlSolicitud, BigDecimal>{

    @Override
    public void save(PaetSlSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetSlSolicitud merge(PaetSlSolicitud o) {
        try {
            iniciaOperacion();
            String hql = "update PaetSlSolicitud sl set sl.slResultado = :slResultado where sl.slCodigo = :slCodigo";
            int updatedEntities = getSesion().createQuery(hql)
                    .setCharacter("slResultado", o.getSlResultado())
                    .setBigDecimal("slCodigo", o.getSlCodigo())
                    .executeUpdate();
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
    public void delete(PaetSlSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetSlSolicitud findById(BigDecimal o) {
        PaetSlSolicitud solicitud = null;

        try {
            iniciaOperacion();
            solicitud = (PaetSlSolicitud) getSesion().get(PaetSlSolicitud.class, o);
        } finally {
            getSesion().close();
        }
        return solicitud;
    }

    @Override
    public List<PaetSlSolicitud> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetSlSolicitud> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetSlSolicitud> listaSolicitudes;
        Query query;
        
        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetSlSolicitud where "+filterBy+" = '"+filter+"'");
            }
            else
            {
                query = getSesion().createQuery("from PaetSlSolicitud where lower("+filterBy+") like ?");
                query.setString(0, "%"+filter.toLowerCase()+"%");
            }
            listaSolicitudes = query.list();
            
        } finally {
            getSesion().close();
        }

        return listaSolicitudes;
    }

    @Override
    public BigDecimal saveWithReturn(PaetSlSolicitud o) {
        BigDecimal slCodigo;
        
        PaetPsProcesoSolicitud procesoSolicitud = new PaetPsProcesoSolicitud();
        PAET_PS_PROCESO_SOLICITUD_BL procesoSolicitudBl = new PAET_PS_PROCESO_SOLICITUD_BL();
        
        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransac().commit();
            slCodigo = o.getSlCodigo();
            //insertar proceso solicitud finalizado
            procesoSolicitud.setPsFechaEntrada(o.getSlFecha());
            procesoSolicitud.setSlSolicitud(o.getSlCodigo());
            procesoSolicitud.setEsEstado(new BigDecimal(1));
            procesoSolicitud.setPsFechaAtendido(o.getSlFecha());
            procesoSolicitud.setPsEstado('F');
            procesoSolicitudBl.save(procesoSolicitud);
            //insertar proceso solicitud pendiente
            procesoSolicitud = new PaetPsProcesoSolicitud();
            procesoSolicitud.setPsFechaEntrada(o.getSlFecha());
            procesoSolicitud.setSlSolicitud(o.getSlCodigo());
            procesoSolicitud.setEsEstado(new BigDecimal(2));
            procesoSolicitud.setPsEstado('P');
            procesoSolicitudBl.save(procesoSolicitud);
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        
        return slCodigo;
    }
    
}
