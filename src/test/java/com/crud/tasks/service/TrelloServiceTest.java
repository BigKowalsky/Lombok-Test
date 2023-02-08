package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloServiceTest {

    @Autowired
    TrelloService trelloService;

    @Test
    void createTrelloCardTest(){
        //GIVEN
        TrelloCardDto cardDto = new TrelloCardDto();
        cardDto.setPos("1");
        cardDto.setName("test_cardDto");
        cardDto.setDescription("test_description");
        cardDto.setListId("6353fdce0b089600c4e2efe7");
        //WHEN
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(cardDto);
        //THEN
        assertEquals(cardDto.getName(),createdCard.getName());
    }
}