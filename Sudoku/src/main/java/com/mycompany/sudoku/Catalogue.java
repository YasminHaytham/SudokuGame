package com.mycompany.sudoku;

import java.nio.file.Path;

public class Catalogue {
    private Path baseDir;

    public Catalogue() {
        this.baseDir = Path.of("games");
    }
    public Catalogue(Path baseDir) {
        this.baseDir = baseDir;
    }

    public void setPath (String newBaseDir) {
        this.baseDir = Path.of(newBaseDir);
    }
    



}
