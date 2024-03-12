package com.eletronicpoint.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PointRegister {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); // formantando horario
    private Integer idPoint;    //identificador unico
    private LocalDateTime localDateTime;    //data e horario
    private String type; // entrada ou saída
    private String justification; // justificativa

    public PointRegister() {

    }
    public PointRegister(LocalDateTime localDateTime, String type, String justification) {
        this.localDateTime = localDateTime;
        this.type = type;
        this.justification = justification;
    }


    // um construtor para caso haja justificativa de atraso por exemplo.
    public PointRegister(Integer idPoint, LocalDateTime localDateTime, String type, String justification) {
        this.idPoint = idPoint;
        this.localDateTime = localDateTime;
        this.type = type;
        this.justification = justification;
    }


    // aqui a principípio existe a possibilidade de mudar tudo
    public Integer getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString (){
        return "id:" + this.idPoint +
                "\n data/Hora:" + this.localDateTime.format(formatter) +
                "\n tipo:" + this.type +
                "\n justificativa:" + this.justification;
    }
}
