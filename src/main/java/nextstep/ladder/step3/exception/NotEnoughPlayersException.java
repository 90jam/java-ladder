package nextstep.ladder.step3.exception;

public class NotEnoughPlayersException extends IllegalArgumentException {

    private static final String MESSAGE = "사다리 게임은 %d명 이상 있어야만 할 수 있습니다.";

    public NotEnoughPlayersException(int playerMinimumSize) {
        super(String.format(MESSAGE, playerMinimumSize));
    }

}