package pl.valueadd.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public abstract class BaseRequest {
    protected String q;
    protected String textSearch;
    protected int pageNumber = 0;
    protected int pageSize = 50;
    @ApiModelProperty(example = "createdAt DESC`")
    protected String sort;

    public BaseRequest(String q) {
        this.q = q;
    }

    public BaseRequest(String q, String textSearch) {
        this.q = q;
        this.textSearch = textSearch;
    }

    public BaseRequest(String q, int pageNumber, int pageSize, String sort) {
        this.q = q;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public BaseRequest(String q, String textSearch, int pageNumber, int pageSize, String sort) {
        this.q = q;
        this.textSearch = textSearch;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public String toLuceneQuery() {
        if(textSearch == null || textSearch.isEmpty())return q;
        String fullTextSearch = Arrays.stream(textSearch.split(" "))
                .map(e-> String.format("search:*%s*", e.toLowerCase()))
                .collect(Collectors.joining(" AND "));

        if(q == null || q.isEmpty()){
            return fullTextSearch;
        }
        return String.format("(%s) AND (%s)", q, fullTextSearch);
    }

    @JsonIgnore
    public Pageable toPage(){

        if(sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(" ");
            if(parts.length >= 2) {
                return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(parts[1].toUpperCase()), parts[0]));
            } else {
                return PageRequest.of(pageNumber, pageSize, Sort.by(parts[0]));
            }
        } else {
            return PageRequest.of(pageNumber, pageSize);
        }
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "q='" + q + '\'' +
                ", textSearch='" + textSearch + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sort='" + sort + '\'' +
                '}';
    }
}
