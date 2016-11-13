/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DOMAIN.PaetEtEquipoTecnologico;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_ET_EQUIPO_TECNOLOGICO_BL extends BaseBL implements IBaseBL<PaetEtEquipoTecnologico, BigDecimal>{

    public PAET_ET_EQUIPO_TECNOLOGICO_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetEtEquipoTecnologico o) {
        this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetEtEquipoTecnologico merge(PaetEtEquipoTecnologico o) {
        return (PaetEtEquipoTecnologico) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetEtEquipoTecnologico o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaetEtEquipoTecnologico findById(BigDecimal o) {
        return (PaetEtEquipoTecnologico) this.getDao(PaetEtEquipoTecnologico.class.getName()).findById(o);
    }

    @Override
    public List<PaetEtEquipoTecnologico> findAll(String className) {
        return this.getDao(className).findAll();
    }

    @Override
    public List<PaetEtEquipoTecnologico> findDynamicFilter(String filterBy, String filter, Boolean unique, String className) {
        return this.getDao(className).findDynamicFilter(filterBy, filter, unique);
    }

    @Override
    public BigDecimal saveWithReturn(PaetEtEquipoTecnologico o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
