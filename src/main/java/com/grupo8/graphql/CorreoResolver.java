package com.grupo8.graphql;

import graphql.schema.DataFetcher;
import com.grupo8.dao.CorreoDao;
import com.grupo8.models.Correo;

import java.util.*;
import java.util.stream.Collectors;

public class CorreoResolver {

    private static final CorreoDao correoDao = new CorreoDao();

    public static DataFetcher<List<Map<String, Object>>> getCorreosByIdUsuarioFetcher() {
        return environment -> {
            String idUsuario = environment.getArgument("idUsuario");
            List<Correo> correos = correoDao.getCorreosByIdUsuario(idUsuario);

            return correos.stream().map(correo -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", correo.getId());
                map.put("subject", correo.getSubject());
                map.put("mensaje", correo.getMensaje());
                map.put("destinatarios", correo.getDestinatarios());
                map.put("idUsuario", correo.getIdUsuario());
                return map;
            }).collect(Collectors.toList());
        };
    }

    public static DataFetcher<String> insertCorreoFetcher() {
        return environment -> {
            Map<String, Object> input = environment.getArgument("input");

            Correo nuevoCorreo = new Correo();
            nuevoCorreo.setSubject((String) input.get("subject"));
            nuevoCorreo.setMensaje((String) input.get("mensaje"));
            nuevoCorreo.setDestinatarios((String) input.get("destinatarios"));
            nuevoCorreo.setIdUsuario((String) input.get("idUsuario"));

            correoDao.insertCorreo(nuevoCorreo);

            return "Correo insertado con éxito";
        };
    }
}