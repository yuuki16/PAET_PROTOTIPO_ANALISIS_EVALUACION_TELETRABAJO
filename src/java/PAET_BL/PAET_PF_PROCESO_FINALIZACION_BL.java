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

import PAET_DOMAIN.PaetPfProcesoFinalizacion;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_PF_PROCESO_FINALIZACION_BL extends BaseBL implements IBaseBL<PaetPfProcesoFinalizacion, BigDecimal>{

    public PAET_PF_PROCESO_FINALIZACION_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetPfProcesoFinalizacion o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetPfProcesoFinalizacion merge(PaetPfProcesoFinalizacion o) {
        return (PaetPfProcesoFinalizacion) this.getDao(o.getClass().getName()).merge(o);
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
    public List<PaetPfProcesoFinalizacion> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetPfProcesoFinalizacion> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetPfProcesoFinalizacion o) {
        BigDecimal pfCodigo;
        pfCodigo = (BigDecimal) this.getDao(o.getClass().getName()).saveWithReturn(o);
        return pfCodigo;
    }
    
}
