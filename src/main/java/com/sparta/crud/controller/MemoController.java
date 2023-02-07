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

    @PostMapping("api/memos") //post 방식이라 body가 존재하고 그 body 안에 우리가 원하는 값이 있기 때문에
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

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
//        return new ResponseEntity<>(updatedMemoId, HttpStatus.OK);
//    }
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



    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }



    //id값에 해당하는 게시글만 출력됨 .
    @GetMapping("/api/memos/{id}")
    public ResponseEntity<MemoResponseDto> getMemo(@PathVariable Long id) {
        Memo memo = memoService.getMemoById(id);
        return ResponseEntity.ok(new MemoResponseDto(memo));
    }
}
