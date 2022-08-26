package com.miro.ui.domain;

import java.util.Objects;

public class Board {
    private String title;
    private String description;

    public Board() {
    }

    public Board(String name, String description) {
        this.title = name;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Board setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Board setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        if (!title.equals(board.title)) return false;
        return Objects.equals(description, board.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}