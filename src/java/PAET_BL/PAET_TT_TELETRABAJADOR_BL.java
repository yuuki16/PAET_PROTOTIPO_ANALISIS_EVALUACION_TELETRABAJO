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

import PAET_DOMAIN.PaetTtTeletrabajador;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_TT_TELETRABAJADOR_BL extends BaseBL implements IBaseBL<PaetTtTeletrabajador, BigDecimal>{

    public PAET_TT_TELETRABAJADOR_BL()
    {
        super();
    }
    
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
        return (PaetTtTeletrabajador) this.getDao(PaetTtTeletrabajador.class.getName()).findById(o);
    }

    @Override
    public List<PaetTtTeletrabajador> findAll(String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetTtTeletrabajador> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetTtTeletrabajador o) {
        BigDecimal ttCodigo;
        ttCodigo = (BigDecimal) this.getDao(o.getClass().getName()).saveWithReturn(o);
        return ttCodigo;
    }
    
}
