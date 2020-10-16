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
        String ip = couchbaseContainer.getContainerInfo().getNetworkSettings().getIpAddress();
        System.setProperty("ip",ip);

        couchbaseContainer.setCommand("couchbase-cli cluster-init -c " + ip +" --cluster-username Administrator " +
                " --cluster-password 123456 --services data --cluster-ramsize 4096");

        couchbaseContainer.setCommand("couchbase-cli user-manage -c " + ip + ":8091 -u Administrator " +
                " -p password --set --rbac-username Administrator --rbac-password 123456 " +
                " --rbac-name Administrator --roles bucket_admin[default],replication_admin " +
                " --auth-domain local");
        couchbaseContainer.getImage();

    }

    @Test
    void shouldTest(){
        assertTrue(couchbaseContainer.isCreated());
        assertTrue(couchbaseContainer.isRunning());
    }
}
