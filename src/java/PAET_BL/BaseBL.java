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
            daos.put("PAET_DOMAIN.PaetAcActividad", new PAET_AC_ACTIVIDAD_DAO());
            daos.put("PAET_DOMAIN.PaetAcCaActCaract", new PAET_AC_CA_ACT_CARACT_DAO());
            daos.put("PAET_DOMAIN.PaetAcAnActividadAnalisis", new PAET_AC_AN_ACTIVIDAD_ANALISIS_DAO());
            daos.put("PAET_DOMAIN.PaetAnAnalisisPuesto", new PAET_AN_ANALISIS_PUESTO_DAO());
            daos.put("PAET_DOMAIN.PaetAmAccionMejora", new PAET_AM_ACCION_MEJORA_DAO());
            daos.put("PAET_DOMAIN.PaetArArea", new PAET_AR_AREA_DAO());
            daos.put("PAET_DOMAIN.PaetCaCaracteristica", new PAET_CA_CARACTERISTICA_DAO());
            daos.put("PAET_DOMAIN.PaetChCalculoAhorro", new PAET_CH_CALCULO_AHORRO_DAO());
            daos.put("PAET_DOMAIN.PaetCnCanton", new PAET_CN_CANTON_DAO());
            daos.put("PAET_DOMAIN.PaetCrCorreo", new PAET_CR_CORREO_DAO());
            daos.put("PAET_DOMAIN.PaetCuCausa", new PAET_CU_CAUSA_DAO());
            daos.put("PAET_DOMAIN.PaetDcDocumentacion", new PAET_DC_DOCUMENTACION_DAO());
            daos.put("PAET_DOMAIN.PaetDfDireccionFisica", new PAET_DF_DIRECCION_FISICA_DAO());
            daos.put("PAET_DOMAIN.PaetDiDia", new PAET_DI_DIA_DAO());
            daos.put("PAET_DOMAIN.PaetDiSlDiaSolicitud", new PAET_DI_SL_DIA_SOLICITUD_DAO());
            daos.put("PAET_DOMAIN.PaetDiTtDiaTeletrabajador", new PAET_DI_TT_DIA_TELETRABAJADOR_DAO());
            daos.put("PAET_DOMAIN.PaetDrDireccion", new PAET_DR_DIRECCION_DAO());
            daos.put("PAET_DOMAIN.PaetDsDistrito", new PAET_DS_DISTRITO_DAO());
            daos.put("PAET_DOMAIN.PaetDvDivision", new PAET_DV_DIVISION_DAO());
            daos.put("PAET_DOMAIN.PaetEsEstado", new PAET_ES_ESTADO_DAO());
            daos.put("PAET_DOMAIN.PaetEtEquipoTecnologico", new PAET_ET_EQUIPO_TECNOLOGICO_DAO());
            daos.put("PAET_DOMAIN.PaetEtSlEquipoSolicitud", new PAET_ET_SL_EQUIPO_SOLICITUD_DAO());
            daos.put("PAET_DOMAIN.PaetEvEvaluacion", new PAET_EV_EVALUACION_DAO());
            daos.put("PAET_DOMAIN.PaetFcFactorComplementario", new PAET_FC_FACTOR_COMPLEMENTARIO_DAO());
            daos.put("PAET_DOMAIN.PaetFcAnFactorAnalisis", new PAET_FC_AN_FACTOR_ANALISIS_DAO());
            daos.put("PAET_DOMAIN.PaetGrGerencia", new PAET_GR_GERENCIA_DAO());
            daos.put("PAET_DOMAIN.PaetMtMeta", new PAET_MT_META_DAO());
            daos.put("PAET_DOMAIN.PaetPgProcesoSeguimiento", new PAET_PG_PROCESO_SEGUIMIENTO_DAO());
            daos.put("PAET_DOMAIN.PaetPrProvincia", new PAET_PR_PROVINCIA_DAO());
            daos.put("PAET_DOMAIN.PaetPsProcesoSolicitud", new PAET_PS_PROCESO_SOLICITUD_DAO());
            daos.put("PAET_DOMAIN.PaetPtPuesto", new PAET_PT_PUESTO_DAO());
            daos.put("PAET_DOMAIN.PaetReRecomendacion", new PAET_RE_RECOMENDACION_DAO());
            daos.put("PAET_DOMAIN.PaetSlSolicitud", new PAET_SL_SOLICITUD_DAO());
            daos.put("PAET_DOMAIN.PaetTlTelefono", new PAET_TL_TELEFONO_DAO());
            daos.put("PAET_DOMAIN.PaetTrTrabajador", new PAET_TR_TRABAJADOR_DAO());
            daos.put("PAET_DOMAIN.PaetTtTeletrabajador", new PAET_TT_TELETRABAJADOR_DAO());   
    }

    public IBaseDAO getDao(String className) {
        return daos.get(className);
    }
}
