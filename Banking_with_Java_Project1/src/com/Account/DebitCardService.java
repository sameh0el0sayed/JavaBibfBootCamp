package com.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DebitCardService implements IDebitCardService {

    private List<DebitCard> debitCards;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath = "src/com/data/debit_cards.json";

    public DebitCardService() {
        this.debitCards = new ArrayList<>();
        loadCards();
    }

    private void loadCards() {
        try {
            DebitCard[] cardsArray = mapper.readValue(new File(filePath), DebitCard[].class);
            for (DebitCard card : cardsArray) {
                debitCards.add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DebitCard> getAllCards() {
        return debitCards;
    }

    public DebitCard getCardByType(String type) {
        return debitCards.stream()
                .filter(card -> card.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }

    public void saveCards() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), debitCards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
