package com.realworld.springmongo.article.dto;

import com.realworld.springmongo.article.Tag;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagListView {
    List<String> tags;

    public static TagListView of(List<Tag> tags) {
        var rowTags = tags.stream().map(Tag::getTagName).collect(Collectors.toList());
        return new TagListView()
                .setTags(rowTags);
    }
}
