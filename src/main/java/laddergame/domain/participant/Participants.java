package laddergame.domain.participant;

import laddergame.exception.LadderGameException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Participants {

	private static final int MIN_PARTICIPANT_COUNT = 2;
	private static final String PARTICIPANT_SHOULD_EQUAL_OR_OVER_N = "참가자 수는 %d명 이상이어야 합니다.";
	private static final String PLEASE_INPUT_NOT_DUPLICATE_PARTICIPANT = "중복되지 않은 참가자를 입력해주세요.";
	private static final String PLEASE_INPUT_PARTICIPANT_NAME = "게임 참가자의 이름을 입력해주세요.";

	private final Map<String, Participant> participants;

	public Participants(List<Participant> participants) {
		this.participants = createParticipants(participants);
	}

	public int getParticipantsCount() {
		return participants.size();
	}

	public Collection<Participant> getParticipants() {
		return participants.values();
	}

	public Participant getCoordinateByName(String name) {
		return Optional.ofNullable(participants.get(name))
						.orElseThrow(() -> new LadderGameException(PLEASE_INPUT_PARTICIPANT_NAME));
	}

	private Map<String, Participant> createParticipants(List<Participant> participants) {
		if(participants.size() < MIN_PARTICIPANT_COUNT) {
			throw new LadderGameException(String.format(PARTICIPANT_SHOULD_EQUAL_OR_OVER_N, MIN_PARTICIPANT_COUNT));
		}

		try {
			return participants.stream()
						.collect(Collectors.toMap(Participant::getName, Function.identity(), (participant, participant2) -> participant, LinkedHashMap::new));
		} catch (IllegalStateException e) {
			throw new LadderGameException(PLEASE_INPUT_NOT_DUPLICATE_PARTICIPANT, e);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participants that = (Participants) o;
		return participants.equals(that.participants);
	}

	@Override
	public int hashCode() {
		return Objects.hash(participants);
	}
}