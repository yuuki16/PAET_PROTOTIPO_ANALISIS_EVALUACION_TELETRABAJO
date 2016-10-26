/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DAO.IBaseDAO;
import PAET_DAO.*;
import java.util.HashMap;

/**
 *
 * @author Michelle
 */
public class BaseBL {

    private final HashMap<String, IBaseDAO> daos;

    public BaseBL(){
            daos = new HashMap();
            daos.put("PAET_DOMAIN.PaetDiDia", new PAET_DI_DIA_DAO());
            daos.put("PAET_DOMAIN.PaetArArea", new PAET_AR_AREA_DAO());
            daos.put("PAET_DOMAIN.PaetGrGerencia", new PAET_GR_GERENCIA_DAO());
            daos.put("PAET_DOMAIN.PaetDvDivision", new PAET_DV_DIVISION_DAO());
            daos.put("PAET_DOMAIN.PaetDrDireccion", new PAET_DR_DIRECCION_DAO());
            daos.put("PAET_DOMAIN.PaetFcFactorComplementario", new PAET_FC_FACTOR_COMPLEMENTARIO_DAO());
            daos.put("PAET_DOMAIN.PaetEtEquipoTecnologico", new PAET_ET_EQUIPO_TECNOLOGICO_DAO());
    }

    public IBaseDAO getDao(String className) {
        return daos.get(className);
    }
}
