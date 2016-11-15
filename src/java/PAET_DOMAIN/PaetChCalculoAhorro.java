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
package PAET_DOMAIN;

import java.math.BigDecimal;

/**
 *
 * @author Michelle
 */
public class PaetChCalculoAhorro {

    private BigDecimal chCodigo;
    private BigDecimal chSalarioTotal;
    private BigDecimal chSalarioLey;
    private BigDecimal chCostoEventos;
    private BigDecimal psProcesoSolicitud;
    
    public PaetChCalculoAhorro() {
    }

    public PaetChCalculoAhorro(BigDecimal chCodigo, BigDecimal chSalarioTotal, BigDecimal chSalarioLey, BigDecimal chCostoEventos, BigDecimal psProcesoSolicitud) {
       this.chCodigo = chCodigo;
       this.chSalarioTotal = chSalarioTotal;
       this.chSalarioLey = chSalarioLey;
       this.chCostoEventos = chCostoEventos;
       this.psProcesoSolicitud = psProcesoSolicitud;
    }
   
    public BigDecimal getChCodigo() {
        return this.chCodigo;
    }
    
    public void setChCodigo(BigDecimal chCodigo) {
        this.chCodigo = chCodigo;
    }
    
    public BigDecimal getChSalarioTotal() {
        return this.chSalarioTotal;
    }
    
    public void setChSalarioTotal(BigDecimal chSalarioTotal) {
        this.chSalarioTotal = chSalarioTotal;
    }
    
    public BigDecimal getChSalarioLey() {
        return this.chSalarioLey;
    }
    
    public void setChSalarioLey(BigDecimal chSalarioLey) {
        this.chSalarioLey = chSalarioLey;
    }
    
    public BigDecimal getChCostoEventos() {
        return this.chCostoEventos;
    }
    
    public void setPsProcesoSolicitud(BigDecimal psProcesoSolicitud) {
        this.psProcesoSolicitud = psProcesoSolicitud;
    }
    
    public BigDecimal getPsProcesoSolicitud() {
        return this.psProcesoSolicitud;
    }
    
    public void setChCostoEventos(BigDecimal chCostoEventos) {
        this.chCostoEventos = chCostoEventos;
    }
}
