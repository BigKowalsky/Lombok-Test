package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void trelloConfigTest() {
        assertEquals("tomekkowalski89", trelloConfig.getTrelloUsername());
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
        assertEquals("16713a9293b2316c40b1ddb5e2c73525", trelloConfig.getTrelloAppKey());
        assertEquals("2e2c6724cab6a3073c90fd2c5752393ac09909d4253fecde56f0a3f4d826a831", trelloConfig.getTrelloToken());
    }
}