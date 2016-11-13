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
package PAET_BL;

import PAET_DOMAIN.PaetPsProcesoSolicitud;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_PS_PROCESO_SOLICITUD_BL extends BaseBL implements IBaseBL<PaetPsProcesoSolicitud, BigDecimal>{

    public PAET_PS_PROCESO_SOLICITUD_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetPsProcesoSolicitud o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetPsProcesoSolicitud merge(PaetPsProcesoSolicitud o) {
        return (PaetPsProcesoSolicitud) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetPsProcesoSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetPsProcesoSolicitud findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetPsProcesoSolicitud> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetPsProcesoSolicitud> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetPsProcesoSolicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
