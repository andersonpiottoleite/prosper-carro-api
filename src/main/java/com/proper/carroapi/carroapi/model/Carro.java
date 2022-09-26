package com.proper.carroapi.carroapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private int quantidadeLocacoes;

    public void incrementaQuantidadeLocacoes(){
        this.quantidadeLocacoes++;
    }
}
