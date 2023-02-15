package com.sparta.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteMemoResponseDto {
    //    private boolean success;
//    private Long deletedMemoId;
    @Column(nullable = false)
    private String msg;
    private int statusCode;

//    public DeleteMemoResponseDto(boolean success, Long deletedMemoId) {
//        this.success = success;
//        this.deletedMemoId = deletedMemoId;
//    }

}
