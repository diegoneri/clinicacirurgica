/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import br.com.cirurgica.model.Telefone;

/**
 *
 * @author dfelix3
 */
public class Fornecedor {
    private Integer codigo;
    private String razaoSocial;
    private String CNPJ;
    private String endereco;
    private String numeroEndereco;
    private String complemento;
    private String bairro;
    private String CEP;
    private Telefone telefoneUm;
    private Telefone telefoneDois;
    private Telefone celularUm;
    private Telefone celularDois;
    private String email;
    private String nomeFantasia;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Telefone getTelefoneUm() {
        return telefoneUm;
    }

    public void setTelefoneUm(Telefone telefoneUm) {
        this.telefoneUm = telefoneUm;
    }

    public Telefone getTelefoneDois() {
        return telefoneDois;
    }

    public void setTelefoneDois(Telefone telefoneDois) {
        this.telefoneDois = telefoneDois;
    }

    public Telefone getCelularUm() {
        return celularUm;
    }

    public void setCelularUm(Telefone celularUm) {
        this.celularUm = celularUm;
    }

    public Telefone getCelularDois() {
        return celularDois;
    }

    public void setCelularDois(Telefone celularDois) {
        this.celularDois = celularDois;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
