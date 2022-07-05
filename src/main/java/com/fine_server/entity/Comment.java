package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fine_server.entity.comment.CommentDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    @JsonProperty(value = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    @JsonIgnore
    private Posting posting;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Comment parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Comment> child = new ArrayList<Comment>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
        posting.getComments().add(this);
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public void updateText(String text) {
        this.text = text;
    }

    public void addChildComment(Comment child) {
        this.child.add(child);
        this.setParent(this);
    }
}
