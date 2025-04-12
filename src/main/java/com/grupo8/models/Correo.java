package com.grupo8.models;

public class Correo {
    private Integer id;
    private String subject;
    private String mensaje;
    private String destinatarios;
    private String idUsuario;

    public Correo() {
        this.id = 0;
        this.subject = "";
        this.mensaje = "";
        this.destinatarios = "";
        this.idUsuario = "";
    }
    
    public Correo(Integer id, String subject, String mensaje, String destinatarios, String idUsuario) {
        this.id = id;
        this.subject = subject;
        this.mensaje = mensaje;
        this.destinatarios = destinatarios;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getDestinatarios() {
        return destinatarios;
    }
    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
