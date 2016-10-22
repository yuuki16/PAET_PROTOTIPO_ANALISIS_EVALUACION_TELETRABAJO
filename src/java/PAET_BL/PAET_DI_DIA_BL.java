/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DAO.PAET_DI_DIA_DAO;
import PAET_DOMAIN.PaetDiDia;
import java.util.List;

/**
 *
 * @author Michelle
 */
public class PAET_DI_DIA_BL extends BaseBL implements IBaseBL<PaetDiDia, String>{

    public PAET_DI_DIA_BL()
    {
        super();
    }
    
    @Override
    public void save(PaetDiDia o) {
       this.getDao(o.getClass().getName()).save(o);
    }

    @Override
    public PaetDiDia merge(PaetDiDia o) {
        return (PaetDiDia) this.getDao(o.getClass().getName()).merge(o);
    }

    @Override
    public void delete(PaetDiDia o) {
        
    }

    @Override
    public PaetDiDia findById(String o) {
        return (PaetDiDia) this.getDao(PaetDiDia.class.getName()).findById(o);
    }

    @Override
    public List<PaetDiDia> findAll(String className) {
        return this.getDao(className).findAll();
    }
    
}
