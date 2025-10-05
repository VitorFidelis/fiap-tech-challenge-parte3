package com.agendio_api.agendamento.api.routes;

public class RotasConsulta {
    private RotasConsulta() {
    }


    public static final String CONSULTA = "/consultas";
    public static final String ID = "/{id}";
    public static final String CANCELAR = ID + "/cancelar";
    public static final String TODAS = "/todas";
    public static final String CONSULTA_COM_ID = CONSULTA + ID;
    public static final String POR_MEDICO = "/medicos/{idMedico}";
    public static final String POR_PACIENTE = "/pacientes/{idPaciente}";
    public static final String POR_ENFERMEIRO = "/enfermeiros/{idEnfermeiro}";
    private static final String POR_PERIODO = "/periodo";
    public static final String POR_MEDICO_E_PERIODO = POR_MEDICO + POR_PERIODO;

}
