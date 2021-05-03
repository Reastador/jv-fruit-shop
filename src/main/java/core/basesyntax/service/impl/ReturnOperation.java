package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.Fruit;
import core.basesyntax.model.dto.FruitRecordDto;
import core.basesyntax.service.FruitOperationHandler;

public class ReturnOperation implements FruitOperationHandler {
    @Override
    public int apply(FruitRecordDto fruitRecordDto) {
        Fruit fruit = new Fruit(fruitRecordDto.getFruitName());
        Integer currentQuantity = Storage.fruits.get(fruit);
        int returnAmount = fruitRecordDto.getQuantity();
        if (returnAmount < 0) {
            throw new RuntimeException("Return amount can't be less than 0");
        }
        int newQuantity = currentQuantity + returnAmount;
        Storage.fruits.put(fruit, newQuantity);
        return newQuantity;
    }

    @Override
    public String getOperationType() {
        return "r";
    }
}