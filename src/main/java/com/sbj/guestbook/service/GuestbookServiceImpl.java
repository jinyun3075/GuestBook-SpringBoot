package com.sbj.guestbook.service;

import com.sbj.guestbook.controller.dto.GuestbookDto;
import com.sbj.guestbook.controller.dto.PageRequestDto;
import com.sbj.guestbook.controller.dto.PageResultDto;
import com.sbj.guestbook.entity.Guestbook;
import com.sbj.guestbook.entity.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
    private final GuestbookRepository repository;
    @Override
    public Long register(GuestbookDto dto) {
        log.info("DTO--------------");
        log.info(dto);
        Guestbook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = repository.findAll(pageable);
        Function<Guestbook,GuestbookDto> fn =(entity->entityToDto(entity));

        return new PageResultDto<>(result,fn);
    }
}
