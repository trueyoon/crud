package com.sparta.crud.controller;

import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.dto.MemoResponseDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.sparta.crud.repository.MemoRepository;



import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final MemoRepository memoRepository;



    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    //게시글 작성
    @PostMapping("api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = memoService.createMemo(requestDto);
        return new MemoResponseDto(memo);
    }

    //전체 게시글 조회
    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

//    @PutMapping("/api/memos/{id}")
//    public Long updateMomo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
//        return memoService.update(id, requestDto);
//    }


//    @PutMapping("/api/memos/{id}")
//    public ResponseEntity<Long> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        String expectedPassword = "password";
//        if (!expectedPassword.equals(requestDto.getPassword())) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        Long updatedMemoId = memoService.update(id, requestDto);
//        return new ResponseEntity<>(pduatedMemoId, HttpStatus.OK);
//    }

    //게시글 수정
    @PutMapping("/api/memos/{id}")
    public ResponseEntity<Long> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElse(null);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!memo.getPassword().equals(requestDto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long updatedMemoId = memoService.update(id, requestDto);
        return new ResponseEntity<>(updatedMemoId, HttpStatus.OK);
    }


//    @DeleteMapping("/api/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id){
//        return memoService.deleteMemo(id);
//    }

    //선택한 게시글 삭제
    @DeleteMapping("/api/memos/{id}")
    public ResponseEntity<Long> deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        String password = memoRepository.findById(id).get().getPassword();
        if (!password.equals(requestDto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long deletedMemoId = memoService.deleteMemo(id);
        return new ResponseEntity<>(deletedMemoId, HttpStatus.OK);
    }



    //id값에 해당하는 게시글만 조회
    @GetMapping("/api/memos/{id}")
    public ResponseEntity<MemoResponseDto> getMemo(@PathVariable Long id) {
        Memo memo = memoService.getMemoById(id);
        return ResponseEntity.ok(new MemoResponseDto(memo));
    }
}
