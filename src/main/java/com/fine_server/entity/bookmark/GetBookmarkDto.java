package com.fine_server.entity.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookmarkDto {

    Long bookmarkId;

    Long memberId;

    public GetBookmarkDto(Long bookmarkId, Long memberId) {
        this.bookmarkId = bookmarkId;
        this.memberId = memberId;
    }
}
