package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //GIVEN
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List number 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "List number 2", false);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "Board To DO", Arrays.asList(trelloListDto1));
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Board DONE", Arrays.asList(trelloListDto2));
        List<TrelloBoardDto> trelloBoardsDto = Arrays.asList(trelloBoardDto1, trelloBoardDto2);
        //WHEN
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //THEN
        assertEquals(2, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Board DONE", trelloBoards.get(1).getName());
        //CLEANUP
        trelloBoards.clear();
    }

    @Test
    public void mapToBoardsDtoTest() {
        //GIVEN
        TrelloList trelloList1 = new TrelloList("1", "List number 1", false);
        TrelloList trelloList2 = new TrelloList("2", "List number 2", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Board To DO", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Board DONE", trelloLists);
        List<TrelloBoard> trelloBoards = Arrays.asList(trelloBoard1, trelloBoard2);
        //WHEN
        List<TrelloBoardDto> trelloBoardsDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        //THEN
        assertEquals(2, trelloBoardsDtoList.size());
        assertEquals("1", trelloBoardsDtoList.get(0).getId());
        assertEquals("Board DONE", trelloBoardsDtoList.get(1).getName());
        //CLEANUP
        trelloBoardsDtoList.clear();
    }

    @Test
    void mapToListTest() {
        // GIVEN
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List number 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "List number 2", false);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);
        // WHEN
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        // THEN
        assertEquals(2, trelloLists.size());
        assertEquals("List number 1", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(1).isClosed());
        //CLEANUP
        trelloLists.clear();
    }

    @Test
    void mapToListDtoTest() {
        // GIVEN
        TrelloList trelloList1 = new TrelloList("1", "List number 1", false);
        TrelloList trelloList2 = new TrelloList("2", "List number 2", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        // WHEN
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        // THEN
        assertEquals(2, trelloListsDto.size());
        assertEquals("List number 1", trelloListsDto.get(0).getName());
        assertTrue(trelloListsDto.get(1).isClosed());
        //CLEANUP
        trelloListsDto.clear();
    }

    @Test
    void mapToCardTest() {
        // GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "Description 1", "Pos 1", "100");
        // WHEN
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        // THEN
        assertEquals("Card 1", trelloCard.getName());
        assertEquals("Description 1", trelloCard.getDescription());

    }

    @Test
    void mapToCardDtoTest() {
        // GIVEN
        TrelloCard trelloCard = new TrelloCard("Card 1", "Description 1", "Pos 1", "100");
        // WHEN
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        // THEN
        assertEquals("Card 1", trelloCardDto.getName());
        assertEquals("Description 1", trelloCardDto.getDescription());
    }
}
