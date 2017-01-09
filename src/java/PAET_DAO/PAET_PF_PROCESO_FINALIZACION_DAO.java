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

import PAET_DOMAIN.PaetPfProcesoFinalizacion;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_PF_PROCESO_FINALIZACION_DAO extends HibernateUtil implements IBaseDAO<PaetPfProcesoFinalizacion, BigDecimal> {

    @Override
    public void save(PaetPfProcesoFinalizacion o) {
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
    public PaetPfProcesoFinalizacion merge(PaetPfProcesoFinalizacion o) {
        try {
            iniciaOperacion();
            String hql = "update PaetPfProcesoFinalizacion pf set pf.pfObservacion = :observacion, pf.pfFechaAtendido = :fecha, pf.pfEstado = :estado where pf.pfCodigo = :pfCodigo";
            int updatedEntities = getSesion().createQuery(hql)
                    .setString("observacion", o.getPfObservacion())
                    .setDate("fecha", o.getPfFechaAtendido())
                    .setCharacter("estado", o.getPfEstado())
                    .setBigDecimal("pfCodigo", o.getPfCodigo())
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
    public void delete(PaetPfProcesoFinalizacion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPfProcesoFinalizacion findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetPfProcesoFinalizacion> findAll() {
        List<PaetPfProcesoFinalizacion> listaProcesosSeguimiento;

        try {
            iniciaOperacion();
            listaProcesosSeguimiento = getSesion().createQuery("from PaetPfProcesoFinalizacion").list();
        } finally {
            getSesion().close();
        }

        return listaProcesosSeguimiento;
    }

    @Override
    public List<PaetPfProcesoFinalizacion> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetPfProcesoFinalizacion> listaProcesosSeguimiento;
        Query query;

        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetPfProcesoFinalizacion where " + filterBy + " = '" + filter + "'");
            } else {
                query = getSesion().createQuery("from PaetPfProcesoFinalizacion where lower(" + filterBy + ") like ?");
                query.setString(0, "%" + filter.toLowerCase() + "%");
            }
            listaProcesosSeguimiento = query.list();

        } finally {
            getSesion().close();
        }

        return listaProcesosSeguimiento;
    }

    @Override
    public BigDecimal saveWithReturn(PaetPfProcesoFinalizacion o) {
        BigDecimal pgCodigo;

        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransac().commit();
            pgCodigo = o.getPfCodigo();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }

        return pgCodigo;
    }

}
