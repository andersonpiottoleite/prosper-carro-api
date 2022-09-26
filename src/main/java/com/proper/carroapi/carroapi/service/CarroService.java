package com.proper.carroapi.carroapi.service;

import com.proper.carroapi.carroapi.dto.CarroAlugadoDTO;
import com.proper.carroapi.carroapi.dto.CarroDTO;
import com.proper.carroapi.carroapi.exception.CarroNotFoundException;
import com.proper.carroapi.carroapi.model.Carro;
import com.proper.carroapi.carroapi.repository.CarroRepository;
import com.proper.carroapi.carroapi.vo.CarroVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {


    private static Logger LOGGER = LoggerFactory.getLogger(CarroService.class);

    private CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public void save(CarroDTO carroDTO) {
        Carro carro = new Carro();
        BeanUtils.copyProperties(carroDTO, carro);
        carroRepository.save(carro);
    }

    public CarroVO findById(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new CarroNotFoundException("Carro não encontrado com o id " + id));
        CarroVO carroVO = new CarroVO();
        BeanUtils.copyProperties(carro, carroVO);
        return carroVO;
    }

    public void incrementaQuantidadeLocacao(CarroAlugadoDTO carroAlugadoDTO) {
        LOGGER.info("Incrementando locação para carro de id  "+ carroAlugadoDTO.getId());
        Carro carro = carroRepository.findById(carroAlugadoDTO.getId())
                        .orElseThrow( () -> new CarroNotFoundException("Carro não encontrado com o id " + carroAlugadoDTO.getId()));
        carro.incrementaQuantidadeLocacoes();
        carroRepository.save(carro);
    }
}
