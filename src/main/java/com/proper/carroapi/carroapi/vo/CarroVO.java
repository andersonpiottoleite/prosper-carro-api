package com.proper.carroapi.carroapi.vo;

import lombok.Data;

@Data
public class CarroVO {

    private Long id;
    private String marca;
    private String modelo;
    private int quantidadeLocacoes;
}
