package com.fine_server.controller.Posting;

import com.fine_server.Service.Posting.BookmarkService;
import com.fine_server.entity.Bookmark;
import com.fine_server.entity.bookmark.BookmarkDto;
import com.fine_server.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // 북마크 생성
    @PostMapping("/bookmark")
    public Bookmark creatBookmark(@RequestBody BookmarkDto bookmarkDto) {
        Bookmark bookmark = bookmarkService.makeBookmark(bookmarkDto);
        return bookmark;
    }

    // 북마크 제거
    @DeleteMapping("/bookmark/{bookmarkId}")
    public Long deleteBookmark(@PathVariable Long bookmarkId) {
        Long deleteBookmark = bookmarkService.deleteBookmark(bookmarkId);
        return deleteBookmark;
    }
}
