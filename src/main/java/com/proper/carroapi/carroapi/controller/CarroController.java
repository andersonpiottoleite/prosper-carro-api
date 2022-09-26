package com.proper.carroapi.carroapi.controller;

import com.proper.carroapi.carroapi.dto.CarroAlugadoDTO;
import com.proper.carroapi.carroapi.dto.CarroDTO;
import com.proper.carroapi.carroapi.service.CarroService;
import com.proper.carroapi.carroapi.vo.CarroVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<?> saveCarro(@RequestBody CarroDTO carroDTO){
        carroService.save(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<?> incrementaQuantidadeLocacao(@RequestBody CarroAlugadoDTO carroAlugadoDTO){
        carroService.incrementaQuantidadeLocacao(carroAlugadoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarroVO> findById(@PathVariable("id") Long id){
        CarroVO carroVO = carroService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carroVO);
    }
}
