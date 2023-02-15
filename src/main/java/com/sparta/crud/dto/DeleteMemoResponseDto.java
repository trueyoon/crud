package com.sparta.crud.dto;

public class DeleteMemoResponseDto {
    private boolean success;
    private Long deletedMemoId;

    public DeleteMemoResponseDto(boolean success, Long deletedMemoId) {
        this.success = success;
        this.deletedMemoId = deletedMemoId;
    }
}
