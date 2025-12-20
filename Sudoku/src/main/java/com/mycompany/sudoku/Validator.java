package com.mycompany.sudoku;

import java.util.List;

public interface Validator {
boolean isValid();
List<String> getValidationErrors();
}
