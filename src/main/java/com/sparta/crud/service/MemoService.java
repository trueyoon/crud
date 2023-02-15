package com.sparta.crud.service;


import com.sparta.crud.dto.DeleteMemoResponseDto;
import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.dto.MemoResponseDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.entity.User;
import com.sparta.crud.entity.UserRoleEnum;
import com.sparta.crud.jwt.JwtUtil;
import com.sparta.crud.repository.MemoRepository;
import com.sparta.crud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


/*    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }*/

    //    @Transactional
//    public Memo createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
//        // Request에서 Token 가져오기
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//        if (token != null) {
//            // Token 검증
//            if (jwtUtil.validateToken(token)) {
//                // 토큰에서 사용자 정보 가져오기
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
//                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//            );
//
//            Memo memo = new Memo(requestDto);
//            memoRepository.save(memo);
//            return memo;
//        } else {
//            return null;
//        }
//    }
    @Transactional
    public Memo createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
        // Import Token from Request
        String token = jwtUtil.resolveToken(request);
        Claims claims;


        if (token != null) {
            // Token verification
            if (jwtUtil.validateToken(token)) {
                // Get user information from token
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("User does not exist")
            );

            Memo memo = new Memo(requestDto);
            memo.setUser(user);
            memoRepository.save(memo);
            return memo;
        } else {
            throw new IllegalArgumentException("Token Error");
        }
    }


//    @Transactional(readOnly = true)
//    public List<Memo> getMemos() {
//        return memoRepository.findAllByOrderByModifiedAtDesc();
//    }

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
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            if (memo.getUser().getId() == user.getId() || user.getRole().equals(UserRoleEnum.ADMIN)) {
                memo.update(requestDto);
                return new MemoResponseDto(memo);
            }else {
                throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 수정할 수 없습니다!");
            }
//
//        Memo memo = memoRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );  //db에 내가 수정할 메모가 있는지 확인



        }
        return null;

    }

    @Transactional
    public DeleteMemoResponseDto deleteMemo(Long id, MemoRequestDto memoRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글 없음")
        );

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            if (memo.getUser().getId() == user.getId() || user.getRole().equals(UserRoleEnum.ADMIN)) {
                memoRepository.deleteById(id);
                DeleteMemoResponseDto deleteMemoResponseDto =  new DeleteMemoResponseDto("success", HttpStatus.OK.value());
                //return id;
                return deleteMemoResponseDto;
            }else {
                throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");
            }
        } else {
            return null;
        }
    }
}