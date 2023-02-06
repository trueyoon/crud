package com.sparta.crud.controller;

import com.sparta.crud.dto.MemoRequestDto;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("api/memos") //post 방식이라 body가 존재하고 그 body 안에 우리가 원하는 값이 있기 때문에
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMomo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }
}
