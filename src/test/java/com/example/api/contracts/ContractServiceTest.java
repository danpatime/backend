package com.example.api.contracts;

import com.example.api.JpaTestWithInitData;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.domain.ChatRoom;
import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@JpaTestWithInitData
class ContractServiceTest{
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Test
    @DisplayName("요청 수락 및 채팅방 생성")
    void shouldAcceptSuggestAndCreateChatRoom() {
        AcceptSuggestCommand acceptSuggestCommand = new AcceptSuggestCommand(1L);

        // 요청 수락
        final OfferEmployment offerEmployment = loadOffer(acceptSuggestCommand.suggestId());
        offerEmployment.succeeded();
        // 채팅방 생성
        createChatRoom(offerEmployment);

        OfferEmployment findOfferEmployment = offerRepository.findById(1L)
                .orElseThrow(() -> new AssertionError("요청이 존재하지 않습니다."));

        assertThat(findOfferEmployment.isSuggestSucceeded())
                .as("요청 수락으로 변경")
                .isTrue();

        // Assert: 채팅방 생성 여부 검증
        assertThat(chatRoomRepository.findById(1L))
                .as("채팅방이 존재하지 않음")
                .isPresent();
    }

    private OfferEmployment loadOffer(final Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow();
    }

    private void createChatRoom(final OfferEmployment offer) {
        ChatRoom chatRoom = new ChatRoom(offer);
        chatRoomRepository.save(chatRoom);
    }


}