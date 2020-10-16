package com.marveldextra.couchbase_repository.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.startupcheck.IndefiniteWaitOneShotStartupCheckStrategy;
import org.testcontainers.containers.startupcheck.OneShotStartupCheckStrategy;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public abstract class AbstractTestContainersBase extends Assertions {

    private static final String COUCHBASE_IMAGE_VERSION = "couchbase:community-6.6.0";

    protected static final DockerImageName
            COUCHBASE_IMAGE = new DockerImageName(COUCHBASE_IMAGE_VERSION);
    //protected static final int[] COUCHBASE_EXPOSED_PORTS = {11207, 11210, 11211};

    @ClassRule
    protected static GenericContainer<?> couchbaseContainer =
            new GenericContainer<>(COUCHBASE_IMAGE_VERSION)
                    .withExposedPorts(8091,8080,11207, 11210, 11211, 32825, 32826, 32827)
                    .waitingFor(Wait.forHttp("/")
                    .forPort(8091)
                    .forStatusCode(200));

}
