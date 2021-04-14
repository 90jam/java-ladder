package ladder.domain;

import ladder.util.RandomUtil;

public class FirstPointStrategy implements PointStrategy {

    public FirstPointStrategy() {
    }

    @Override
    public Direction direction() {
        if (RandomUtil.trueOrFalse()) {
            return Direction.RIGHT;
        }
        return Direction.NO_DIRECTION;
    }

    @Override
    public PointNew point() {
        return new PointNew(0, new DirectionNew(false, RandomUtil.trueOrFalse()));
    }
}
