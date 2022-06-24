package com.fine_server.entity.bookmark;

import com.fine_server.entity.Bookmark;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkDto {

    private Long memberId;
    private Long postingId;

    public Bookmark toEntity() {
        return Bookmark.builder().build();
    }
}
