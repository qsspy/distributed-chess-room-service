package com.qsspy.roomservice.enums;

public enum PlayerColor {
    BLACK, WHITE;

    public PlayerColor inverse() {
        return PlayerColor.BLACK == this ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
}
