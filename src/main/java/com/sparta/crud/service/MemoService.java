package com.sparta.crud.service;


import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.dto.MemoResponseDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

/*    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }*/

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

//    @Transactional(readOnly = true)
//    public List<Memo> getMemos() {
//        return memoRepository.findAllByOrderByModifiedAtDesc();
//    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for (Memo memo : memos) {
            memoResponseDtos.add(new MemoResponseDto(memo));
        }
        return memoResponseDtos;
    }

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );  //db에 내가 수정할 메모가 있는지 확인
        memo.update(requestDto);
        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}