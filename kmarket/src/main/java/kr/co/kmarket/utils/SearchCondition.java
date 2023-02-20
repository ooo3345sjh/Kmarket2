package kr.co.kmarket.utils;

import kr.co.kmarket.vo.CsVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String cate1;
    private String cate2;
    private Integer no = 0;
    private String searchField;
    private String searchWord;

    // 추가 필드
    private int type; // 상품 조회를 위한 사용자 타입, 아이디
    private String uid;
    private String csType;

    public String getQueryString(Integer page){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        return getQueryString(page, no);
    }

    public String getQueryString(Integer page, Integer no){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        if (page != null && page != 0)
            builder.queryParam("page", page);

        if (cate1 != null)
            builder.queryParam("cate1", cate1);

        if (cate2 != null)
            builder.queryParam("cate2", cate2); // 이렇게 하면 cate1과 cate2가 없을 시 수행하지 않음 (김진우 수정)

        if (no != null && no != 0)
            builder.queryParam("no", no);

        if (csType != null)
            builder.queryParam("csType", csType);


        // 검색 기능
        if(searchField != null && !searchWord.isBlank()){
            builder.queryParam("searchField", searchField)
                    .queryParam("searchWord", searchWord);
        }

        return builder.toUriString();
    }

    public String getQueryString(){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        return getQueryString(page);
    }

    // limit
    public Integer getOffset() {
        return (page-1) * pageSize;
    }

    public void setPage(Integer page) {
        this.page = page == 0 ? 1:page;
    }

}
