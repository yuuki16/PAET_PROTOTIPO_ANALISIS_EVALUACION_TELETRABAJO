/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAET_BL;

import PAET_DAO.IBaseDAO;
import PAET_DAO.*;
import java.util.LinkedHashMap;

/**
 *
 * @author Michelle
 */
public class BaseBL {
    private final LinkedHashMap<String, IBaseDAO> daos;

    public BaseBL() {
        daos = new LinkedHashMap();
        daos.put("PAET_DOMAIN.PaetDiDia", new PAET_DI_DIA_DAO());
    }
    
    public IBaseDAO getDao(String className){
        return daos.get(className);
    }
}
