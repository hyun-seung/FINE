package com.fine_server.service.posting;

import com.fine_server.entity.Bookmark;
import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.bookmark.BookmarkDto;
import com.fine_server.repository.BookmarkRepository;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;
    private final BookmarkRepository bookmarkRepository;

    public Bookmark makeBookmark(BookmarkDto bookmarkDto) {
        Optional<Member> member = memberRepository.findById(bookmarkDto.getMemberId());
        Optional<Posting> posting = postingRepository.findById(bookmarkDto.getPostingId());
        Bookmark save = bookmarkRepository.save(bookmarkDto.toEntity());
        save.setMember(member.get());
        save.setPosting(posting.get());
        return save;
    }

    public Long deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
        return bookmarkId;
    }


}
