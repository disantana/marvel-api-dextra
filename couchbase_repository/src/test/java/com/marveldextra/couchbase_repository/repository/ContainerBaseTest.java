package com.marveldextra.couchbase_repository.repository;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.Network;

import java.util.Arrays;

@SpringBootTest

public class ContainerBaseTest extends AbstractTestContainersBase {

    @BeforeAll
    static void startup() {


        String[] portBinds = {"11210"};
        couchbaseContainer.setPortBindings(Arrays.asList(portBinds));
        Testcontainers.exposeHostPorts(8091,11210);
        couchbaseContainer.start();
        System.setProperty("ip",couchbaseContainer.getContainerInfo().getNetworkSettings().getIpAddress());
        }

    @Test
    void shouldTest(){
        assertTrue(couchbaseContainer.isCreated());
        assertTrue(couchbaseContainer.isRunning());
    }
}
