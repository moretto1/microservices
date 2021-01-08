package com.hrworker.resources;

import com.hrworker.entities.Worker;
import com.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    //@Value("${test.config}")
    //private String testeConfig;

    @Autowired
    private Environment env;

    @Autowired
    WorkerRepository workerRepository;

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        //logger.info("CONFIG = " + testeConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workerList = workerRepository.findAll();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        logger.info("PORT = " + env.getProperty("local.server.port"));

        Worker worker = workerRepository.findById(id).get();
        return ResponseEntity.ok(worker);
    }

}
