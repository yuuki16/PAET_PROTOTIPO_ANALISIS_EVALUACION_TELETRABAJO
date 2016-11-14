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

import PAET_DOMAIN.PaetAnAnalisisPuesto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_AN_ANALISIS_PUESTO_BL extends BaseBL implements IBaseBL<PaetAnAnalisisPuesto, BigDecimal>{

    public PAET_AN_ANALISIS_PUESTO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetAnAnalisisPuesto o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetAnAnalisisPuesto merge(PaetAnAnalisisPuesto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetAnAnalisisPuesto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetAnAnalisisPuesto findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetAnAnalisisPuesto> findAll(String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetAnAnalisisPuesto> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetAnAnalisisPuesto o) {
        
        BigDecimal anCodigo;
        anCodigo = (BigDecimal) this.getDao(o.getClass().getName()).saveWithReturn(o);
        return anCodigo;
    }
    
}
