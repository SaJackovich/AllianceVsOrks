package com.alliance.config;

import org.testcontainers.containers.MariaDBContainer;

public class BaeldungMaraiDbContainer extends MariaDBContainer<BaeldungMaraiDbContainer> {
    private static final String IMAGE_VERSION = "mariadb";
    private static BaeldungMaraiDbContainer container;

    private BaeldungMaraiDbContainer() {
        super(IMAGE_VERSION);
    }

    public static BaeldungMaraiDbContainer getInstance() {
        if (container == null) {
            container = new BaeldungMaraiDbContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {

    }
}
