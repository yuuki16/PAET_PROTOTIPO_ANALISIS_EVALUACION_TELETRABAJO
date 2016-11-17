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

import PAET_DOMAIN.PaetPgProcesoSeguimiento;
import PAET_UTILS.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Michelle
 */
public class PAET_PG_PROCESO_SEGUIMIENTO_DAO extends HibernateUtil implements IBaseDAO<PaetPgProcesoSeguimiento, BigDecimal> {

    @Override
    public void save(PaetPgProcesoSeguimiento o) {
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
    public PaetPgProcesoSeguimiento merge(PaetPgProcesoSeguimiento o) {
        try {
            iniciaOperacion();
            String hql = "update PaetPgProcesoSeguimiento pg set pg.pgObservacion = :observacion, pg.pgFecha = :fecha, pg.pgEstado = :estado where pg.pgCodigo = :pgCodigo";
            int updatedEntities = getSesion().createQuery(hql)
                    .setString("observacion", o.getPgObservacion())
                    .setDate("fecha", o.getPgFechaAtendido())
                    .setCharacter("estado", o.getPgEstado())
                    .setBigDecimal("pgCodigo", o.getPgCodigo())
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
    public void delete(PaetPgProcesoSeguimiento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPgProcesoSeguimiento findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetPgProcesoSeguimiento> findAll() {
        List<PaetPgProcesoSeguimiento> listaProcesosSeguimiento;

        try {
            iniciaOperacion();
            listaProcesosSeguimiento = getSesion().createQuery("from PaetPgProcesoSeguimiento").list();
        } finally {
            getSesion().close();
        }

        return listaProcesosSeguimiento;
    }

    @Override
    public List<PaetPgProcesoSeguimiento> findDynamicFilter(String filterBy, String filter, Boolean unique) {
        List<PaetPgProcesoSeguimiento> listaProcesosSeguimiento;
        Query query;

        try {
            iniciaOperacion();
            if (unique) {
                query = getSesion().createQuery("from PaetPgProcesoSeguimiento where " + filterBy + " = '" + filter + "'");
            } else {
                query = getSesion().createQuery("from PaetPgProcesoSeguimiento where lower(" + filterBy + ") like ?");
                query.setString(0, "%" + filter.toLowerCase() + "%");
            }
            listaProcesosSeguimiento = query.list();

        } finally {
            getSesion().close();
        }

        return listaProcesosSeguimiento;
    }

    @Override
    public BigDecimal saveWithReturn(PaetPgProcesoSeguimiento o) {
        BigDecimal pgCodigo;

        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransac().commit();
            pgCodigo = o.getPgCodigo();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }

        return pgCodigo;
    }

}
