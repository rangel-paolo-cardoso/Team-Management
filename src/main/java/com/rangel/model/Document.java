package com.rangel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    private String workCardNumber;
    private String cbfNumber;

    public Document() {}

    public Document(Integer id, String cpf, String workCardNumber, String cbfNumber) {
        this.id = id;
        this.cpf = cpf;
        this.workCardNumber = workCardNumber;
        this.cbfNumber = cbfNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getWorkCardNumber() {
        return workCardNumber;
    }

    public void setWorkCardNumber(String workCardNumber) {
        this.workCardNumber = workCardNumber;
    }

    public String getCbfNumber() {
        return cbfNumber;
    }

    public void setCbfNumber(String cbfNumber) {
        this.cbfNumber = cbfNumber;
    }
}
