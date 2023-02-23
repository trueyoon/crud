package com.sparta.crud.service;


import com.sparta.crud.dto.DeleteMemoResponseDto;
import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.dto.MemoResponseDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.entity.MemoLike;
import com.sparta.crud.entity.User;
import com.sparta.crud.entity.UserRoleEnum;
import com.sparta.crud.jwt.JwtUtil;
import com.sparta.crud.repository.MemoLikeRepository;
import com.sparta.crud.repository.MemoRepository;
import com.sparta.crud.repository.UserRepository;
import com.sparta.crud.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemoLikeRepository memoLikeRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto, User user) {
        Memo memo = new Memo(requestDto, user);
        memoRepository.save(memo);
        return new MemoResponseDto(memo);
    }


    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> memos = memoRepository.findAllByOrderByCreatedAtDesc();
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for (Memo memo : memos) {
            memoResponseDtos.add(new MemoResponseDto(memo));
        }
        return memoResponseDtos;
    }

    @Transactional(readOnly = true)
    public Memo getMemoById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Memo not found with id: " + id));
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (user.getRole() == UserRoleEnum.ADMIN || user.getUsername().equals(memo.getUser().getUsername())) {
            memo.update(requestDto);
            // return StatusResponseDto.success(new requestDto(memo));
            return new MemoResponseDto(memo);
        } else {
            throw new IllegalArgumentException("작성자만 수정가능합니다.");
        }

    }

    @Transactional
    public DeleteMemoResponseDto deleteMemo(Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (user.getRole() == UserRoleEnum.ADMIN || user.getUsername().equals(memo.getUser().getUsername())) {
            memoRepository.deleteById(id);
            DeleteMemoResponseDto deleteMemoResponseDto = new DeleteMemoResponseDto("success", HttpStatus.OK.value());
            //return id;
            return deleteMemoResponseDto;
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");
        }
    }

    @Transactional
    public MemoResponseDto likeMemo(Long id, UserDetailsImpl userDetails){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글 없음")
        );
        Optional<MemoLike> optionalMemoLike = memoLikeRepository.findByMemoAndUser(memo, userDetails.getUser());
        if (optionalMemoLike.isPresent()){
            memoLikeRepository.deleteById(optionalMemoLike.get().getId());
//            long l = memo.getLikeCount() - 1;
//            memoLikeRepository.save(memo);
            memo.setLikeCount(memo.getLikeCount()-1);
            return new MemoResponseDto(memo);
        }

        memoLikeRepository.save(new MemoLike(memo, userDetails.getUser()));
        memo.setLikeCount(memo.getLikeCount()+1);
        return new MemoResponseDto(memo);
    }
}