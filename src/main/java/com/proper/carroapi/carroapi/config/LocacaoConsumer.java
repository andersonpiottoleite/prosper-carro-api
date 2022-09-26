package com.proper.carroapi.carroapi.config;

import com.google.gson.Gson;
import com.proper.carroapi.carroapi.dto.CarroAlugadoDTO;
import com.proper.carroapi.carroapi.exception.CarroException;
import com.proper.carroapi.carroapi.exception.CarroNotFoundException;
import com.proper.carroapi.carroapi.model.Carro;
import com.proper.carroapi.carroapi.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LocacaoConsumer {

    private static Logger LOGGER = LoggerFactory.getLogger(LocacaoConsumer.class);

    private List<CarroAlugadoDTO> persons = new ArrayList<>();
    private final CarroRepository carroRepository;

    @JmsListener( destination = "${activemq.name}" )
    public void listen(String mensagem) {
        LOGGER.info("Consumindo mensagem: " + mensagem);
        try {
            Gson gson = new Gson();
            CarroAlugadoDTO carroAlugadoDTO = gson.fromJson(mensagem, CarroAlugadoDTO.class);
            Carro carro = carroRepository.findById(carroAlugadoDTO.getId()).orElseThrow(() -> new CarroNotFoundException("Carro não encontrado com o id " + carroAlugadoDTO.getId()));
            LOGGER.info("Incrementando quantidade de locacações para o carro de id  " + carroAlugadoDTO.getId());
            carro.incrementaQuantidadeLocacoes();
            carroRepository.save(carro);
        }catch(Exception e){
            throw new CarroException("Ocorreu um erro ao atualizar o status do carro relacionado a mensagem" + mensagem);
        }
    }
}
