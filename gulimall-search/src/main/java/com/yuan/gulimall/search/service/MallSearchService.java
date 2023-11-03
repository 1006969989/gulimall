package com.yuan.gulimall.search.service;

import com.yuan.gulimall.search.vo.SearchParam;
import com.yuan.gulimall.search.vo.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
