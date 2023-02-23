package com.sparta.crud.controller;

import com.sparta.crud.dto.DeleteMemoResponseDto;
import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.dto.MemoResponseDto;
import com.sparta.crud.dto.SignupResponseDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.repository.UserRepository;
import com.sparta.crud.security.UserDetailsImpl;
import com.sparta.crud.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.sparta.crud.repository.MemoRepository;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;



    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    //게시글 작성
    @PostMapping("api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){//HttpServletRequest request) {
        Memo memo = memoService.createMemo(requestDto, userDetails);
        return new MemoResponseDto(memo);
    }

    //전체 게시글 조회
    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }



    //게시글 수정
    @PutMapping("/api/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        Memo memo = memoRepository.findById(id).orElse(null);
//        if (memo == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
////        if (!memo.getPassword().equals(requestDto.getPassword())) {
////            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////        }
//        Long updatedMemoId = memoService.update(id, requestDto, request);
//        //return new ResponseEntity<>(updatedMemoId, HttpStatus.OK);
//        memo = memoService.getMemoById(id);
        //return ResponseEntity.ok(new MemoResponseDto(memo));
        return memoService.update(id, requestDto, userDetails);
    }


//    @DeleteMapping("/api/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id){
//        return memoService.deleteMemo(id);
//    }

    //선택한 게시글 삭제
    @DeleteMapping("/api/memos/{id}")
    public DeleteMemoResponseDto deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id, userDetails);
    }

    //id값에 해당하는 게시글만 조회
    @GetMapping("/api/memos/{id}")
    public ResponseEntity<MemoResponseDto> getMemo(@PathVariable Long id) {
        Memo memo = memoService.getMemoById(id);
        return ResponseEntity.ok(new MemoResponseDto(memo));
    }

    @PostMapping("api/memos/like/{id}")
    public MemoResponseDto likeMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.likeMemo(id, userDetails);
    }
}
