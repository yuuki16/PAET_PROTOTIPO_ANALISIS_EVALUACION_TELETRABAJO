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

import PAET_DOMAIN.PaetEvEvaluacion;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_EV_EVALUACION_BL extends BaseBL implements IBaseBL<PaetEvEvaluacion, BigDecimal>{

    public PAET_EV_EVALUACION_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetEvEvaluacion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEvEvaluacion merge(PaetEvEvaluacion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PaetEvEvaluacion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEvEvaluacion findById(BigDecimal o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetEvEvaluacion> findAll(String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaetEvEvaluacion> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal saveWithReturn(PaetEvEvaluacion o) {
        BigDecimal evCodigo;
        evCodigo = (BigDecimal) this.getDao(o.getClass().getName()).saveWithReturn(o);
        return evCodigo;
    }
    
}
