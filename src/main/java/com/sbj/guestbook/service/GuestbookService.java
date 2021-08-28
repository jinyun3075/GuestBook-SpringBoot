package com.sbj.guestbook.service;

import com.sbj.guestbook.controller.dto.GuestbookDto;
import com.sbj.guestbook.controller.dto.PageRequestDto;
import com.sbj.guestbook.controller.dto.PageResultDto;
import com.sbj.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDto dto);

    PageResultDto<GuestbookDto,Guestbook> getList(PageRequestDto requestDto);

    default GuestbookDto entityToDto(Guestbook entity){
        GuestbookDto dto = GuestbookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

    default Guestbook dtoToEntity(GuestbookDto dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}
