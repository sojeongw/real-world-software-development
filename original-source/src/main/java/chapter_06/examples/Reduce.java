package chapter_06.examples;

import static chapter_06.Position.INITIAL_POSITION;
import static java.util.Comparator.comparingInt;
import static java.util.function.BinaryOperator.maxBy;

import chapter_06.Position;
import chapter_06.Twoot;
import java.util.List;
import java.util.function.BinaryOperator;


public class Reduce {

    // tag::ReduceExample[]
    private final BinaryOperator<Position> maxPosition = maxBy(comparingInt(Position::getValue));

    Twoot combineTwootsBy(final List<Twoot> twoots, final String senderId, final String newId) {
        return twoots
            .stream()
            .reduce(
                new Twoot(newId, senderId, "", INITIAL_POSITION),
                (acc, twoot) -> new Twoot(
                    newId,
                    senderId,
                    twoot.getContent() + acc.getContent(),
                    maxPosition.apply(acc.getPosition(), twoot.getPosition())));
    }
    // end::ReduceExample[]
}
