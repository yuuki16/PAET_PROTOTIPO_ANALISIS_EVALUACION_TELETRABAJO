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
package PAET_DOMAIN;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
/**
 *
 * @author Michelle
 */
public class PaetDoDocumento implements java.io.Serializable{
    
    private BigDecimal doCodigo;
    private Clob doDocumento;
    private Date doFecha;
    private char doTipoProceso;
    private BigDecimal doProceso;
    private String doArchivo;
    
    public PaetDoDocumento(){
    }
    
    public PaetDoDocumento(BigDecimal doCodigo, Clob doDocumento, Date doFecha, char doTipoProceso, BigDecimal doProceso, String doArchivo){
        this.doCodigo = doCodigo;
        this.doDocumento = doDocumento;
        this.doFecha = doFecha;
        this.doTipoProceso = doTipoProceso;
        this.doProceso = doProceso;
        this.doArchivo = doArchivo;
    }
    
    public BigDecimal getDoCodigo() {
        return this.doCodigo;
    }
    
    public void setDoCodigo(BigDecimal doCodigo) {
        this.doCodigo = doCodigo;
    }
    
    public Clob getDoDocumento() {
        return this.doDocumento;
    }
    
    public void setDoDocumento(Clob doDocumento) {
        this.doDocumento = doDocumento;
    }
    
    public Date getDoFecha() {
        return this.doFecha;
    }
    
    public void setDoFecha(Date doFecha) {
        this.doFecha = doFecha;
    }

    public char getDoTipoProceso() {
        return this.doTipoProceso;
    }
    
    public void setDoTipoProceso(char doTipoProceso) {
        this.doTipoProceso = doTipoProceso;
    }
    
    public BigDecimal getDoProceso() {
        return this.doProceso;
    }
    
    public void setDoProceso(BigDecimal doProceso) {
        this.doProceso = doProceso;
    }
    
    public String getDoArchivo() {
        return this.doArchivo;
    }
    
    public void setDoArchivo(String doArchivo) {
        this.doArchivo = doArchivo;
    }
}
