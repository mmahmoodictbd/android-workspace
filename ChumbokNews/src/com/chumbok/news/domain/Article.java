package com.chumbok.news.domain;

import java.util.Date;
import java.util.List;

import com.orm.SugarRecord;

public class Article extends SugarRecord<Article> {

	private Long id;

	private long sourceId;
	private String sourceText;
	private String sourceUrl;
	private String sourceImageUrl;

	private String title;
	private String desc;
	private String imageUrl;

	private List<String> tags;

	private Date createdDate;
	private Date updatedDate;
	private Date publishDate;

	// SugerORM suggested
	public Article() {
	}

}
