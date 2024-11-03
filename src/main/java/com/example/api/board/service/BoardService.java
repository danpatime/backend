package com.example.api.board.service;

import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public MyInfoDTO findMyInfoById(long id) {
        return boardRepository.findMyInfoDTOById(id);
    }
}
