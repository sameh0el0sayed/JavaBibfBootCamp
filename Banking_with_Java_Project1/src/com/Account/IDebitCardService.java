package com.Account;

import java.util.List;

public interface IDebitCardService {

    // Get all debit cards
    List<DebitCard> getAllCards();

    // Get a debit card by its type
    DebitCard getCardByType(String type);

    // Save changes to the JSON file
    void saveCards();
}
