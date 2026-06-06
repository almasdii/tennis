package TableTennis.validator;

import TableTennis.Exception.BadRequestException;
import TableTennis.Exception.MatchNotFoundException;
import TableTennis.Exception.PlayerSearchValidationException;
import TableTennis.Exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchValidator {
    public void validateNames(String firstPlayerName, String secondPlayerName) {
        List<String> errors = new ArrayList<>();
        if (firstPlayerName == null || secondPlayerName == null) {
            throw new BadRequestException("Names cannot be null");
        }
        if(firstPlayerName.isEmpty() || secondPlayerName.isEmpty()){
            errors.add("names cannot be empty");
        }

        if(!firstPlayerName.matches("^[A-Z]{1}[a-z]+")){
            errors.add("first player name : name should start from capital letter");
        }
        if(!secondPlayerName.matches("^[A-Z]{1}[a-z]+")){
            errors.add("second player name : name should start from capital letter");
        }

        if (!firstPlayerName.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
            errors.add("first player name : allowed letters a-b A-Z а-я А-Я ёЁ");
        }

        if (!secondPlayerName.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
            errors.add("second player name : allowed letters a-b A-Z а-я А-Я ёЁ");
        }

        if (firstPlayerName.equalsIgnoreCase(secondPlayerName)) {
            errors.add("must be different");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join("\n ", errors));
        }
    }
    public void validateFilterName(String name){
        List<String> errors = new ArrayList<>();
        if(name == null){
            return;
        }
        if(name.length() > 20){
            errors.add("name max size is 20");
        }
        if (!errors.isEmpty()) {
            throw new PlayerSearchValidationException(String.join("\n", errors));
        }

    }
    public void validatePage(int page) {
        if (page < 0) {
            throw new BadRequestException("Page cannot be negative");
        }

    }
}
